package com.xbzhangshi.mvp.webview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.CookieStore;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;
import com.xbzhangshi.view.CustomToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Cookie;

/**
 * 活动详情
 */
public class ActDescActivity extends BaseWebViewActivity {


    public static void start(Context context, String id) {
        Intent intent = new Intent(context, ActDescActivity.class);
        intent.putExtra("id", id);
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
       String id = getIntent().getStringExtra("id");
        return Url.activeDesc+"?id="+id ;
    }

}
