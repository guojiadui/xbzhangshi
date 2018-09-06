package com.xbzhangshi.mvp.home.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.view.ConfirmDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 用户中心
 */
public class HomeUserCenterFragment extends BaseFragment {

    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.tv_title3)
    TextView tvTitle3;
    @BindView(R.id.icon1)
    ImageView icon1;
    @BindView(R.id.icon2)
    ImageView icon2;
    @BindView(R.id.icon3)
    ImageView icon3;
    @BindView(R.id.icon4)
    ImageView icon4;
    @BindView(R.id.icon5)
    ImageView icon5;
    @BindView(R.id.icon6)
    ImageView icon6;
    @BindView(R.id.icon7)
    ImageView icon7;
    @BindView(R.id.icon8)
    ImageView icon8;
    @BindView(R.id.icon9)
    ImageView icon9;
    @BindView(R.id.icon10)
    ImageView icon10;
    @BindView(R.id.withdrawal_password_modify)
    RelativeLayout withdrawalPasswordModify;
    @BindView(R.id.icon11)
    ImageView icon11;
    @BindView(R.id.fl_control)
    NestedScrollView flControl;
    Unbinder unbinder;

    public static HomeUserCenterFragment newInstance() {
        HomeUserCenterFragment fragment = new HomeUserCenterFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_user_center_fragment;
    }

    @Override
    protected void initView(View view) {
         withdrawalPasswordModify.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String s =null;
                 Log.e("TAG",s);
                /* ConfirmDialog confirmDialog = new ConfirmDialog(mActivity);
                 confirmDialog.show();
                 AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);*/

             }
         });
    }


}
