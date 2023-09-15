package com.tsl3060.open.extend.core.payload;

import com.tsl3060.open.extend.core.IApiRequest;

public class WalletQueryPayload extends RequestPayload implements IApiRequest {

    private String openid;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String path() {
        return "/v1/wanshun/wallet/query";
    }
}
