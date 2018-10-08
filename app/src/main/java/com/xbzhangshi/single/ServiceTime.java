package com.xbzhangshi.single;

import android.content.Context;

import android.os.Handler;

import android.text.TextUtils;


import com.lzy.okgo.callback.StringCallback;

import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.MyApplication;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.home.bean.LoctteryBean;

import com.xbzhangshi.mvp.home.event.UpdateLotteryEvent;


import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * 服务器时间
 */
public class ServiceTime {

    static ServiceTime serviceTime;
    public Long remoteServiceTime = System.currentTimeMillis();

    private int TIME = 1000;  //每隔1s执行一次.
    Handler handler = new Handler();
    HashSet<ObserverListener> listeners = new HashSet<>();
    Context context;

    private boolean isstop =true;

    public List<LoctteryBean.ContentBean> getContentBeanList() {
        return contentBeanList;
    }

    public void setContentBeanList(List<LoctteryBean.ContentBean> contentBeanList) {
        this.contentBeanList = contentBeanList;
    }

    List<LoctteryBean.ContentBean> contentBeanList;

    public void exit() {
        isstop =false;
        listeners.clear();
        serviceTime = null;
    }

    public static ServiceTime getInstance(Context context, List<LoctteryBean.ContentBean> contentBeanList) {
        if (serviceTime == null) {
            serviceTime = new ServiceTime(context, contentBeanList);
        }
        return serviceTime;
    }

    public static ServiceTime getInstance() {
        return serviceTime;
    }


    private ServiceTime(Context context, List<LoctteryBean.ContentBean> contentBeanList) {
        this.contentBeanList = contentBeanList;
        this.context = context;
        remoteServiceTime = contentBeanList.get(contentBeanList.size() - 1).getServerTime();//初始化服务器时间
        //第一次获取时间
        for (LoctteryBean.ContentBean contentBean : contentBeanList) {
            getItemServiceTime(context, contentBean.getCode());
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (!isstop) {
                        return;//判断程序退出
                    }
                    handler.removeCallbacks(this);
                    handler.postDelayed(this, TIME);
                    // Long startIime = System.currentTimeMillis();
                    remoteServiceTime = remoteServiceTime + 1000;//更新服务器时间
                    //判断是否有时间要更新
                    for (LoctteryBean.ContentBean contentBean : contentBeanList) {
                        // contentBean.setServerTime(contentBean.getServerTime() + 1000);
                        if (contentBean.getActiveTime() <= remoteServiceTime) {//开奖时间到
                            getItemServiceTime(context, contentBean.getCode());
                        }
                    }
                    /**
                     * 每一秒回调刷新可见view的时间
                     */
                    Iterator<ObserverListener> it = listeners.iterator();
                    // Log.e("TAG", "view的数量" + listeners.size());
                    while (it.hasNext()) {
                        ObserverListener str = it.next();
                        str.onSecond(remoteServiceTime);
                    }
                    //  Long endTime = System.currentTimeMillis();
                   /* long now = SystemClock.uptimeMillis();
                    long next = now + (1000 - now % 1000);*/
                    // Log.e("TAG", "耗时" + (endTime - startIime));

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
        HttpManager.get(context, Url.ServiceTime1 + code + Url.ServiceTime2, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                codeLoadings.remove(code);

                try {
                    JSONObject json = new JSONObject(response.body());
                    remoteServiceTime = json.getLong("serverTime");//服务器时间
                    JSONObject j = json.getJSONObject(code);
                    for (LoctteryBean.ContentBean bean : contentBeanList) {
                        if (!TextUtils.isEmpty(bean.getCode()) && bean.getCode().equals(code)) {
                            bean.setActiveTime(j.getLong("actionTime"));
                            bean.setLastQihao(j.getString("lastQiHao"));
                            bean.setQiHao(j.getString("qiHao"));
                            bean.setLastHaoMa(j.getString("lastHaoMa"));
                            //发送更是成功的信息
                            EventBus.getDefault().post(new UpdateLotteryEvent(bean.getCode()));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
        public void onSecond(long serviceitem);
    }

}
