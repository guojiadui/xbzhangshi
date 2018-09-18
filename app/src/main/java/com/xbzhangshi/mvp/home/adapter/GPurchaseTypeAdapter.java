package com.xbzhangshi.mvp.home.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;
import com.xbzhangshi.mvp.home.bean.PurchaseTypeBean;
import com.xbzhangshi.view.CustomDigitalClock;

import java.util.List;

public class GPurchaseTypeAdapter extends BaseQuickAdapter<LotterysCountDownBean.ContentBean,BaseViewHolder> {
    Context context;
      int curPosition =0;
    public GPurchaseTypeAdapter( Context context,  @Nullable List<LotterysCountDownBean.ContentBean> data,int curPosition) {
        super(R.layout.g_purchase_type_adapter_item, data);
        this.context = context;
        this.curPosition = curPosition;
    }

    @Override
    protected void convert(BaseViewHolder helper, LotterysCountDownBean.ContentBean item) {
        String url = URL.lottert_png + item.getLotCode() + ".png";
        Glide.with(context).load(url).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.name, item.getLotName());

        CustomDigitalClock customDigitalClock = helper.getView(R.id.time);
        customDigitalClock.setTag(curPosition);
        customDigitalClock.setContentBean(item);
    }
}
