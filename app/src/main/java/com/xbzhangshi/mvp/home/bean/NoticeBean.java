package com.xbzhangshi.mvp.home.bean;

public class NoticeBean {

    /**
     * success : true
     * accessToken : 42ef60a8-8206-4bdd-87d2-25a68bd0a39c
     * content : <p>信博平台系统采用Java+Linux系统服务器搭建，稳定性、安全性、流畅性远胜市面上大部分其它平台模式，金字塔代理模式，后台简洁明了，操作简单易上手，功能丰富易拓展，综合站开版本费2万，维护费一个月2万------------------ 纯彩票开版本费6千元，维护费一个月6千元，（以上交一季度维护费免开版费。真人报表到200万，免当月维护费）</p><p>如果贵方需要平台转称或新写平台，可随时联系我们(QQ:780038065) skype: xinboinfo@gmail.com https://xinbo.info/，值得一提的是我们有几百个模板，极大方便了客户的挑选，同时免费为客户定制其他网站模板，(公司特色：后台简洁，一目了然，功能多，风控系统，红包，转盘，点击签到送积分等。)期待您的合作!</p><p></p>
     */

    private boolean success;
    private String accessToken;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
