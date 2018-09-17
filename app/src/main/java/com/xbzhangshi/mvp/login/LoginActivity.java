package com.xbzhangshi.mvp.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.login.BaseView.ILoginView;
import com.xbzhangshi.mvp.login.adapter.LoginSelectAdapter;
import com.xbzhangshi.mvp.login.bean.LoginSelectBean;
import com.xbzhangshi.mvp.login.presenter.LogInPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 用户登录
 */

public class LoginActivity extends BaseActivity implements ILoginView {


    public static void startLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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

    LogInPresenter logInPresenter;

    @Override
    protected int getlayout() {
        return R.layout.login_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        logInPresenter = LogInPresenter.newInstance(this);
        logInPresenter.init();
        selectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHistoryNames(logInPresenter.getNamelist());
            }
        });
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        onlineCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean r = checkboxPassword.isChecked();
                String name = mName.getText().toString();
                String pwd = mPassword.getText().toString();
                logInPresenter.login(LoginActivity.this, name, pwd, r);
            }
        });

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

        if(list==null||list.size()==0){
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
}
