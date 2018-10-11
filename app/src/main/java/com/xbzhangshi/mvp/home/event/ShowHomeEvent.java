package com.xbzhangshi.mvp.home.event;

public class ShowHomeEvent {
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ShowHomeEvent(boolean flag) {
        this.flag = flag;
    }

    boolean flag;
}
