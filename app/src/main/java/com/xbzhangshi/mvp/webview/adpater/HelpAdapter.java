package com.xbzhangshi.mvp.webview.adpater;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.webview.bean.HelpListBean;

import java.util.List;

public class HelpAdapter extends BaseQuickAdapter<HelpListBean.HplistBean, BaseViewHolder> {
    public HelpAdapter(@Nullable List<HelpListBean.HplistBean> data) {
        super(R.layout.help_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HelpListBean.HplistBean item) {
        helper.setText(R.id.text, item.getTitle());
    }
}
