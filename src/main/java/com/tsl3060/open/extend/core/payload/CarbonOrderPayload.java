package com.tsl3060.open.extend.core.payload;

import com.alibaba.fastjson2.annotation.JSONField;
import com.tsl3060.open.extend.core.IApiRequest;

public class CarbonOrderPayload extends RequestPayload implements IApiRequest {
    @Override
    public String path() {
        return "/v1/wanshun/wallet/carbon";
    }

    private String openid;
    @JSONField(name = "order_no")
    private String orderNo;

    private String mileage;
    @JSONField(name = "order_time")
    private String orderTime;
    @JSONField(name = "order_state")
    private String orderState;
    @JSONField(name = "vehicle_model")
    private String vehicleModel = "";
    @JSONField(name = "new_energy")
    private boolean newEnergy = false;
    @JSONField(name = "complete_time")
    private String completeTime = "";

    @JSONField(name = "order_pay")
    private String orderPay;
    private String behavior = "";


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


    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public boolean isNewEnergy() {
        return newEnergy;
    }

    public void setNewEnergy(boolean newEnergy) {
        this.newEnergy = newEnergy;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }


    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public void setMileage(double mileage) {
        this.setMileage(String.valueOf(mileage));
    }

    public String getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(String orderPay) {
        this.orderPay = orderPay;
    }

    /**
     * @param orderPay
     */
    public void setOrderPay(double orderPay) {
        this.setOrderPay(String.valueOf(orderPay));
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}
