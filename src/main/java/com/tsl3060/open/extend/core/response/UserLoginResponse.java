package com.tsl3060.open.extend.core.response;

import com.alibaba.fastjson2.annotation.JSONField;

public class UserLoginResponse {
    @JSONField(name = "expire_at")
    private String expireAt;
    private String openid;
    private String token;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(String expireAt) {
        this.expireAt = expireAt;
    }
}
