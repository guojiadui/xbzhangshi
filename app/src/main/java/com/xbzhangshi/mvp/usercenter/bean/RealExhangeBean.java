package com.xbzhangshi.mvp.usercenter.bean;

import java.util.List;

public class RealExhangeBean {


    /**
     * success : true
     * accessToken : a42c9998-e0e6-4904-b1f4-700d1e8ae6e7
     * content : [{"balance":0,"dataCode":0,"imgUrl":"/native/resources/images/agreal.png","playCode":"ag","title":"AG真人娱乐"},{"balance":0,"dataCode":0,"imgUrl":"/native/resources/images/bbinreal.png","playCode":"bbin","title":"BBIN真人娱乐"},{"balance":0,"dataCode":0,"imgUrl":"/native/resources/images/mgreal.png","playCode":"mg","title":"MG真人娱乐"},{"balance":0,"dataCode":0,"imgUrl":"/native/resources/images/ab.png","playCode":"ab","title":"AB真人娱乐"},{"balance":0,"dataCode":0,"imgUrl":"/native/resources/images/pt.png","playCode":"pt","title":"PT电子游戏"},{"balance":0,"dataCode":0,"imgUrl":"/native/resources/images/qt.png","playCode":"qt","title":"QT电子游戏"},{"balance":0,"dataCode":0,"imgUrl":"/native/resources/images/ogzr.png","playCode":"og","title":"OG真人娱乐"},{"balance":0,"dataCode":0,"imgUrl":"/native/resources/images/dszr.png","playCode":"ds","title":"DS真人娱乐"},{"balance":0,"dataCode":0,"imgUrl":"/native/resources/images/cq9.png","playCode":"cq9","title":"CQ9电子游戏"},{"balance":0,"dataCode":0,"imgUrl":"/native/resources/images/HGSPORT.png","playCode":"ibc","title":"沙巴体育"},{"balance":0,"dataCode":0,"imgUrl":"/native/resources/images/bg.png","playCode":"bg","title":"BG真人娱乐"}]
     */

    private boolean success;
    private String accessToken;
    private List<ContentBean> content;

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

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        /**
         * balance : 0
         * dataCode : 0
         * imgUrl : /native/resources/images/agreal.png
         * playCode : ag
         * title : AG真人娱乐
         */

        private Double balance;
        private int dataCode;
        private String imgUrl;
        private String playCode;
        private String title;




        public int getDataCode() {
            return dataCode;
        }

        public void setDataCode(int dataCode) {
            this.dataCode = dataCode;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getPlayCode() {
            return playCode;
        }

        public void setPlayCode(String playCode) {
            this.playCode = playCode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
