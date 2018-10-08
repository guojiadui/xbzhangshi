package com.xbzhangshi.mvp.record.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.record.baseview.IAountChangeBaseView;
import com.xbzhangshi.mvp.record.baseview.ILotteryBaseView;
import com.xbzhangshi.mvp.record.bean.AcountChangeRecordBean;
import com.xbzhangshi.mvp.record.bean.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AcountChangeRecordPresenter extends BasePresenter {

    public static AcountChangeRecordPresenter newInstance(IAountChangeBaseView contentView) {
        return new AcountChangeRecordPresenter(contentView);
    }

    IAountChangeBaseView contentView;
    public String curtype;

    public int curpage = 1;
    SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");


    public AcountChangeRecordPresenter(IAountChangeBaseView contentView) {
        this.contentView = contentView;
    }

    Map<String, String> MYkeys = new HashMap<>();

    private void initData(Context context) {
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.getMnyrecordType, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                try {
                    JSONObject jsonObject = JSON.parseObject(response.body());
                    Set<String> keys = jsonObject.keySet();
                    Iterator iterator = keys.iterator();
                    List<String> strings = new ArrayList<>();
                    while (iterator.hasNext()) {
                        String key = (String) iterator.next();
                        MYkeys.put(key, jsonObject.getString(key));
                        strings.add(jsonObject.getString(key));
                    }
                    if (MYkeys.size() > 0) {
                        contentView.setInitSearch(strings);
                        query(context, "", "");
                    } else {
                        contentView.error("请求出错");
                    }
                } catch (Exception e) {
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

    public void query(Context context, String start, String end) {

        if (MYkeys.size() <= 0) {
            initData(context);
            return;
        }
        if (TextUtils.isEmpty(start)) {
            Date curDate = new Date(System.currentTimeMillis());
            start = dFormat.format(curDate);
        }
        if (TextUtils.isEmpty(end)) {
            Date curDate = new Date(System.currentTimeMillis());
            end = dFormat.format(curDate);
        }

        HttpParams httpParams = new HttpParams();
        if (!TextUtils.isEmpty(start)) {
            httpParams.put("startTime", start + " 00:00:00");
        }
        if (!TextUtils.isEmpty(end)) {
            httpParams.put("endTime", end + " 23:59:59");
        }
        String curkey = null;
        Iterator<Map.Entry<String, String>> entries = MYkeys.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            if (curtype.equals(entry.getValue())) {
                curkey = entry.getKey();
                break;
            }
        }
        if (TextUtils.isEmpty(curkey)) {
            httpParams.put("type", "");
        } else {
            httpParams.put("type", curkey);
        }

        httpParams.put("page", curpage);
        httpParams.put("rows", 10);

        Object tag = HttpManager.postObject(context,AcountChangeRecordBean.class, Url.mnyrecord_list, httpParams, new OkGoCallback<AcountChangeRecordBean>() {
            @Override
            public void onSuccess(AcountChangeRecordBean response) {
                if (response.getList() != null && response.getList().size() > 0) {
                    if (curpage == 1) {
                        contentView.successData(response.getList(), MYkeys, response.isHasNext());
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
                    curpage = response.getNextPage();
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
