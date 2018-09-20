package com.xbzhangshi.mvp.home.baseView;

import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;
import com.xbzhangshi.mvp.home.bean.OpenPrizeBean;

import java.util.List;

public interface IOpenPrizeBaseView {
    public void onSuccess(List<OpenPrizeBean.DataBean> contentBeans);

    public void onEmpty();

    public void onError();
}
