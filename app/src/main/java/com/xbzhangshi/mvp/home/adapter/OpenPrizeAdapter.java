package com.xbzhangshi.mvp.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.home.bean.OpenPrizeBean;

import java.util.List;

public class OpenPrizeAdapter extends BaseQuickAdapter<OpenPrizeBean.DataBean, BaseViewHolder> {

    int[] colors = {0xffff00, 0x0089ff, 0x4d4d4d, 0xff7300, 0x81ffff, 0x5200ff, 0xb8b8b8, 0xff0000, 0x760000, 0x28c200};
    Context context;
    public OpenPrizeAdapter(Context context, @Nullable List<OpenPrizeBean.DataBean>   data) {
        super(R.layout.open_prize_adapter_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OpenPrizeBean.DataBean item) {
        String url = Url.lottert_png + item.getLotCode() + ".png";
        Glide.with(context).load(url).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.name,item.getLotName());
        helper.setText(R.id.stage,"第"+item.getQihao()+"期");
        setHaoma(helper,item);
    }


    public void setHaoma(BaseViewHolder helper,OpenPrizeBean.DataBean item) {
        if (!TextUtils.isEmpty(item.getHaoma())) {
            if (item.getHaoma().contains("?")) {
                helper.setVisible(R.id.haoma, true);
                LinearLayout layout = helper.getView(R.id.haoma_layout);
                layout.setVisibility(View.INVISIBLE);
                layout.removeAllViews();
            } else {
                helper.setVisible(R.id.haoma, false);
                LinearLayout layout = helper.getView(R.id.haoma_layout);
                layout.setVisibility(View.VISIBLE);
                String[] strings = item.getHaoma().split(",");
                layout.removeAllViews();
                for (String s : strings) {
                    TextView textView = new TextView(context);
                    textView.setTextColor(Color.WHITE);
                    textView.setBackgroundResource(R.drawable.bg_msg_bubble);
                    if (s.length() == 1) {
                        textView.setText("0" + s);
                    } else {
                        textView.setText(s);
                    }

                    textView.setPadding(12, 4, 12, 4);
                    layout.addView(textView);
                }
            }
        } else {
            helper.setVisible(R.id.haoma, false);
            helper.setVisible(R.id.haoma_layout, false);
        }
    }
}
