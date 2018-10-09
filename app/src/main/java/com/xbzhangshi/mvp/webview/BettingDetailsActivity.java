package com.xbzhangshi.mvp.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


import com.blankj.utilcode.util.LogUtils;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;
import com.xbzhangshi.mvp.details.OpenPrizedetailsActivity;
import com.xbzhangshi.mvp.login.LoginActivity;
import com.xbzhangshi.mvp.record.LotteryRecordActivity;
import com.xbzhangshi.single.UserInfo;


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

    @JavascriptInterface
    public void loginLose(String ss) {
        LoginActivity.startLogin(this);
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

}
