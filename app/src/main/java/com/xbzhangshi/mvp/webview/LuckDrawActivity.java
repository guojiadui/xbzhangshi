package com.xbzhangshi.mvp.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.CookieStore;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;
import com.xbzhangshi.mvp.login.LoginActivity;
import com.xbzhangshi.single.UserInfo;

import java.util.List;

import okhttp3.Cookie;

/**
 * 幸运转盘
 */

public class LuckDrawActivity extends BaseWebViewActivity {

    public static void start(Context context) {
        if(!UserInfo.userInfo.isLogin){
            LoginActivity.startLogin(context);
            return;
        }
        Intent intent = new Intent(context, LuckDrawActivity.class);
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
        return Url.turnlate;
    }
    @Override
    public void setCookie(Bundle savedInstanceState) {
        super.setCookie(savedInstanceState);
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        String cookieString = allCookie.get(0).name() + "=" + allCookie.get(0).value() + ";domain=" + allCookie.get(0).domain();
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(Url.turnlate_cookie, cookieString);
        CookieSyncManager.getInstance().sync();
    }
}
