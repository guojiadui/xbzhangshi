package com.xbzhangshi.mvp.usercenter.bean;

public class ItemBalanceBean {


    /**
     * msg : 获取余额成功
     * balance : 0.0
     * success : true
     */

    private String msg;
    private double balance;
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
