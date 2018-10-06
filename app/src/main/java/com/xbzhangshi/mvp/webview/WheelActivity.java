package com.xbzhangshi.mvp.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseWebViewActivity;


/**
 * 签到
 */
public class WheelActivity extends BaseWebViewActivity {

    public  static  void  start(Context context ){
        Intent intent = new Intent(context,WheelActivity.class);
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

        return "http://xbzhanshi.com/mobile/v3/turnlate.do" ;
    }

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
