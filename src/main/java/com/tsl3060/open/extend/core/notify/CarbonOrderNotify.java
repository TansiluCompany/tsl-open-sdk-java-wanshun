package com.tsl3060.open.extend.core.notify;

import com.alibaba.fastjson2.annotation.JSONField;

public class CarbonOrderNotify {
    /**
     * 用户OpenID
     */
    private String openid;
    /**
     * 订单号
     */
    @JSONField(name = "order_no")
    private String orderNo;
    /**
     * 低碳订单号
     */
    @JSONField(name = "carbon_no")
    private String carbonNo;

    /**
     * 用户低碳积分
     */
    @JSONField(name = "carbon")
    private double carbon;

    /**
     * 本次订单新增积分
     */
    @JSONField(name = "amount")
    private double amount;
    /**
     * 订单时间
     */
    @JSONField(name = "order_time")
    private String orderTime;
    /**
     * 低碳积分订单完成时间
     */
    @JSONField(name = "complete_time")
    private String completeTime;
    /**
     * 订单类型
     */
    private String type;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCarbonNo() {
        return carbonNo;
    }

    public void setCarbonNo(String carbonNo) {
        this.carbonNo = carbonNo;
    }

    public double getCarbon() {
        return carbon;
    }

    public void setCarbon(double carbon) {
        this.carbon = carbon;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
