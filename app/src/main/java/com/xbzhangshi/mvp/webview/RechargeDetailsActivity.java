package com.xbzhangshi.mvp.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.CookieStore;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;
import com.xbzhangshi.mvp.home.HomeActivity;
import com.xbzhangshi.mvp.record.AcountDetailsRecordActivity;

import java.util.List;

import okhttp3.Cookie;


/**
 * 彩票投注的详情页
 */
public class RechargeDetailsActivity extends BaseWebViewActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, RechargeDetailsActivity.class);
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
        return Url.pay_select_way;
    }

    @Override
    public void setCookie(Bundle savedInstanceState) {
        setCookie1(savedInstanceState);
        super.setCookie(savedInstanceState);
    }

    public void setCookie1(Bundle savedInstanceState) {
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        String cookieString = allCookie.get(0).name() + "=" + allCookie.get(0).value() + ";domain=" + allCookie.get(0).domain();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(Url.generatePayOrder, cookieString);
        CookieSyncManager.getInstance().sync();
    }

    @JavascriptInterface
    @Override
    public void confirm(String s) {
        super.confirm(s);
        switch (s) {
            case "homePage":
                HomeActivity.start(this); //返回首页
                break;
            case "source":
                AcountDetailsRecordActivity.start(this);
                break;
        }
    }
}
