package com.xbzhangshi.single;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.MyApplication;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;
import com.xbzhangshi.mvp.home.event.UpdateLotteryEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 服务器时间
 */
public class ServiceTime {

    static ServiceTime serviceTime;


    private int TIME = 1000;  //每隔1s执行一次.
    Handler handler = new Handler();
    HashSet<ObserverListener> listeners = new HashSet<>();
    Context context;
    public boolean isStop = true;

    public List<LotterysCountDownBean.ContentBean> getContentBeanList() {
        return contentBeanList;
    }

    public void setContentBeanList(List<LotterysCountDownBean.ContentBean> contentBeanList) {
        this.contentBeanList = contentBeanList;
    }

    List<LotterysCountDownBean.ContentBean> contentBeanList;


    public static ServiceTime getInstance(Context context, List<LotterysCountDownBean.ContentBean> contentBeanList) {
        if (serviceTime == null) {
            serviceTime = new ServiceTime(context, contentBeanList);
        }
        return serviceTime;
    }

    public static ServiceTime getInstance() {
        return serviceTime;
    }


    private ServiceTime(Context context, List<LotterysCountDownBean.ContentBean> contentBeanList) {
        this.contentBeanList = contentBeanList;
        this.context = context;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (MyApplication.isExit) {
                        serviceTime = null;
                        return;//判断程序退出
                    }
                    handler.removeCallbacks(this);
                    handler.postDelayed(this, TIME);
                    Long startIime = System.currentTimeMillis();
                    //判断是否有时间要更新
                    for (LotterysCountDownBean.ContentBean contentBean : contentBeanList) {
                        contentBean.setServerTime(contentBean.getServerTime() + 1000);
                        if (contentBean.getActiveTime() <= contentBean.getServerTime()) {
                            getItemServiceTime(context, contentBean.getLotCode());
                        }
                    }
                    /**
                     * 每一秒回调刷新可见view的时间
                     */
                    Iterator<ObserverListener> it = listeners.iterator();
                    Log.e("TAG", "view的数量" + listeners.size());
                    while (it.hasNext()) {
                        ObserverListener str = it.next();
                        str.onSecond();
                    }
                    Long endTime = System.currentTimeMillis();
                   /* long now = SystemClock.uptimeMillis();
                    long next = now + (1000 - now % 1000);*/
                    Log.e("TAG", "耗时" + (endTime - startIime));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, TIME); // 在初始化方法里.

    }

    /**
     * 获取某一个服务时间
     *
     * @param code
     */
    Set<String> codeLoadings = new HashSet<>();

    public void getItemServiceTime(Context context, String code) {
        /**
         * 判断这个code是否在请求中
         */
        if (codeLoadings.contains(code)) {
            return;
        }
        codeLoadings.add(code);
        HttpParams httpParams = new HttpParams();
        httpParams.put("lotCodes", code);
        HttpManager.get(context, URL.BASE_URL + URL.LotterysCountDown, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                codeLoadings.remove(code);
                LotterysCountDownBean lotterysCountDownBean = null;
                try {
                    lotterysCountDownBean = JSON.parseObject(response.body(), LotterysCountDownBean.class);
                } catch (Exception e) {

                }
                if (lotterysCountDownBean != null && lotterysCountDownBean.isSuccess()) {
                    if (lotterysCountDownBean.getContent().size() > 0) {
                        LotterysCountDownBean.ContentBean resultBean = lotterysCountDownBean.getContent().get(0);
                        for (LotterysCountDownBean.ContentBean bean : contentBeanList) {
                            if (!TextUtils.isEmpty(bean.getLotCode()) && bean.getLotCode().equals(resultBean.getLotCode())) {
                                //更新某一个服务时间
                                bean.setServerTime(resultBean.getServerTime());
                                bean.setActiveTime(resultBean.getActiveTime());
                                bean.setLastQihao(resultBean.getLastQihao());
                                bean.setQiHao(resultBean.getQiHao());
                                //发送更是成功的信息
                                EventBus.getDefault().post(new UpdateLotteryEvent(bean.getLotCode()));
                            }
                        }
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                codeLoadings.remove(code);
            }
        });
    }


    public void addListener(ObserverListener observerListenr) {
        if (!listeners.contains(observerListenr)) {
            listeners.add(observerListenr);
        }
    }

    public void removeListener(ObserverListener observerListenr) {
        if (listeners.contains(observerListenr)) {
            listeners.remove(observerListenr);
        }
    }

    private void clearListener() {
        listeners.clear();
    }


    public interface ObserverListener {
        /**
         * 每一秒回调一次
         *
         * @param
         */
        public void onSecond();
    }

}
