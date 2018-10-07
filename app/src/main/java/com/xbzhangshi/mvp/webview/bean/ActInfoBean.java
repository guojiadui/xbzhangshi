package com.xbzhangshi.mvp.webview.bean;

import java.util.List;

public class ActInfoBean {

    /**
     * success : true
     * accessToken : 8b91f6db-51eb-4248-b3c1-6d432b95f97e
     * content : [{"readFlag":0,"overTime":1569772800000,"updateTime":1538323200000,"id":208,"title":"疯子活动","titleImgUrl":"","content":"hdsjfkdjfkdshfjksdflhsjdfdhlsfjkffd"},{"readFlag":0,"overTime":1569772800000,"updateTime":1538323200000,"id":209,"title":"123","titleImgUrl":"https://xbtu111.com/vnlinuser/b145/01.jpg","content":"<p>\n    <img src=\"https://xbtu111.com/vnlinuser/b145/08.jpg\"/>\n<\/p>"},{"readFlag":0,"overTime":1569772800000,"updateTime":1538323200000,"id":210,"title":"","titleImgUrl":"https://xbtu111.com/vnlinuser/b145/011.jpg","content":"<img src=\"https://xbtu111.com/vnlinuser/b145/010.jpg\"/>"}]
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
         * readFlag : 0
         * overTime : 1569772800000
         * updateTime : 1538323200000
         * id : 208
         * title : 疯子活动
         * titleImgUrl :
         * content : hdsjfkdjfkdshfjksdflhsjdfdhlsfjkffd
         */

        private int readFlag;
        private long overTime;
        private long updateTime;
        private int id;
        private String title;
        private String titleImgUrl;
        private String content;

        public int getReadFlag() {
            return readFlag;
        }

        public void setReadFlag(int readFlag) {
            this.readFlag = readFlag;
        }

        public long getOverTime() {
            return overTime;
        }

        public void setOverTime(long overTime) {
            this.overTime = overTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleImgUrl() {
            return titleImgUrl;
        }

        public void setTitleImgUrl(String titleImgUrl) {
            this.titleImgUrl = titleImgUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
