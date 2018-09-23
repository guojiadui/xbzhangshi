package com.xbzhangshi.mvp.login.BaseView;

import android.graphics.Bitmap;

public interface ILoginView {


    /**
     * 初始化记住密码的钩
     */

    public void setRemmberPwd(Boolean is);

    /**
     * 初始化账号密码
     *
     * @param name
     * @param pwd
     */
    public void setUserInfo(String name, String pwd);

    /**
     * 登陆成功
     */
    public void loginSuccess();

    /**
     * 登录出错
     */
    public void LoginonError(String msg );
    /**
     * 显示验证码
     */
    public  void  showCode(Bitmap bitmap);
}
