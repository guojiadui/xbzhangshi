package com.xbzhangshi.mvp.home.bean;

import java.util.List;

public class BesidesLotteryBean {

    /**
     * success : true
     * accessToken : ede36c81-4f20-47b3-a04a-5852b41c8619
     * content : [{"balance":0,"dataCode":1,"imgUrl":"/native/resources/images/agreal.png","playCode":"ag","title":"AG真人娱乐"},{"balance":0,"dataCode":1,"imgUrl":"/native/resources/images/mgreal.png","playCode":"mg","title":"MG真人娱乐"},{"balance":0,"dataCode":1,"imgUrl":"/native/resources/images/bbinreal.png","playCode":"bbin","title":"BBIN真人娱乐"},{"balance":0,"dataCode":1,"imgUrl":"/native/resources/images/ab.png","playCode":"ab","title":"AB真人娱乐"},{"balance":0,"dataCode":1,"imgUrl":"/native/resources/images/ogzr.png","playCode":"og","title":"OG真人娱乐"},{"balance":0,"dataCode":1,"imgUrl":"/native/resources/images/dszr.png","playCode":"ds","title":"DS真人娱乐"},{"balance":0,"dataCode":1,"imgUrl":"/native/resources/images/vr.png","playCode":"bg","title":"BG真人娱乐"},{"balance":0,"dataCode":1,"imgUrl":"/native/resources/images/vr.png","playCode":"vr","title":"VR游戏"}]
     */

    private boolean success;
    private String accessToken;
    private List<ContentBean> content;

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
        /**
         * balance : 0
         * dataCode : 1
         * imgUrl : /native/resources/images/agreal.png
         * playCode : ag
         * title : AG真人娱乐
         */


        private int balance;
        private int dataCode;
        private String imgUrl;
        private String playCode;
        private String title;
        private  String gameId;

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }
        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

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
