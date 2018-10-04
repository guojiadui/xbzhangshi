package com.xbzhangshi.mvp.webview;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;


import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;


/**
 * 彩票投注的详情页
 */
public class BettingDetailsActivity extends BaseWebViewActivity {

    public  static  void  start(Context context,String code){
        Intent intent = new Intent(context,BettingDetailsActivity.class);
        intent.putExtra("code",code);
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
        return "http://xbzhanshi.com/mobile/v3/bet_lotterys.do?lotCode="+code;
    }

    /**
     * JS调用android的方法
     *
     * @param
     * @return
     */
    @JavascriptInterface //仍然必不可少
    public void confirm(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}
