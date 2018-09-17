package com.xbzhangshi.mvp.home.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.mvp.home.bean.BesidesLotteryBean;
import com.xbzhangshi.mvp.home.bean.BettingTypeBean;

import java.util.List;

public class BettingTypeAdapter extends BaseQuickAdapter<BesidesLotteryBean.ContentBean, BaseViewHolder> {
    Context context;

    public BettingTypeAdapter(Context context, @Nullable List<BesidesLotteryBean.ContentBean> data) {
        super(R.layout.bettting_type_item_layout, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BesidesLotteryBean.ContentBean item) {
        LogUtils.e("TAG",URL.BASE_PNG_URL + item.getImgUrl());
        Glide.with(context)
                .load(URL.BASE_PNG_URL + item.getImgUrl())
                .into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.name, item.getTitle());
    }
}
