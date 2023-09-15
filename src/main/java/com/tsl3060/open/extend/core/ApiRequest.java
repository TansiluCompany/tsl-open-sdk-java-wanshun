package com.tsl3060.open.extend.core;

import com.alibaba.fastjson2.annotation.JSONField;

public class ApiRequest {
    @JSONField(name = "app_id")
    private String appId;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 访问TOKEN
     */
    @JSONField(name = "access_token")
    private String accessToken;

    /**
     * 签名数据
     */
    private String sign;

    /**
     * 签名类型
     */
    @JSONField(name = "sign_type")
    private String signType;
    /**
     * 数据编码
     */
    private String charset;
    /**
     * 时间
     */
    private String time;
    /**
     * 载体
     */
    private Object payload;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
