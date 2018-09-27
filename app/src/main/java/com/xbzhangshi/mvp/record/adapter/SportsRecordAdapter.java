package com.xbzhangshi.mvp.record.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;

import java.util.List;

/**
 * 体育记录
 */
public class SportsRecordAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public SportsRecordAdapter(@Nullable List<String> data) {
        super(R.layout.sports_record_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
