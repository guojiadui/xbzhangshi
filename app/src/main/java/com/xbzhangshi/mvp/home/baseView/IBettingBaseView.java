package com.xbzhangshi.mvp.home.baseView;

import com.xbzhangshi.mvp.base.IBaseView;

public interface IBettingBaseView   {

    /**
     * 登陆成功
     */
    public void loginSuccess();

    /**
     * 登录出错
     */
    public void LoginonError(String msg);
    /**
     * 公告
     */
    public  void setNotice(String content,boolean isShow);
    /**
     * 更新余额
     * @param msg
     */
    public void  updateBalance(String msg);
    /**
     * 签到大转盘
     */
    public  void  setSwith(boolean dzp,boolean qd);
}
