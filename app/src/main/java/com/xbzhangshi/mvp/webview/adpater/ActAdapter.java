package com.xbzhangshi.mvp.webview.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.webview.bean.ActInfoBean;

import java.util.List;

public class ActAdapter extends BaseQuickAdapter<ActInfoBean.ContentBean, BaseViewHolder> {
    Context context;

    public ActAdapter(Context context, @Nullable List<ActInfoBean.ContentBean> data) {
        super(R.layout.act_adapter_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ActInfoBean.ContentBean item) {
        if (!TextUtils.isEmpty(item.getTitleImgUrl())) {
            Glide.with(context).load(item.getTitleImgUrl()).into((ImageView) helper.getView(R.id.img));
        } else {
            ImageView imageView = helper.getView(R.id.img);
            imageView.setImageResource(R.mipmap.help_default);
        }
    }
}
