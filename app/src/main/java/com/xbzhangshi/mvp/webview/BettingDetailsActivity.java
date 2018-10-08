package com.xbzhangshi.mvp.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;
import com.xbzhangshi.mvp.details.OpenPrizedetailsActivity;
import com.xbzhangshi.mvp.login.LoginActivity;
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
        return "http://xbzhanshi.com/mobile/v3/bet_lotterys.do?lotCode=" + code;
    }

    @Override
    protected void onRestart() {
        String code = getIntent().getStringExtra("code");
        webView.loadUrl("http://xbzhanshi.com/mobile/v3/bet_lotterys.do?lotCode=" + code);
        super.onRestart();
    }

    /**
     * JS调用android的方法
     *
     * @param
     * @return
     */
    @JavascriptInterface //仍然必不可少
    public void confirm(String s, String d) {

    }


}
