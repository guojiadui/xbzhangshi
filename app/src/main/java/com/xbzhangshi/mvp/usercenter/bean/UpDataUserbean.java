package com.xbzhangshi.mvp.usercenter.bean;

public class UpDataUserbean {
    private  String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private  boolean success;

}
