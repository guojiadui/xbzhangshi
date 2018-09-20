package com.xbzhangshi.mvp.details.baseview;

import com.xbzhangshi.mvp.base.IBaseView;
import com.xbzhangshi.mvp.details.bean.OpenPrizeListBean;
import com.xbzhangshi.mvp.home.bean.OpenPrizeBean;

import java.util.List;

public interface IOpenPrizeDetailsBaseView {


    public void onSuccess(List<OpenPrizeListBean.DataBean.ListBean> contentBeans,boolean hasNext);

    public void onEmpty();

    public void onError();

    public void onMOre(List<OpenPrizeListBean.DataBean.ListBean> contentBeans,boolean hasNext);

    public void onMoreEmpty();

    public void onMoreError();

}
