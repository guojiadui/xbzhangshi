package com.xbzhangshi.mvp.details;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;


/**
 * 投注的详情页
 */
public class BettingDetailsActivity extends BaseWebViewActivity {

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
    public String getUrl() {
        return "https://blog.csdn.net/lowprofile_coding/article/details/77928614";
    }
}
