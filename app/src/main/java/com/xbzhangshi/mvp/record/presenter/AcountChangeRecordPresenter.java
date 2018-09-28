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
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.record.baseview.IAountChangeBaseView;
import com.xbzhangshi.mvp.record.baseview.ILotteryBaseView;
import com.xbzhangshi.mvp.record.bean.AcountChangeRecordBean;
import com.xbzhangshi.mvp.record.bean.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

    Map<String,String> MYkeys = new HashMap<>();
    private void initData(Context context) {
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.getMnyrecordType, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

               try {
                   JSONObject jsonObject = JSON.parseObject(response.body());
                   Set<String> keys = jsonObject.keySet();
                   Iterator iterator = keys.iterator();
                   while(iterator.hasNext()){
                       String key = (String) iterator.next();
                       Log.e("tag","key: "+key);
                       MYkeys.put(key,jsonObject.getString(key));
                   }
                   if(MYkeys.size()>0){
                       query(context,"","");
                   }else {
                       contentView.error("请求出错");
                   }
               }catch (Exception e){
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

        if(MYkeys.size()<=0){
            initData(context);
        }

        if(TextUtils.isEmpty(start)){
            Date curDate = new Date(System.currentTimeMillis());
            start = dFormat.format(curDate);
        }
        if(TextUtils.isEmpty(end)){
            Date curDate = new Date(System.currentTimeMillis());
            end= dFormat.format(curDate);
        }

        HttpParams httpParams = new HttpParams();
        if (!TextUtils.isEmpty(start)) {
            httpParams.put("startTime", start + " 00:00:00");
        }
        if (!TextUtils.isEmpty(end)) {
            httpParams.put("endTime", end + " 23:59:59");
        }

        if (!TextUtils.isEmpty(curtype)) {
            if (curtype.equals("全部")) {
                httpParams.put("type", "");
            } else if (curtype.equals("人工扣款")) {
                if (curtype.equals("人工加款")) {
                    httpParams.put("type", 1);
                } else if (curtype.equals("人工扣款")) {
                    httpParams.put("type", 2);
                } else if (curtype.equals("在线取款失败")) {
                    httpParams.put("type", 3);
                } else if (curtype.equals("在线取款")) {
                    httpParams.put("type", 4);
                }
            } else {
                httpParams.put("type", "");
            }
            httpParams.put("page", curpage);
            httpParams.put("rows", 10);

            Object tag = HttpManager.post(context, Url.mnyrecord_list, httpParams, new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    AcountChangeRecordBean bean = null;
                    try {
                        bean = JSON.parseObject(response.body(), AcountChangeRecordBean.class);
                    } catch (Exception e) {
                        contentView.error("请求出错");
                        return;
                    }

                    if (bean != null) {
                        if (bean.getList() != null && bean.getList().size() > 0) {
                            if (curpage == 1) {
                                contentView.successData(bean.getList(),MYkeys, bean.isHasNext());
                            } else {
                                contentView.successMore(bean.getList(), bean.isHasNext());
                            }

                        } else {
                            if (curpage == 1) {
                                contentView.empty();
                            } else {
                                contentView.emptyMore(bean.isHasNext());
                            }

                        }
                        if (bean.isHasNext()) {
                            curpage = bean.getNextPage();
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
}
