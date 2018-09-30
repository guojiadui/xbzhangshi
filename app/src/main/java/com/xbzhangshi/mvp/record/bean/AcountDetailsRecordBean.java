package com.xbzhangshi.mvp.record.bean;

import java.util.List;

public class AcountDetailsRecordBean {


    /**
     * aggsData : {"drawMoney":201}
     * currentPageNo : 1
     * hasNext : false
     * hasPre : false
     * list : [{"createUserId":2240,"modifyDatetime":1538229288798,"fee":0,"modifyUserId":366,"remark":"涉嫌违规下注！如有在出现违规下注公司将冻结账户处理，请您须知","bankName":"中国银行","type":4,"cardNo":"134566646848","modifyUser":"t006admin","id":70,"trueMoney":101,"memberId":2240,"stationId":23,"levelGroup":2,"drawMoney":101,"orderNo":"4084516320905216","levelName":"vip1","userName":"莫雷","createDatetime":1538134066521,"lockFlag":3,"moneyRecordId":47755,"atCreateDatetime":1535945201943,"flagActive":1,"account":"ml123456","status":3},{"createUserId":2240,"fee":0,"bankName":"中国银行","type":4,"cardNo":"134566646848","id":68,"trueMoney":100,"memberId":2240,"stationId":23,"levelGroup":2,"drawMoney":100,"orderNo":"4081188377823232","levelName":"vip1","userName":"莫雷","createDatetime":1537930944995,"lockFlag":3,"moneyRecordId":47597,"atCreateDatetime":1535945201943,"flagActive":1,"account":"ml123456","status":5}]
     * nextPage : 1
     * pageSize : 10
     * prePage : 1
     * results : [{"$ref":"$.list[0]"},{"$ref":"$.list[1]"}]
     * set : [{"$ref":"$.list[1]"},{"$ref":"$.list[0]"}]
     * start : 0
     * totalCount : 2
     * totalPageCount : 1
     */

    private AggsDataBean aggsData;
    private int currentPageNo;
    private boolean hasNext;
    private boolean hasPre;
    private int nextPage;
    private int pageSize;
    private int prePage;
    private int start;
    private int totalCount;
    private int totalPageCount;
    private List<ListBean> list;
    private List<ResultsBean> results;
    private List<SetBean> set;

    public AggsDataBean getAggsData() {
        return aggsData;
    }

    public void setAggsData(AggsDataBean aggsData) {
        this.aggsData = aggsData;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPre() {
        return hasPre;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public List<SetBean> getSet() {
        return set;
    }

    public void setSet(List<SetBean> set) {
        this.set = set;
    }

    public static class AggsDataBean {
        /**
         * drawMoney : 201
         */

        private int drawMoney;

        public int getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(int totalMoney) {
            this.totalMoney = totalMoney;
        }

        private  int totalMoney;

        public int getDrawMoney() {
            return drawMoney;
        }

        public void setDrawMoney(int drawMoney) {
            this.drawMoney = drawMoney;
        }
    }

    public static class ListBean {
        /**
         * createUserId : 2240
         * modifyDatetime : 1538229288798
         * fee : 0
         * modifyUserId : 366
         * remark : 涉嫌违规下注！如有在出现违规下注公司将冻结账户处理，请您须知
         * bankName : 中国银行
         * type : 4
         * cardNo : 134566646848
         * modifyUser : t006admin
         * id : 70
         * trueMoney : 101
         * memberId : 2240
         * stationId : 23
         * levelGroup : 2
         * drawMoney : 101
         * orderNo : 4084516320905216
         * levelName : vip1
         * userName : 莫雷
         * createDatetime : 1538134066521
         * lockFlag : 3
         * moneyRecordId : 47755
         * atCreateDatetime : 1535945201943
         * flagActive : 1
         * account : ml123456
         * status : 3
         */

        private int createUserId;
        private long modifyDatetime;
        private int fee;
        private int modifyUserId;
        private String remark;
        private String bankName;
        private int type;
        private String cardNo;
        private String modifyUser;
        private int id;
        private int trueMoney;
        private int memberId;
        private int stationId;
        private int levelGroup;
        private int drawMoney;
        private String orderNo;
        private String levelName;
        private String userName;
        private long createDatetime;
        private int lockFlag;
        private int moneyRecordId;
        private long atCreateDatetime;
        private int flagActive;
        private String account;
        private int status;

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public  int money;

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public long getModifyDatetime() {
            return modifyDatetime;
        }

        public void setModifyDatetime(long modifyDatetime) {
            this.modifyDatetime = modifyDatetime;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getModifyUserId() {
            return modifyUserId;
        }

        public void setModifyUserId(int modifyUserId) {
            this.modifyUserId = modifyUserId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getModifyUser() {
            return modifyUser;
        }

        public void setModifyUser(String modifyUser) {
            this.modifyUser = modifyUser;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTrueMoney() {
            return trueMoney;
        }

        public void setTrueMoney(int trueMoney) {
            this.trueMoney = trueMoney;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public int getLevelGroup() {
            return levelGroup;
        }

        public void setLevelGroup(int levelGroup) {
            this.levelGroup = levelGroup;
        }

        public int getDrawMoney() {
            return drawMoney;
        }

        public void setDrawMoney(int drawMoney) {
            this.drawMoney = drawMoney;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public long getCreateDatetime() {
            return createDatetime;
        }

        public void setCreateDatetime(long createDatetime) {
            this.createDatetime = createDatetime;
        }

        public int getLockFlag() {
            return lockFlag;
        }

        public void setLockFlag(int lockFlag) {
            this.lockFlag = lockFlag;
        }

        public int getMoneyRecordId() {
            return moneyRecordId;
        }

        public void setMoneyRecordId(int moneyRecordId) {
            this.moneyRecordId = moneyRecordId;
        }

        public long getAtCreateDatetime() {
            return atCreateDatetime;
        }

        public void setAtCreateDatetime(long atCreateDatetime) {
            this.atCreateDatetime = atCreateDatetime;
        }

        public int getFlagActive() {
            return flagActive;
        }

        public void setFlagActive(int flagActive) {
            this.flagActive = flagActive;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class ResultsBean {
        /**
         * $ref : $.list[0]
         */

        private String $ref;

        public String get$ref() {
            return $ref;
        }

        public void set$ref(String $ref) {
            this.$ref = $ref;
        }
    }

    public static class SetBean {
        /**
         * $ref : $.list[1]
         */

        private String $ref;

        public String get$ref() {
            return $ref;
        }

        public void set$ref(String $ref) {
            this.$ref = $ref;
        }
    }
}
