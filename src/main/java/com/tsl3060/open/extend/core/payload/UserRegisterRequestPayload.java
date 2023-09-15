package com.tsl3060.open.extend.core.payload;

import com.tsl3060.open.extend.core.IApiRequest;
import com.tsl3060.open.extend.core.payload.RequestPayload;

public class UserRegisterRequestPayload extends RequestPayload implements IApiRequest {
    @Override
    public String path() {
        return "/v1/wanshun/account/register";
    }

    private String phone;
    private String uid;
    private String type;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
