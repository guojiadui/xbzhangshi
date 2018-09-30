package com.xbzhangshi.mvp.record.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.record.baseview.IThreeLotteryBaseView;
import com.xbzhangshi.mvp.record.bean.ThreeLotteryRecordBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ElectronicsLotteryRecordPresenter extends BasePresenter {

    public static ElectronicsLotteryRecordPresenter newInstance(IThreeLotteryBaseView contentView) {
        return new ElectronicsLotteryRecordPresenter(contentView);
    }

    IThreeLotteryBaseView contentView;
    public String curPlatformType;
    public int curpage = 1;
    SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat df = new DecimalFormat("#0.00");


    public ElectronicsLotteryRecordPresenter(IThreeLotteryBaseView contentView) {
        this.contentView = contentView;

    }


    public void initData(Context context) {
        //获取默认的查询
        Date curDate = new Date(System.currentTimeMillis());
        String str = dFormat.format(curDate);
        query(context, str, str);
    }

    public void query(Context context, String start, String end) {

        HttpParams httpParams = new HttpParams();
        if (!TextUtils.isEmpty(start)) {
            httpParams.put("startTime", start + " 00:00:00");
        }
        if (!TextUtils.isEmpty(end)) {
            httpParams.put("endTime", end + " 23:59:59");
        }

        if ("所有电子".equals(curPlatformType)) {
            httpParams.put("type", 0);
        } else if ("mg电子游艺".equals(curPlatformType)) {
            httpParams.put("type", 3);
        } else if ("ag电子游艺".equals(curPlatformType)) {
            httpParams.put("type", 1);
        } else if ("bbin电子游艺".equals(curPlatformType)) {
            httpParams.put("type", 2);
        } else if ("QT电子游艺".equals(curPlatformType)) {
            httpParams.put("type", 4);
        } else if ("PT电子游艺".equals(curPlatformType)) {
            httpParams.put("type", 6);
        } else if ("CQ9电子游艺".equals(curPlatformType)) {
            httpParams.put("type", 9);
        } else if ("JDB电子游艺".equals(curPlatformType)) {
            httpParams.put("type", 11);
        } else if ("TTG电子游艺".equals(curPlatformType)) {
            httpParams.put("type", 92);
        } else if ("MW电子游艺".equals(curPlatformType)) {
            httpParams.put("type", 95);
        } else if ("ISB电子游艺".equals(curPlatformType)) {
            httpParams.put("type", 96);
        } else if ("BG电子游艺".equals(curPlatformType)) {
            httpParams.put("type", 98);
        }
        httpParams.put("page", curpage);
        httpParams.put("rows", 10);

        Object tag = HttpManager.post(context, Url.egamerecord, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ThreeLotteryRecordBean bean = null;
                try {
                    bean = JSON.parseObject(response.body(), ThreeLotteryRecordBean.class);
                } catch (Exception e) {
                    contentView.error("请求出错");
                    return;
                }

                if (bean != null) {
                    if (bean.getAggsData() != null) {
                        double d = bean.getAggsData().getWinMoneyCount() - bean.getAggsData().getBettingMoneyCount();
                        String s = df.format(d);
                        contentView.setprofit(bean.getAggsData().getBettingMoneyCount(), bean.getAggsData().getWinMoneyCount(), Double.valueOf(s));
                    }
                    if (bean.getRows() != null && bean.getRows().size() > 0) {
                        contentView.successData(bean.getRows());

                    } else {
                        contentView.empty();
                    }

                } else {
                    contentView.error("请求出错");
                }


            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.error("请求出错");
            }
        });
        addNet(tag);
    }


}
