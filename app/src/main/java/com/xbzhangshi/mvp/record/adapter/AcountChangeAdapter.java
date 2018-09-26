package com.xbzhangshi.mvp.record.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;

import java.util.List;

public class AcountChangeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public AcountChangeAdapter( @Nullable List<String> data) {
        super(R.layout.acount_change_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        String s = "s";
        helper.setText(R.id.lottery_type, "类型:" + s);

        TextView number = helper.getView(R.id.lottery_order_number);
        s = "订单号:" + "< color=\"black\">" + s + "</font>";
        number.setText(Html.fromHtml(s));
        TextView before = helper.getView(R.id.sum_change_before);
        s = "变动前金额:" + "< color=\"blue\">" + s + "</font>元";
        before.setText(Html.fromHtml(s));
        TextView change = helper.getView(R.id.change_sum);
        s = "变动金额:" + "< color=\"red\">" + s + "</font>元";
        change.setText(Html.fromHtml(s));
        TextView late = helper.getView(R.id.sum_chage_late);
        s = "变动后金额:" + "< color=\"green\">" + s + "</font>元";
        late.setText(Html.fromHtml(s));
        TextView time = helper.getView(R.id.change_time);
        s = "变动时间:" + "< color=\"0xff3d4145\">" + s + "</font>元";
        time.setText(Html.fromHtml(s));

        helper.setText(R.id.remarks, s);

    }
}
