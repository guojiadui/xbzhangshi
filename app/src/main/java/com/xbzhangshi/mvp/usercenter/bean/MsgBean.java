package com.xbzhangshi.mvp.usercenter.bean;

import java.util.List;

public class MsgBean {

    /**
     * success : true
     * accessToken : e6c41a27-82f4-4d47-af3a-01f2326525b1
     * content : {"datas":[{"createTime":1537772553624,"id":113,"message":"ddddddddddddddddddd","status":1,"title":"标题2","type":1},{"createTime":1537767576231,"id":107,"message":"随便","status":2,"title":"标题","type":1},{"createTime":1537763589497,"id":105,"message":"http://justcoding.iteye.com/blog/1405951\r\n\r\n---------------------\r\n\r\n本文来自 正能量_ 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/www9500net_/article/details/51601690?utm_source=copy ","status":2,"title":"哈哈哈啊哈","type":1}],"totalCount":3}
     */

    private boolean success;
    private String accessToken;
    private ContentBean content;

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

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * datas : [{"createTime":1537772553624,"id":113,"message":"ddddddddddddddddddd","status":1,"title":"标题2","type":1},{"createTime":1537767576231,"id":107,"message":"随便","status":2,"title":"标题","type":1},{"createTime":1537763589497,"id":105,"message":"http://justcoding.iteye.com/blog/1405951\r\n\r\n---------------------\r\n\r\n本文来自 正能量_ 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/www9500net_/article/details/51601690?utm_source=copy ","status":2,"title":"哈哈哈啊哈","type":1}]
         * totalCount : 3
         */

        private int totalCount;
        private List<DatasBean> datas;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * createTime : 1537772553624
             * id : 113
             * message : ddddddddddddddddddd
             * status : 1
             * title : 标题2
             * type : 1
             */

            private long createTime;
            private int id;
            private String message;
            private int status;
            private String title;
            private int type;

            public boolean isIscheck() {
                return ischeck;
            }

            public void setIscheck(boolean ischeck) {
                this.ischeck = ischeck;
            }

            public  boolean ischeck ;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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
        }
    }
}
