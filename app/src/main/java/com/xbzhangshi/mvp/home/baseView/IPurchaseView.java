package com.xbzhangshi.mvp.home.baseView;

import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;

import java.util.List;

public interface IPurchaseView {
    /**
     * 设置查看模式的背景
     *
     * @param mode
     */
    public void setLookModeViewBg(boolean mode);

    public void onSuccess();

    public void onEmpty();

    public void onError();
}
