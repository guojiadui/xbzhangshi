package com.xbzhangshi.mvp.details.bean;

import java.util.List;

public class OpenPrizeListBean {

    /**
     * data : {"currentPageNo":2,"hasNext":true,"hasPre":true,"list":[{"date":"2018-09-20","haoMaList":["1","+","4","+","2","=","7"],"qiHao":"910886","time":"12:20:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["5","+","4","+","2","=","11"],"qiHao":"910885","time":"12:15:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["6","+","0","+","1","=","7"],"qiHao":"910884","time":"12:10:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["4","+","6","+","8","=","18"],"qiHao":"910883","time":"12:05:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["4","+","8","+","3","=","15"],"qiHao":"910882","time":"12:00:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["0","+","8","+","8","=","16"],"qiHao":"910881","time":"11:55:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["2","+","5","+","2","=","9"],"qiHao":"910880","time":"11:50:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["2","+","5","+","5","=","12"],"qiHao":"910879","time":"11:45:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["8","+","2","+","5","=","15"],"qiHao":"910878","time":"11:40:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["4","+","8","+","2","=","14"],"qiHao":"910877","time":"11:35:00","weekday":"星期四"}],"nextPage":3,"pageSize":10,"prePage":1,"results":[{"$ref":"$.data.list[0]"},{"$ref":"$.data.list[1]"},{"$ref":"$.data.list[2]"},{"$ref":"$.data.list[3]"},{"$ref":"$.data.list[4]"},{"$ref":"$.data.list[5]"},{"$ref":"$.data.list[6]"},{"$ref":"$.data.list[7]"},{"$ref":"$.data.list[8]"},{"$ref":"$.data.list[9]"}],"set":[{"$ref":"$.data.list[0]"},{"$ref":"$.data.list[6]"},{"$ref":"$.data.list[4]"},{"$ref":"$.data.list[7]"},{"$ref":"$.data.list[3]"},{"$ref":"$.data.list[2]"},{"$ref":"$.data.list[5]"},{"$ref":"$.data.list[9]"},{"$ref":"$.data.list[1]"},{"$ref":"$.data.list[8]"}],"start":10,"totalCount":229,"totalPageCount":23}
     * success : true
     */

    private DataBean data;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * currentPageNo : 2
         * hasNext : true
         * hasPre : true
         * list : [{"date":"2018-09-20","haoMaList":["1","+","4","+","2","=","7"],"qiHao":"910886","time":"12:20:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["5","+","4","+","2","=","11"],"qiHao":"910885","time":"12:15:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["6","+","0","+","1","=","7"],"qiHao":"910884","time":"12:10:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["4","+","6","+","8","=","18"],"qiHao":"910883","time":"12:05:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["4","+","8","+","3","=","15"],"qiHao":"910882","time":"12:00:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["0","+","8","+","8","=","16"],"qiHao":"910881","time":"11:55:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["2","+","5","+","2","=","9"],"qiHao":"910880","time":"11:50:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["2","+","5","+","5","=","12"],"qiHao":"910879","time":"11:45:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["8","+","2","+","5","=","15"],"qiHao":"910878","time":"11:40:00","weekday":"星期四"},{"date":"2018-09-20","haoMaList":["4","+","8","+","2","=","14"],"qiHao":"910877","time":"11:35:00","weekday":"星期四"}]
         * nextPage : 3
         * pageSize : 10
         * prePage : 1
         * results : [{"$ref":"$.data.list[0]"},{"$ref":"$.data.list[1]"},{"$ref":"$.data.list[2]"},{"$ref":"$.data.list[3]"},{"$ref":"$.data.list[4]"},{"$ref":"$.data.list[5]"},{"$ref":"$.data.list[6]"},{"$ref":"$.data.list[7]"},{"$ref":"$.data.list[8]"},{"$ref":"$.data.list[9]"}]
         * set : [{"$ref":"$.data.list[0]"},{"$ref":"$.data.list[6]"},{"$ref":"$.data.list[4]"},{"$ref":"$.data.list[7]"},{"$ref":"$.data.list[3]"},{"$ref":"$.data.list[2]"},{"$ref":"$.data.list[5]"},{"$ref":"$.data.list[9]"},{"$ref":"$.data.list[1]"},{"$ref":"$.data.list[8]"}]
         * start : 10
         * totalCount : 229
         * totalPageCount : 23
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



        public static class ListBean {
            /**
             * date : 2018-09-20
             * haoMaList : ["1","+","4","+","2","=","7"]
             * qiHao : 910886
             * time : 12:20:00
             * weekday : 星期四
             */

            private String date;
            private String qiHao;
            private String time;
            private String weekday;
            private List<String> haoMaList;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getQiHao() {
                return qiHao;
            }

            public void setQiHao(String qiHao) {
                this.qiHao = qiHao;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getWeekday() {
                return weekday;
            }

            public void setWeekday(String weekday) {
                this.weekday = weekday;
            }

            public List<String> getHaoMaList() {
                return haoMaList;
            }

            public void setHaoMaList(List<String> haoMaList) {
                this.haoMaList = haoMaList;
            }
        }


    }
}
