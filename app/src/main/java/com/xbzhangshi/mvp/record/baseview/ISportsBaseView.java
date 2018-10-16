package com.xbzhangshi.mvp.record.baseview;

import com.xbzhangshi.mvp.record.bean.SBSportsRecordBean;
import com.xbzhangshi.mvp.record.bean.HGSportsRecordBean;

import java.util.List;

public interface ISportsBaseView {

    public void HGsuccess (List<HGSportsRecordBean.RowsBean> listBeans );
    public void BSsuccess (List<SBSportsRecordBean.RowsBean> listBeans );
    public void error(String msg);
    public void empty();
    public void setprofit(double sbetting, double sprize, double sprofit);

}
