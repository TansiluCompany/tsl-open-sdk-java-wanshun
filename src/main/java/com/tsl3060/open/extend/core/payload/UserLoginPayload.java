package com.tsl3060.open.extend.core.payload;

import com.alibaba.fastjson2.annotation.JSONField;
import com.tsl3060.open.extend.core.IApiRequest;

/**
 * 用户登录载体
 */
public class UserLoginPayload extends RequestPayload implements IApiRequest {
    private String device;

    private String openid;


    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String path() {
        return "/v1/wanshun/account/login";
    }
}
