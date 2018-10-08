package com.xbzhangshi.view;

import java.util.Calendar;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.DigitalClock;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.xbzhangshi.mvp.home.Fragment.HomePurchaseFragment;
import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;
import com.xbzhangshi.single.ServiceTime;

/**
 * Custom digital clock
 *
 * @author veally@foxmail.com
 */
public class CustomDigitalClock extends android.support.v7.widget.AppCompatTextView implements ServiceTime.ObserverListener {

    private ClockListener mClockListener;


    public LoctteryBean.ContentBean getContentBean() {
        return contentBean;
    }

    public void setContentBean(LoctteryBean.ContentBean contentBean) {
        this.contentBean = contentBean;

    }

    LoctteryBean.ContentBean contentBean;


    public CustomDigitalClock(Context context) {
        super(context);
    }

    public CustomDigitalClock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onSecond(long serviceitem) {
        countDown(serviceitem);
    }

    /**
     * 倒计时
     */
    public void countDown(long servicetime) {
        int i = (int) getTag();
        if (i != HomePurchaseFragment.curVisPage) {
            return;
        }
        if (contentBean == null) {
            return;
        }

        //激活时间减去age时间等于封盘时间
        //激活时间是开奖时间
        if (servicetime < contentBean.getActiveTime() - (contentBean.getDuration() * 1000)) {
            //封盘时间
            if (mClockListener != null) {
                mClockListener.close();
            }
            long distanceTime = contentBean.getActiveTime() - (contentBean.getDuration() * 1000) - servicetime;
            distanceTime /= 1000;
            if (distanceTime <= 0) {
                setText("00:00:00");
            } else {
                setText(dealTime(distanceTime));
            }
        } else {
            //开盘时间
            if (mClockListener != null) {
                mClockListener.open();
            }
            long distanceTime = contentBean.getActiveTime() - servicetime;
            distanceTime /= 1000;
            if (distanceTime <= 0) {
                setText("00:00:00");
            } else {
                setText(dealTime(distanceTime));
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ServiceTime.getInstance().addListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        contentBean = null;
        mClockListener = null;
        if (ServiceTime.getInstance() != null){
            ServiceTime.getInstance().removeListener(this);
        }

    }

    /**
     * deal time string
     *
     * @param time
     * @return
     */
    public static String dealTime(long time) {
        StringBuffer returnString = new StringBuffer();

        long hours = time / 3600;//小时
        long minutes = (time % 3600) / 60;
        long second = (time % 3600) % 60;
        String hoursStr = timeStrFormat(hours + "");
        String minutesStr = timeStrFormat(String.valueOf(minutes));
        String secondStr = timeStrFormat(String.valueOf(second));
        returnString.append(hoursStr).append(":").append(minutesStr).append(":").append(secondStr);
        return returnString.toString();
    }

    /**
     * format time
     *
     * @param timeStr
     * @return
     */
    private static String timeStrFormat(String timeStr) {
        switch (timeStr.length()) {
            case 1:
                timeStr = "0" + timeStr;
                break;
        }
        return timeStr;
    }


    public void setClockListener(ClockListener clockListener) {
        this.mClockListener = clockListener;
    }


    public abstract static class ClockListener {
        public boolean close = true;
        public boolean open = true;

        /**
         * 封盘
         */
        public void close() {
            if (close) {
                close = false;
                closeLottery();
            }
        }

        public void open() {
            if (open) {
                open = false;
                openPrize();
            }
        }

        public abstract void closeLottery();

        /**
         * 开奖
         */
        public abstract void openPrize();
    }

}
