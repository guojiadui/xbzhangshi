package com.xbzhangshi.mvp.usercenter.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.usercenter.BaseView.IExchangeBaseView;
import com.xbzhangshi.mvp.usercenter.BaseView.IUpPwdBaseView;
import com.xbzhangshi.mvp.usercenter.bean.ExchangeConfigBean;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.util.DES;

import java.util.regex.Pattern;

public class UpPwdPresenter extends BasePresenter {

    final String loginReg = "^[a-zA-Z0-9]{6,16}$";//登录密码的正则

    public static UpPwdPresenter newInstance(IUpPwdBaseView contentView) {
        return new UpPwdPresenter(contentView);
    }

    IUpPwdBaseView contentView;


    public UpPwdPresenter(IUpPwdBaseView contentView) {
        this.contentView = contentView;
    }


    public void upPwd(Context context, String pwd, String newPwd, String rpwd, int type) {
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(newPwd)) {
            Toast.makeText(context, "新密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(rpwd)) {
            Toast.makeText(context, "重复密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!newPwd.equals(rpwd)) {
            Toast.makeText(context, "俩次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        if (type == 1) {
            //登录密码
            boolean isMatch1 = Pattern.matches(loginReg, newPwd);
            if (!isMatch1) {
                Toast.makeText(context, "密码格式不正确", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        getpwd(context, pwd, newPwd, rpwd, type);
    }


    private void getpwd(Context context, String pwd, String newPwd, String rpwd, int type) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("opwd", pwd);
        httpParams.put("pwd", newPwd);
        httpParams.put("rpwd", rpwd);
        httpParams.put("updType", type);
        Object tag = HttpManager.post(context, Url.up_login_pwd, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ResultBean resultBean = JSON.parseObject(response.body(), ResultBean.class);
                if (resultBean.isSuccess()) {
                    //保存账号密码
                    //否记住密码的钩
                    boolean remmber = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.LOGIN_C_ISCHECK_PWD, false);
                    //加密
                    String p = DES.encryptDES(newPwd, Key.decryptKey);
                    //是否记住密码
                    if (remmber) {
                        SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.REMMBER_USER_PWD, p);
                    } else {
                        SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.REMMBER_USER_PWD, "");
                    }
                    //保存账号密码用于下次进入的登录
                    SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.LOGIN_USER_PWD, p);

                    UserInfo.getInstance().setmPassword(newPwd);
                    contentView.LoginPwdSuccess();
                } else {
                    if (!TextUtils.isEmpty(resultBean.getMsg())) {
                        contentView.LoginPwdError(resultBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.LoginPwdError("请求出错");
            }
        });
        addNet(tag);
    }

}
