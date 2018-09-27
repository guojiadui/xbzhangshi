package com.xbzhangshi.mvp.record.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;

import java.util.List;

public class LotterytRecordAdapter extends BaseQuickAdapter<ResultLotteryRecordBean.PageBean.ListBean, BaseViewHolder> {

    public LotterytRecordAdapter(@Nullable List<ResultLotteryRecordBean.PageBean.ListBean> data) {
        super(R.layout.lottery_record_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ResultLotteryRecordBean.PageBean.ListBean item) {
        helper.setText(R.id.lottery_type, "彩种:" + item.getLotName());
        String s = "投注金额:" + "<font color=\"red\">" + item.getBuyMoney() + "</font>元";
        TextView t= helper.getView(R.id.sum);
        t.setText(Html.fromHtml(s));
        helper.setText(R.id.number,"号码:"+item.getHaoMa());
        if(TextUtils.isEmpty(item.getOpenTime())){
            helper.setText(R.id.time,"时间:");
        }else {
            helper.setText(R.id.time,"时间:"+item.getOpenTime());
        }

        //	  status 1 未开奖 2 已中奖 3 未中奖 4撤单
        if(item.getStatus()==1){
            helper.setText(R.id.state,"未开奖");
            helper.setTextColor(R.id.state,0xff408cf2);
            helper.getView(R.id.cancel).setVisibility(View.VISIBLE);

        }else  if(item.getStatus()==2){
            helper.setText(R.id.state,"已中奖");
            helper.setTextColor(R.id.state,0xff00aa51);
            helper.getView(R.id.cancel).setVisibility(View.GONE);
        }else  if(item.getStatus()==3){
            helper.setText(R.id.state,"未中奖");
            helper.setTextColor(R.id.state,0xffff2525);
            helper.getView(R.id.cancel).setVisibility(View.GONE);
        }else if(item.getStatus()==4){
            helper.setText(R.id.state,"已撤单");
            helper.setTextColor(R.id.state,0xff999999);
            helper.getView(R.id.cancel).setVisibility(View.GONE);
        }
    }
}
