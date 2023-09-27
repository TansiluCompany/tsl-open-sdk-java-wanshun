package com.tsl3060.open.extend.core;

import com.tsl3060.open.extend.core.secure.RSASecure;

public class Config {
    private String privateKey = "";
    private String publicKey = "";
    /**
     * API 平台公钥
     */
    private String apiPublicKey = "";

    private String appid = "";

    private String signType = RSASecure.NAME;

    private String charset = "utf-8";

    private String host = "https://open.tsl3060.com";
    private String dataFormat = "yyyy-MM-dd HH:mm:ss";

    private int timeout = 25000;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
        if (this.privateKey != null) {
            this.privateKey = this.privateKey
                    .replace("\r", "")
                    .replace("\n", "")
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "");
        }
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
        if (this.publicKey != null) {
            this.publicKey = this.publicKey
                    .replace("\r", "")
                    .replace("\n", "")
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "");
        }
    }

    public String getApiPublicKey() {
        return apiPublicKey;
    }

    public void setApiPublicKey(String apiPublicKey) {
        this.apiPublicKey = apiPublicKey;
        if (this.apiPublicKey != null) {
            this.apiPublicKey = this.apiPublicKey
                    .replace("\r", "")
                    .replace("\n", "")
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "");

        }
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }


    public void useSandBox() {
        this.setHost("https://opendev.tsl3060.com");
    }
}
