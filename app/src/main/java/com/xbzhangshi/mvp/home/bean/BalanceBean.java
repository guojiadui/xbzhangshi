package com.xbzhangshi.mvp.home.bean;

public class BalanceBean {

    /**
     * success : true
     * accessToken : 0c309e60-c506-406e-a71f-44441fb3b783
     * content : {"balance":10000,"login":true,"account":"zhang"}
     */

    private boolean success;
    private String accessToken;
    private ContentBean content;

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
         * balance : 10000.0
         * login : true
         * account : zhang
         */

        private double balance;
        private boolean login;
        private String account;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public boolean isLogin() {
            return login;
        }

        public void setLogin(boolean login) {
            this.login = login;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }
}
