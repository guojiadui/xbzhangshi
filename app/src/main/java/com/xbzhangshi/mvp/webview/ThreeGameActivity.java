package com.xbzhangshi.mvp.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.xbzhangshi.mvp.webview.bean.TransUrlBean;

import java.util.List;

import okhttp3.Cookie;

public class ThreeGameActivity extends BaseWebViewActivity {
   MultipleStatusView multipleStatusView;
    public static  void start(Context context,String code){
        Intent intent =new Intent(context,ThreeGameActivity.class);
        intent.putExtra("code",code);
        context.startActivity(intent);
    }
  String code ;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView(Bundle savedInstanceState) {
        progressBar = (ContentLoadingProgressBar) findViewById(getProgressBarId());//进度条
        webView = (WebView) findViewById(getWebViewId());
        multipleStatusView = findViewById(R.id.multipleStatusView);
        code  = getIntent().getStringExtra("code");
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUrl();
            }
        });
        getUrl();
    }
    public  void getUrl(){

        multipleStatusView.showLoading();
        HttpManager.getObject(this, TransUrlBean.class, Url.forwardReal+code, null, new OkGoCallback<TransUrlBean>() {
            @Override
            public void onSuccess(TransUrlBean response) {
                multipleStatusView.showContent();
                if(response.isSuccess()){
                    initWeb(response.getContent().getUrl());
                }else {
                    if(!TextUtils.isEmpty(response.getMsg())){
                        Toast.makeText(ThreeGameActivity.this,response.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void parseError() {
                multipleStatusView.showError();
            }

            @Override
            public void onError(Response<String> response) {
                multipleStatusView.showError();
            }
        });
    }

    public void initWeb(String url ) {
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
        setCookie(url );
        webView.loadUrl(url);//加载url
    }
    public void setCookie(String url) {
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        String cookieString = allCookie.get(0).name() + "=" + allCookie.get(0).value() + ";domain=" + allCookie.get(0).domain();
        CookieManager cookieManager = CookieManager.getInstance();
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
