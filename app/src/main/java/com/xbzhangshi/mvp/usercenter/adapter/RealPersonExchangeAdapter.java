package com.xbzhangshi.mvp.usercenter.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.usercenter.bean.RealExhangeBean;

import java.util.List;

public class RealPersonExchangeAdapter extends BaseQuickAdapter<RealExhangeBean.ContentBean, BaseViewHolder> {
    Context context;

    public RealPersonExchangeAdapter(Context context, @Nullable List<RealExhangeBean.ContentBean> data) {
        super(R.layout.real_person_exchange_adapter_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RealExhangeBean.ContentBean item) {
        helper.addOnClickListener(R.id.input).addOnClickListener(R.id.out)
                .addOnClickListener(R.id.refresh).addOnClickListener(R.id.enter_game);
        String url = Url.realpng + item.getPlayCode() + ".png";
        Glide.with(context)
                .load(url)
                .into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.name, item.getTitle());
        helper.setText(R.id.balance_item, item.getBalance()+"");
        if (item.getBalance()>=0) {
            helper.setText(R.id.balance_item, item.getBalance()+"");
        } else {
            helper.setText(R.id.balance_item, "点击刷新");
        }
    }
}
