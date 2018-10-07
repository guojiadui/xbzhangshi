package com.xbzhangshi.mvp.webview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;

/**
 * 客服
 */
public class CustomerServiceActivity extends BaseWebViewActivity {


    public static void start(Context context ) {
        Intent intent = new Intent(context, CustomerServiceActivity.class);
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

        return Url.customerService;
    }

}
