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

import com.xbzhangshi.mvp.home.Fragment.HomePurchaseFragment;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;
import com.xbzhangshi.single.ServiceTime;

/**
 * Custom digital clock
 *
 * @author veally@foxmail.com
 */
public class CustomDigitalClock extends android.support.v7.widget.AppCompatTextView implements ServiceTime.ObserverListener {

    private ClockListener mClockListener;


    public LotterysCountDownBean.ContentBean getContentBean() {
        return contentBean;
    }

    public void setContentBean(LotterysCountDownBean.ContentBean contentBean) {
        this.contentBean = contentBean;
    }

    LotterysCountDownBean.ContentBean contentBean;


    public CustomDigitalClock(Context context) {
        super(context);
    }

    public CustomDigitalClock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onSecond() {
        countDown();
    }

    /**
     * 倒计时
     */
    public void countDown() {
        int i = (int) getTag();
        if(i!= HomePurchaseFragment.curVisPage){
            return;
        }
        if (contentBean == null) {
            return;
        }
        long distanceTime = contentBean.getActiveTime() - contentBean.getServerTime();
        distanceTime /= 1000;
        if (distanceTime <= 0) {
            setText("00:00:00");
        } else {
            setText(dealTime(distanceTime));
        }
        invalidate();

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
        ServiceTime.getInstance().removeListener(this);
    }

    /**
     * deal time string
     *
     * @param time
     * @return
     */
    public static String dealTime(long time) {
        StringBuffer returnString = new StringBuffer();
        long hours = (time%86400) /3600;
        long minutes = ((time % 86400) % 3600) / 60;
        long second = ((time % 86400) % 3600) % 60;
        String hoursStr = timeStrFormat(String.valueOf(hours));
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


    public interface ClockListener {
        void timeEnd();

        void remainFiveMinutes();
    }

}
