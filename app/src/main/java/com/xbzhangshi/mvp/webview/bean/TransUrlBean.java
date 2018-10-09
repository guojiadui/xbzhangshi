package com.xbzhangshi.mvp.webview.bean;

public class TransUrlBean {

    /**
     * success : true
     * accessToken : 712a0bef-d71c-4750-aabc-1b28875cfd71
     * content : {"success":true,"url":"http://cashapi.dg20mu.com/cashapi/DoBusiness.aspx?params=YWdlbnQ9eGIkdXNlcm5hbWU9eGJ6aGFuZyRwYXNzd29yZD04OWE5YmEyNDMzJGRvbWFpbj15aWJvMTEuY29tJGdhbWV0eXBlPTEkZ2FtZWtpbmQ9MCRpZnJhbWU9MCRwbGF0Zm9ybW5hbWU9T3JpZW50YWwkbGFuZz16aCRtZXRob2Q9dGc=&Key=d201c67722a3775ef5204bec322c2146"}
     */

    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    private String accessToken;
    private ContentBean content;

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
         * success : true
         * url : http://cashapi.dg20mu.com/cashapi/DoBusiness.aspx?params=YWdlbnQ9eGIkdXNlcm5hbWU9eGJ6aGFuZyRwYXNzd29yZD04OWE5YmEyNDMzJGRvbWFpbj15aWJvMTEuY29tJGdhbWV0eXBlPTEkZ2FtZWtpbmQ9MCRpZnJhbWU9MCRwbGF0Zm9ybW5hbWU9T3JpZW50YWwkbGFuZz16aCRtZXRob2Q9dGc=&Key=d201c67722a3775ef5204bec322c2146
         */

        private boolean success;
        private String url;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
