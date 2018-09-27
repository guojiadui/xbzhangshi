package com.xbzhangshi.mvp.record.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.record.LotteryRecordBean;
import com.xbzhangshi.mvp.record.baseview.ILotteryBaseView;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LotteryRecordPresenter extends BasePresenter {

    public static LotteryRecordPresenter newInstance(ILotteryBaseView contentView) {
        return new LotteryRecordPresenter(contentView);
    }

    ILotteryBaseView contentView;
    public String curlotteryType;
    public String curlotterystate;
    public int curpage = 1;
    SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat df = new DecimalFormat("#0.00");

    public LotteryRecordPresenter(ILotteryBaseView contentView) {
        this.contentView = contentView;
    }


    public void initData(Context context) {
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.Loctterys, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LotteryRecordBean lotteryRecordBean = JSON.parseObject(response.body(), LotteryRecordBean.class);
                if (lotteryRecordBean.isSuccess()) {
                    //移除六合彩
                    for (int i = -0; i < lotteryRecordBean.getContent().size(); i++) {
                        LotteryRecordBean.ContentBean contentBean = lotteryRecordBean.getContent().get(i);
                        if ("LHC".equals(contentBean.getCode())) {
                            lotteryRecordBean.getContent().remove(i);
                            break;
                        }
                    }
                    contentView.successType(lotteryRecordBean.getContent());
                    //获取默认的查询
                    Date curDate = new Date(System.currentTimeMillis());
                    String str = dFormat.format(curDate);
                    query(context, str, str);
                } else {
                    if (TextUtils.isEmpty(lotteryRecordBean.getMsg())) {
                        contentView.error(lotteryRecordBean.getMsg());
                    }
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

    public void query(Context context, String start, String end) {


        HttpParams httpParams = new HttpParams();
        if (!TextUtils.isEmpty(start)) {
            httpParams.put("startTime", start + " 00:00:00");
        }
        if (!TextUtils.isEmpty(end)) {
            httpParams.put("endTime", end + " 23:59:59");
        }
        if (!TextUtils.isEmpty(curlotteryType)) {
            httpParams.put("code", curlotteryType);
        } else {
            httpParams.put("code", "");
        }
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
        Log.e("TAg", httpParams.toString());
        HttpManager.post(context, Url.get_lottery_order, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ResultLotteryRecordBean bean = null;
                try {
                    bean = JSON.parseObject(response.body(), ResultLotteryRecordBean.class);
                } catch (Exception e) {

                }
                if (bean != null && bean.getPage() != null) {
                    double d = bean.getSumWinMoney() - bean.getSumBuyMoney();
                    String s = df.format(d);
                    contentView.setprofit(bean.getSumBuyMoney(), bean.getSumWinMoney(), Double.valueOf(s));
                    if (bean.getPage().getList() != null && bean.getPage().getList().size() > 0) {
                        if(curpage==1){
                            contentView.successData(bean.getPage().getList(),bean.getPage().isHasNext());
                        }else {
                            contentView.successMore(bean.getPage().getList(),bean.getPage().isHasNext());
                        }

                    } else {
                        if(curpage==1){
                            contentView.empty();
                        }else {
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
    }


}
