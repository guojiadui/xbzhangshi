package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;

import com.xbzhangshi.app.URL;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IHomeBaseView;


public class HomePresenter extends BasePresenter {

    public static HomePresenter newInstance(IHomeBaseView contentView) {
        return new HomePresenter(contentView);
    }

    IHomeBaseView contentView;


    public HomePresenter(IHomeBaseView contentView) {
        this.contentView = contentView;

    }
    public void init(){

    }

}
