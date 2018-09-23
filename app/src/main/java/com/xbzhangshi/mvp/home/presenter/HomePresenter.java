package com.xbzhangshi.mvp.home.presenter;

import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IHomeBaseView;
import com.xbzhangshi.single.UserInfo;


public class HomePresenter extends BasePresenter {

    public static HomePresenter newInstance(IHomeBaseView contentView) {
        return new HomePresenter(contentView);
    }

    IHomeBaseView contentView;


    public HomePresenter(IHomeBaseView contentView) {
        this.contentView = contentView;

    }

    public boolean isLogin() {
        return UserInfo.getInstance().isLogin();
    }




}
