package com.xbzhangshi.mvp.home.event;

public class RedPackEvent {
    public RedPackEvent(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private boolean flag = false;

}
