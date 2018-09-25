package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IUserCenterBaseView;
import com.xbzhangshi.mvp.home.bean.BalanceBean;
import com.xbzhangshi.mvp.home.bean.LogOutBean;
import com.xbzhangshi.mvp.home.bean.MsgCountBean;
import com.xbzhangshi.mvp.home.bean.VIPBean;
import com.xbzhangshi.mvp.home.event.LogoutEvent;
import com.xbzhangshi.mvp.login.LoginSuccessEvent;
import com.xbzhangshi.single.UserInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        getVip(context);
        getConfigure(context);
        getMsgCount(context);
    }

    public void getVip(Context context) {
        Object tag = HttpManager.get(context, Url.getVip, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                VIPBean vipBean = JSON.parseObject(response.body(), VIPBean.class);
                if (vipBean == null)
                    return;
                if (!TextUtils.isEmpty(vipBean.getCurrent())) {
                    contentView.upVip(vipBean.getCurrent());
                }
            }
        });
        addNet(tag);
    }

    /**
     * 获取页面配置信息
     */
    public void getConfigure(Context context) {
        HttpManager.get(context, "http://xbzhanshi.com/native/getUniversalSwitch.do", null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

            }
        });
    }

    /**
     * 登出
     */
    public void Logout(Context context) {
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.logout, null, new StringCallback() {
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


    public void getBalance(Context context) {
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.meminfo, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                BalanceBean balanceBean = JSON.parseObject(response.body(), BalanceBean.class);
                if (balanceBean.isSuccess()) {
                    try {
                        DecimalFormat df = new DecimalFormat("#0.00");
                        contentView.updateBalance(df.format(balanceBean.getContent().getBalance()));
                    } catch (Exception e) {

                    }

                }
            }
        });
        addNet(tag);
    }

    public void getMsgCount(Context context) {
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.getMsgCount, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                MsgCountBean msgCountBean = JSON.parseObject(response.body(), MsgCountBean.class);
                if (msgCountBean.isSuccess()) {
                    if (msgCountBean.getContent() > 0) {
                        contentView.upMsgCount(msgCountBean.getContent() + "");
                    } else {
                        contentView.upMsgCount("");
                    }
                }
            }
        });
        addNet(tag);
    }
}
