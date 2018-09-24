package com.xbzhangshi.mvp.home.bean;

public class MsgCountBean {

    /**
     * success : true
     * accessToken : e6c41a27-82f4-4d47-af3a-01f2326525b1
     * content : 1
     */

    private boolean success;
    private String accessToken;
    private int content;

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

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }
}
