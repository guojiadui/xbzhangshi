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
import com.xbzhangshi.mvp.record.LotteryRecordBean;
import com.xbzhangshi.mvp.record.baseview.ISportsBaseView;
import com.xbzhangshi.mvp.record.bean.BSSportsRecordBean;
import com.xbzhangshi.mvp.record.bean.HGSportsRecordBean;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SportsRecordPresenter extends BasePresenter {

    public static SportsRecordPresenter newInstance(ISportsBaseView contentView) {
        return new SportsRecordPresenter(contentView);
    }

    public final int hg_sport = 1;
    public final int bs_sport = 2;
    public int curSport = hg_sport;

    ISportsBaseView contentView;
    public String curplatform;
    public String curtype;
    public String curstate;
    public int curpage = 1;
    SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat df = new DecimalFormat("#0.00");

    public SportsRecordPresenter(ISportsBaseView contentView) {
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
        String url;
        if ("皇冠体育".equals(curplatform)) {
            //只有皇冠体育有
            curSport = hg_sport;
            url = Url.hg_sport;
            if (curtype.equals("足球")) {
                httpParams.put("sportType", 1);
            } else if (curtype.equals("篮球")) {
                httpParams.put("sportType", 2);
            } else {
                httpParams.put("sportType", 0);
            }

            //1全部 2已中奖 3未开奖 4未成功
            if (curstate.equals("全部")) {
                httpParams.put("recordType", 1);
            } else if (curstate.equals("已中奖")) {
                httpParams.put("recordType", 2);
            } else if (curstate.equals("未开奖")) {
                httpParams.put("recordType", 3);
            } else if (curstate.equals("未成功")) {
                httpParams.put("recordType", 4);
            }
        } else {
            curSport = bs_sport;
            url = Url.betrecord;
        }
        final int cur  =curSport;
        Object tag = HttpManager.post(context, url, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(cur==hg_sport){
                    //皇冠体育
                    HGSportsRecordBean bean = null;
                    try {
                        bean = JSON.parseObject(response.body(), HGSportsRecordBean.class);
                    } catch (Exception e) {
                        contentView.error("请求出错");
                        return;
                    }
                    if (bean != null) {
                           if(bean.getRows().size()>0){
                               double d = bean.getAggsData().getTotalBetResult() - bean.getAggsData().getTotalBetMoney();
                               String s = df.format(d);
                               contentView.setprofit(bean.getAggsData().getTotalBetMoney(),bean.getAggsData().getTotalBetResult(),Double.valueOf(s));
                               contentView.HGsuccess(bean.getRows());
                           }else {
                               contentView.empty();
                           }
                    } else {
                        contentView.error("请求出错");
                    }

                }else if(cur==bs_sport){
                    //芭莎体育
                    BSSportsRecordBean bean = null;
                    try {
                        bean = JSON.parseObject(response.body(), BSSportsRecordBean.class);
                        if(bean.getRows().size()>0){
                            double d = bean.getAggsData().getWinMoneyCount() - bean.getAggsData().getBettingMoneyCount();
                            String s = df.format(d);
                            contentView.setprofit(bean.getAggsData().getBettingMoneyCount(),bean.getAggsData().getWinMoneyCount(),Double.valueOf(s));
                          //  contentView.BSsuccess(bean.getAggsData());
                        }else {
                            contentView.empty();
                        }
                    } catch (Exception e) {
                        contentView.error("请求出错");
                        return;
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



}
