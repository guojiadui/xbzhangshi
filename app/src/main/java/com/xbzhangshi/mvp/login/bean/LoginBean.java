package com.xbzhangshi.mvp.login.bean;

public class LoginBean {

    /**
     * 1 会员平台账号
     2 租户超级管理员
     3 租户管理员
     4 代理平台账号
     5 总代理平台账号
     6 平台试玩账号
     */
    /**
     * success : true
     * accessToken : 93631014-b0b1-4cec-84f1-cde4f28438e8
     * content : {"cpVersion":"2","accountType":4,"account":"zhang"}
     */
    /**
     * //试玩账号
     * {"success":true,"accessToken":"cf7cf796-68c3-4174-ac77-6b79d2b8df2b",
     * "content":{"cpVersion":"2","accountType":6,"account":"guest1433"}}
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
