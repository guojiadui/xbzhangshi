package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IHomeBaseView;
import com.xbzhangshi.mvp.home.baseView.IUserCenterBaseView;
import com.xbzhangshi.mvp.home.bean.BalanceBean;
import com.xbzhangshi.mvp.home.bean.LogOutBean;
import com.xbzhangshi.mvp.home.event.LogoutEvent;
import com.xbzhangshi.mvp.login.LoginSuccessEvent;
import com.xbzhangshi.single.ServiceTime;
import com.xbzhangshi.single.UserInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.security.spec.ECField;
import java.text.DecimalFormat;

public class UserCenterPresenter extends BasePresenter {

    public static UserCenterPresenter newInstance(IUserCenterBaseView contentView) {
        return new UserCenterPresenter(contentView);
    }

    IUserCenterBaseView contentView;


    public UserCenterPresenter(IUserCenterBaseView contentView) {
        this.contentView = contentView;

    }

    public void init(Context context) {
        if (!UserInfo.getInstance().isLogin) {
            return;
        }
        contentView.setUserinfo(UserInfo.getInstance().mUsername);
        getBalance(context);
    }

    //成功
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginSuccessEvent event) {
       /* if (noLoginLayout != null) {
            noLoginLayout.setVisibility(View.GONE);
            balance.setVisibility(View.VISIBLE);
            balance.setText("");
            if (bettingPresenter != null) {
                //获取余额
                bettingPresenter.getBalance(mActivity);
            }
        }*/
    }

    //退出
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogoutEvent event) {
      /*  if (noLoginLayout != null) {
            noLoginLayout.setVisibility(View.VISIBLE);
            balance.setVisibility(View.GONE);
            balance.setText("");
        }*/
    }

    /**
     * 登出
     */
    public void Logout(Context context) {
        Object tag = HttpManager.get(context, URL.BASE_URL + URL.logout, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogOutBean logOutBean = JSON.parseObject(response.body(), LogOutBean.class);
                if (logOutBean.isSuccess()) {
                    //清空自动登录的账号
                    SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.LOGIN_USER_NAME, "");
                    SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.LOGIN_USER_PWD, "");
                    //清空内存数据
                    UserInfo.getInstance().logout();
                    contentView.LogoutSuccess();
                    EventBus.getDefault().post(new LogoutEvent());
                } else {
                    contentView.LogoutonError();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.LogoutonError();
            }
        });
        addNet(tag);
    }

    /**
     * 获取余额
     */
    public void getBalance(Context context) {
        Object tag = HttpManager.get(context, URL.BASE_URL + URL.meminfo, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                BalanceBean balanceBean = JSON.parseObject(response.body(), BalanceBean.class);
                if (balanceBean.isSuccess()) {
                    try {
                        contentView.updateBalance(subZeroAndDot(balanceBean.getContent().getBalance()+""));
                    }catch (Exception e){

                    }

                }
            }
        });
        addNet(tag);
    }
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
