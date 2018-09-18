package com.xbzhangshi.mvp.home.event;

public class UpdateLotteryEvent {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    String code;

    public UpdateLotteryEvent(String code) {
        this.code = code;
    }
}
