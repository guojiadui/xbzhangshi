package com.xbzhangshi.mvp.home.baseView;

public interface IUserCenterBaseView {


   public  void setUserinfo(String name);
    public  void  LogoutSuccess();
    public  void  LogoutonError();
    public  void updateBalance(String msg);
    public  void upVip(String msg);
    public  void upMsgCount(String msg);
}