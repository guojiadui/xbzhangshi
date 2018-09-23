package com.xbzhangshi.mvp.login.BaseView;

import android.graphics.Bitmap;

import com.xbzhangshi.mvp.login.bean.RegisterItemBean;

import java.util.List;

public interface IRegisterView {


    public void setRegconfig(List<RegisterItemBean.ContentBean> contentBeans);

    public void setRegconfigError();


    /**
     * 注册成功
     */
    public void registerSuccess();

    /**
     * 注册失败
     *
     * @param msg
     */
    public void registerError(String msg);


    /**
     * 登陆成功
     */
    public void loginSuccess();

    /**
     * 登录出错
     */
    public void LoginonError(String msg);

    /**
     * 更新验证码
     * @param
     */
    public void updateCode( );
}
