package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IHomeBaseView;
import com.xbzhangshi.mvp.login.bean.LoginBean;
import com.xbzhangshi.mvp.login.bean.LoginUserInfoBean;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.util.DES;


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
