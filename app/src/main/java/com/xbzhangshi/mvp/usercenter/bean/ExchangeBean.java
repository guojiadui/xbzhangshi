package com.xbzhangshi.mvp.usercenter.bean;

public class ExchangeBean {

    /**
     * success : true
     * accessToken : b19e12cb-d77c-4f5d-853a-d05fd5062399
     */

    private boolean success;
    private String accessToken;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
