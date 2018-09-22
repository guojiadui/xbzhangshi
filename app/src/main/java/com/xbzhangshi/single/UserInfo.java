package com.xbzhangshi.single;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.login.bean.LoginBean;
import com.xbzhangshi.mvp.login.bean.LoginUserInfoBean;

import java.util.List;

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
    public LoginUserInfoBean loginUserInfoBean;


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
    public Object login(Context context, String name, String password, StringCallback stringCallback) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("username", name);
        httpParams.put("password", password);
        return HttpManager.post(context, URL.BASE_URL + URL.login, httpParams, stringCallback);
    }

    /**
     * 获取用户信息
     *
     * @param
     */
    public Object getUserInfo(Context context, StringCallback stringCallback) {
        return HttpManager.get(context, URL.BASE_URL + URL.accountInfo, null, stringCallback);
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
