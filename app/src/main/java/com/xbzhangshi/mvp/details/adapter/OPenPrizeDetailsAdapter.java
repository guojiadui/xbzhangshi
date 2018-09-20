package com.xbzhangshi.mvp.details.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.details.bean.OpenPrizeListBean;
import com.xbzhangshi.mvp.home.bean.BesidesLotteryBean;
import com.xbzhangshi.mvp.home.bean.LoctteryBean;

import java.util.ArrayList;
import java.util.List;

public class OPenPrizeDetailsAdapter extends BaseQuickAdapter<OpenPrizeListBean.DataBean.ListBean, BaseViewHolder> {
    Context context;
    String code;

    public OPenPrizeDetailsAdapter(Context context, @Nullable List<OpenPrizeListBean.DataBean.ListBean> data, String code) {
        super(R.layout.open_prize_details_adapter_item, data);
        this.context = context;
        this.code = code;
    }

    @Override
    protected void convert(BaseViewHolder helper, OpenPrizeListBean.DataBean.ListBean item) {
        helper.setText(R.id.name, "第" + item.getQiHao() + "期");
        helper.setText(R.id.time, item.getDate() + " " + item.getTime());
        setHaoma(helper, item);

    }

    public void setHaoma(BaseViewHolder helper, OpenPrizeListBean.DataBean.ListBean item) {

        if (item.getHaoMaList() != null) {
            if (item.getHaoMaList().get(0).contains("?")) {
                if(helper.getAdapterPosition()==0){
                    helper.setVisible(R.id.content_tip, true);
                    LinearLayout layout = helper.getView(R.id.content);
                    layout.setVisibility(View.INVISIBLE);
                    layout.removeAllViews();
                }else {
                    helper.setVisible(R.id.content_tip, false);
                    helper.setVisible(R.id.content, false);
                }

            } else {
                helper.setVisible(R.id.content_tip, false);
                LinearLayout layout = helper.getView(R.id.content);
                layout.setVisibility(View.VISIBLE);
                layout.removeAllViews();
                switchCase(layout, helper, item);

            }
        } else {
            helper.setVisible(R.id.content_tip, false);
            helper.setVisible(R.id.content, false);
        }

    }

    public void switchCase(LinearLayout layout, BaseViewHolder helper, OpenPrizeListBean.DataBean.ListBean item) {
        List<String> list = new ArrayList<>();
        switch (code) {
            case "BJSC"://北京赛车
            case "XYFT"://幸运飞艇
            case "SFSC"://澳门赛车
                for (String s : item.getHaoMaList()) {
                    list.add(s);
                }
                list.add("=");
                try {
                    int one = Integer.parseInt(item.getHaoMaList().get(0));
                    int two = Integer.parseInt(item.getHaoMaList().get(1));
                    int total = one + two;
                    if (total >= 10) {
                        list.add(total + "");
                    } else {
                        list.add("0" + total);
                    }
                    if (total <= 10) {
                        list.add("小");
                    } else {
                        list.add("大");
                    }
                    if (total % 2 == 0) {
                        list.add("双");
                    } else {
                        list.add("单");
                    }
                } catch (Exception e) {

                }

                break;
            case "CQXYNC"://重庆幸运农场
            case "HNKLSF"://湖南快乐十分
            case "GDKLSF"://广东快乐十分
            case "SFLHC"://十分六合彩
            case "LHC"://六合彩
            case "FC3D"://福彩3D
            case "PL3"://排列三
                for (String s : item.getHaoMaList()) {
                    list.add(s);
                }
                break;
            case "CQSSC"://重庆时时彩
            case "TJSSC"://天津时时彩
            case "XJSSC"://新疆时时彩
            case "FFC"://分分彩
            case "EFC"://二分彩
            case "WFC"://五分彩
                try {
                    int total = 0;
                    for (String s : item.getHaoMaList()) {
                        list.add(s);
                        total += Integer.parseInt(s);
                    }
                    list.add("=");
                    if (total >= 10) {
                        list.add(total + "");
                    } else {
                        list.add("0" + total);
                    }
                    if (total <= 22) {
                        list.add("小");
                    } else {
                        list.add("大");
                    }
                    if (total % 2 == 0) {
                        list.add("双");
                    } else {
                        list.add("单");
                    }
                } catch (Exception e) {

                }
                break;
            case "JSSB3"://江苏骰宝(快3)
            case "HBK3"://湖北快三
            case "AHK3"://安徽快三
            case "SHHK3"://上海快三
            case "HEBK3"://河北快三
            case "GXK3"://广西快三
            case "BJK3"://北京快三
            case "JXK3"://江西快三
            case "GSK3"://甘肃快三
            case "WFK3"://幸运快三
            case "JLK3"://吉林快三
                try {
                    int total = 0;
                    for (String s : item.getHaoMaList()) {
                        list.add(s);
                        total += Integer.parseInt(s);
                    }
                    list.add("=");
                    if (total >= 10) {
                        list.add(total + "");
                    } else {
                        list.add("0" + total);
                    }
                    if (total <= 10) {
                        list.add("小");
                    } else {
                        list.add("大");
                    }
                    if (total % 2 == 0) {
                        list.add("双");
                    } else {
                        list.add("单");
                    }
                } catch (Exception e) {

                }
                break;
            case "PCEGG"://PC蛋蛋
                try {

                    for (String s : item.getHaoMaList()) {
                        list.add(s);
                    }
                    int total = Integer.parseInt(item.getHaoMaList().get(item.getHaoMaList().size() - 1));
                    if (total <= 13) {
                        list.add("小");
                    } else {
                        list.add("大");
                    }
                    if (total % 2 == 0) {
                        list.add("双");
                    } else {
                        list.add("单");
                    }
                } catch (Exception e) {

                }
                break;
            case "GD11X5"://广东11选5
            case "SD11X5"://山东11选5
            case "JX11X5"://江西11选5
            case "SH11X5"://上海11选5
                try {
                    int total = 0;
                    for (String s : item.getHaoMaList()) {
                        list.add(s);
                        total += Integer.parseInt(s);
                    }
                    list.add("=");
                    if (total >= 10) {
                        list.add(total + "");
                    } else {
                        list.add("0" + total);
                    }
                    if (total <= 29) {
                        list.add("小");
                    } else {
                        list.add("大");
                    }
                    if (total % 2 == 0) {
                        list.add("双");
                    } else {
                        list.add("单");
                    }
                } catch (Exception e) {

                }
                break;
             default:
                 for (String s : item.getHaoMaList()) {
                     list.add(s);
                 }
                 break;
        }
        //添加
        for (String s : list) {
            TextView textView = new TextView(context);

            if (!"-".equals(s) && !"+".equals(s) && !"=".equals(s)) {
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundResource(R.drawable.bg_msg_bubble);
                textView.setPadding(12, 4, 12, 4);
            }
            if (s.length() == 1) {
                if (!"小".equals(s) && !"大".equals(s) && !"单".equals(s) && !"双".equals(s) && !"合".equals(s) && !"质".equals(s)) {
                    textView.setText(" " + s + " ");
                } else {
                    textView.setText(s);
                }
            } else {
                textView.setText(s);
            }
            layout.addView(textView);

        }
    }
}
