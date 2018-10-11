package com.xbzhangshi.mvp.usercenter.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.usercenter.BaseView.ISetPwdBaseView;
import com.xbzhangshi.mvp.usercenter.BaseView.IUpPwdBaseView;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;
import com.xbzhangshi.single.UserInfo;

public class SetPwdPresenter extends BasePresenter {
    public static SetPwdPresenter newInstance(ISetPwdBaseView contentView) {
        return new SetPwdPresenter(contentView);
    }

    ISetPwdBaseView contentView;


    public SetPwdPresenter(ISetPwdBaseView contentView) {
        this.contentView = contentView;
    }


    public void setPwd(Context context, String pwd, String rpwd) {

        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(rpwd)) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.equals(rpwd)) {
            Toast.makeText(context, "俩次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        HttpParams httpParams = new HttpParams();
        httpParams.put("pwd", pwd);
        httpParams.put("rpwd", rpwd);
        HttpManager.postObject(context, ResultBean.class, Url.drawing_money_pwd, httpParams, new OkGoCallback<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) {
                if (response.isSuccess()&&UserInfo.getInstance().getLoginUserInfoBean()!=null) {
                    UserInfo.getInstance().getLoginUserInfoBean().getContent().setReceiptPwd("********");//用假密码代替
                    contentView.success();
                } else {
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        contentView.error(response.getMsg());
                    }
                }

            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.error("请求出错");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.error("请求出错");
            }
        });
    }

}
