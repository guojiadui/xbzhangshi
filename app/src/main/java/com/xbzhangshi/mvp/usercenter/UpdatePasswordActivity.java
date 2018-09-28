package com.xbzhangshi.mvp.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.usercenter.BaseView.IUpPwdBaseView;
import com.xbzhangshi.mvp.usercenter.presenter.UpPwdPresenter;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.dialog.ExchangeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 更改密码
 */
public class UpdatePasswordActivity extends BaseActivity implements IUpPwdBaseView {

    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.new_pwd)
    EditText newPwd;
    @BindView(R.id.rpwd)
    EditText rpwd;
    @BindView(R.id.commit)
    TextView commit;

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, UpdatePasswordActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    public static  final int type1 = 1;//登录密码
    public static final int type2 = 2;//取款密码

    int curType;
    UpPwdPresenter upPwdPresenter;

    @Override
    protected int getlayout() {
        return R.layout.update_password_activity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (upPwdPresenter != null) {
            upPwdPresenter.onDestory();
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        upPwdPresenter = UpPwdPresenter.newInstance(this);
        curType = getIntent().getIntExtra("type", 0);
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (curType == type1) {
            ltMainTitle.setText("修改登录密码");
        } else if (curType == type2) {
            ltMainTitle.setText("修改取款密码");
        }
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upPwdPresenter != null) {
                    upPwdPresenter.upPwd(UpdatePasswordActivity.this,
                            pwd.getText().toString(),
                            newPwd.getText().toString(),
                            rpwd.getText().toString(),
                            curType);
                }
            }
        });
    }

    @Override
    protected void initdata() {

    }


    @Override
    public void LoginPwdSuccess() {
        ExchangeDialog exchangeDialog = new ExchangeDialog(this, "修改成功");
        exchangeDialog.show();
    }

    @Override
    public void LoginPwdError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
