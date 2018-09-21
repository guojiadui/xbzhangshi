package com.xbzhangshi.mvp.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.home.HomeActivity;
import com.xbzhangshi.mvp.login.BaseView.IRegisterView;
import com.xbzhangshi.mvp.login.presenter.RegisterPresenter;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.dialog.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户注册
 */
public class RegisterUserActivity extends BaseActivity implements IRegisterView {

    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    LoadingDialog loadingDialog;
    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterUserActivity.class);
        context.startActivity(intent);
    }
   RegisterPresenter registerPresenter;

    @Override
    protected int getlayout() {
        return R.layout.register_user_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        customtoolbar.setMainTitle("快速注册");
        registerPresenter =RegisterPresenter.newInstance(this);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (registerPresenter != null) {
            registerPresenter.onDestory();
        }
    }
    @OnClick({R.id.lt_main_title_left, R.id.register_user, R.id.free_play, R.id.back_login, R.id.back_home, R.id.online_customer, R.id.go_login})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.lt_main_title_left:
                finish();
                break;
            case R.id.register_user://注册
                break;
            case R.id.free_play://试玩
                if(loadingDialog==null){
                    loadingDialog = new LoadingDialog(this);
                }
                loadingDialog.show();
                registerPresenter.getFreeUser(this);
                break;
            case R.id.back_login:
            case R.id.go_login:
                LoginActivity.startLogin(this);
                finish();
                break;
            case R.id.back_home:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.online_customer: //在线客服
                break;

        }
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

    @Override
    public void loginSuccess() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        finish();
    }

    @Override
    public void LoginonError(String msg) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
