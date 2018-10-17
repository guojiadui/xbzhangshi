package com.xbzhangshi.mvp.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.CookieStore;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;
import com.xbzhangshi.mvp.details.OpenPrizedetailsActivity;
import com.xbzhangshi.mvp.home.bean.BalanceBean;
import com.xbzhangshi.mvp.home.presenter.BettingPresenter;
import com.xbzhangshi.mvp.login.LoginActivity;
import com.xbzhangshi.mvp.record.LotteryRecordActivity;
import com.xbzhangshi.single.UserInfo;

import java.util.List;

import okhttp3.Cookie;


/**
 * 彩票投注的详情页
 */
public class BettingDetailsActivity extends BaseWebViewActivity {
    public static long startitme = 0;

    public static void start(Context context, String code) {
        //连续俩次点击大于500
        long cur = System.currentTimeMillis();
        if ((cur - startitme) < 500) {
            return;
        }
        startitme = cur;
        if (!UserInfo.getInstance().isLogin) {
            LoginActivity.startLogin(context);
            return;
        }
        Intent intent = new Intent(context, BettingDetailsActivity.class);
        intent.putExtra("code", code);
        context.startActivity(intent);
    }

    @Override
    protected int getlayout() {
        return R.layout.betting_details_activity_layout;
    }

    @Override
    public int getWebViewId() {
        return R.id.webview;
    }

    @Override
    public int getProgressBarId() {
        return R.id.progressbar;
    }

    @Override
    public String getUrl(Bundle savedInstanceState) {
        String code = getIntent().getStringExtra("code");
        return Url.bet_lotterys + code;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String code = intent.getStringExtra("code");
        webView.loadUrl(Url.bet_lotterys + code);
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {//点击返回按钮的时候判断有没有上一页
            finish();// goBack()表示返回webView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * JS调用android的方法
     *
     * @param
     * @return
     */


    //投注记录
    @JavascriptInterface
    public void locaLotteryNotes(String ss) {
        LotteryRecordActivity.start(this);
    }


    //开奖历史记录
    @JavascriptInterface
    public void lotteryCode(String code, String name, String type) {
        OpenPrizedetailsActivity.start(this, name, type, code);
    }

    //彩票跳转
    @JavascriptInterface
    public void locaLottery(String code) {
        BettingDetailsActivity.start(this, code);
    }

    //投注成功
    @JavascriptInterface
    public void lotteryReplace() {
        getBalance();
    }


    boolean isSet = false;
    boolean isAct = false;

    @Override
    public void loadStart() {
        super.loadStart();
        isSet = false;
    }

    @Override
    public void loadFinish() {
        isSet = true;
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,1500);
        getBalance();
    }

    //获取余额
    public void getminfo(String s) {
        if (webView != null)
            webView.loadUrl("javascript:lotteryReplaceMoney( \'" + s + "\')");
    }

    @Override
    protected void onResume() {
        super.onResume();
        isAct = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isAct = false;
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(Url.meminfo);
        handler.removeCallbacks(runnable);

        super.onDestroy();
    }

    Handler handler = new Handler();
    boolean isloadingBalance =false;
    /**
     * 获取余额
     */
    public void getBalance() {
        if(isloadingBalance){
            return;
        }
        isloadingBalance =true;
        HttpManager.postObjectByWeb(this, BalanceBean.class,
                Url.BASE_URL + Url.meminfo, null, new OkGoCallback<BalanceBean>() {
                    @Override


                    public void onSuccess(BalanceBean response) {
                        isloadingBalance =false;
                        if (response.isSuccess()) {
                            getminfo(BettingPresenter.subZeroAndDot(response.getContent().getBalance() + "元"));
                        }
                    }
                    @Override
                    public void parseError() {
                        isloadingBalance =false;
                    }

                    @Override
                    public void onError(Response<String> response) {
                        isloadingBalance =false;
                    }
                });

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 20000);
            if(isAct&&isSet){
                getBalance();
            }
        }
    };
}
