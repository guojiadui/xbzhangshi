package com.xbzhangshi.mvp.home.baseView;

import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.bean.LotteryTypeBean;

import java.util.List;

public interface IBettingItemBaseView<T> {



    public  void  onSuccess(List<T> list);
    public  void  onSuccess2(List<LoctteryBean.ContentBean> list);
    public  void  onEmpty();
    public  void  onError();
}
