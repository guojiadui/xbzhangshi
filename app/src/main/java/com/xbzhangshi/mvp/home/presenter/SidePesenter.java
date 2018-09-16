package com.xbzhangshi.mvp.home.presenter;

import android.view.View;

import com.xbzhangshi.app.MyApplication;

public class SidePesenter {
    public static SidePesenter newInstance(View sideView) {
        return new SidePesenter(sideView);
    }

    View sideView;

    public SidePesenter(View sideView) {
        this.sideView = sideView;
    }

/***
 * 退出程序
 */

public  void  exit(){
    MyApplication.getInstance().exit();
}
}
