package com.xbzhangshi.mvp.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;

import com.classic.common.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.home.HomeActivity;
import com.xbzhangshi.mvp.login.BaseView.IRegisterView;
import com.xbzhangshi.mvp.login.adapter.RegisterAdapter;
import com.xbzhangshi.mvp.login.base.RegisterBaseActivty;
import com.xbzhangshi.mvp.login.bean.RegisterItemBean;
import com.xbzhangshi.mvp.login.presenter.RegisterPresenter;
import com.xbzhangshi.mvp.webview.CustomerServiceActivity;
import com.xbzhangshi.view.CustomToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户注册
 */
public class RegisterUserActivity extends RegisterBaseActivty implements IRegisterView {

    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.register_user)
    TextView registerUser;
    @BindView(R.id.online_customer)
    TextView onlineCustomer;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    LoadingDailog loadingDialog;

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
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multipleStatusView.showLoading();
                registerPresenter.getRegconfig(RegisterUserActivity.this);
            }
        });
        onlineCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerServiceActivity.start(RegisterUserActivity.this);
            }
        });
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
                    LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(RegisterUserActivity.this)
                            .setMessage("加载中...")
                            .setCancelable(true)
                            .setCancelOutside(true);
                    loadingDialog=loadBuilder.create();
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
    public void registerSuccess(String name,String  pwd) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (registerPresenter != null) {
            registerPresenter.login(this,name,pwd);
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
