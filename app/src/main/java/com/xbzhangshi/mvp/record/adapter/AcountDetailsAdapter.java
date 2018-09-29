package com.xbzhangshi.mvp.record.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.record.bean.AcountDetailsRecordBean;

import java.util.List;

public class AcountDetailsAdapter extends BaseQuickAdapter<AcountDetailsRecordBean.ListBean,BaseViewHolder> {
    public AcountDetailsAdapter(  @Nullable List<AcountDetailsRecordBean.ListBean> data) {
        super(R.layout.acount_details_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AcountDetailsRecordBean.ListBean item) {

    }
}
