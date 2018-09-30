package com.xbzhangshi.mvp.record.baseview;

import com.xbzhangshi.mvp.record.bean.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ThreeLotteryRecordBean;

import java.util.List;

public interface IThreeLotteryBaseView {

   /* public void successMore(List<ThreeLotteryRecordBean.RowsBean> listBeans, boolean ismore);
    public void errorMore(String msg);
    public void emptyMore(boolean ismore);*/
    public void successData(List<ThreeLotteryRecordBean.RowsBean> listBeans );
    public void error(String msg);
    public void empty();
    public void setprofit(double sbetting, double sprize, double sprofit);
  /*  public  void  cancalSuccess(String id);
    public  void  cancalError(String msg);*/
}
