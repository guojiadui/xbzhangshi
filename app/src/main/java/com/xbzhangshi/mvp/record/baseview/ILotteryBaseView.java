package com.xbzhangshi.mvp.record.baseview;

import com.xbzhangshi.mvp.record.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;

import java.util.List;

public interface ILotteryBaseView {
    public void successType(List<LotteryRecordBean.ContentBean> contentBeans);
    public void successMore(List<ResultLotteryRecordBean.PageBean.ListBean> listBeans,boolean ismore);
    public void errorMore(String msg);
    public void emptyMore(boolean ismore);
    public void successData(List<ResultLotteryRecordBean.PageBean.ListBean> listBeans,boolean ismore);
    public void error(String msg);
    public void empty();
    public void setprofit(double sbetting,double sprize,double sprofit);
    public  void  cancalSuccess(String id);
    public  void  cancalError(String msg);
}
