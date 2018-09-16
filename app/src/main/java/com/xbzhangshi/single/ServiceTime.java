package com.xbzhangshi.single;

import android.os.Handler;
import android.util.Log;

import com.xbzhangshi.app.MyApplication;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * 服务器时间
 */
public class ServiceTime {

    static ServiceTime serviceTime;


    private long remoteTime = System.currentTimeMillis();//服务器的时间
    private int TIME = 1000;  //每隔1s执行一次.
    Handler handler = new Handler();
    HashSet<ObserverListener> listeners = new HashSet<>();

    public static ServiceTime getInstance() {
        if (serviceTime == null) {
            serviceTime = new ServiceTime();
        }
        return serviceTime;
    }


    private ServiceTime() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if(MyApplication.isExit){
                        return;//判断程序退出
                    }
                  //  Log.e("TAG","remoteTime:"+remoteTime);
                    remoteTime = 1000 + remoteTime;
                    Iterator<ObserverListener> it = listeners.iterator();
                    while (it.hasNext()) {
                        ObserverListener str = it.next();
                        str.onSecond(remoteTime);
                    }
                    handler.postDelayed(this, TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, TIME); // 在初始化方法里.

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

    public void setRemoteTime(long remoteTime) {
        this.remoteTime = remoteTime;
    }
    public long getRemoteTime() {
        return remoteTime;
    }
    public interface ObserverListener {
        /**
         * 每一秒回调一次
         *
         * @param servicetime
         */
        public void onSecond(long servicetime);
    }

}
