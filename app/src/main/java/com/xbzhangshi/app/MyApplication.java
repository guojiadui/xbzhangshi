package com.xbzhangshi.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.squareup.leakcanary.LeakCanary;
import com.xbzhangshi.single.ServiceTime;
import com.xbzhangshi.single.UserInfo;


import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class MyApplication extends Application implements Utils.OnAppStatusChangedListener  {


    //记录当前栈里所有activity
    private List<Activity> activities = new ArrayList<Activity>();
    android.os.Handler handler = new android.os.Handler();

    public static boolean isExit = false;
    /**
     * 应用实例
     **/
    private static MyApplication instance;

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        isExit = false;

        OkGo.getInstance().init(this);//默认初始化
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        OkGo.getInstance().setOkHttpClient(builder.build());

      /*   CrashUtils.init(new CrashUtils.OnCrashListener() {
            @Override
            public void onCrash(String crashInfo, Throwable e) {
               exit();
            }
        });*/
        LogUtils.getConfig().setLogSwitch(true);
       /* AppUtils.registerAppStatusChangedListener(AppUtils.class.getName(), this);
        LogUtils.e("TAG", "------------------app");
         if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);*/

    }


    /**
     * 获得实例
     *
     * @return
     */
    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 新建了一个activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }


    /**
     * 应用退出，结束所有的activity
     */
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        OkGo.getInstance().cancelAll();
        if(ServiceTime.getInstance()!=null){
            ServiceTime.getInstance().exit();
        }
        isExit = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        },500);
      // System.exit(0);
    }

    @Override
    public void onForeground() {

    }

    @Override
    public void onBackground() {

    }
}
