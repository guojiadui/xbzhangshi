package com.xbzhangshi.mvp.login;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.login.adapter.LoginSelectAdapter;
import com.xbzhangshi.mvp.login.bean.LoginSelectBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户登录
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.select_name)
    ImageView selectName;
    @BindView(R.id.name)
    EditText name;
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
    EditText password;
    @BindView(R.id.checkbox_password)
    CheckBox checkboxPassword;



    @Override
    protected int getlayout() {
        return R.layout.login_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        selectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopuWindow();
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

            }
        });
    }

    @Override
    protected void initdata() {

    }

    RecyclerView nameRecyclerView;
    private List<LoginSelectBean> datas = new ArrayList<LoginSelectBean>();
    private PopupWindow selectPopupWindow = null;

    /**
     * 初始化PopupWindow
     */
    private void initPopuWindow() {

        // PopupWindow浮动下拉框布局
        View loginwindow = (View) this.getLayoutInflater().inflate(
                R.layout.login_user_list, null);
        nameRecyclerView = (RecyclerView) loginwindow.findViewById(R.id.recyclerView);
        nameRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        datas = new ArrayList<LoginSelectBean>();
        datas.add(new LoginSelectBean());
        datas.add(new LoginSelectBean());
        datas.add(new LoginSelectBean());
        datas.add(new LoginSelectBean());
        // 设置自定义Adapter
        LoginSelectAdapter optionsAdapter = new LoginSelectAdapter(datas);
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
        selectPopupWindow.showAsDropDown(name, 0, dpToPx(this,5));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
