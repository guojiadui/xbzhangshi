package com.xbzhangshi.mvp.home.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.baseView.IUserCenterBaseView;
import com.xbzhangshi.mvp.home.event.ClearHomeMsgEvent;
import com.xbzhangshi.mvp.home.presenter.UserCenterPresenter;
import com.xbzhangshi.mvp.login.LoginSuccessEvent;
import com.xbzhangshi.mvp.record.AcountChangeActivity;
import com.xbzhangshi.mvp.record.LotteryRecordActivity;
import com.xbzhangshi.mvp.record.SportsRecordActivity;
import com.xbzhangshi.mvp.usercenter.DrawingMoneyActivity;
import com.xbzhangshi.mvp.usercenter.ExchangeActivity;
import com.xbzhangshi.mvp.usercenter.MessageListActivity;
import com.xbzhangshi.mvp.usercenter.SetPasswordActivity;
import com.xbzhangshi.mvp.usercenter.UpdatePasswordActivity;
import com.xbzhangshi.mvp.usercenter.UserInfoActivity;
import com.xbzhangshi.mvp.usercenter.event.UpdateMsgCount;
import com.xbzhangshi.view.GlideCircleBorderTransform;
import com.xbzhangshi.view.dialog.TipDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户中心
 */
public class HomeUserCenterFragment extends BaseFragment implements IUserCenterBaseView {

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
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.vip)
    TextView vip;
    @BindView(R.id.msg_count)
    TextView msgCount;
    @BindView(R.id.record)
    RelativeLayout record;


    public static HomeUserCenterFragment newInstance() {
        HomeUserCenterFragment fragment = new HomeUserCenterFragment();
        return fragment;
    }

    UserCenterPresenter userCenterPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.home_user_center_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Override
    protected void initView(View view) {
        userCenterPresenter = UserCenterPresenter.newInstance(this);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // AcountChangeActivity.start(mActivity);
                SportsRecordActivity.start(mActivity);
            }
        });
        withdrawalPasswordModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  ExchangeActivity.start(mActivity);
                //   UpdatePasswordActivity.start(mActivity,1);
               // SetPasswordActivity.start(mActivity);
             //   DrawingMoneyActivity.start(mActivity);
               // LotteryRecordActivity.start(mActivity);
                AcountChangeActivity.start(mActivity);
                SportsRecordActivity.start(mActivity);
            }
        });
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoActivity.start(mActivity);
            }
        });
        EventBus.getDefault().post(new ClearHomeMsgEvent());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            EventBus.getDefault().post(new ClearHomeMsgEvent());
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        userCenterPresenter.init(mActivity);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        if (userCenterPresenter != null) {
            userCenterPresenter.onDestory();
        }
    }

    @Override
    public void setUserVisibleHint(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.d("TAG", "setUserVisibleHint" + toString() + ";   isVisibleToUser:" + hidden);
    }

    @OnClick({R.id.logout, R.id.msg_count_layout})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.logout:
                TipDialog tipDialog = new TipDialog(mActivity, "确定要退出吗?", "", "", new TipDialog.ClickListener() {
                    @Override
                    public void but1(Dialog dialog, View v) {
                        dialog.dismiss();
                    }

                    @Override
                    public void but2(Dialog dialog, View v) {
                        dialog.dismiss();
                        if (userCenterPresenter != null) {
                            userCenterPresenter.Logout(mActivity);
                        }
                    }
                });
                tipDialog.show();
                break;
            case R.id.msg_count_layout:
                if (userCenterPresenter != null) {
                    MessageListActivity.start(mActivity);
                }
                break;
        }
    }

    //成功
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginSuccessEvent event) {
        if (userCenterPresenter != null) {
            userCenterPresenter.init(mActivity);
        }
    }

    //更新未读
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UpdateMsgCount event) {
        if (userCenterPresenter != null) {
            userCenterPresenter.getMsgCount(mActivity);
        }
    }

    @Override
    public void setUserinfo(String name) {
        userName.setText(name);
        RequestOptions requestOptions = new RequestOptions().transform(new GlideCircleBorderTransform(9, 0xffff5555));
        Glide.with(this).load("http://xbzhanshi.com/mobile/v3/images/touxiang.png").apply(requestOptions).into(userIcon);
    }

    @Override
    public void LogoutSuccess() {
        userName.setText("");
        balance.setText("可用余额: ");
    }

    @Override
    public void LogoutonError() {
        Toast.makeText(mActivity, "登出出错", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateBalance(String meg) {
        balance.setText("可用余额: " + meg + "元");
    }

    @Override
    public void upVip(String meg) {
        vip.setText(meg);
    }

    @Override
    public void upMsgCount(String msg) {
        if (TextUtils.isEmpty(msg)) {
            msgCount.setVisibility(View.INVISIBLE);
        } else {
            msgCount.setVisibility(View.VISIBLE);
            msgCount.setText(msg);
        }
    }


}
