package com.xbzhangshi.mvp.home.bean;

public class VersionBean {
    /**
     * success : true
     * content : {"content":"dddddddddddddddd","createTime":1540115267690,"flag":"android","id":6,"status":2,"version":"2"}
     */

    private boolean success;
    private ContentBean content;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * content : dddddddddddddddd
         * createTime : 1540115267690
         * flag : android
         * id : 6
         * status : 2
         * version : 2
         */

        private String content;
        private long createTime;
        private String flag;
        private int id;
        private int status;
        private String version;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private  String url;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
