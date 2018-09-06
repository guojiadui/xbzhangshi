package com.xbzhangshi.mvp.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.home.bean.PurchaseTypeBean;

import java.util.List;

public class GPurchaseTypeAdapter extends BaseQuickAdapter<PurchaseTypeBean,BaseViewHolder> {
    public GPurchaseTypeAdapter(  @Nullable List<PurchaseTypeBean> data) {
        super(R.layout.g_purchase_type_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PurchaseTypeBean item) {

    }
}
