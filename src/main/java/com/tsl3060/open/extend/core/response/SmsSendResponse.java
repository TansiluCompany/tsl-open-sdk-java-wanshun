package com.tsl3060.open.extend.core.response;

import com.alibaba.fastjson2.annotation.JSONField;

public class SmsSendResponse {
    private String phone;
    private String send;
    private int time;
    @JSONField(name = "send_time")
    private String sendTime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}

