package com.xbzhangshi.mvp.usercenter.BaseView;

import com.xbzhangshi.mvp.usercenter.bean.MsgBean;
import com.xbzhangshi.mvp.usercenter.presener.MessageListPresenter;

import java.util.List;

public interface IMesssageListBaseView {


    public  void success(MessageListPresenter presenter,List<MsgBean.ContentBean.DatasBean> contentBeans);
    public  void empty( );
    public  void Error(String msg);
    public  void ishowEditLayout(boolean isShow);
    public  void readSuccess(String s);
    public  void readError(String  msg);
    public  void delSuccess(String s);
    public  void delError(String  msg);
}
