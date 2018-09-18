package com.xbzhangshi.mvp.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.TimeUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;
import com.xbzhangshi.mvp.home.bean.PurchaseTypeBean;
import com.xbzhangshi.view.CustomDigitalClock;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VPurchaseTypeAdapter extends BaseQuickAdapter<LotterysCountDownBean.ContentBean, BaseViewHolder> {
    Context context;
    int curPosition = 0;
    public VPurchaseTypeAdapter(Context context, @Nullable List<LotterysCountDownBean.ContentBean> data,int curPosition) {
        super(R.layout.v_purchase_type_adapter_item, data);
        this.context = context;
        this.curPosition = curPosition;
    }

    @Override
    protected void convert(BaseViewHolder helper, LotterysCountDownBean.ContentBean item) {
        String url = URL.lottert_png + item.getLotCode() + ".png";
        Glide.with(context).load(url).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.name, item.getLotName());

        if (isContainNumber(item.getLastQihao())) {
            SpannableString spannableString = new SpannableString("第" + item.getLastQihao() + "期");
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
            spannableString.setSpan(colorSpan, 1, spannableString.length() - 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.stage, spannableString);

        } else {
            helper.setText(R.id.stage, item.getLastQihao());
        }

        SpannableString spannableString = new SpannableString("距第" + item.getQiHao() + "期");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(colorSpan, 2, spannableString.length() - 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        helper.setText(R.id.next_stage, spannableString);
        //倒计时
        CustomDigitalClock customDigitalClock = helper.getView(R.id.time);
        customDigitalClock.setTag(curPosition);
        customDigitalClock.setContentBean(item);

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
