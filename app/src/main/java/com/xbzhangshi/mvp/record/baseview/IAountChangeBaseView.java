package com.xbzhangshi.mvp.record.baseview;

import com.xbzhangshi.mvp.record.bean.AcountChangeRecordBean;
import com.xbzhangshi.mvp.record.bean.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IAountChangeBaseView {

    public  void  setInitSearch(List<String> keys);
    public void successMore(List<AcountChangeRecordBean.ListBean> listBeans, boolean ismore);
    public void errorMore(String msg);
    public void emptyMore(boolean ismore);
    public void successData(List<AcountChangeRecordBean.ListBean> listBeans, Map<String,String> keys, boolean ismore);
    public void error(String msg);
    public void empty();

    ;
}
