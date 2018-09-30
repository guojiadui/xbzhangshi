package com.xbzhangshi.mvp.record.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.record.bean.ThreeLotteryRecordBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ThreeRecordAdapter extends BaseQuickAdapter<ThreeLotteryRecordBean.RowsBean, BaseViewHolder> {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DecimalFormat df = new DecimalFormat("#0.00");

    public ThreeRecordAdapter(@Nullable List<ThreeLotteryRecordBean.RowsBean> data) {
        super(R.layout.three_lottery_record_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ThreeLotteryRecordBean.RowsBean item) {
        helper.setText(R.id.lottery_type, "名称:" + item.getGameType());
        String s = "投注金额:" + "<font color=\"red\">" + item.getBettingMoney() + "</font>元";
        TextView t = helper.getView(R.id.sum);
        t.setText(Html.fromHtml(s));
        helper.setText(R.id.time, "时间:" + formatter.format(item.getCreateDatetime()));
        String m = df.format(item.getWinMoney());
        helper.setText(R.id.state, "盈亏:" + m);
    }
}
