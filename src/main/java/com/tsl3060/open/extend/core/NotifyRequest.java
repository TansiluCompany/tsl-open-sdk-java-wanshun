package com.tsl3060.open.extend.core;

import com.alibaba.fastjson2.annotation.JSONField;

public class NotifyRequest {
    @JSONField(name = "notify_id")
    private String notifyId;
    @JSONField(name = "app_id")
    private String appId;
    private String module = "";
    @JSONField(name = "source_id")
    private String sourceId = "";
    @JSONField(name = "err_code")
    private int errCode;
    @JSONField(name = "err_msg")
    private String errMsg;
    @JSONField(name = "sub_err")
    private int subErr;
    @JSONField(name = "sub_msg")
    private String subMsg;
    @JSONField(name = "sign_type")
    private String signType;
    private Object payload;
    @JSONField(name = "open_id")
    private String openId;
    private String description;
    private String charset = "UTF-8";
    private String time;
    private String sign;

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getSubErr() {
        return subErr;
    }

    public void setSubErr(int subErr) {
        this.subErr = subErr;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
