package com.xbzhangshi.mvp.usercenter.bean;

public class DrawMoneyInfoBean {


    /**
     * success : true
     * accessToken : 70f8d37b-83e9-4c0a-99f2-8cf76905023c
     * content : {"accountBalance":0,"accountStatus":0,"bankAddress":null,"bankName":"工商银行","cardNo":"***4646","checkBetNum":0,"curWnum":0,"drawFlag":"是","enablePick":true,"endTime":"23:59","maxPickMoney":"200000","minPickMoney":"100","startTime":"00:00","userName":"张","validBetMoney":0,"wnum":4}
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
         * accountBalance : 0
         * accountStatus : 0
         * bankAddress : null
         * bankName : 工商银行
         * cardNo : ***4646
         * checkBetNum : 0
         * curWnum : 0
         * drawFlag : 是
         * enablePick : true
         * endTime : 23:59
         * maxPickMoney : 200000
         * minPickMoney : 100
         * startTime : 00:00
         * userName : 张
         * validBetMoney : 0
         * wnum : 4
         */

        private int accountBalance;
        private int accountStatus;
        private Object bankAddress;
        private String bankName;
        private String cardNo;
        private int checkBetNum;
        private int curWnum;
        private String drawFlag;
        private boolean enablePick;
        private String endTime;
        private String maxPickMoney;
        private String minPickMoney;
        private String startTime;
        private String userName;
        private int validBetMoney;
        private int wnum;

        public int getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(int accountBalance) {
            this.accountBalance = accountBalance;
        }

        public int getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(int accountStatus) {
            this.accountStatus = accountStatus;
        }

        public Object getBankAddress() {
            return bankAddress;
        }

        public void setBankAddress(Object bankAddress) {
            this.bankAddress = bankAddress;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public int getCheckBetNum() {
            return checkBetNum;
        }

        public void setCheckBetNum(int checkBetNum) {
            this.checkBetNum = checkBetNum;
        }

        public int getCurWnum() {
            return curWnum;
        }

        public void setCurWnum(int curWnum) {
            this.curWnum = curWnum;
        }

        public String getDrawFlag() {
            return drawFlag;
        }

        public void setDrawFlag(String drawFlag) {
            this.drawFlag = drawFlag;
        }

        public boolean isEnablePick() {
            return enablePick;
        }

        public void setEnablePick(boolean enablePick) {
            this.enablePick = enablePick;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getMaxPickMoney() {
            return maxPickMoney;
        }

        public void setMaxPickMoney(String maxPickMoney) {
            this.maxPickMoney = maxPickMoney;
        }

        public String getMinPickMoney() {
            return minPickMoney;
        }

        public void setMinPickMoney(String minPickMoney) {
            this.minPickMoney = minPickMoney;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getValidBetMoney() {
            return validBetMoney;
        }

        public void setValidBetMoney(int validBetMoney) {
            this.validBetMoney = validBetMoney;
        }

        public int getWnum() {
            return wnum;
        }

        public void setWnum(int wnum) {
            this.wnum = wnum;
        }
    }
}
