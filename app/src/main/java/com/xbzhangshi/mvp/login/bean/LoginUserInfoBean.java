package com.xbzhangshi.mvp.login.bean;

public class LoginUserInfoBean {

    /**
     * success : true
     * accessToken : 5dd7793b-2d0e-4850-ac5b-dfb2185e503b
     * content : {"account":"zhang","bankAddress":"","bankName":"","betNum":0,"cardNo":"","city":"",
     * "email":"","phone":"","province":"","qq":"","sex":0,"userName":"","wechat":""}
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

    private  String msg;

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
         * account : zhang
         * bankAddress :
         * bankName :
         * betNum : 0
         * cardNo :
         * city :
         * email :
         * phone :
         * province :
         * qq :
         * sex : 0
         * userName :
         * wechat :
         */

        private String account;
        private String bankAddress;
        private String bankName;
        private int betNum;
        private String cardNo;
        private String city;
        private String email;
        private String phone;
        private String province;
        private String qq;
        private int sex;
        private String userName;
        private String wechat;

        public String getReceiptPwd() {
            return receiptPwd;
        }

        public void setReceiptPwd(String receiptPwd) {
            this.receiptPwd = receiptPwd;
        }

        private  String receiptPwd;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getBankAddress() {
            return bankAddress;
        }

        public void setBankAddress(String bankAddress) {
            this.bankAddress = bankAddress;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getBetNum() {
            return betNum;
        }

        public void setBetNum(int betNum) {
            this.betNum = betNum;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }
    }
}
