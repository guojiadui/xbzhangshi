package com.xbzhangshi.mvp.record.bean;

import java.util.List;

public class ResultLotteryRecordBean {

    /**
     * sumWinMoney : 200.99
     * sumBuyMoney : 104.0
     * page : {"aggsData":{"winSum":200.99,"buySum":104},"currentPageNo":1,"hasNext":false,"hasPre":false,"list":[{"account":"zhang","accountId":2364,"ago":60,"buyMoney":1,"buyZhuShu":1,"createTime":"2018-09-26 15:55:16","haoMa":"和小","id":7909,"jointPurchase":"1","logId":0,"lotCode":"XYFT","lotName":"幸运飞艇","lotType":8,"lotteryHaoMa":"03,07,08,04,06,05,10,01,09,02","markSixId":10551,"model":1,"multiple":1,"odds":1.99,"openTime":"2018-09-26 15:59:46","orderId":"L18092600055","parents":",367,","playCode":"shuangmianpan","playName":"双面盘","qiHao":"20180926035","rollBackStatus":1,"roomId":0,"stationId":23,"status":2,"winMoney":1.99,"winZhuShu":1,"zhuiHao":"1"},{"account":"zhang","accountId":2364,"ago":30,"buyMoney":3,"buyZhuShu":1,"createTime":"2018-09-26 15:54:36","haoMa":"和小","id":7908,"jointPurchase":"1","logId":0,"lotCode":"BJSC","lotName":"北京赛车","lotType":8,"lotteryHaoMa":"08,05,07,10,09,02,06,03,01,04","markSixId":10551,"model":1,"multiple":1,"odds":1.99,"openTime":"2018-09-26 15:58:06","orderId":"L18092600054","parents":",367,","playCode":"shuangmianpan","playName":"双面盘","qiHao":"706025","rollBackStatus":1,"roomId":0,"stationId":23,"status":3,"winMoney":0,"winZhuShu":0,"zhuiHao":"1"},{"account":"zhang","accountId":2364,"ago":30,"buyMoney":100,"buyZhuShu":1,"createTime":"2018-09-26 15:41:17","haoMa":"和大","id":7906,"jointPurchase":"1","logId":0,"lotCode":"BJSC","lotName":"北京赛车","lotType":8,"lotteryHaoMa":"10,03,06,05,09,01,07,08,02,04","markSixId":10550,"model":1,"multiple":1,"odds":1.99,"openTime":"2018-09-26 15:43:06","orderId":"L18092600053","parents":",367,","playCode":"shuangmianpan","playName":"双面盘","qiHao":"706022","rollBackStatus":1,"roomId":0,"stationId":23,"status":2,"winMoney":199,"winZhuShu":1,"xBet":104,"xWin":200.99,"zhuiHao":"1"}],"nextPage":1,"pageSize":10,"prePage":1,"results":[{"$ref":"$.page.list[0]"},{"$ref":"$.page.list[1]"},{"$ref":"$.page.list[2]"}],"set":[{"$ref":"$.page.list[0]"},{"$ref":"$.page.list[1]"},{"$ref":"$.page.list[2]"}],"start":0,"totalCount":3,"totalPageCount":1}
     * subBuyMoney : 104.0
     */

    private double sumWinMoney;
    private double sumBuyMoney;
    private PageBean page;
    private double subBuyMoney;
    private String msg;

    public double getSumWinMoney() {
        return sumWinMoney;
    }

    public void setSumWinMoney(double sumWinMoney) {
        this.sumWinMoney = sumWinMoney;
    }

    public double getSumBuyMoney() {
        return sumBuyMoney;
    }

    public void setSumBuyMoney(double sumBuyMoney) {
        this.sumBuyMoney = sumBuyMoney;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public double getSubBuyMoney() {
        return subBuyMoney;
    }

    public void setSubBuyMoney(double subBuyMoney) {
        this.subBuyMoney = subBuyMoney;
    }

    public static class PageBean {
        /**
         * aggsData : {"winSum":200.99,"buySum":104}
         * currentPageNo : 1
         * hasNext : false
         * hasPre : false
         * list : [{"account":"zhang","accountId":2364,"ago":60,"buyMoney":1,"buyZhuShu":1,"createTime":"2018-09-26 15:55:16","haoMa":"和小","id":7909,"jointPurchase":"1","logId":0,"lotCode":"XYFT","lotName":"幸运飞艇","lotType":8,"lotteryHaoMa":"03,07,08,04,06,05,10,01,09,02","markSixId":10551,"model":1,"multiple":1,"odds":1.99,"openTime":"2018-09-26 15:59:46","orderId":"L18092600055","parents":",367,","playCode":"shuangmianpan","playName":"双面盘","qiHao":"20180926035","rollBackStatus":1,"roomId":0,"stationId":23,"status":2,"winMoney":1.99,"winZhuShu":1,"zhuiHao":"1"},{"account":"zhang","accountId":2364,"ago":30,"buyMoney":3,"buyZhuShu":1,"createTime":"2018-09-26 15:54:36","haoMa":"和小","id":7908,"jointPurchase":"1","logId":0,"lotCode":"BJSC","lotName":"北京赛车","lotType":8,"lotteryHaoMa":"08,05,07,10,09,02,06,03,01,04","markSixId":10551,"model":1,"multiple":1,"odds":1.99,"openTime":"2018-09-26 15:58:06","orderId":"L18092600054","parents":",367,","playCode":"shuangmianpan","playName":"双面盘","qiHao":"706025","rollBackStatus":1,"roomId":0,"stationId":23,"status":3,"winMoney":0,"winZhuShu":0,"zhuiHao":"1"},{"account":"zhang","accountId":2364,"ago":30,"buyMoney":100,"buyZhuShu":1,"createTime":"2018-09-26 15:41:17","haoMa":"和大","id":7906,"jointPurchase":"1","logId":0,"lotCode":"BJSC","lotName":"北京赛车","lotType":8,"lotteryHaoMa":"10,03,06,05,09,01,07,08,02,04","markSixId":10550,"model":1,"multiple":1,"odds":1.99,"openTime":"2018-09-26 15:43:06","orderId":"L18092600053","parents":",367,","playCode":"shuangmianpan","playName":"双面盘","qiHao":"706022","rollBackStatus":1,"roomId":0,"stationId":23,"status":2,"winMoney":199,"winZhuShu":1,"xBet":104,"xWin":200.99,"zhuiHao":"1"}]
         * nextPage : 1
         * pageSize : 10
         * prePage : 1
         * results : [{"$ref":"$.page.list[0]"},{"$ref":"$.page.list[1]"},{"$ref":"$.page.list[2]"}]
         * set : [{"$ref":"$.page.list[0]"},{"$ref":"$.page.list[1]"},{"$ref":"$.page.list[2]"}]
         * start : 0
         * totalCount : 3
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
             * winSum : 200.99
             * buySum : 104.0
             */

            private double winSum;
            private double buySum;

            public double getWinSum() {
                return winSum;
            }

            public void setWinSum(double winSum) {
                this.winSum = winSum;
            }

            public double getBuySum() {
                return buySum;
            }

            public void setBuySum(double buySum) {
                this.buySum = buySum;
            }
        }

        public static class ListBean {
            /**
             * account : zhang
             * accountId : 2364
             * ago : 60
             * buyMoney : 1.0
             * buyZhuShu : 1
             * createTime : 2018-09-26 15:55:16
             * haoMa : 和小
             * id : 7909
             * jointPurchase : 1
             * logId : 0
             * lotCode : XYFT
             * lotName : 幸运飞艇
             * lotType : 8
             * lotteryHaoMa : 03,07,08,04,06,05,10,01,09,02
             * markSixId : 10551
             * model : 1
             * multiple : 1
             * odds : 1.99
             * openTime : 2018-09-26 15:59:46
             * orderId : L18092600055
             * parents : ,367,
             * playCode : shuangmianpan
             * playName : 双面盘
             * qiHao : 20180926035
             * rollBackStatus : 1
             * roomId : 0
             * stationId : 23
             * status : 2
             * winMoney : 1.99
             * winZhuShu : 1
             * zhuiHao : 1
             * xBet : 104.0
             * xWin : 200.99
             */

            private String account;
            private int accountId;
            private int ago;
            private double buyMoney;
            private int buyZhuShu;
            private String createTime;
            private String haoMa;
            private int id;
            private String jointPurchase;
            private int logId;
            private String lotCode;
            private String lotName;
            private int lotType;
            private String lotteryHaoMa;
            private int markSixId;
            private int model;
            private int multiple;
            private double odds;
            private String openTime;
            private String orderId;
            private String parents;
            private String playCode;
            private String playName;
            private String qiHao;
            private int rollBackStatus;
            private int roomId;
            private int stationId;
            private int status;
            private double winMoney;
            private int winZhuShu;
            private String zhuiHao;
            private double xBet;
            private double xWin;

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public int getAgo() {
                return ago;
            }

            public void setAgo(int ago) {
                this.ago = ago;
            }

            public double getBuyMoney() {
                return buyMoney;
            }

            public void setBuyMoney(double buyMoney) {
                this.buyMoney = buyMoney;
            }

            public int getBuyZhuShu() {
                return buyZhuShu;
            }

            public void setBuyZhuShu(int buyZhuShu) {
                this.buyZhuShu = buyZhuShu;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getHaoMa() {
                return haoMa;
            }

            public void setHaoMa(String haoMa) {
                this.haoMa = haoMa;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getJointPurchase() {
                return jointPurchase;
            }

            public void setJointPurchase(String jointPurchase) {
                this.jointPurchase = jointPurchase;
            }

            public int getLogId() {
                return logId;
            }

            public void setLogId(int logId) {
                this.logId = logId;
            }

            public String getLotCode() {
                return lotCode;
            }

            public void setLotCode(String lotCode) {
                this.lotCode = lotCode;
            }

            public String getLotName() {
                return lotName;
            }

            public void setLotName(String lotName) {
                this.lotName = lotName;
            }

            public int getLotType() {
                return lotType;
            }

            public void setLotType(int lotType) {
                this.lotType = lotType;
            }

            public String getLotteryHaoMa() {
                return lotteryHaoMa;
            }

            public void setLotteryHaoMa(String lotteryHaoMa) {
                this.lotteryHaoMa = lotteryHaoMa;
            }

            public int getMarkSixId() {
                return markSixId;
            }

            public void setMarkSixId(int markSixId) {
                this.markSixId = markSixId;
            }

            public int getModel() {
                return model;
            }

            public void setModel(int model) {
                this.model = model;
            }

            public int getMultiple() {
                return multiple;
            }

            public void setMultiple(int multiple) {
                this.multiple = multiple;
            }

            public double getOdds() {
                return odds;
            }

            public void setOdds(double odds) {
                this.odds = odds;
            }

            public String getOpenTime() {
                return openTime;
            }

            public void setOpenTime(String openTime) {
                this.openTime = openTime;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getParents() {
                return parents;
            }

            public void setParents(String parents) {
                this.parents = parents;
            }

            public String getPlayCode() {
                return playCode;
            }

            public void setPlayCode(String playCode) {
                this.playCode = playCode;
            }

            public String getPlayName() {
                return playName;
            }

            public void setPlayName(String playName) {
                this.playName = playName;
            }

            public String getQiHao() {
                return qiHao;
            }

            public void setQiHao(String qiHao) {
                this.qiHao = qiHao;
            }

            public int getRollBackStatus() {
                return rollBackStatus;
            }

            public void setRollBackStatus(int rollBackStatus) {
                this.rollBackStatus = rollBackStatus;
            }

            public int getRoomId() {
                return roomId;
            }

            public void setRoomId(int roomId) {
                this.roomId = roomId;
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

            public double getWinMoney() {
                return winMoney;
            }

            public void setWinMoney(double winMoney) {
                this.winMoney = winMoney;
            }

            public int getWinZhuShu() {
                return winZhuShu;
            }

            public void setWinZhuShu(int winZhuShu) {
                this.winZhuShu = winZhuShu;
            }

            public String getZhuiHao() {
                return zhuiHao;
            }

            public void setZhuiHao(String zhuiHao) {
                this.zhuiHao = zhuiHao;
            }

            public double getXBet() {
                return xBet;
            }

            public void setXBet(double xBet) {
                this.xBet = xBet;
            }

            public double getXWin() {
                return xWin;
            }

            public void setXWin(double xWin) {
                this.xWin = xWin;
            }
        }

        public static class ResultsBean {
            /**
             * $ref : $.page.list[0]
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
             * $ref : $.page.list[0]
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
}
