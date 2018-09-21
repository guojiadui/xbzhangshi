package com.xbzhangshi.mvp.home.presenter;

import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IHomeBaseView;
import com.xbzhangshi.mvp.home.baseView.IUserCenterBaseView;
import com.xbzhangshi.single.UserInfo;

public class UserCenterPresenter extends BasePresenter {

    public static UserCenterPresenter newInstance(IUserCenterBaseView contentView) {
        return new UserCenterPresenter(contentView);
    }

    IUserCenterBaseView contentView;


    public UserCenterPresenter(IUserCenterBaseView contentView) {
        this.contentView = contentView;

    }





}
