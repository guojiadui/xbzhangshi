package com.xbzhangshi.mvp.home.baseView;

import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;

import java.util.List;

public interface IOpenPrizeBaseView {
    public void onSuccess(List<LotterysCountDownBean.ContentBean> contentBeans);

    public void onEmpty();

    public void onError();
}
