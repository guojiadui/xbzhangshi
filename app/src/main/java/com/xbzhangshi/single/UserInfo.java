package com.xbzhangshi.single;

import android.content.Context;
import android.text.TextUtils;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.login.bean.LoginBean;
import com.xbzhangshi.mvp.login.bean.LoginUserInfoBean;

public class UserInfo {
    public static UserInfo userInfo;

    public static UserInfo getInstance() {
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    private UserInfo() {

    }

    public boolean isLogin = false;
    public String mUsername;
    public String mPassword;
    private LoginUserInfoBean loginUserInfoBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    private LoginBean loginBean;


    public boolean isLogin() {
        return isLogin;
    }



    /**
     * 登出
     */
    public void logout() {
        isLogin = false;
        mUsername = "";
        mPassword = "";
        loginUserInfoBean = null;
    }

    /**
     * 登录
     */
    public  <T> Object login(Context context, Class<T> c,String name, String password, String code, OkGoCallback<T> stringCallback) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("username", name);
        httpParams.put("password", password);
        if (!TextUtils.isEmpty(code)) {
            httpParams.put("verifyCode", code);
        }
        return HttpManager.postObject(context, c,Url.BASE_URL + Url.login, httpParams, stringCallback);
    }

    /**
     * 获取用户信息
     *
     * @param
     */
    public <T> Object  getUserInfo(Context context,Class<T> t, OkGoCallback<T> okGoCallback) {
        return HttpManager.getObject(context, t,
                Url.BASE_URL + Url.accountInfo, null, okGoCallback);
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public LoginUserInfoBean getLoginUserInfoBean() {
        return loginUserInfoBean;
    }

    public void setLoginUserInfoBean(LoginUserInfoBean loginUserInfoBean) {
        this.loginUserInfoBean = loginUserInfoBean;
    }
}
