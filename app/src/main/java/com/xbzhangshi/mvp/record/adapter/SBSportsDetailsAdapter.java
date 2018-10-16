package com.xbzhangshi.mvp.record.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.record.bean.BSMatchBean;
import com.xbzhangshi.mvp.record.bean.HGSportsRecordBean;
import com.xbzhangshi.mvp.record.bean.MatchBean;
import com.xbzhangshi.mvp.record.bean.SBSportsRecordBean;

import java.text.SimpleDateFormat;
import java.util.List;

public class SBSportsDetailsAdapter extends RecyclerView.Adapter<SBSportsDetailsAdapter.DetailHoder> {

    SBSportsRecordBean.RowsBean listBean;
    Context context;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public SBSportsDetailsAdapter(Context context, SBSportsRecordBean.RowsBean listBean) {
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
                hoder.title1.setText("会员");
                hoder.content1.setText(listBean.getAccount());
                hoder.title2.setText("下注金额");
                hoder.content2.setText(listBean.getBettingMoney() + "");
                break;
            case 1:
                hoder.title1.setText("下注时间");
                String dateString = formatter.format(listBean.getCreateDatetime());
                hoder.content1.setText(dateString);
                hoder.title2.setText("球类");
                hoder.content2.setText(listBean.getGameName());
                break;
            case 2:
                hoder.title1.setText("类型");
                hoder.content1.setText(listBean.getPlayName());
                hoder.title2.setText("盘");
               /* if ("H".equals(listBean.getPlate())) {
                    hoder.content2.setText("亚洲盘");
                } else if ("I".equals(listBean.getPlate())) {
                    hoder.content2.setText("印尼盘");
                } else if ("E".equals(listBean.getPlate())) {
                    hoder.content2.setText("欧洲盘");
                } else if ("M".equals(listBean.getPlate())) {
                    hoder.content2.setText("马来西亚盘");
                }*/
                break;
            case 3:
                hoder.title1.setText("提交状态");
                hoder.title1.setText("提交状态");
               /* if (1 == listBean.getBettingStatus()) {
                    hoder.content1.setText("待确认");
                } else if (2 == listBean.getBettingStatus()) {
                    hoder.content1.setText("已确认");
                } else if (3 == listBean.getBettingStatus()) {
                    hoder.content1.setText("已取消");
                }*/
                hoder.title2.setText("结算状态");
                switch (listBean.getResStatus()) {
                    case 3:
                        hoder.content2.setText( "未中奖");
                        break;
                    case 1:
                        hoder.content2.setText( "等待开奖");
                        break;
                    case 4:
                        hoder.content2.setText( "撤单");
                        break;
                    case 5:
                        hoder.content2.setText( "派奖回滚成功");
                        break;
                    case 2:
                        hoder.content2.setText( "已中奖");
                        break;
                    case 6:
                        hoder.content2.setText( "回滚异常");
                        break;
                    case 7:
                        hoder.content2.setText( "开奖异常");
                        break;
                }
                break;
            case 4:
                hoder.title1.setText("派彩金额");
                hoder.content1.setText(listBean.getWinMoney()+"");
                hoder.layout2.setVisibility(View.GONE);
                break;
            case 5:
                hoder.title1.setText("赛事");
                hoder.content1.setVisibility(View.GONE);

                    if (!TextUtils.isEmpty(listBean.getParlayData())) {
                        List<BSMatchBean> matchBeans = null;
                        try {
                            matchBeans = JSON.parseArray(listBean.getParlayData(), BSMatchBean.class);
                        } catch (Exception e) {

                        }
                        if (matchBeans != null) {
                            for (BSMatchBean matchBean : matchBeans) {
                                TextView textView1 = new TextView(context);
                                String s = "<br>" + matchBean.getLeagueName() + "<br><br>" +
                                        matchBean.getHomeTeam() + "&nbsp;vs&nbsp;" + matchBean.getAwayTeam() + "<br><br>" +
                                        "<font color=\"red\">" + matchBean.getPlayName() + "</font>@<font color=\"red\">"
                                        + matchBean.getOdds() + "</font><br>" + "";
                                textView1.setText(Html.fromHtml(s));
                                textView1.setGravity(Gravity.CENTER);
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                params.gravity = Gravity.CENTER_HORIZONTAL;
                                textView1.setLayoutParams(params);

                                View view = new View(context);
                                view.setBackgroundColor(0xfff2f2f0);
                                LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
                                view.setLayoutParams(param2);
                                hoder.content1Layout.addView(textView1);
                                hoder.content1Layout.addView(view);

                            }
                        }
                    } else {
                        try {

                            TextView textView1 = new TextView(context);
                            String s = "<br>" + listBean.getLeague() + "<br><br>" +
                                    listBean.getHomeTeam() + "&nbsp;vs&nbsp;" + listBean.getAwayTeam() + "<br><br>" +
                                    "<font color=\"red\">" + listBean.getInfo() + "</font>@<font color=\"red\">" +
                                    listBean.getOdds() + "</font><br>" + "";
                            textView1.setText(Html.fromHtml(s));
                            textView1.setGravity(Gravity.CENTER);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.gravity = Gravity.CENTER_HORIZONTAL;
                            textView1.setLayoutParams(params);
                            hoder.content1Layout.addView(textView1);
                        } catch (Exception e) {

                        }


                    }

                hoder.layout2.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class DetailHoder extends RecyclerView.ViewHolder {
        TextView title1;
        TextView content1;
        TextView title2;
        TextView content2;
        LinearLayout layout2;
        LinearLayout content1Layout;

        public DetailHoder(@NonNull View itemView) {
            super(itemView);
            title1 = itemView.findViewById(R.id.title1);
            content1 = itemView.findViewById(R.id.content1);
            title2 = itemView.findViewById(R.id.title2);
            content2 = itemView.findViewById(R.id.content2);
            layout2 = itemView.findViewById(R.id.layout2);
            content1Layout = itemView.findViewById(R.id.content1_layout);
        }
    }
}
