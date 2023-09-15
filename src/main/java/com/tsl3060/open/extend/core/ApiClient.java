package com.tsl3060.open.extend.core;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.tsl3060.open.extend.core.exception.ApiException;
import com.tsl3060.open.extend.core.exception.BadResourceException;
import com.tsl3060.open.extend.core.notify.IAnswer;
import com.tsl3060.open.extend.core.router.INotifyRouter;
import com.tsl3060.open.extend.core.router.NotifyMapRouter;
import com.tsl3060.open.extend.core.secure.ISecure;
import com.tsl3060.open.extend.core.secure.SecureTool;
import okhttp3.*;
import org.w3c.dom.Document;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ApiClient {

    private static final int ERROR_OK = 1000;

    private static final int SUB_OK = 2000;

    /**
     * 请求内容格式
     */
    private final MediaType contentType = MediaType.parse("application/json;charset=utf-8");

    /**
     * 返回内容格式
     */
    private final MediaType acceptType = MediaType.parse("application/json");


    private SecureTool secureTool;

    private Config config;


    private final Log log = LogFactory.get();

    private SecureTool getSecureTool() {
        if (secureTool == null) {
            secureTool = new SecureTool(getConfig());
        }
        return secureTool;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public <T> T request(IApiRequest request, Class<T> tClass) throws Exception {
        ApiResponse apiResponse = this.request(request);
        if (apiResponse == null) {
            return null;
        }
        if (apiResponse.getErrCode() != ERROR_OK) {
            //系统级错误
            throw new ApiException(apiResponse.getDescription() + "(" + apiResponse.getErrCode() + ")");
        }
        if (apiResponse.getSubErr() != SUB_OK) {
            //业务级错误
            throw new ApiException(apiResponse.getDescription() + "(" + apiResponse.getSubErr() + ")");
        }
        Object o = apiResponse.getPayload();
        if (o != null) {
            return JSON.to(tClass, o);
        }
        return null;
    }

    /**
     * 接口请求
     *
     * @param request
     * @return
     */
    public ApiResponse request(IApiRequest request) throws Exception {
        ApiRequest apiRequest = pack(request.path(), request);
        ApiResponse apiResponse = this.request(apiRequest);
        if (apiResponse == null) {
            throw new Exception("返回结果为空");
        }
        return apiResponse;
    }

    /**
     * 打包请求
     *
     * @param path
     * @param data
     * @return
     */
    private ApiRequest pack(String path, Object data) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setPath(path);
        apiRequest.setCharset(this.config.getCharset());
        DateFormat dateFormat = new SimpleDateFormat(this.config.getDataFormat());
        apiRequest.setTime(dateFormat.format(new Date()));
        apiRequest.setSignType(this.config.getSignType());
        apiRequest.setPayload(data);
        apiRequest.setAccessToken("");
        apiRequest.setAppId(this.config.getAppid());
        return apiRequest;
    }

    /**
     * 执行请求
     *
     * @param apiRequest
     * @return
     */
    public ApiResponse request(ApiRequest apiRequest) {
        ISecure iSecure = getSecureTool().getSecure(apiRequest.getSignType());
        String signStr = iSecure.requestSign(apiRequest);
        apiRequest.setSign(signStr);
        String fBody = JSON.toJSONString(apiRequest);
        log.debug(">>> {}", fBody);
        RequestBody requestBody = RequestBody.create(fBody, contentType);
        /**
         * 通讯主机地址
         */

        String host = this.config.getHost();
        Request request = new Request.Builder()
                .url(String.format("%s%s", host, apiRequest.getPath()))
                .addHeader("ACCEPT", acceptType.toString())
                .post(requestBody)
                .build();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.build();
        try {
            Call call = okHttpClient.newCall(request);
            try (Response response = call.execute()) {
                ResponseBody responseBody = response.body();
                ApiResponse apiResponse = null;
                if (responseBody != null) {
                    String bStr = responseBody.string();
                    log.debug("<<< {}", bStr);
                    apiResponse = JSON.parseObject(bStr, ApiResponse.class);
                }
                if (apiResponse == null) {
                    return null;
                }
                //验证签名
                if (this.verifyResponse(apiResponse)) {
                    return apiResponse;
                }
                return null;

            }
        } catch (IOException e) {
            log.error(e);
        }
        return null;
    }

    /**
     * 验证反馈数据
     *
     * @param response
     * @return
     */
    public boolean verifyResponse(ApiResponse response) {
        ISecure iSecure = this.secureTool.getSecure(response.getSignType());
        if (iSecure == null) {
            log.debug("没有找到签名类型 %s", response.getSignType());
            return false;
        }
        return iSecure.verifyResponse(response);
    }


    private INotifyListener notifyListener;

    public INotifyListener getNotifyListener() {
        return notifyListener;
    }

    public void setNotifyListener(INotifyListener notifyListener) {
        this.notifyListener = notifyListener;
    }


    private NotifyMapRouter notifyMapRouter;

    /**
     * 验证回调数据
     *
     * @return
     */
    public String notifyRun(String raw, String contentType, String accept) throws BadResourceException {
        if (notifyMapRouter == null) {
            notifyMapRouter = new NotifyMapRouter(this.notifyListener);
        }
        if (StrUtil.isEmpty(raw)) {
            throw new BadResourceException("通知内容为空");
        }

        NotifyRequest notifyRequest;
        if (contentType.contains("application/json")) {
            notifyRequest = JSON.parseObject(raw, NotifyRequest.class);
        } else if (contentType.contains("application/xml")) {
            //XML格式
            Document document = XmlUtil.parseXml(raw);
            //TODO 对XML解析
            throw new BadResourceException("暂不支持的格式");
        } else {
            throw new BadResourceException("不支持的数据格式");
        }
        //验证通知
        ISecure iSecure = getSecureTool().getSecure(notifyRequest.getSignType());
        if (!iSecure.verifyNotify(notifyRequest)) {
            throw new BadResourceException("验签未通过");
        }
        INotifyRouter notifyRouter = this.notifyMapRouter.getRouter(notifyRequest.getModule());
        if (notifyRouter == null) {
            throw new BadResourceException("未知的通知");
        }

        //组装
        NotifyAnswerResponse notifyAnswerResponse = new NotifyAnswerResponse();
        notifyAnswerResponse.setAnswerId(notifyRequest.getNotifyId());
        notifyAnswerResponse.setAppId(this.config.getAppid());
        notifyAnswerResponse.setTime(DateTime.now().toString(this.config.getDataFormat()));
        notifyAnswerResponse.setSignType(this.config.getSignType());
        notifyAnswerResponse.setCharset(this.config.getCharset());
        //通知
        try {
            IAnswer o = notifyRouter.makeBody(notifyRequest);
            notifyAnswerResponse.setPayload(o);
            notifyAnswerResponse.setResult("ok");
        } catch (Exception e) {
            e.printStackTrace();
            notifyAnswerResponse.setResult("fail");
        }
        //生成签名
        ISecure makeSecure = getSecureTool().getSecure(notifyAnswerResponse.getSignType());
        String signStr = makeSecure.answerSign(notifyAnswerResponse);
        notifyAnswerResponse.setSign(signStr);
        //解析完成
        if (accept.contains("application/json")) {

            return JSON.toJSONString(notifyAnswerResponse);
        } else if (accept.contains("application/xml")) {
            //返回XML格式
            return "";
        } else {
            //未知的格式
            return "";
        }
    }
}
