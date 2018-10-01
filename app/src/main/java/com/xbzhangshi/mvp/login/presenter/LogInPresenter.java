package com.xbzhangshi.mvp.login.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.HomeActivity;
import com.xbzhangshi.mvp.login.BaseView.ILoginView;
import com.xbzhangshi.mvp.login.LoginSuccessEvent;
import com.xbzhangshi.mvp.login.bean.LoginBean;
import com.xbzhangshi.mvp.login.bean.LoginUserInfoBean;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.util.DES;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LogInPresenter extends BasePresenter {

    public static LogInPresenter newInstance(ILoginView contentView) {
        return new LogInPresenter(contentView);
    }

    ILoginView contentView;

    List<String> namelist;
    boolean isshowCode =false;

    public LogInPresenter(ILoginView contentView) {
        this.contentView = contentView;
    }


    public void init() {
        boolean check = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.LOGIN_C_ISCHECK_PWD, false);
        contentView.setRemmberPwd(check);
        String userName = SPUtils.getInstance(Key.APP_USER_INFO_NAME).getString(Key.REMMBER_USER_NAME);
        String pwd = SPUtils.getInstance(Key.APP_USER_INFO_NAME).getString(Key.REMMBER_USER_PWD);
        //解密
        if (!TextUtils.isEmpty(pwd)) {
            pwd = DES.decryptDES(pwd, Key.decryptKey);
        }
        contentView.setUserInfo(userName, pwd);
        String names = SPUtils.getInstance(Key.APP_USER_INFO_NAME).getString(Key.USER_HISTORY_LSIT);
        if (!TextUtils.isEmpty(names)) {
            String[] strs = names.split("/");
            if (strs.length > 0) {
                namelist = Arrays.asList(strs);
            }
        }
    }

    /**
     * 登录
     *
     * @param name
     * @param pwd
     * @param remmber
     */

    public void login(Context context, String name, String pwd,String code, boolean remmber) {
        if(isshowCode){
           if(TextUtils.isEmpty(code)){
               contentView.LoginonError("验证码不能我空");
               return;
           }
        }

        Object tag = UserInfo.getInstance().login(context, name, pwd,code, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoginBean loginBean = JSON.parseObject(response.body(), LoginBean.class);
                UserInfo.getInstance().setLoginBean(loginBean);
                if (loginBean.isSuccess()) {
                    //获取用户信息
                    getUserInfo(context, name, pwd, remmber);
                } else {
                    if (!TextUtils.isEmpty(loginBean.getMsg())) {
                        contentView.LoginonError(loginBean.getMsg());
                        /**
                         * 添加验证码判断
                         */
                       /* isshowCode =true;
                        HttpParams httpParams = new HttpParams();
                        httpParams.put("timestamp",System.currentTimeMillis());
                        HttpManager.getBitmap(context, Url.login_code, httpParams, new BitmapCallback() {
                            @Override
                            public void onSuccess(Response<Bitmap> response) {
                                contentView.showCode(response.body());
                            }
                        });*/
                    } else {
                        contentView.LoginonError("登录失败");
                    }
                }
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
     * 更新验证码
     * @param context
     */
   public  void  upDateCode(Context context){
       HttpParams httpParams = new HttpParams();
       httpParams.put("timestamp",System.currentTimeMillis());
       HttpManager.getBitmap(context, Url.login_code , httpParams, new BitmapCallback() {
           @Override
           public void onSuccess(Response<Bitmap> response) {
               contentView.showCode(response.body());
           }
       });
   }
    /**
     * 获取用户信息
     *
     * @param context
     * @param remmber
     */
    public void getUserInfo(Context context, String name, String pwd, boolean remmber) {
        //获取用户信息
        Object tag = UserInfo.getInstance().getUserInfo(context, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoginUserInfoBean loginUserInfoBean = JSON.parseObject(response.body(), LoginUserInfoBean.class);
                if (loginUserInfoBean.isSuccess()) {
                    //保存账号密码
                    //保存是否记住密码的钩
                    SPUtils.getInstance(Key.APP_SET_NAME).put(Key.LOGIN_C_ISCHECK_PWD, remmber);
                    //保存登录的名字
                    SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.REMMBER_USER_NAME, name);
                    //加密
                    String p = DES.encryptDES(pwd, Key.decryptKey);
                    //保存账号密码用于下次进入的登录
                    SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.LOGIN_USER_NAME, name);
                    SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.LOGIN_USER_PWD, p);
                    //是否记住密码
                    if (remmber) {
                        SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.REMMBER_USER_PWD, p);
                    } else {
                        SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.REMMBER_USER_PWD, "");
                    }
                    //设置以及登录信息
                    UserInfo.getInstance().setLogin(true);
                    UserInfo.getInstance().setmUsername(name);
                    UserInfo.getInstance().setmPassword(pwd);
                    UserInfo.getInstance().setLoginUserInfoBean(loginUserInfoBean);
                    contentView.loginSuccess();
                    EventBus.getDefault().post(new LoginSuccessEvent());
                    HomeActivity.start(context);
                } else {
                    if (!TextUtils.isEmpty(loginUserInfoBean.getMsg())) {
                        contentView.LoginonError(loginUserInfoBean.getMsg());
                    } else {
                        contentView.LoginonError("登录失败");
                    }
                }
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
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.reg_guest, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoginBean loginBean = JSON.parseObject(response.body(), LoginBean.class);
                UserInfo.getInstance().setLoginBean(loginBean);
                if (loginBean.isSuccess()) {
                    //获取用户信息
                    getFreeUserInfo(context, loginBean.getContent().getAccount());
                } else {
                    if (!TextUtils.isEmpty(loginBean.getMsg())) {
                        contentView.LoginonError(loginBean.getMsg());
                    } else {
                        contentView.LoginonError("登录失败");
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
        addNet(tag);
    }

    /**
     * 获取免费账号的信息
     *
     * @param
     */
    public void getFreeUserInfo(Context context,String name) {
        Object tag = UserInfo.getInstance().getUserInfo(context, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoginUserInfoBean loginUserInfoBean = JSON.parseObject(response.body(), LoginUserInfoBean.class);
                if (loginUserInfoBean.isSuccess()) {

                    //设置以及登录信息
                    UserInfo.getInstance().setLogin(true);
                    UserInfo.getInstance().setmUsername(name);
                    UserInfo.getInstance().setLoginUserInfoBean(loginUserInfoBean);
                    contentView.loginSuccess();
                    EventBus.getDefault().post(new LoginSuccessEvent());
                    HomeActivity.start(context);
                } else {
                    if (!TextUtils.isEmpty(loginUserInfoBean.getMsg())) {
                        contentView.LoginonError(loginUserInfoBean.getMsg());
                    } else {
                        contentView.LoginonError("登录失败");
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.LoginonError("请求出错");
            }
        });
        addNet(tag);
    }


    public void saveHistoryName(String name) {
        if (namelist == null) {
            namelist = new ArrayList<>();
        }
        namelist.add(name);
        Iterator<String> it = namelist.iterator();
        StringBuilder sb = new StringBuilder();
        for (; ; ) {
            String e = it.next();
            sb.append(e);
            if (!it.hasNext()) {
                SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.USER_HISTORY_LSIT, sb.toString());
                return;
            }
            sb.append('/');
        }

    }
    public List<String> getNamelist() {
        return namelist;
    }

    public void setNamelist(List<String> namelist) {
        this.namelist = namelist;
    }
}
