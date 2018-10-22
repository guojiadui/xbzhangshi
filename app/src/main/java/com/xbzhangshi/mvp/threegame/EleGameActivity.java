package com.xbzhangshi.mvp.threegame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.classic.common.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.CookieStore;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;
import com.xbzhangshi.mvp.login.LoginActivity;
import com.xbzhangshi.mvp.webview.bean.TransUrlBean;
import com.xbzhangshi.single.UserInfo;

import java.util.List;

import okhttp3.Cookie;

/**
 * 三方电子游戏
 */
public class EleGameActivity extends BaseWebViewActivity {

    public static long startitme = 0;

    public static void start(Context context, String code) {

        long cur = System.currentTimeMillis();
        if ((cur - startitme) < 500) {
            return;
        }
        startitme = cur;
        if (!UserInfo.getInstance().isLogin) {
            LoginActivity.startLogin(context);
            return;
        }
        Intent intent = new Intent(context, EleGameActivity.class);
        intent.putExtra("code", code);
        context.startActivity(intent);
    }

    MultipleStatusView multipleStatusView;
    String code;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView(Bundle savedInstanceState) {
        progressBar = (ContentLoadingProgressBar) findViewById(getProgressBarId());//进度条
        webView = (WebView) findViewById(getWebViewId());
        multipleStatusView = findViewById(R.id.multipleStatusView);
        code = getIntent().getStringExtra("code");
        String url =Url.ele_index+code;
        initWeb(url);
    }



    public void initWeb(String url) {
        webView.addJavascriptInterface(this, "android");//添加js监听 这样html就能调用客户端
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);
        WebSettings settings = webView.getSettings();

// webview启用javascript支持 用于访问页面中的javascript
        settings.setJavaScriptEnabled(true);

        //设置WebView缓存模式 默认断网情况下不缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//让WebView支持DOM storage API
        settings.setDomStorageEnabled(true);

//让WebView支持播放插件
        settings.setPluginState(WebSettings.PluginState.ON);
//设置在WebView内部是否允许访问文件
        settings.setAllowFileAccess(true);
//设置脚本是否允许自动打开弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
// 加快HTML网页加载完成速度
        /*if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }*/
// 设置编码格式
        settings.setDefaultTextEncodingName("utf-8");
        setCookie(url);
        webView.loadUrl(url);//加载url
    }

    public void setCookie(String url) {
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        String cookieString = allCookie.get(0).name() + "=" + allCookie.get(0).value() + ";domain=" + allCookie.get(0).domain();
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, cookieString);
        CookieSyncManager.getInstance().sync();
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
        return null;
    }

    @Override
    protected int getlayout() {
        return R.layout.three_game_activity_layout;
    }
}
