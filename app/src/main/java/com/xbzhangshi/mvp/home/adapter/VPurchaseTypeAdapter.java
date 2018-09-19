package com.xbzhangshi.mvp.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.TimeUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;
import com.xbzhangshi.mvp.home.bean.PurchaseTypeBean;
import com.xbzhangshi.single.ServiceTime;
import com.xbzhangshi.view.CustomDigitalClock;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VPurchaseTypeAdapter extends BaseQuickAdapter<LoctteryBean.ContentBean, BaseViewHolder> {
    Context context;
    int curPosition = 0;

    public VPurchaseTypeAdapter(Context context, @Nullable List<LoctteryBean.ContentBean> data, int curPosition) {
        super(R.layout.v_purchase_type_adapter_item, data);
        this.context = context;
        this.curPosition = curPosition;
    }

    @Override
    protected void convert(BaseViewHolder helper, LoctteryBean.ContentBean item) {
        String url = URL.lottert_png + item.getCode() + ".png";
        Glide.with(context).load(url).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.name, item.getName());

        if (!TextUtils.isEmpty(item.getLastQihao())) {
            SpannableString spannableString = new SpannableString("第" + item.getLastQihao() + "期");
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
            spannableString.setSpan(colorSpan, 1, spannableString.length() - 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.stage, spannableString);

        } else {
            helper.setText(R.id.stage, "");
        }
        //号码

        //开封盘
        //激活时间减去age时间等于封盘时间
        //激活时间是开奖时间
        if (ServiceTime.getInstance().remoteServiceTime < item.getActiveTime() - (item.getDuration() * 1000)) {
            //封盘时间
            //下一期
            closeLottrey(helper, item);
        } else {
            //开盘
            //下一期
            openLottrey(helper, item);
        }
        //设置号码
        setHaoma(helper, item);
        //倒计时
        CustomDigitalClock customDigitalClock = helper.getView(R.id.time);
        customDigitalClock.setTag(curPosition);
        customDigitalClock.setContentBean(item);
        customDigitalClock.setClockListener(new CustomDigitalClock.ClockListener() {
            @Override
            public void closeLottery() {
                //下一期
                closeLottrey(helper, item);
            }

            @Override
            public void openPrize() {
                //设置号码
                setHaoma(helper, item);
                //下一期
                openLottrey(helper, item);
            }
        });
    }

    public void openLottrey(BaseViewHolder helper, LoctteryBean.ContentBean item) {
        if (!TextUtils.isEmpty(item.getQiHao())) {
            SpannableString spannableString = new SpannableString("距第" + item.getQiHao() + "期开盘");
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
            spannableString.setSpan(colorSpan, 2, spannableString.length() - 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.next_stage, spannableString);
        } else {
            helper.setText(R.id.stage, "");
        }
    }

    public void closeLottrey(BaseViewHolder helper, LoctteryBean.ContentBean item) {
        if (!TextUtils.isEmpty(item.getQiHao())) {
            SpannableString spannableString = new SpannableString("距第" + item.getQiHao() + "期封盘");
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
            spannableString.setSpan(colorSpan, 2, spannableString.length() - 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.next_stage, spannableString);
        } else {
            helper.setText(R.id.stage, "");
        }
    }


    public void setHaoma(BaseViewHolder helper, LoctteryBean.ContentBean item) {
        helper.setText(R.id.haoma, item.getLastHaoMa());
        if (!TextUtils.isEmpty(item.getLastHaoMa())) {
            if (item.getLastHaoMa().contains("?")) {
                helper.setText(R.id.haoma, "开奖中...");
                helper.setVisible(R.id.haoma, true);
                LinearLayout layout = helper.getView(R.id.haoma_layout);
                layout.setVisibility(View.INVISIBLE);
                layout.removeAllViews();
            } else {
                helper.setText(R.id.haoma, item.getLastHaoMa());
                helper.setVisible(R.id.haoma, false);
                LinearLayout layout = helper.getView(R.id.haoma_layout);
                layout.setVisibility(View.VISIBLE);
                String[] strings = item.getLastHaoMa().split(",");
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
            helper.setText(R.id.haoma, "");
        }
    }


    public static boolean isContainNumber(String company) {

        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(company);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
