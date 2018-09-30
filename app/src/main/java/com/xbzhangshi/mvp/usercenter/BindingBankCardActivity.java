package com.xbzhangshi.mvp.usercenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.usercenter.BaseView.IBindingBlankBaseView;
import com.xbzhangshi.mvp.usercenter.presenter.BindingBlankPresenter;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.dialog.TipDialog;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 绑定银行卡
 */
public class BindingBankCardActivity extends BaseActivity implements IBindingBlankBaseView {

    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;

    BindingBlankPresenter bindingBlankPresenter;
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.blank_name)
    AppCompatSpinner blankName;
    @BindView(R.id.bank_address)
    EditText bankAddress;
    @BindView(R.id.blank_car)
    EditText blankCar;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.next)
    TextView next;
    final String[] blanks = {"建设银行", "工商银行", "工商银行", "农业银行", "中国邮政银行", "中国银行",
            "中国招商银行", "中国交通银行", "中国民生银行", "中信银行", "中国兴业银行", "浦发银行", "平安银行",
            "华夏银行", "广州银行", "BEA东亚银行", "广州农商银行", "顺德农商银行", "北京银行", "杭州银行", "温州银行",
            "上海农商银行", "中国光大银行", "渤海银行", "浙商银行", "晋商银行", "汉口银行", "上海银行", "广发银行",
            "深圳发展银行", "东莞银行", "宁波银行", "南京银行", "北京农商银行", "重庆银行", "广西农村信用社", "吉林银行",
            "江苏银行", "成都银行", "尧都区农村信用联社", "浙江稠州商业银行", "珠海市农村信用合作联社", "其他"};

    String curblank = blanks[0];

    public static void start(Context context) {
        Intent intent = new Intent(context, BindingBankCardActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getlayout() {
        return R.layout.binding_bank_car_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        bindingBlankPresenter = BindingBlankPresenter.newInstance(this);
        ltMainTitle.setText("绑定新的银行卡");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, blanks);
        blankName.setAdapter(spinnerAdapter);
        blankName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                curblank = blanks[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bindingBlankPresenter != null) {
                    bindingBlankPresenter.commit(BindingBankCardActivity.this,
                            name.getText().toString(),
                            bankAddress.getText().toString(),
                            curblank,
                            blankCar.getText().toString(),
                            pwd.getText().toString());
                }
            }
        });
        if (UserInfo.getInstance().getLoginUserInfoBean() != null &&
                !TextUtils.isEmpty(UserInfo.getInstance().getLoginUserInfoBean().getContent().getUserName())) {
            name.setText(UserInfo.getInstance().getLoginUserInfoBean().getContent().getUserName());
            return;
        }
        //初始化
       /* if(UserInfo.getInstance().getLoginUserInfoBean()!=null){
            if(!TextUtils.isEmpty(UserInfo.getInstance().loginUserInfoBean.getContent().getUserName())){
                name.setText(UserInfo.getInstance().loginUserInfoBean.getContent().getUserName());
            }
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bindingBlankPresenter != null) {
            bindingBlankPresenter.onDestory();
        }
    }

    @Override
    public void success() {
        TipDialog tipDialog = new TipDialog(this, "设置成功！", "返回", "马上提款", new TipDialog.ClickListener() {
            @Override
            public void but1(Dialog dialog, View v) {
                dialog.dismiss();
                finish();
            }

            @Override
            public void but2(Dialog dialog, View v) {
                dialog.dismiss();
                finish();
                DrawingMoneyActivity.start(BindingBankCardActivity.this);
            }
        });
        tipDialog.show();
    }

    @Override
    public void error(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
