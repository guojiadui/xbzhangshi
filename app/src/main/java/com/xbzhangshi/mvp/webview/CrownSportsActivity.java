package com.xbzhangshi.mvp.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.CookieStore;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;
import com.xbzhangshi.mvp.details.OpenPrizedetailsActivity;
import com.xbzhangshi.mvp.login.LoginActivity;
import com.xbzhangshi.mvp.record.LotteryRecordActivity;
import com.xbzhangshi.single.UserInfo;

import java.util.List;

import okhttp3.Cookie;


/**
 * 彩票投注的详情页
 */
public class CrownSportsActivity extends BaseWebViewActivity {
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
        Intent intent = new Intent(context, CrownSportsActivity.class);
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
        return Url.Crown_Sports;
    }
//http://xbzhanshi.com/mobile/sports/hg/getData.do
    @Override
    public void setCookie(Bundle savedInstanceState) {
        super.setCookie(savedInstanceState);
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        String cookieString = allCookie.get(0).name() + "=" + allCookie.get(0).value() + ";domain=" + allCookie.get(0).domain();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(Url.Crown_Sports_cookis, cookieString);
        CookieSyncManager.getInstance().sync();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {//点击返回按钮的时候判断有没有上一页
            finish();// goBack()表示返回webView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }





}
