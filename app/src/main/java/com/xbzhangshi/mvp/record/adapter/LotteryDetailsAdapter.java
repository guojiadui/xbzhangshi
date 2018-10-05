package com.xbzhangshi.mvp.record.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;

import butterknife.BindView;

public class LotteryDetailsAdapter extends RecyclerView.Adapter<LotteryDetailsAdapter.DetailHoder> {

    ResultLotteryRecordBean.PageBean.ListBean listBean;
    Context context;


    public LotteryDetailsAdapter(Context context, ResultLotteryRecordBean.PageBean.ListBean listBean) {
        this.context = context;
        this.listBean = listBean;
    }

    @NonNull
    @Override
    public DetailHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.lottery_details_adapter_item, viewGroup, false);
        DetailHoder detailHoder = new DetailHoder(view);
        return detailHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHoder hoder, int i) {
        switch (i) {
            case 0:
                hoder.title1.setText("账号");
                hoder.content1.setText(listBean.getAccount());
                hoder.layout2.setVisibility(View.GONE);
                break;
            case 1:
                hoder.title1.setText("下注时间");
                hoder.content1.setText(listBean.getCreateTime());
                hoder.layout2.setVisibility(View.GONE);
                break;
            case 2:
                hoder.title1.setText("彩种");
                hoder.content1.setText(listBean.getLotName());
                hoder.layout2.setVisibility(View.GONE);
                break;
            case 3:
                hoder.title1.setText("期号");
                hoder.content1.setText(listBean.getQiHao());
                hoder.layout2.setVisibility(View.GONE);
                break;
            case 4:
                hoder.title1.setText("玩法");
                hoder.content1.setText(listBean.getPlayName());
                hoder.layout2.setVisibility(View.GONE);
                break;
            case 5:
                hoder.title1.setText("开奖号码");
                hoder.content1.setText(listBean.getLotteryHaoMa());
                hoder.layout2.setVisibility(View.GONE);
                break;
            case 6:
                hoder.title1.setText("状态");
                if (listBean.getStatus() == 1) {
                    hoder.content1.setText(  "未开奖");
                } else if (listBean.getStatus() == 2) {
                    hoder.content1.setText( "已中奖");
                } else if (listBean.getStatus() == 3) {
                    hoder.content1.setText(  "未中奖");
                } else if (listBean.getStatus() == 4) {
                    hoder.content1.setText( "已撤单");
                }
                hoder.title2.setText("盈亏");
                double s =  listBean.getWinMoney()-listBean.getBuyMoney();
                hoder.content2.setText(s + "");
                break;
            case 7:
                hoder.title1.setText("投注号码");
                hoder.content1.setText(listBean.getHaoMa());
                hoder.title2.setText("单注金额");
                hoder.content2.setText(listBean.getBuyMoney()+"");
                break;
            case 8:
                hoder.title1.setText("投注注数");
                hoder.content1.setText(listBean.getBuyZhuShu()+"");
                hoder.title2.setText("倍数");
                hoder.content2.setText(listBean.getMultiple()+"");
                break;
            case 9:
                hoder.title1.setText("投注总额");
                hoder.content1.setText(listBean.getBuyMoney()+"");
                hoder.title2.setText("赔率");
                hoder.content2.setText(listBean.getOdds()+"");
                break;
            case 10:
                hoder.title1.setText("中奖注数");
                hoder.content1.setText(listBean.getWinZhuShu()+"");
                hoder.title2.setText("中奖金额");
                hoder.content2.setText(listBean.getWinMoney()+"");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 11;
    }

    public static class DetailHoder extends RecyclerView.ViewHolder {
        TextView title1;
        TextView content1;
        TextView title2;
        TextView content2;
        LinearLayout layout2;

        public DetailHoder(@NonNull View itemView) {
            super(itemView);
            title1 = itemView.findViewById(R.id.title1);
            content1 = itemView.findViewById(R.id.content1);
            title2 = itemView.findViewById(R.id.title2);
            content2 = itemView.findViewById(R.id.content2);
            layout2 = itemView.findViewById(R.id.layout2);
        }
    }
}
