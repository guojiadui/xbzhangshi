package com.xbzhangshi.mvp.record.baseview;

import com.xbzhangshi.mvp.record.bean.AcountDetailsRecordBean;
import com.xbzhangshi.mvp.record.bean.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;

import java.util.List;

public interface IAcountDetailsBaseView {

    public void successMore(List<AcountDetailsRecordBean.ListBean> listBeans, boolean ismore);
    public void errorMore(String msg);
    public void emptyMore(boolean ismore);
    public void successData(List<AcountDetailsRecordBean.ListBean> listBeans,int type, boolean ismore);
    public void error(String msg);
    public void empty();
    public  void  setTotalMpney(int type,int sum);

   /* public  void  cancalSuccess(String id);
    public  void  cancalError(String msg);*/
}
