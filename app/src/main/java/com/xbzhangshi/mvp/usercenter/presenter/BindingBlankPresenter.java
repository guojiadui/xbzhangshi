package com.xbzhangshi.mvp.usercenter.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.android.tu.loadingdialog.LoadingDailog;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.login.bean.LoginUserInfoBean;
import com.xbzhangshi.mvp.usercenter.BaseView.IBindingBlankBaseView;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;
import com.xbzhangshi.mvp.usercenter.event.UpUserInfoEvent;
import com.xbzhangshi.single.UserInfo;

import org.greenrobot.eventbus.EventBus;

public class BindingBlankPresenter extends BasePresenter {
    public static BindingBlankPresenter newInstance(IBindingBlankBaseView contentView) {
        return new BindingBlankPresenter(contentView);
    }

    IBindingBlankBaseView contentView;


    public BindingBlankPresenter(IBindingBlankBaseView contentView) {
        this.contentView = contentView;
    }

    LoadingDailog loadingDialog;

    public void commit(Context context, String name, String bankAddress, String blankName, String blankCar, String pwd) {
        if (TextUtils.isEmpty(name)) {
            toast(context, "持卡人不能为空");
            return;
        }
        if (TextUtils.isEmpty(blankName)) {
            toast(context, "银行不能为空");
            return;
        }
        if (TextUtils.isEmpty(blankCar)) {
            toast(context, "银行卡号不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            toast(context, "提款密码不能为空");
            return;
        }
        if (loadingDialog == null) {
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                    .setMessage("加载中...")
                    .setCancelable(true)
                    .setCancelOutside(true);
            loadingDialog = loadBuilder.create();
        }
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("userName", name);
        httpParams.put("bankName", blankName);
        if (!TextUtils.isEmpty(bankAddress))
            httpParams.put("bankAddress", bankAddress);
        httpParams.put("cardNo", blankCar);
        httpParams.put("repPwd", pwd);
        Object tag = HttpManager.postObject(context,ResultBean.class, Url.bindingBlank, httpParams, new OkGoCallback<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) {
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
                if (response.isSuccess()) {
                    getUserInfo(context);
                } else {
                    if (loadingDialog != null && loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                    }
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        contentView.error(response.getMsg());
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
                contentView.error("绑定失败");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
                contentView.error("请求出错");
            }
        });
        addNet(tag);
    }

    /**
     * 更新本地的用户信息
     */

    public void getUserInfo(Context context) {
        Object tag = UserInfo.getInstance().getUserInfo(context, LoginUserInfoBean.class, new OkGoCallback<LoginUserInfoBean>() {
            @Override
            public void onSuccess(LoginUserInfoBean response) {
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }

                if (response.isSuccess()) {
                    UserInfo.getInstance().setLoginUserInfoBean(response);
                    contentView.success();
                    EventBus.getDefault().post(new UpUserInfoEvent());
                } else {
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        contentView.error(response.getMsg());
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
                contentView.error("请求出错");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
                contentView.error("请求出错");
            }
        });
        addNet(tag);
    }

}
