package com.xbzhangshi.mvp.usercenter.BaseView;

import com.xbzhangshi.mvp.usercenter.bean.MsgBean;
import com.xbzhangshi.mvp.usercenter.presenter.MessageListPresenter;

import java.util.List;

public interface IMesssageListBaseView {


    public  void success(MessageListPresenter presenter,List<MsgBean.ListBean> contentBeans,boolean isMore);
    public  void empty(boolean isMore );
    public  void Error(String msg);
    public  void successMore(MessageListPresenter presenter,List<MsgBean.ListBean> contentBeans,boolean isMore);
    public  void emptyMore( boolean isMore);
    public  void ErrorMore(String msg);

    public  void ishowEditLayout(boolean isShow);
    public  void readSuccess(String s);
    public  void readError(String  msg);
    public  void delSuccess(String s);
    public  void delError(String  msg);
}
