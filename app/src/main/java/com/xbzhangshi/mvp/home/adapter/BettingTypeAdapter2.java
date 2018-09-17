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
import com.xbzhangshi.mvp.home.bean.LoctteryBean;

import java.util.List;

public class BettingTypeAdapter2 extends BaseQuickAdapter<LoctteryBean.ContentBean, BaseViewHolder> {
    Context context;

    public BettingTypeAdapter2(Context context, @Nullable List<LoctteryBean.ContentBean> data) {
        super(R.layout.bettting_type_item_layout, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, LoctteryBean.ContentBean item) {
        String url = "http://xbzhanshi.com/mobile/v3/images/lottery/"+item.getCode()+".png";
         Glide.with(context)
                .load(url)
                .into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.name, item.getName());
    }
}
