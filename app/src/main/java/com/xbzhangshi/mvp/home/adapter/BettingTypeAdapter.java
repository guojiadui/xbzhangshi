package com.xbzhangshi.mvp.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.home.bean.BettingTypeBean;

import java.util.List;

public class BettingTypeAdapter extends BaseQuickAdapter<BettingTypeBean,BaseViewHolder> {
    public BettingTypeAdapter(  @Nullable List<BettingTypeBean> data) {
        super(R.layout.bettting_type_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BettingTypeBean item) {

    }
}
