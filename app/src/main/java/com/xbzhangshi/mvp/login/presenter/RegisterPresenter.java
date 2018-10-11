package com.xbzhangshi.mvp.login.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.android.tu.loadingdialog.LoadingDailog;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.HomeActivity;
import com.xbzhangshi.mvp.login.BaseView.IRegisterView;
import com.xbzhangshi.mvp.login.LoginSuccessEvent;
import com.xbzhangshi.mvp.login.bean.LoginBean;
import com.xbzhangshi.mvp.login.bean.LoginUserInfoBean;
import com.xbzhangshi.mvp.login.bean.RegisterBean;
import com.xbzhangshi.mvp.login.bean.RegisterItemBean;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.util.DES;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.regex.Pattern;

public class RegisterPresenter extends BasePresenter {
    public static RegisterPresenter newInstance(IRegisterView contentView) {
        return new RegisterPresenter(contentView);
    }

    IRegisterView contentView;

    LoadingDailog loadingDialog;

    public RegisterPresenter(IRegisterView contentView) {
        this.contentView = contentView;
    }


    /**
     * 或者注册配置信息
     *
     * @param context
     * @param
     */
    public void getRegconfig(Context context) {
        Object tag = HttpManager.getObject(context, RegisterItemBean.class,
                Url.BASE_URL + Url.regconfig, null, new OkGoCallback<RegisterItemBean>() {
                    @Override
                    public void onSuccess(RegisterItemBean response) {
                        if (response.isSuccess()) {
                            RegisterItemBean.ContentBean contentBean4 = new RegisterItemBean.ContentBean();
                            contentBean4.setName("验证码");
                            contentBean4.setKey("verifyCode");
                            response.getContent().add(contentBean4);
                            RegisterItemBean.ContentBean contentBean3 = new RegisterItemBean.ContentBean();
                            contentBean3.setName("重复密码");
                            contentBean3.setRegex("^[a-zA-Z0-9]{6,16}$");
                            contentBean3.setKey("rpassword");
                            response.getContent().add(0, contentBean3);
                            RegisterItemBean.ContentBean contentBean2 = new RegisterItemBean.ContentBean();
                            contentBean2.setName("登录密码");
                            contentBean2.setRegex("^[a-zA-Z0-9]{6,16}$");
                            contentBean2.setKey("password");
                            response.getContent().add(0, contentBean2);
                            RegisterItemBean.ContentBean contentBean1 = new RegisterItemBean.ContentBean();
                            contentBean1.setName("用户账号");
                            contentBean1.setRegex("^[a-zA-Z0-9]{5,11}$");
                            contentBean1.setKey("account");
                            response.getContent().add(0, contentBean1);
                            contentView.setRegconfig(response.getContent());
                        } else {
                            contentView.setRegconfigError();
                        }
                    }

                    @Override
                    public void parseError() {
                        super.parseError();
                        contentView.setRegconfigError();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        contentView.setRegconfigError();
                    }
                });
        addNet(tag);
    }


    public void register(Context context, List<RegisterItemBean.ContentBean> datas) {

        if (TextUtils.isEmpty(datas.get(0).getValue())) {
            showDialog(context, "账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(datas.get(1).getValue())) {
            showDialog(context, "密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(datas.get(2).getValue())) {
            showDialog(context, "重复密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(datas.get(datas.size() - 1).getValue())) {
            showDialog(context, "验证码不能为空");
            return;
        }
        //判断密码前后是否相同
        if (!datas.get(1).getValue().equals(datas.get(2).getValue())) {
            showDialog(context, "俩次密码不一致");
            return;
        }

        final String name1 = datas.get(0).getValue();
        final String pwd1 = datas.get(1).getValue();
        HttpParams httpParams = new HttpParams();
        for (int i = 0; i < datas.size(); i++) {
            RegisterItemBean.ContentBean contentBean = datas.get(i);
            //正则表达式的匹配
            if (!TextUtils.isEmpty(contentBean.getRegex()) && !TextUtils.isEmpty(contentBean.getValue())) {
                //判断
                boolean isMatch = Pattern.matches(contentBean.getRegex(), contentBean.getValue());
                if (!isMatch) {
                    showDialog(context, contentBean.getName() + "格式不正确");
                    return;
                }
            }
            if (!TextUtils.isEmpty(contentBean.getValue())) {
                httpParams.put(contentBean.getKey(), contentBean.getValue());
            }
        }
        if (loadingDialog == null) {
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                    .setMessage("加载中...")
                    .setCancelable(true)
                    .setCancelOutside(true);
            loadingDialog = loadBuilder.create();
        }
        loadingDialog.show();
        HttpManager.postObject(context, RegisterBean.class, Url.BASE_URL + Url.register, httpParams, new OkGoCallback<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean response) {
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }

                if (response.isSuccess()) {
                    contentView.registerSuccess(name1, pwd1);
                } else {
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        showDialog(context, response.getMsg());
                        contentView.updateCode();
                    } else {
                        contentView.registerError("注册失败");
                        contentView.updateCode();
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
                contentView.registerError("请求失败");
                contentView.updateCode();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
                contentView.registerError("请求失败");
                contentView.updateCode();
            }
        });
    }

    AlertDialog.Builder localBuilder;

    public void showDialog(Context context, String content) {
        if (localBuilder == null) {
            localBuilder = new AlertDialog.Builder(context);
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
        }
        localBuilder.setMessage(content);
        localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                paramAnonymousDialogInterface.dismiss();
            }
        });
        localBuilder.show();

    }

    public void login(Context context, String name, String pwd) {

        Object tag = UserInfo.getInstance().login(context, LoginBean.class, name, pwd, "", new OkGoCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean response) {

                UserInfo.getInstance().setLoginBean(response);
                if (response.isSuccess()) {
                    //获取用户信息
                    getUserInfo(context, name, pwd);
                } else {
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        contentView.LoginonError(response.getMsg());
                    } else {
                        contentView.LoginonError("登录失败");
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.LoginonError("请求出错");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.LoginonError("请求出错");
            }
        });
        addNet(tag);

    }

    /**
     * 获取用户信息
     *
     * @param context
     * @param
     */
    private void getUserInfo(Context context, String name, String pwd) {
        //获取用户信息
        Object tag = UserInfo.getInstance().getUserInfo(context, LoginUserInfoBean.class, new OkGoCallback<LoginUserInfoBean>() {
            @Override
            public void onSuccess(LoginUserInfoBean response) {

                if (response.isSuccess()) {
                    //保存账号密码
                    //保存是否记住密码的钩
                    SPUtils.getInstance(Key.APP_SET_NAME).put(Key.LOGIN_C_ISCHECK_PWD, true);
                    //保存登录的名字
                    SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.REMMBER_USER_NAME, name);
                    //加密
                    String p = DES.encryptDES(pwd, Key.decryptKey);
                    //保存账号密码用于下次进入的登录
                    SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.LOGIN_USER_NAME, name);
                    SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.LOGIN_USER_PWD, p);
                    //记住密码
                    SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.REMMBER_USER_PWD, p);
                    //设置以及登录信息
                    UserInfo.getInstance().setLogin(true);
                    UserInfo.getInstance().setLoginUserInfoBean(response);
                    contentView.loginSuccess();
                    EventBus.getDefault().post(new LoginSuccessEvent());
                    HomeActivity.start(context);
                } else {
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        contentView.LoginonError(response.getMsg());
                    } else {
                        contentView.LoginonError("登录失败");
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.LoginonError("请求出错");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.LoginonError("请求出错");
            }
        });
        addNet(tag);
    }

    /**
     * 获取免费账号
     *
     * @param
     */
    public void getFreeUser(Context context) {
        Object tag = HttpManager.getObject(context, LoginBean.class,
                Url.BASE_URL + Url.reg_guest, null, new OkGoCallback<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean response) {
                        UserInfo.getInstance().setLoginBean(response);
                        if (response.isSuccess()) {
                            //获取用户信息
                            getFreeUserInfo(context, response.getContent().getAccount());
                        } else {
                            if (!TextUtils.isEmpty(response.getMsg())) {
                                contentView.LoginonError(response.getMsg());
                            } else {
                                contentView.LoginonError("登录失败");
                            }
                        }
                    }

                    @Override
                    public void parseError() {
                        super.parseError();
                        contentView.LoginonError("请求出错");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        contentView.LoginonError("请求出错");
                    }
                });
        addNet(tag);
    }

    /**
     * 获取免费账号的信息
     *
     * @param
     */
    public void getFreeUserInfo(Context context, String name) {
        Object tag = UserInfo.getInstance().getUserInfo(context, LoginUserInfoBean.class, new OkGoCallback<LoginUserInfoBean>() {
            @Override
            public void onSuccess(LoginUserInfoBean response) {

                if (response.isSuccess()) {
                    //设置以及登录信息
                    UserInfo.getInstance().setLogin(true);
                    UserInfo.getInstance().setLoginUserInfoBean(response);
                    contentView.loginSuccess();
                    EventBus.getDefault().post(new LoginSuccessEvent());
                    HomeActivity.start(context);
                } else {
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        contentView.LoginonError(response.getMsg());
                    } else {
                        contentView.LoginonError("登录失败");
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.LoginonError("请求出错");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.LoginonError("请求出错");
            }
        });
        addNet(tag);
    }


}
