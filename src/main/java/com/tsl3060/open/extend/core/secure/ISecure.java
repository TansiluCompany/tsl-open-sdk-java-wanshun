package com.tsl3060.open.extend.core.secure;

import com.tsl3060.open.extend.core.*;

public interface ISecure {
    /**
     * 参数设置
     *
     * @param config
     */
    void setConfig(Config config);

    /**
     * 安全模块名称
     *
     * @return 名称
     */
    String name();

    /**
     * 对请求进行签名
     *
     * @param apiRequest 请求
     * @return 签名字符串
     */
    String requestSign(ApiRequest apiRequest);

    /**
     * 同步返回验签
     *
     * @param apiResponse 同步返回
     * @return 是否正确
     */
    boolean verifyResponse(ApiResponse apiResponse);

    /**
     * 异步通知验签
     *
     * @param notifyRequest 异步通知
     * @return 是否正确
     */
    boolean verifyNotify(NotifyRequest notifyRequest);


    String answerSign(NotifyAnswerResponse response);

}
