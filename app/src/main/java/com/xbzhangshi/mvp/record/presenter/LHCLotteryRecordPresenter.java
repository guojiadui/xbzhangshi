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
import com.xbzhangshi.mvp.record.baseview.ILHCLotteryBaseView;
import com.xbzhangshi.mvp.record.baseview.ILotteryBaseView;
import com.xbzhangshi.mvp.record.bean.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LHCLotteryRecordPresenter extends BasePresenter {

    public static LHCLotteryRecordPresenter newInstance(ILHCLotteryBaseView contentView) {
        return new LHCLotteryRecordPresenter(contentView);
    }

    ILHCLotteryBaseView contentView;

    public String curlotterystate;
    public int curpage = 1;
    SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat df = new DecimalFormat("#0.00");

    public LHCLotteryRecordPresenter(ILHCLotteryBaseView contentView) {
        this.contentView = contentView;
    }


    public void initData(Context context) {
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
        httpParams.put("code", "LHC");
        if (!TextUtils.isEmpty(curlotterystate)) {
            //status 1 未开奖 2 已中奖 3 未中奖 4撤单
            if (curlotterystate.equals("未开奖")) {
                httpParams.put("status", 1);
            } else if (curlotterystate.equals("已中奖")) {
                httpParams.put("status", 2);
            } else if (curlotterystate.equals("未中奖")) {
                httpParams.put("status", 3);
            } else if (curlotterystate.equals("撤单")) {
                httpParams.put("status", 4);
            }
        } else {
            httpParams.put("status", "");
        }
        httpParams.put("page", curpage);
        httpParams.put("rows", 10);

        Object tag = HttpManager.post(context, Url.get_lottery_order, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ResultLotteryRecordBean bean = null;
                try {
                    bean = JSON.parseObject(response.body(), ResultLotteryRecordBean.class);
                } catch (Exception e) {
                    contentView.error("请求出错");
                    return;
                }

                if (bean != null && bean.getPage() != null) {
                    double d = bean.getSumWinMoney() - bean.getSumBuyMoney();
                    String s = df.format(d);
                    contentView.setprofit(bean.getSumBuyMoney(), bean.getSumWinMoney(), Double.valueOf(s));
                    if (bean.getPage().getList() != null && bean.getPage().getList().size() > 0) {
                        if (curpage == 1) {
                            contentView.successData(bean.getPage().getList(), bean.getPage().isHasNext());
                        } else {
                            contentView.successMore(bean.getPage().getList(), bean.getPage().isHasNext());
                        }

                    } else {
                        if (curpage == 1) {
                            contentView.empty();
                        } else {
                            contentView.emptyMore(bean.getPage().isHasNext());
                        }

                    }
                    if (bean.getPage().isHasNext()) {
                        curpage = curpage + 1;
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


    public void cancelOrder(Context context, String orderId) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("orderId", orderId);
        Object tag = HttpManager.post(context, Url.cancelOrder, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ResultBean resultBean = null;
                try {
                    resultBean = JSON.parseObject(response.body(), ResultBean.class);
                } catch (Exception e) {
                    contentView.cancalError("撤单失败");
                    return;
                }
                if (resultBean.isSuccess()) {
                    contentView.cancalSuccess(orderId);
                } else {
                    if (!TextUtils.isEmpty(resultBean.getMsg())) {
                        contentView.cancalError(resultBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.cancalError("请求出错");
            }
        });
        addNet(tag);
    }
}
