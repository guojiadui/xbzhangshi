package com.xbzhangshi.mvp.record.baseview;

import com.xbzhangshi.mvp.record.bean.AcountChangeRecordBean;
import com.xbzhangshi.mvp.record.bean.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;

import java.util.HashMap;
import java.util.List;

public interface IAountChangeBaseView {

    public void successMore(List<AcountChangeRecordBean.ListBean> listBeans, boolean ismore);
    public void errorMore(String msg);
    public void emptyMore(boolean ismore);
    public void successData(List<AcountChangeRecordBean.ListBean> listBeans, HashMap<Integer,String> keys, boolean ismore);
    public void error(String msg);
    public void empty();

    ;
}
