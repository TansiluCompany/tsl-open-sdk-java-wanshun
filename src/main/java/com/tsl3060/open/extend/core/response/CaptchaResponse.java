package com.tsl3060.open.extend.core.response;

/**
 * 验证码载体
 */
public class CaptchaResponse {
    /**
     * 验证码内容
     */
    private String image;
    /**
     * 验证码ID
     */
    private String id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
