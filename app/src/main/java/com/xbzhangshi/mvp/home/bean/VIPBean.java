package com.xbzhangshi.mvp.home.bean;

public class VIPBean {

    /**
     * next : vip2
     * current : vip1
     * need : 40000.0
     * gifMoney : 100.0
     */

    private String next;
    private String current;
    private double need;
    private double gifMoney;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public double getNeed() {
        return need;
    }

    public void setNeed(double need) {
        this.need = need;
    }

    public double getGifMoney() {
        return gifMoney;
    }

    public void setGifMoney(double gifMoney) {
        this.gifMoney = gifMoney;
    }
}
