package com.xbzhangshi.mvp.record.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.record.bean.AcountChangeRecordBean;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcountChangeAdapter extends BaseQuickAdapter<AcountChangeRecordBean.ListBean, BaseViewHolder> {

    Map<String, String> keys;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public AcountChangeAdapter(@Nullable List<AcountChangeRecordBean.ListBean> data, Map<String, String> keys) {
        super(R.layout.acount_change_adapter_item, data);
        this.keys = keys;
    }

    @Override
    protected void convert(BaseViewHolder helper, AcountChangeRecordBean.ListBean item) {
        if (keys.containsKey(item.getType())) {
            String s = keys.get(item.getType());
            helper.setText(R.id.lottery_type, "类型:  " + s);
        }

        TextView number = helper.getView(R.id.lottery_order_number);
        String num = "订单号:&nbsp;  " + "<font color=\"black\">" + item.getOrderId() + "</font>";
        number.setText(Html.fromHtml(num));
        TextView before = helper.getView(R.id.sum_change_before);
        String BeforeMoney = "变动前金额: &nbsp; " + "<font color=#0894ec>" + item.getBeforeMoney() + "</font>元";
        before.setText(Html.fromHtml(BeforeMoney));
        TextView change = helper.getView(R.id.change_sum);
        String money = "变动金额:&nbsp;  " + "<font color=\"red\">" + item.getMoney() + "</font>元";
        change.setText(Html.fromHtml(money));
        TextView late = helper.getView(R.id.sum_chage_late);
        String AfterMoney = "变动后金额:&nbsp;  " + "<font color=#4cd964>" + item.getAfterMoney() + "</font>元";
        late.setText(Html.fromHtml(AfterMoney));

        String dateString = formatter.format(item.getBizDatetime());

        helper.setText(R.id.change_time, "变动时间:  " + dateString);
        helper.setText(R.id.remarks, "备注:  " + item.getRemark());

    }
}
