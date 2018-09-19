package com.xbzhangshi.mvp.home.baseView;

import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;

import java.util.List;

public interface IPurchaseItemView {

    public void onSuccess(List<LoctteryBean.ContentBean> list, boolean mode, int curPosition);
    public void swtih(List<LoctteryBean.ContentBean> list,boolean mode,int curPosition);

    public void onEmpty();

    public void onError();
}
