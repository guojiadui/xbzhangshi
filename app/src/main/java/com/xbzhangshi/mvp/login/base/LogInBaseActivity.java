package com.xbzhangshi.mvp.login.base;

import android.os.Bundle;

import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BaseActivity;

public abstract class   LogInBaseActivity  extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpManager.IsOpenLogin =true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpManager.IsOpenLogin =false;
    }
}
