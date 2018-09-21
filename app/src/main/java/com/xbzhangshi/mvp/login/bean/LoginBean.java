package com.xbzhangshi.mvp.login.bean;

public class LoginBean {

    /**
     * success : true
     * accessToken : 93631014-b0b1-4cec-84f1-cde4f28438e8
     * content : {"cpVersion":"2","accountType":4,"account":"zhang"}
     */

    private boolean success;
    private String accessToken;
    private ContentBean content;
    private  String msg;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
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
         * accountType : 4
         * account : zhang
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
