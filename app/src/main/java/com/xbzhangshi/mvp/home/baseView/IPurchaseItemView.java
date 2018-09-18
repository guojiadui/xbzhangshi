package com.xbzhangshi.mvp.home.baseView;

import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;

import java.util.List;

public interface IPurchaseItemView {

    public void onSuccess(List<LotterysCountDownBean.ContentBean> list,boolean mode);
    public void swtih(List<LotterysCountDownBean.ContentBean> list,boolean mode);

    public void onEmpty();

    public void onError();
}
