package com.xbzhangshi.mvp.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.home.bean.OpenPrizeBean;

import java.util.List;

public class OpenPrizeAdapter extends BaseQuickAdapter<OpenPrizeBean, BaseViewHolder> {

    int[] colors = {0xffff00, 0x0089ff, 0x4d4d4d, 0xff7300, 0x81ffff, 0x5200ff, 0xb8b8b8, 0xff0000, 0x760000, 0x28c200};
    Context context;
    public OpenPrizeAdapter(Context context,@Nullable List<OpenPrizeBean> data) {
        super(R.layout.open_prize_adapter_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OpenPrizeBean item) {
        LinearLayout content = helper.getView(R.id.content);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        content.removeAllViews();
       for(int i=0;i<5;i++){
           TextView textView = new TextView(context);
           textView.setTextColor(Color.WHITE);
           textView.setBackgroundResource(R.drawable.bg_msg_bubble);
           textView.setText("39");
           textView.setPadding(12,4,12,4);
           content.addView(textView);
       }
    }
}
