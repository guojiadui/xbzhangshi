package com.xbzhangshi.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.squareup.leakcanary.LeakCanary;


import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application implements Utils.OnAppStatusChangedListener {

    //记录当前栈里所有activity
    private List<Activity> activities = new ArrayList<Activity>();
    android.os.Handler handler = new android.os.Handler();

    public  static  boolean isExit = false;
    /**
     * 应用实例
     **/
    private static MyApplication instance;

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        isExit=false;
        OkGo.getInstance().init(this);//默认初始化
       /* CrashUtils.init(new CrashUtils.OnCrashListener() {
            @Override
            public void onCrash(String crashInfo, Throwable e) {
                AppUtils.relaunchApp();
            }
        });*/
        LogUtils.getConfig().setLogSwitch(true);
        AppUtils.registerAppStatusChangedListener(AppUtils.class.getName(),this);
        LogUtils.e("TAG", "------------------app");
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);

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
        isExit=true;
        System.exit(0);
    }

    @Override
    public void onForeground() {
        
    }

    @Override
    public void onBackground() {

    }
}
