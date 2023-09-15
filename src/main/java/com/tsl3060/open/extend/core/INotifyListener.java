package com.tsl3060.open.extend.core;

import com.tsl3060.open.extend.core.notify.CarbonOrderNotify;
import com.tsl3060.open.extend.core.notify.CarbonOrderNotifyAnswer;

public interface INotifyListener {
    /**
     * 低碳积分通知
     *
     * @param notify
     * @return
     */
    CarbonOrderNotifyAnswer carbon(CarbonOrderNotify notify);
}
