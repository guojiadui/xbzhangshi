package com.xbzhangshi.mvp.usercenter.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;

import java.util.List;

public class RealPersonExchangeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public RealPersonExchangeAdapter(  @Nullable List<String> data) {
        super(R.layout.real_person_exchange_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
