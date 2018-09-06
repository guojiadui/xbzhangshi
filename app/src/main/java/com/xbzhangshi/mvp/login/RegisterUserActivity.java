package com.xbzhangshi.mvp.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.util.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户注册
 */
public class RegisterUserActivity extends BaseActivity {




    @Override
    protected int getlayout() {
        return R.layout.register_user_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initdata() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
