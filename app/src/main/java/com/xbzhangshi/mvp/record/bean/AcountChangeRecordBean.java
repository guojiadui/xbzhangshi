package com.xbzhangshi.mvp.record.bean;

import java.util.List;

public class AcountChangeRecordBean {

    /**
     * currentPageNo : 1
     * hasNext : true
     * hasPre : false
     * list : [{"createUserId":2364,"bizDatetime":1538112192667,"orderId":"M18092800051","remark":"用户撤单[M18092800051]","afterMoney":9525.39,"type":143,"operatorName":"zhang","createDatetime":1538113465350,"accountId":2364,"money":1,"flagActive":1,"id":47732,"beforeMoney":9524.39,"operatorId":2364,"account":"zhang","stationId":23,"parents":",367,"},{"createUserId":2364,"bizDatetime":1538112192667,"orderId":"M18092800052","remark":"用户撤单[M18092800052]","afterMoney":9524.39,"type":143,"operatorName":"zhang","createDatetime":1538113462099,"accountId":2364,"money":1,"flagActive":1,"id":47731,"beforeMoney":9523.39,"operatorId":2364,"account":"zhang","stationId":23,"parents":",367,"},{"createUserId":2364,"bizDatetime":1538113091244,"orderId":"M18092800053","remark":"用户撤单[M18092800053]","afterMoney":9523.39,"type":143,"operatorName":"zhang","createDatetime":1538113459760,"accountId":2364,"money":1,"flagActive":1,"id":47730,"beforeMoney":9522.39,"operatorId":2364,"account":"zhang","stationId":23,"parents":",367,"},{"createUserId":2364,"bizDatetime":1538113094769,"orderId":"M18092800054","remark":"用户撤单[M18092800054]","afterMoney":9522.39,"type":143,"operatorName":"zhang","createDatetime":1538113456122,"accountId":2364,"money":1,"flagActive":1,"id":47729,"beforeMoney":9521.39,"operatorId":2364,"account":"zhang","stationId":23,"parents":",367,"},{"createUserId":2364,"bizDatetime":1538113098034,"orderId":"M18092800055","remark":"用户撤单[M18092800055]","afterMoney":9521.39,"type":143,"operatorName":"zhang","createDatetime":1538113450127,"accountId":2364,"money":1,"flagActive":1,"id":47728,"beforeMoney":9520.39,"operatorId":2364,"account":"zhang","stationId":23,"parents":",367,"},{"createUserId":2364,"bizDatetime":1538113101545,"orderId":"M18092800056","remark":"用户撤单[M18092800056]","afterMoney":9520.39,"type":143,"operatorName":"zhang","createDatetime":1538113290634,"accountId":2364,"money":1,"flagActive":1,"id":47726,"beforeMoney":9519.39,"operatorId":2364,"account":"zhang","stationId":23,"parents":",367,"},{"createUserId":2364,"bizDatetime":1538113105495,"orderId":"M18092800057","remark":"用户撤单[M18092800057]","afterMoney":9519.39,"type":143,"operatorName":"zhang","createDatetime":1538113284339,"accountId":2364,"money":1,"flagActive":1,"id":47725,"beforeMoney":9518.39,"operatorId":2364,"account":"zhang","stationId":23,"parents":",367,"},{"createUserId":2364,"bizDatetime":1538113141596,"orderId":"M18092800058","remark":"用户撤单[M18092800058]","afterMoney":9518.39,"type":143,"operatorName":"zhang","createDatetime":1538113282241,"accountId":2364,"money":1,"flagActive":1,"id":47724,"beforeMoney":9517.39,"operatorId":2364,"account":"zhang","stationId":23,"parents":",367,"},{"createUserId":2364,"bizDatetime":1538113144449,"orderId":"M18092800059","remark":"用户撤单[M18092800059]","afterMoney":9517.39,"type":143,"operatorName":"zhang","createDatetime":1538113279670,"accountId":2364,"money":1,"flagActive":1,"id":47723,"beforeMoney":9516.39,"operatorId":2364,"account":"zhang","stationId":23,"parents":",367,"},{"createUserId":2364,"bizDatetime":1538113144449,"orderId":"M18092800059","remark":"投注彩种：六合彩;期号：2018111订单号：M18092800059","afterMoney":9516.39,"type":140,"operatorName":"zhang","createDatetime":1538113144462,"accountId":2364,"money":-1,"flagActive":1,"id":47722,"beforeMoney":9517.39,"operatorId":2364,"account":"zhang","stationId":23,"parents":",367,"}]
     * nextPage : 2
     * pageSize : 10
     * prePage : 1
     * results : [{"$ref":"$.list[0]"},{"$ref":"$.list[1]"},{"$ref":"$.list[2]"},{"$ref":"$.list[3]"},{"$ref":"$.list[4]"},{"$ref":"$.list[5]"},{"$ref":"$.list[6]"},{"$ref":"$.list[7]"},{"$ref":"$.list[8]"},{"$ref":"$.list[9]"}]
     * set : [{"$ref":"$.list[2]"},{"$ref":"$.list[7]"},{"$ref":"$.list[4]"},{"$ref":"$.list[5]"},{"$ref":"$.list[9]"},{"$ref":"$.list[8]"},{"$ref":"$.list[3]"},{"$ref":"$.list[1]"},{"$ref":"$.list[6]"},{"$ref":"$.list[0]"}]
     * start : 0
     * totalCount : 17
     * totalPageCount : 2
     */

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

    public static class ListBean {
        /**
         * createUserId : 2364
         * bizDatetime : 1538112192667
         * orderId : M18092800051
         * remark : 用户撤单[M18092800051]
         * afterMoney : 9525.39
         * type : 143
         * operatorName : zhang
         * createDatetime : 1538113465350
         * accountId : 2364
         * money : 1.0
         * flagActive : 1
         * id : 47732
         * beforeMoney : 9524.39
         * operatorId : 2364
         * account : zhang
         * stationId : 23
         * parents : ,367,
         */

        private int createUserId;
        private long bizDatetime;
        private String orderId;
        private String remark;
        private double afterMoney;
        private int type;
        private String operatorName;
        private long createDatetime;
        private int accountId;
        private double money;
        private int flagActive;
        private int id;
        private double beforeMoney;
        private int operatorId;
        private String account;
        private int stationId;
        private String parents;

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public long getBizDatetime() {
            return bizDatetime;
        }

        public void setBizDatetime(long bizDatetime) {
            this.bizDatetime = bizDatetime;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public double getAfterMoney() {
            return afterMoney;
        }

        public void setAfterMoney(double afterMoney) {
            this.afterMoney = afterMoney;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public long getCreateDatetime() {
            return createDatetime;
        }

        public void setCreateDatetime(long createDatetime) {
            this.createDatetime = createDatetime;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getFlagActive() {
            return flagActive;
        }

        public void setFlagActive(int flagActive) {
            this.flagActive = flagActive;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getBeforeMoney() {
            return beforeMoney;
        }

        public void setBeforeMoney(double beforeMoney) {
            this.beforeMoney = beforeMoney;
        }

        public int getOperatorId() {
            return operatorId;
        }

        public void setOperatorId(int operatorId) {
            this.operatorId = operatorId;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public String getParents() {
            return parents;
        }

        public void setParents(String parents) {
            this.parents = parents;
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
         * $ref : $.list[2]
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
