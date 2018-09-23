package com.xbzhangshi.mvp.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.login.BaseView.ILoginView;
import com.xbzhangshi.mvp.login.adapter.LoginSelectAdapter;
import com.xbzhangshi.mvp.login.presenter.LogInPresenter;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.dialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 用户登录
 */

public class LoginActivity extends BaseActivity implements ILoginView {


    @BindView(R.id.verifyCode_line)
    FrameLayout verifyCodeLine;
    @BindView(R.id.verifyCode)
    EditText verifyCode;
    @BindView(R.id.verifyCode_img)
    ImageView verifyCodeImg;
    @BindView(R.id.verifyCode_layout)
    RelativeLayout verifyCodeLayout;

    public static void startLogin(Activity context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.select_name)
    ImageView selectName;
    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.register_user)
    TextView registerUser;
    @BindView(R.id.free_play)
    TextView freePlay;
    @BindView(R.id.back_home)
    TextView backHome;
    @BindView(R.id.online_customer)
    TextView onlineCustomer;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.checkbox_password)
    CheckBox checkboxPassword;
    @BindView(R.id.customtoolbar)
    CustomToolbar customToolbar;
    LogInPresenter logInPresenter;
    LoadingDialog loadingDialog;

    @Override
    protected int getlayout() {
        return R.layout.login_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        logInPresenter = LogInPresenter.newInstance(this);
        logInPresenter.init();
        customToolbar.setMainTitle("登录");
        customToolbar.mTvMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        selectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHistoryNames(logInPresenter.getNamelist());
            }
        });
        /**
         * 注册
         */
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUserActivity.start(LoginActivity.this);
            }
        });
        onlineCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         * 获取免费账号
         */
        freePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logInPresenter != null) {
                    if (loadingDialog == null) {
                        loadingDialog = new LoadingDialog(LoginActivity.this);
                    }
                    loadingDialog.show();
                    logInPresenter.getFreeUser(LoginActivity.this);
                }
            }
        });
        /**
         * 登录
         */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean r = checkboxPassword.isChecked();
                String name = mName.getText().toString();
                String pwd = mPassword.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (logInPresenter != null) {
                    if (loadingDialog == null) {
                        loadingDialog = new LoadingDialog(LoginActivity.this);
                    }
                    loadingDialog.show();
                    String code = verifyCode.getText().toString();
                    logInPresenter.login(LoginActivity.this, name, pwd, code,r);
                }

            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginSuccessEvent event) {
        finish();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        if (logInPresenter != null) {
            logInPresenter.onDestory();
        }
        OkGo.getInstance().cancelTag(Url.login_code);
    }

    @Override
    protected void initdata() {

    }

    RecyclerView nameRecyclerView;

    private PopupWindow selectPopupWindow = null;

    /**
     * 初始化PopupWindow
     */
    private void showHistoryNames(List<String> list) {

        if (list == null || list.size() == 0) {
            return;
        }
        // PopupWindow浮动下拉框布局
        View loginwindow = (View) this.getLayoutInflater().inflate(
                R.layout.login_user_list, null);
        nameRecyclerView = (RecyclerView) loginwindow.findViewById(R.id.recyclerView);
        nameRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置自定义Adapter
        LoginSelectAdapter optionsAdapter = new LoginSelectAdapter(list);
        optionsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mName.setText(list.get(position));
            }
        });
        nameRecyclerView.setAdapter(optionsAdapter);

        selectPopupWindow = new PopupWindow(loginwindow, getMobileWidth(),
                LinearLayout.LayoutParams.WRAP_CONTENT, true);

        selectPopupWindow.setOutsideTouchable(true);

        selectPopupWindow.setFocusable(false);

        // 这一句是为了实现弹出PopupWindow后，当点击屏幕其他部分及Back键时PopupWindow会消失，
        // 没有这一句则效果不能出来，但并不会影响背景
        // 本人能力极其有限，不明白其原因，还望高手、知情者指点一下
        selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        selectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
               /* iv_openlist.setVisibility(View.VISIBLE);
                iv_closelist.setVisibility(View.GONE);*/
            }
        });
        selectPopupWindow.showAsDropDown(mName, 0, dpToPx(this, 5));
    }

    @Override
    public void setRemmberPwd(Boolean is) {
        checkboxPassword.setChecked(is);
    }

    @Override
    public void setUserInfo(String name, String pwd) {
        if (!TextUtils.isEmpty(name)) {
            mName.setText(name);
        }
        if (!TextUtils.isEmpty(pwd)) {
            mPassword.setText(pwd);
        }
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
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showCode(Bitmap bitmap) {
        verifyCodeLine.setVisibility(View.VISIBLE);
        verifyCodeLayout.setVisibility(View.VISIBLE);
        verifyCodeImg.setImageBitmap(bitmap);
        verifyCodeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(logInPresenter!=null){
                    logInPresenter.upDateCode(LoginActivity.this);
                }
            }
        });
    }


}
