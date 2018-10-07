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

import java.util.List;

import okhttp3.Cookie;


/**
 * 签到
 */
public class RedPackageActivity extends BaseWebViewActivity {

    public  static  void  start(Context context ){
        Intent intent = new Intent(context,RedPackageActivity.class);
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

        return Url.redPackage ;
    }

    @Override
    public void setCookie(Bundle savedInstanceState) {
        super.setCookie(savedInstanceState);
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        String cookieString = allCookie.get(0).name() + "=" + allCookie.get(0).value() + ";domain=" + allCookie.get(0).domain();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(Url.grab, cookieString);
        CookieSyncManager.getInstance().sync();
    }
    /*  @Override
    public void setCookie(Bundle savedInstanceState) {

        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        String cookieString = allCookie.get(0).name() + "=" + allCookie.get(0).value() + ";domain=" + allCookie.get(0).domain();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie("http://xbzhanshi.com/center/redpacket/grab.do", cookieString);
        CookieSyncManager.getInstance().sync();
    }*/
    /**
     * JS调用android的方法
     *
     * @param
     * @return
     */
    @JavascriptInterface //仍然必不可少
    public void confirm(String s,String d){
        Toast.makeText(this, s+d, Toast.LENGTH_SHORT).show();
    }


}
