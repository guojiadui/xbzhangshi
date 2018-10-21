package com.xbzhangshi.mvp.usercenter.BaseView;

import com.xbzhangshi.mvp.usercenter.bean.RealExhangeBean;

import java.util.List;

public interface IRealPersonExchangeBaseView {

    public void updateBalance(String balance);

    public void success(List<RealExhangeBean.ContentBean> list);

    public void empty();

    public void error(String msg);

    public void transMoneySuccess();

    public void transMoneyError(String msg);

    public void updateBalanceItem(String type, double balance);

    public void updateBalanceError(String msg);
}
