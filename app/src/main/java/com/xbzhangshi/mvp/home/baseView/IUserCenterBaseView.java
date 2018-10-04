package com.xbzhangshi.mvp.home.baseView;

import android.widget.LinearLayout;

import com.xbzhangshi.mvp.home.bean.USerCenterOnOffBean;
import com.xbzhangshi.mvp.home.bean.VIPBean;

import java.util.List;

public interface IUserCenterBaseView {


   public  void setUserinfo(String name);
    public  void  LogoutSuccess();
    public  void  LogoutonError();
    public  void updateBalance(String msg);
    public  void upVip(VIPBean bean);
    public  void upMsgCount(String msg);
    public  void setConfig(List<USerCenterOnOffBean> list);
    public  void error( );
}
