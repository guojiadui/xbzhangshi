package com.xbzhangshi.mvp.record.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.record.baseview.ILotteryBaseView;
import com.xbzhangshi.mvp.record.baseview.IThreeLotteryBaseView;
import com.xbzhangshi.mvp.record.bean.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ThreeLotteryRecordBean;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreeLotteryRecordPresenter extends BasePresenter {

    public static ThreeLotteryRecordPresenter newInstance(IThreeLotteryBaseView contentView, int type) {
        return new ThreeLotteryRecordPresenter(contentView, type);
    }

    IThreeLotteryBaseView contentView;
    public String curPlatformType;
    public int curpage = 1;
    SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat df = new DecimalFormat("#0.00");
    int curType;

    public ThreeLotteryRecordPresenter(IThreeLotteryBaseView contentView, int type) {
        this.contentView = contentView;
        curType = type;
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
        if(AcountChangeRecordPresenter.isDateOneBigger(start,end)){
            Toast.makeText(context,"截止时间要大于开始时间",Toast.LENGTH_SHORT).show();
            contentView.empty();
            return;
        }
        /*
         * type 0所有记录 1 AG 2 BBIN 3 MG 5 ALLBET 7 OG 8 DS 12 KY 98 	BG 97 VR*/
        if ("所有记录".equals(curPlatformType)) {
            httpParams.put("type", 0);
        } else if ("AG".equals(curPlatformType)) {
            httpParams.put("type", 1);
        } else if ("BBIN".equals(curPlatformType)) {
            httpParams.put("type", 2);
        } else if ("MG".equals(curPlatformType)) {
            httpParams.put("type", 3);
        } else if ("ALLBET".equals(curPlatformType)) {
            httpParams.put("type", 5);
        } else if ("OG".equals(curPlatformType)) {
            httpParams.put("type", 7);
        } else if ("DS".equals(curPlatformType)) {
            httpParams.put("type", 8);
        } else if ("KY".equals(curPlatformType)) {
            httpParams.put("type", 12);
        } else if ("BG".equals(curPlatformType)) {
            httpParams.put("type", 98);
        } else if ("VR".equals(curPlatformType)) {
            httpParams.put("type", 97);
        }
        httpParams.put("page", curpage);
        httpParams.put("rows", 10);

        String url = null;
        if (curType == 1) {
            url = Url.thirdlotteryrecord;//三方彩票
        } else if (curType == 2) {
            url = Url.liveBetRecord;//三方彩票
        } else if (curType == 3) {
            url = Url.chessrecord;//三方彩票
        }
        Object tag = HttpManager.postObject(context,ThreeLotteryRecordBean.class, url, httpParams, new OkGoCallback<ThreeLotteryRecordBean>() {
            @Override
            public void onSuccess(ThreeLotteryRecordBean response) {
                if (response.getAggsData() != null) {
                    double d = response.getAggsData().getWinMoneyCount() - response.getAggsData().getBettingMoneyCount();
                    String s = df.format(d);
                    contentView.setprofit(response.getAggsData().getBettingMoneyCount(), response.getAggsData().getWinMoneyCount(), Double.valueOf(s));
                }
                if (response.getRows() != null && response.getRows().size() > 0) {
                    contentView.successData(response.getRows());

                } else {
                    contentView.empty();
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.error("请求出错");
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
