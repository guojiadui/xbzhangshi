package com.xbzhangshi.mvp.usercenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.usercenter.BaseView.ISetPwdBaseView;
import com.xbzhangshi.mvp.usercenter.presenter.SetPwdPresenter;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.dialog.TipDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置取款密码
 */
public class SetPasswordActivity extends BaseActivity implements ISetPwdBaseView {

    public static void start(Context context) {
        Intent intent = new Intent(context, SetPasswordActivity.class);
        context.startActivity(intent);
    }


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
    @BindView(R.id.rpwd)
    EditText rpwd;
    @BindView(R.id.next)
    TextView next;

    SetPwdPresenter setPwdPresenter;

    @Override
    protected int getlayout() {
        return R.layout.set_password_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setPwdPresenter = SetPwdPresenter.newInstance(this);
        ltMainTitle.setText("首次设置取款密码");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setPwdPresenter != null) {
                    setPwdPresenter.setPwd(SetPasswordActivity.this, pwd.getText().toString(), rpwd.getText().toString());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (setPwdPresenter != null) {
            setPwdPresenter.onDestory();
        }
    }

    @Override
    public void success() {
        TipDialog tipDialog = new TipDialog(this, "设置成功！", "返回首页", "继续设置", new TipDialog.ClickListener() {
            @Override
            public void but1(Dialog dialog, View v) {
                dialog.dismiss();
                finish();
            }

            @Override
            public void but2(Dialog dialog, View v) {
                dialog.dismiss();
                finish();
                BindingBankCardActivity.start(SetPasswordActivity.this);
            }
        });
        tipDialog.show();
    }

    @Override
    public void error(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
