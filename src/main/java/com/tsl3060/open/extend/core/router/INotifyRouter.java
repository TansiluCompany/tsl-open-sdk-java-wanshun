package com.tsl3060.open.extend.core.router;

import com.tsl3060.open.extend.core.NotifyRequest;
import com.tsl3060.open.extend.core.notify.IAnswer;

public interface INotifyRouter {
    String path();

    IAnswer makeBody(NotifyRequest notifyRequest) throws Exception;
}
