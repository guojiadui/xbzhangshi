package com.xbzhangshi.mvp.usercenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.usercenter.BaseView.IUserInfoBaseView;
import com.xbzhangshi.mvp.usercenter.presener.UserInfoPresener;
import com.xbzhangshi.view.CustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户信息
 */
public class UserInfoActivity extends BaseActivity implements IUserInfoBaseView {

    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.real_name)
    TextView realName;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.emial)
    TextView emial;
    @BindView(R.id.qq)
    TextView qq;
    @BindView(R.id.bank_num)
    TextView bankNum;
    @BindView(R.id.opening_bank)
    TextView openingBank;
    @BindView(R.id.bank_Address)
    TextView bankAddress;
    @BindView(R.id.aount)
    TextView aount;

    public static void start(Context context) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }

    UserInfoPresener userInfoPresener;

    @Override
    protected int getlayout() {
        return R.layout.user_lnfo_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        userInfoPresener = UserInfoPresener.newInstance(this);
        ltMainTitle.setText("个人中心");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initdata() {
        userInfoPresener.initData();
    }

    @Override
    public void setAccount(String s) {
        if (!TextUtils.isEmpty(s)) {
            aount.setText(s);
        }
    }

    @Override
    public void setRealName(String s) {
        if(!TextUtils.isEmpty(s)){
            realName.setText(s);
            realName.setOnClickListener(null);
            realName.setBackgroundResource(R.drawable.bg_null);
            realName.setTextColor(0xff5f646e);
        }else {
            realName.setText("立即绑定");
            realName.setBackgroundResource(R.drawable.btn_bg_red2);
            realName.setTextColor(Color.WHITE);
            realName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    @Override
    public void setRPhoneNumber(String s) {
        if(!TextUtils.isEmpty(s)){
            phoneNumber.setText(s);
            phoneNumber.setOnClickListener(null);
            phoneNumber.setBackgroundResource(R.drawable.bg_null);
            phoneNumber.setTextColor(0xff5f646e);
        }else {
            phoneNumber.setText("立即绑定");
            phoneNumber.setBackgroundResource(R.drawable.btn_bg_red2);
            phoneNumber.setTextColor(Color.WHITE);
            phoneNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public void setEmail(String s) {
        if(!TextUtils.isEmpty(s)){
            emial.setText(s);
            emial.setOnClickListener(null);
            emial.setBackgroundResource(R.drawable.bg_null);
            emial.setTextColor(0xff5f646e);
        }else {
            emial.setText("立即绑定");
            emial.setBackgroundResource(R.drawable.btn_bg_red2);
            emial.setTextColor(Color.WHITE);
            emial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public void setQQ(String s) {
        if(!TextUtils.isEmpty(s)){
            qq.setText(s);
            qq.setOnClickListener(null);
            qq.setBackgroundResource(R.drawable.bg_null);
            qq.setTextColor(0xff5f646e);
        }else {
            qq.setText("立即绑定");
            qq.setBackgroundResource(R.drawable.btn_bg_red2);
            qq.setTextColor(Color.WHITE);
            qq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public void setBankNum(String s) {
        if(!TextUtils.isEmpty(s)){
            bankNum.setText(s);
            bankNum.setOnClickListener(null);
            bankNum.setBackgroundResource(R.drawable.bg_null);
            bankNum.setTextColor(0xff5f646e);
        }else {
            bankNum.setText("立即绑定");
            bankNum.setBackgroundResource(R.drawable.bg_rectangle_blue);
            bankNum.setTextColor(Color.WHITE);
            bankNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public void setOpeningBank(String s) {
        if(!TextUtils.isEmpty(s)){
            openingBank.setText(s);
            openingBank.setOnClickListener(null);
            openingBank.setBackgroundResource(R.drawable.bg_null);
            openingBank.setTextColor(0xff5f646e);
        }else {
            openingBank.setText("立即绑定");
            openingBank.setBackgroundResource(R.drawable.bg_rectangle_blue);
            openingBank.setTextColor(Color.WHITE);
            openingBank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public void setBankAddress(String s) {
        if(!TextUtils.isEmpty(s)){
            bankAddress.setText(s);
            bankAddress.setOnClickListener(null);
            bankAddress.setBackgroundResource(R.drawable.bg_null);
            bankAddress.setTextColor(0xff5f646e);
        }else {
            bankAddress.setText("立即绑定");
            bankAddress.setBackgroundResource(R.drawable.btn_bg_red2);
            bankAddress.setTextColor(Color.WHITE);
            bankAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


}
