package com.xbzhangshi.mvp.record.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;

import java.util.List;

public class LotterytRecordAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public LotterytRecordAdapter(  @Nullable List<String> data) {
        super(R.layout.lottery_record_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
