package com.tsl3060.open.extend.core.router.notify;

import com.alibaba.fastjson2.JSON;
import com.tsl3060.open.extend.core.INotifyListener;
import com.tsl3060.open.extend.core.NotifyRequest;
import com.tsl3060.open.extend.core.notify.CarbonOrderNotify;
import com.tsl3060.open.extend.core.notify.IAnswer;
import com.tsl3060.open.extend.core.router.INotifyRouter;

public class CarbonOrderNotifyRouter implements INotifyRouter {


    private final INotifyListener notifyListener;

    public CarbonOrderNotifyRouter(INotifyListener notifyListener) {
        this.notifyListener = notifyListener;
    }

    @Override
    public String path() {
        return "/v1/wanshun/notify/carbon";
    }

    @Override
    public IAnswer makeBody(NotifyRequest notifyRequest) throws Exception {
        CarbonOrderNotify carbonOrderNotify = JSON.to(CarbonOrderNotify.class, notifyRequest.getPayload());
        return this.notifyListener.carbon(carbonOrderNotify);
    }
}
