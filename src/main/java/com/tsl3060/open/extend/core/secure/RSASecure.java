package com.tsl3060.open.extend.core.secure;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSONObject;
import com.tsl3060.open.extend.core.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import java.util.stream.Collectors;

public class RSASecure implements ISecure {

    private final Log log = LogFactory.get();
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final String DIGEST_ALGORITHM = "SHA-256";
    public static final String NAME = "RSA";
    private Config config;

    @Override
    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public String name() {
        return NAME;
    }

    private String object2LinkStr(Object ob) {
        List<String> payloadStr = new ArrayList<>();
        if (ob != null) {
            JSONObject j = JSONObject.from(ob);
            //排序
            Set<String> _keys = j.keySet();
            List<String> keys = _keys.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());

            for (String key : keys) {
                if (j.get(key) != null) {
                    Object v = j.get(key);
                    String vx;
                    if (v instanceof Double) {
                        vx = String.format("%.2f", v);
                    } else if (v instanceof Float) {
                        vx = String.format("%.2f", v);
                    } else if (v instanceof Boolean) {
                        vx = String.format("%s", v);
                    } else if (v instanceof BigDecimal) {
                        vx = String.format("%.2f", v);
                    } else {
                        vx = String.valueOf(v);
                    }
                    payloadStr.add(String.format("%s=%s", key, vx));
                }
            }
        }
        return CollectionUtil.join(payloadStr, "&");
    }

    public String sign(String content, String charset) {

        log.debug("req: {}", content);
        try {
            byte[] bytesContent = content.getBytes(charset);
            MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM);
            byte[] digestData = messageDigest.digest(bytesContent);
            //签名工具
            byte[] pKeyData = Base64.getDecoder().decode(this.config.getPrivateKey());
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(pKeyData);
            KeyFactory keyFactory = KeyFactory.getInstance(NAME);
            PrivateKey pkey = keyFactory.generatePrivate(spec);

            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(pkey);
            signature.update(digestData);
            byte[] s = signature.sign();
            return HexUtil.encodeHexStr(s);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeySpecException |
                 SignatureException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对请求生成签名
     *
     * @param request 请求
     * @return
     */
    @Override
    public String requestSign(ApiRequest request) {
        Object ob = request.getPayload();
        String waitStr = this.object2LinkStr(ob);
        String content = String.format("%s&%s&%s&%s&%s&%s&%s",
                request.getPath(),
                request.getAppId(),
                request.getAccessToken(),
                request.getSignType(),
                request.getCharset(),
                request.getTime(),
                waitStr
        );
        log.debug("req: {}", content);

        return this.sign(content, request.getCharset());

    }


    private boolean responseServerVerify(byte[] content, String sign) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM);
            byte[] digestData = messageDigest.digest(content);
            byte[] pubKeyData = Base64.getDecoder().decode(this.config.getApiPublicKey());
            X509EncodedKeySpec spec = new X509EncodedKeySpec(pubKeyData);
            KeyFactory keyFactory = KeyFactory.getInstance(NAME);
            PublicKey pubKey = keyFactory.generatePublic(spec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(digestData);

            byte[] bodySign = HexUtil.decodeHex(sign);
            return signature.verify(bodySign);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | SignatureException | InvalidKeyException e) {
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean verifyResponse(ApiResponse response) {
        String waitStr = this.object2LinkStr(response.getPayload());
        String formatStr = String.format(
                "%s&%s&%s&%s&%s&%s&%s&%s&%s&%s&%s",
                response.getResponseId(),
                response.getErrCode(),
                response.getErrMsg(),
                response.getSubErr(),
                response.getSubMsg(),
                response.getTime(),
                response.getOpenId(),
                response.getSignType(),
                response.getCharset(),
                response.getDescription(),
                waitStr
        );
        log.debug("反馈内容 {}", formatStr);
        log.debug("签名内容 {}", response.getSign());
        Charset bodyCharset = Charset.forName(response.getCharset());
        byte[] bytesContent = formatStr.getBytes(bodyCharset);
        return this.responseServerVerify(bytesContent, response.getSign());

    }

    /**
     * 验证通知
     *
     * @param response 异步通知
     * @return
     */
    @Override
    public boolean verifyNotify(NotifyRequest response) {
        String waitStr = this.object2LinkStr(response.getPayload());
        String formatStr = String.format(
                "%s&%s&%s&%s&%s&%s&%s&%s&%s&%s&%s&%s&%s",
                response.getNotifyId(),
                response.getSourceId(),
                response.getAppId(),
                response.getErrCode(),
                response.getErrMsg(),
                response.getSubErr(),
                response.getSubMsg(),
                response.getTime(),
                response.getOpenId(),
                response.getSignType(),
                response.getCharset(),
                response.getDescription(),
                waitStr
        );
        log.debug("通知内容 {}", formatStr);
        log.debug("通知签名内容 {}", response.getSign());
        Charset bodyCharset = Charset.forName(response.getCharset());
        byte[] bytesContent = formatStr.getBytes(bodyCharset);

        return this.responseServerVerify(bytesContent, response.getSign());

    }

    /**
     * 通知反馈签名
     *
     * @param response
     * @return
     */
    @Override
    public String answerSign(NotifyAnswerResponse response) {
        Object ob = response.getPayload();
        String waitStr = this.object2LinkStr(ob);
        String content = String.format("%s&%s&%s&%s&%s&%s&%s",
                response.getAnswerId(),
                response.getAppId(),
                response.getResult(),
                response.getSignType(),
                response.getCharset(),
                response.getTime(),
                waitStr
        );
        return this.sign(content, response.getCharset());
    }
}
