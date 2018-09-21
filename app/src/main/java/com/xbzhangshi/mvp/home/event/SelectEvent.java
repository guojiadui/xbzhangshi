package com.xbzhangshi.mvp.home.event;

public class SelectEvent {
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    int position;

    public SelectEvent(int position) {
        this.position = position;
    }
}
