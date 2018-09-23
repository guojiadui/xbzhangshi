package com.xbzhangshi.mvp.login.bean;

public class RegisterBean {

    /**
     * success : true
     * accessToken : 756f96be-1eb7-4ecf-a9d8-a401ff9f2283
     * content : {"cpVersion":"2","accountType":1,"account":"zhang1"}
     */

    private boolean success;
    private String accessToken;
    private ContentBean content;

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

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * cpVersion : 2
         * accountType : 1
         * account : zhang1
         */

        private String cpVersion;
        private int accountType;
        private String account;

        public String getCpVersion() {
            return cpVersion;
        }

        public void setCpVersion(String cpVersion) {
            this.cpVersion = cpVersion;
        }

        public int getAccountType() {
            return accountType;
        }

        public void setAccountType(int accountType) {
            this.accountType = accountType;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }
}
