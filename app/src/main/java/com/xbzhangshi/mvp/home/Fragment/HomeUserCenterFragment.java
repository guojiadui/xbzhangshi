package com.xbzhangshi.mvp.home.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.betting.ElectronicGameActivity;
import com.xbzhangshi.mvp.home.adapter.UserCenterAdapter;
import com.xbzhangshi.mvp.home.baseView.IUserCenterBaseView;
import com.xbzhangshi.mvp.home.bean.USerCenterOnOffBean;
import com.xbzhangshi.mvp.home.bean.VIPBean;
import com.xbzhangshi.mvp.home.event.ClearHomeMsgEvent;
import com.xbzhangshi.mvp.home.presenter.UserCenterPresenter;
import com.xbzhangshi.mvp.login.LoginSuccessEvent;
import com.xbzhangshi.mvp.record.AcountChangeActivity;
import com.xbzhangshi.mvp.record.AcountDetailsRecordActivity;
import com.xbzhangshi.mvp.record.LHCLotteryRecordActivity;
import com.xbzhangshi.mvp.record.LotteryRecordActivity;
import com.xbzhangshi.mvp.record.SportsRecordActivity;
import com.xbzhangshi.mvp.record.ThreeLotteryRecordActivity;
import com.xbzhangshi.mvp.record.adapter.ElectronicsLotteryRecordActivity;
import com.xbzhangshi.mvp.usercenter.BindingBankCardActivity;
import com.xbzhangshi.mvp.usercenter.DrawingMoneyActivity;
import com.xbzhangshi.mvp.usercenter.ExchangeActivity;
import com.xbzhangshi.mvp.usercenter.MessageListActivity;
import com.xbzhangshi.mvp.usercenter.SetPasswordActivity;
import com.xbzhangshi.mvp.usercenter.UpdatePasswordActivity;
import com.xbzhangshi.mvp.usercenter.UserInfoActivity;
import com.xbzhangshi.mvp.usercenter.event.UpdateMsgCount;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.view.DividerGridItemDecoration;
import com.xbzhangshi.view.GlideCircleBorderTransform;
import com.xbzhangshi.view.dialog.TipDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.TimeoutException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 用户中心
 */
public class HomeUserCenterFragment extends BaseFragment implements IUserCenterBaseView {

    @BindView(R.id.name)
    TextView userName;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.vip)
    TextView vip;
    @BindView(R.id.msg_count)
    TextView msgCount;
    @BindView(R.id.vip_grade)
    TextView vipGrade;
    @BindView(R.id.vip_give)
    TextView vipGive;
    @BindView(R.id.vip_info_layout)
    LinearLayout vipInfoLayout;
    @BindView(R.id.vip_line)
    View vipLine;
    @BindView(R.id.vip_next)
    TextView vipNext;
    @BindView(R.id.up_vip_need)
    TextView upVipNeed;


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

    @OnClick({R.id.tv_title1, R.id.tv_title2, R.id.tv_title3, R.id.layout1, R.id.vip,
            R.id.layout2, R.id.layout3, R.id.layout4, R.id.layout5, R.id.layout6, R.id.layout7,
            R.id.layout8, R.id.layout9, R.id.layout10, R.id.layout11, R.id.layout12, R.id.msg_count_layout, R.id.logout})
    public void view(View v) {
        switch (v.getId()) {
            case R.id.vip:
                if (vipInfoLayout.getVisibility() == View.VISIBLE) {
                    vipInfoLayout.setVisibility(View.GONE);
                    vipLine.setVisibility(View.GONE);
                } else {
                    vipInfoLayout.setVisibility(View.VISIBLE);
                    vipLine.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_title1:
                break;
            case R.id.tv_title2:
                //提款
                if (UserInfo.getInstance().getLoginUserInfoBean() != null) {
                    String p = UserInfo.getInstance().getLoginUserInfoBean().getContent().getReceiptPwd();
                    if (TextUtils.isEmpty(p)) {
                        //设置取款密码
                        SetPasswordActivity.start(mActivity);
                        return;
                    }
                    //判断真是姓名，银行，卡号绑定银行卡
                    if (TextUtils.isEmpty(UserInfo.getInstance().loginUserInfoBean.getContent().getUserName()) ||
                            TextUtils.isEmpty(UserInfo.getInstance().loginUserInfoBean.getContent().getBankName()) ||
                            TextUtils.isEmpty(UserInfo.getInstance().loginUserInfoBean.getContent().getCardNo())) {
                        BindingBankCardActivity.start(mActivity);
                        return;
                    }
                    //提款
                    DrawingMoneyActivity.start(mActivity);
                }
                break;
            case R.id.tv_title3:
                break;
            case R.id.layout1://彩票投注记录
                LotteryRecordActivity.start(mActivity);
                break;
            case R.id.layout2://三方彩票记录
                ThreeLotteryRecordActivity.start(mActivity,"三方彩票记录",ThreeLotteryRecordActivity.type1);
                break;
            case R.id.layout3://六合投注记录
                LHCLotteryRecordActivity.start(mActivity);
                break;
            case R.id.layout4://体育投注记录
                SportsRecordActivity.start(mActivity);
                break;
            case R.id.layout5://真人投注记录
                ThreeLotteryRecordActivity.start(mActivity,"真人投注记录",ThreeLotteryRecordActivity.type2);
                break;
            case R.id.layout6://棋牌游戏记录
                ThreeLotteryRecordActivity.start(mActivity,"棋牌游戏记录",ThreeLotteryRecordActivity.type3);
                break;
            case R.id.layout7://电子游戏记录
                ElectronicsLotteryRecordActivity.start(mActivity);
                break;
            case R.id.layout8://用户账变记录
                AcountChangeActivity.start(mActivity);
                break;
            case R.id.layout9://用户明细记录
                AcountDetailsRecordActivity.start(mActivity);
                break;
            case R.id.layout10://登录密码修改
                UpdatePasswordActivity.start(mActivity, UpdatePasswordActivity.type1);
                break;
            case R.id.layout11://取款密码修改
                if (UserInfo.getInstance().getLoginUserInfoBean() != null) {
                    String pwd = UserInfo.getInstance().getLoginUserInfoBean().getContent().getReceiptPwd();//取款密码
                    if (TextUtils.isEmpty(pwd)) {
                        //没有取款密码
                        SetPasswordActivity.start(mActivity);
                    } else {
                        //有取款密码
                        UpdatePasswordActivity.start(mActivity, UpdatePasswordActivity.type2);
                    }
                }
                break;
            case R.id.layout12://积分兑换
                ExchangeActivity.start(mActivity);
                break;
            case R.id.msg_count_layout://我的站内信息
                if (userCenterPresenter != null) {
                    MessageListActivity.start(mActivity);
                }
                break;
            case R.id.logout://登出
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

        }
    }

    @Override
    protected void initView(View view) {
        userCenterPresenter = UserCenterPresenter.newInstance(this);
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
        if (!TextUtils.isEmpty(name))
            userName.setText(name.replace(" ", "").replace("\n", ""));
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
    public void upVip(VIPBean bean) {
        if (!TextUtils.isEmpty(bean.getCurrent())) {
            vip.setText(bean.getCurrent().toUpperCase());
            vipGrade.setText(bean.getCurrent().toUpperCase());
        }
        if (!TextUtils.isEmpty(bean.getNext())) {
            vipNext.setText(bean.getNext().toUpperCase());
        }
        vipGive.setText(bean.getGifMoney() + "");
        upVipNeed.setText(bean.getNeed() + "");

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

    @Override
    public void setConfig(List<USerCenterOnOffBean> list) {
       /* UserCenterAdapter userCenterAdapter = new UserCenterAdapter(list);
        recyclerView.setAdapter(userCenterAdapter);
        userCenterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //修改取款密码


            }
        });*/
    }


}
