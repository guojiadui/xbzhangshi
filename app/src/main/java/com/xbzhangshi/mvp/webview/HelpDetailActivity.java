package com.xbzhangshi.mvp.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.view.CustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 帮助详情
 */
public class HelpDetailActivity extends BaseActivity {

    public static void start(Context context, String content) {
        Intent intent = new Intent(context, HelpDetailActivity.class);
        intent.putExtra("content", content);
        context.startActivity(intent);
    }

    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected int getlayout() {
        return R.layout.help_details_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ltMainTitle.setText("帮助中心详情");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String c = getIntent().getStringExtra("content");
        if (!TextUtils.isEmpty(c)) {
            webview.loadDataWithBaseURL(null, c, "text/html", "utf-8", null);
            webview.getSettings().setJavaScriptEnabled(true);
        }
    }


}
