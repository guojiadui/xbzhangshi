package com.xbzhangshi.mvp.record.baseview;

import com.xbzhangshi.mvp.record.bean.BSSportsRecordBean;
import com.xbzhangshi.mvp.record.bean.HGSportsRecordBean;

import java.util.List;

public interface ISportsBaseView {

    public void HGsuccess (List<HGSportsRecordBean.RowsBean> listBeans );
    public void BSsuccess (List<BSSportsRecordBean.AggsDataBean> listBeans );
    public void error(String msg);
    public void empty();
    public void setprofit(double sbetting, double sprize, double sprofit);

}
