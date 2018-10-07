package com.xbzhangshi.mvp.base;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.CookieStore;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.home.HomeActivity;
import com.xbzhangshi.mvp.record.AcountDetailsRecordActivity;

import java.util.List;

import okhttp3.Cookie;


/**
 * 投注的详情页
 */
public abstract class BaseWebViewActivity extends BaseActivity {
    public WebView webView;
    private ContentLoadingProgressBar progressBar;

    public abstract int getWebViewId();

    public abstract int getProgressBarId();

    public abstract String getUrl(Bundle savedInstanceState);

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView(Bundle savedInstanceState) {
        progressBar = (ContentLoadingProgressBar) findViewById(getProgressBarId());//进度条
        webView = (WebView) findViewById(getWebViewId());

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


        setCookie(savedInstanceState);
        webView.loadUrl(getUrl(savedInstanceState));//加载url
        // webView.loadUrl("file:///android_asset/test.html");//加载asset文件夹下html

    }

    public void setCookie(Bundle savedInstanceState) {
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        String cookieString = allCookie.get(0).name() + "=" + allCookie.get(0).value() + ";domain=" + allCookie.get(0).domain();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(getUrl(savedInstanceState), cookieString);
        CookieSyncManager.getInstance().sync();
    }


    @Override
    protected void initdata() {

    }

    /**
     * JS调用android的方法
     *
     * @param
     * @return
     */
    @JavascriptInterface //仍然必不可少
    public void confirm(String s) {
        switch (s) {
            case "back":
                finish();
                break;
            case "homePage":
                HomeActivity.start(this); //返回首页
                break;
        }
    }

    @JavascriptInterface //仍然必不可少
    public String isAndroidApp() {
        return "'2222'";
    }

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            Log.i("ansen", "拦截Error:" + errorResponse.getEncoding());
            Log.i("ansen", "拦截Error:" + errorResponse.getMimeType());
            Log.i("ansen", "拦截Error:" + errorResponse.getData());
            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            Log.i("ansen", "拦截onReceivedError:" + error);
            super.onReceivedError(view, request, error);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("ansen", "拦截url:" + url);
            if (url.equals("http://www.google.com/")) {
                return true;//表示我已经处理过了
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient = new WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            Toast.makeText(BaseWebViewActivity.this, "不支持", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定", null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ansen", "网页标题:" + title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar.setProgress(newProgress);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {//点击返回按钮的时候判断有没有上一页
            webView.goBack(); // goBack()表示返回webView的上一页面
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
  /*  @JavascriptInterface //仍然必不可少
    public void  getClient(String str){
        Log.i("ansen","html调用客户端:"+str);
    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            webView.destroy();
            webView = null;
        }


    }


}
