package com.xbzhangshi.mvp.usercenter.bean;

public class DrawMoneyInfoBean {

    /**
     * commit : {"drawFlag":"是","curWnum":0,"min":"100","star":"00:00","max":"200000","checkBetNum":0,"wnum":4,"member":{"city":"","drawNeed":0,"bankName":"浦发银行","cardNo":"122222222","accountStatus":2,"score":0,"province":"","registerUrl":"xbzhanshi.com","id":2525,"levelGroup":2,"qq":"4444444","registerIp":"175.100.8.197","accountType":1,"wechat":"","moneyTypeId":1,"userName":"好","registerOs":"ANDROID_MOBILE","bankAddress":"","cardNoStatus":1,"createDatetime":1537680757071,"parentNames":",t006daili,","lastLoginDatetime":1538135184000,"money":0,"betNum":0,"online":2,"account":"zhang7"},"end":"23:59"}
     */

    private CommitBean commit;

    public CommitBean getCommit() {
        return commit;
    }

    public void setCommit(CommitBean commit) {
        this.commit = commit;
    }

    public static class CommitBean {
        /**
         * drawFlag : 是
         * curWnum : 0
         * min : 100
         * star : 00:00
         * max : 200000
         * checkBetNum : 0.0
         * wnum : 4
         * member : {"city":"","drawNeed":0,"bankName":"浦发银行","cardNo":"122222222","accountStatus":2,"score":0,"province":"","registerUrl":"xbzhanshi.com","id":2525,"levelGroup":2,"qq":"4444444","registerIp":"175.100.8.197","accountType":1,"wechat":"","moneyTypeId":1,"userName":"好","registerOs":"ANDROID_MOBILE","bankAddress":"","cardNoStatus":1,"createDatetime":1537680757071,"parentNames":",t006daili,","lastLoginDatetime":1538135184000,"money":0,"betNum":0,"online":2,"account":"zhang7"}
         * end : 23:59
         */

        private String drawFlag;
        private int curWnum;
        private String min;
        private String star;
        private String max;
        private double checkBetNum;
        private int wnum;
        private MemberBean member;
        private String end;

        public String getDrawFlag() {
            return drawFlag;
        }

        public void setDrawFlag(String drawFlag) {
            this.drawFlag = drawFlag;
        }

        public int getCurWnum() {
            return curWnum;
        }

        public void setCurWnum(int curWnum) {
            this.curWnum = curWnum;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public double getCheckBetNum() {
            return checkBetNum;
        }

        public void setCheckBetNum(double checkBetNum) {
            this.checkBetNum = checkBetNum;
        }

        public int getWnum() {
            return wnum;
        }

        public void setWnum(int wnum) {
            this.wnum = wnum;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public static class MemberBean {
            /**
             * city :
             * drawNeed : 0.0
             * bankName : 浦发银行
             * cardNo : 122222222
             * accountStatus : 2
             * score : 0.0
             * province :
             * registerUrl : xbzhanshi.com
             * id : 2525
             * levelGroup : 2
             * qq : 4444444
             * registerIp : 175.100.8.197
             * accountType : 1
             * wechat :
             * moneyTypeId : 1
             * userName : 好
             * registerOs : ANDROID_MOBILE
             * bankAddress :
             * cardNoStatus : 1
             * createDatetime : 1537680757071
             * parentNames : ,t006daili,
             * lastLoginDatetime : 1538135184000
             * money : 0.0
             * betNum : 0.0
             * online : 2
             * account : zhang7
             */

            private String city;
            private double drawNeed;
            private String bankName;
            private String cardNo;
            private int accountStatus;
            private double score;
            private String province;
            private String registerUrl;
            private int id;
            private int levelGroup;
            private String qq;
            private String registerIp;
            private int accountType;
            private String wechat;
            private int moneyTypeId;
            private String userName;
            private String registerOs;
            private String bankAddress;
            private int cardNoStatus;
            private long createDatetime;
            private String parentNames;
            private long lastLoginDatetime;
            private double money;
            private double betNum;
            private int online;
            private String account;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public double getDrawNeed() {
                return drawNeed;
            }

            public void setDrawNeed(double drawNeed) {
                this.drawNeed = drawNeed;
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

            public int getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(int accountStatus) {
                this.accountStatus = accountStatus;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getRegisterUrl() {
                return registerUrl;
            }

            public void setRegisterUrl(String registerUrl) {
                this.registerUrl = registerUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLevelGroup() {
                return levelGroup;
            }

            public void setLevelGroup(int levelGroup) {
                this.levelGroup = levelGroup;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getRegisterIp() {
                return registerIp;
            }

            public void setRegisterIp(String registerIp) {
                this.registerIp = registerIp;
            }

            public int getAccountType() {
                return accountType;
            }

            public void setAccountType(int accountType) {
                this.accountType = accountType;
            }

            public String getWechat() {
                return wechat;
            }

            public void setWechat(String wechat) {
                this.wechat = wechat;
            }

            public int getMoneyTypeId() {
                return moneyTypeId;
            }

            public void setMoneyTypeId(int moneyTypeId) {
                this.moneyTypeId = moneyTypeId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getRegisterOs() {
                return registerOs;
            }

            public void setRegisterOs(String registerOs) {
                this.registerOs = registerOs;
            }

            public String getBankAddress() {
                return bankAddress;
            }

            public void setBankAddress(String bankAddress) {
                this.bankAddress = bankAddress;
            }

            public int getCardNoStatus() {
                return cardNoStatus;
            }

            public void setCardNoStatus(int cardNoStatus) {
                this.cardNoStatus = cardNoStatus;
            }

            public long getCreateDatetime() {
                return createDatetime;
            }

            public void setCreateDatetime(long createDatetime) {
                this.createDatetime = createDatetime;
            }

            public String getParentNames() {
                return parentNames;
            }

            public void setParentNames(String parentNames) {
                this.parentNames = parentNames;
            }

            public long getLastLoginDatetime() {
                return lastLoginDatetime;
            }

            public void setLastLoginDatetime(long lastLoginDatetime) {
                this.lastLoginDatetime = lastLoginDatetime;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public double getBetNum() {
                return betNum;
            }

            public void setBetNum(double betNum) {
                this.betNum = betNum;
            }

            public int getOnline() {
                return online;
            }

            public void setOnline(int online) {
                this.online = online;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }
        }
    }
}
