package com.xbzhangshi.mvp.usercenter.bean;

import java.util.List;

public class MsgBean {


    /**
     * currentPageNo : 1
     * hasNext : false
     * hasPre : false
     * list : [{"createDatetime":1537845953117,"accountId":2364,"id":129,"message":"ffffff","title":"","type":1,"userMessageId":135,"account":"zhang","stationId":23,"status":2},{"createDatetime":1537845912717,"accountId":2364,"id":128,"message":"dddddddddddddddd","title":"b","type":1,"userMessageId":134,"account":"zhang","stationId":23,"status":2},{"createDatetime":1537789183981,"accountId":2364,"id":121,"message":"dffffff","title":"ddd ","type":1,"userMessageId":127,"account":"zhang","stationId":23,"status":2},{"createDatetime":1537789062276,"accountId":2364,"id":120,"message":"fddddddddddd","title":"dd ","type":1,"userMessageId":126,"account":"zhang","stationId":23,"status":2},{"createDatetime":1537788935677,"accountId":2364,"id":119,"message":"哒哒哒哒哒哒多多多多多多","title":"标题等等","type":1,"userMessageId":125,"account":"zhang","stationId":23,"status":2},{"createDatetime":1537785705612,"accountId":2364,"id":118,"message":"哒哒哒哒哒哒多多多多多多多多多","title":"标题六","type":1,"userMessageId":124,"account":"zhang","stationId":23,"status":2},{"createDatetime":1537785685714,"accountId":2364,"id":117,"message":"柔柔弱弱若若若若若若若若若若若若","title":"标题5","type":1,"userMessageId":123,"account":"zhang","stationId":23,"status":2},{"createDatetime":1537785668706,"accountId":2364,"id":116,"message":"","title":"b","type":1,"userMessageId":122,"account":"zhang","stationId":23,"status":2},{"createDatetime":1537785658098,"accountId":2364,"id":115,"message":"反反复复付付付付付付付付付付付付","title":"这标题的","type":1,"userMessageId":121,"account":"zhang","stationId":23,"status":2},{"createDatetime":1537785643058,"accountId":2364,"id":114,"message":"反反复复付付付付付付付付","title":"标题3","type":1,"userMessageId":120,"account":"zhang","stationId":23,"status":2},{"createDatetime":1537763589497,"accountId":2364,"id":105,"message":"http://justcoding.iteye.com/blog/1405951\r\n\r\n---------------------\r\n\r\n本文来自 正能量_ 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/www9500net_/article/details/51601690?utm_source=copy ","title":"哈哈哈啊哈","type":1,"userMessageId":111,"account":"zhang","stationId":23,"status":2}]
     * nextPage : 1
     * pageSize : 100
     * prePage : 1
     * results : [{"$ref":"$.list[0]"},{"$ref":"$.list[1]"},{"$ref":"$.list[2]"},{"$ref":"$.list[3]"},{"$ref":"$.list[4]"},{"$ref":"$.list[5]"},{"$ref":"$.list[6]"},{"$ref":"$.list[7]"},{"$ref":"$.list[8]"},{"$ref":"$.list[9]"},{"$ref":"$.list[10]"}]
     * set : [{"$ref":"$.list[7]"},{"$ref":"$.list[1]"},{"$ref":"$.list[4]"},{"$ref":"$.list[2]"},{"$ref":"$.list[5]"},{"$ref":"$.list[6]"},{"$ref":"$.list[0]"},{"$ref":"$.list[10]"},{"$ref":"$.list[9]"},{"$ref":"$.list[3]"},{"$ref":"$.list[8]"}]
     * start : 0
     * totalCount : 11
     * totalPageCount : 1
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
         * createDatetime : 1537845953117
         * accountId : 2364
         * id : 129
         * message : ffffff
         * title :
         * type : 1
         * userMessageId : 135
         * account : zhang
         * stationId : 23
         * status : 2
         */

        private long createDatetime;
        private int accountId;
        private int id;
        private String message;
        private String title;
        private int type;
        private int userMessageId;
        private String account;
        private int stationId;
        private int status;


        public boolean isIscheck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
            this.ischeck = ischeck;
        }

        public  boolean ischeck;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserMessageId() {
            return userMessageId;
        }

        public void setUserMessageId(int userMessageId) {
            this.userMessageId = userMessageId;
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
         * $ref : $.list[7]
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
