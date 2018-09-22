package com.xbzhangshi.mvp.home.bean;

public class LogOutBean {

    /**
     * success : true
     * accessToken :
     * content : true
     */

    private boolean success;
    private String accessToken;
    private boolean content;

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

    public boolean isContent() {
        return content;
    }

    public void setContent(boolean content) {
        this.content = content;
    }
}
