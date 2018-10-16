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
import com.xbzhangshi.mvp.record.baseview.IAcountDetailsBaseView;
import com.xbzhangshi.mvp.record.bean.AcountDetailsRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AcountDetailsRecordPresenter extends BasePresenter {

    public static AcountDetailsRecordPresenter newInstance(IAcountDetailsBaseView contentView) {
        return new AcountDetailsRecordPresenter(contentView);
    }

    IAcountDetailsBaseView contentView;
    public String curacountType;
    public String curtransactionType;
    public String curprocessorState;
    public int curpage = 1;
    SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat df = new DecimalFormat("#0.00");

    public AcountDetailsRecordPresenter(IAcountDetailsBaseView contentView) {
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
        if(AcountChangeRecordPresenter.isDateOneBigger(start,end)){
            Toast.makeText(context,"截止时间要大于开始时间",Toast.LENGTH_SHORT).show();
            contentView.empty();
            return;
        }
        String url = null;
        int type;
        if ("充值记录".equals(curacountType)) {
            url = Url.chogzhijilu;
            type = 1;
            //5 在线存款 6快速入款 7一般存款
            if ("全部".equals(curtransactionType)) {
                httpParams.put("type", "");
            } else if ("在线存款".equals(curtransactionType)) {
                httpParams.put("type", 5);
            } else if ("快速入款".equals(curtransactionType)) {
                httpParams.put("type", 6);
            } else if ("一般存款".equals(curtransactionType)) {
                httpParams.put("type", 7);
            }

        } else {
            type = 2;
            url = Url.qukjilu;
        }

        if ("全部".equals(curprocessorState)) {
            httpParams.put("status", "");
        } else if ("处理中".equals(curprocessorState)) {
            httpParams.put("status", 1);
        } else if ("处理成功".equals(curprocessorState)) {
            httpParams.put("status", 2);
        } else if ("处理失败".equals(curprocessorState)) {
            httpParams.put("status", 3);
        }

        httpParams.put("page", curpage);
        httpParams.put("rows", 10);

        Object tag = HttpManager.postObject(context, AcountDetailsRecordBean.class,url, httpParams, new OkGoCallback<AcountDetailsRecordBean>() {
            @Override
            public void onSuccess(AcountDetailsRecordBean response) {
                if (type == 1) {
                    contentView.setTotalMpney(type, response.getAggsData().getTotalMoney());
                } else if (type == 2) {
                    contentView.setTotalMpney(type, response.getAggsData().getDrawMoney());
                }
                if (response.getList() != null && response.getList().size() > 0) {
                    if (curpage == 1) {
                        contentView.successData(response.getList(), type, response.isHasNext());
                    } else {
                        contentView.successMore(response.getList(), response.isHasNext());
                    }

                } else {
                    if (curpage == 1) {
                        contentView.empty();
                    } else {
                        contentView.emptyMore(response.isHasNext());
                    }

                }
                if (response.isHasNext()) {
                    curpage = curpage + 1;
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
