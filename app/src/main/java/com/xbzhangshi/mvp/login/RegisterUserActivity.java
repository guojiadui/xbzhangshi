package com.xbzhangshi.mvp.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.classic.common.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.home.HomeActivity;
import com.xbzhangshi.mvp.login.BaseView.IRegisterView;
import com.xbzhangshi.mvp.login.adapter.RegisterAdapter;
import com.xbzhangshi.mvp.login.bean.RegisterItemBean;
import com.xbzhangshi.mvp.login.presenter.RegisterPresenter;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.dialog.LoadingDialog;

import java.util.List;
import java.util.regex.Pattern;

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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.register_user)
    TextView registerUser;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;


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
        registerPresenter = RegisterPresenter.newInstance(this);

    }

    @Override
    protected void initdata() {
        multipleStatusView.showLoading();
        registerPresenter.getRegconfig(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (registerPresenter != null) {
            registerPresenter.onDestory();
        }
        //验证码
        OkGo.getInstance().cancelTag(Url.BASE_URL + Url.regVerifycode);
    }


    @OnClick({R.id.lt_main_title_left, R.id.register_user, R.id.free_play, R.id.back_login, R.id.back_home, R.id.online_customer, R.id.go_login})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.lt_main_title_left:
                finish();
                break;
            case R.id.register_user://注册
                if (recyclerView != null && recyclerView.getAdapter() != null) {
                    RegisterAdapter registerAdapter = (RegisterAdapter) recyclerView.getAdapter();
                    if (registerAdapter.getData() != null) {
                        registerPresenter.register(this, registerAdapter.getData()
                        );
                    }
                }
                break;
            case R.id.free_play://试玩
                if (loadingDialog == null) {
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
    public void setRegconfig(List<RegisterItemBean.ContentBean> contentBeans) {
        multipleStatusView.showContent();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new RegisterAdapter(this, contentBeans));
    }

    @Override
    public void setRegconfigError() {
        multipleStatusView.showError();
    }

    @Override
    public void registerSuccess() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (registerPresenter != null) {
            registerPresenter.getUserInfo(this);
        }
    }

    @Override
    public void registerError(String msg) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

    @Override
    public void updateCode() {
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            RegisterAdapter registerAdapter = (RegisterAdapter) recyclerView.getAdapter();
            if (registerAdapter.getData() != null && registerAdapter.getData().size() > 1) {
                registerAdapter.notifyItemChanged(registerAdapter.getData().size() - 1);
            }

        }
    }


}
