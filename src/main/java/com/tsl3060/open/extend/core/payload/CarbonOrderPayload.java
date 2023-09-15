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

    private double mileage;
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
    private double orderPay = 0;
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

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
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

    public double getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(double orderPay) {
        this.orderPay = orderPay;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}
