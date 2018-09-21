package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;
import android.net.UrlQuerySanitizer;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IBettingBaseView;
import com.xbzhangshi.mvp.home.bean.BalanceBean;
import com.xbzhangshi.mvp.home.bean.NoticeBean;
import com.xbzhangshi.mvp.login.LoginSuccessEvent;
import com.xbzhangshi.mvp.login.bean.LoginBean;
import com.xbzhangshi.mvp.login.bean.LoginUserInfoBean;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.util.DES;

import org.greenrobot.eventbus.EventBus;


public class BettingPresenter  extends BasePresenter{
    public static BettingPresenter newInstance(IBettingBaseView contentView) {
        return new BettingPresenter(contentView);
    }

    IBettingBaseView contentView;


    public BettingPresenter(IBettingBaseView contentView) {
        this.contentView = contentView;

    }



    public void init(){

    }
    public boolean isLogin() {
        return UserInfo.getInstance().isLogin();
    }
    //加载公告
    public  void  loadData(Context context){
        //加载公告
        HttpParams httpParams = new HttpParams();
        httpParams.put("code","13");
      Object tag=  HttpManager.get(context, URL.BASE_URL + URL.notice, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.e("TAG",response.body());
                NoticeBean noticeBean = JSON.parseObject(response.body(),NoticeBean.class);
                if(noticeBean.isSuccess()){
                    if(!TextUtils.isEmpty(noticeBean.getContent()))
                        contentView.setNotice(noticeBean.getContent());
                }
            }
        });
      addNet(tag);
    }

    /**
     * 自动登录
     * @param context
     */
    public void outologin(Context context) {
        //保存账号密码用于下次进入的登录
        String name = SPUtils.getInstance(Key.APP_USER_INFO_NAME).getString(Key.LOGIN_USER_NAME);
        String pwd = SPUtils.getInstance(Key.APP_USER_INFO_NAME).getString(Key.LOGIN_USER_PWD);
        //解密
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            return;
        }
        pwd = DES.decryptDES(pwd, Key.decryptKey);
        if(!TextUtils.isEmpty(pwd)){
            login(context, name, pwd);
        }
    }

    /**
     * 登录
     *
     * @param name
     * @param pwd
     * @param
     */

    private void login(Context context, String name, String pwd) {
        Object tag = UserInfo.getInstance().login(context, name, pwd, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoginBean loginBean = JSON.parseObject(response.body(), LoginBean.class);
                if (loginBean.isSuccess()) {
                    //获取用户信息
                    getUserInfo(context, name, pwd );
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
    private void getUserInfo(Context context, String name, String pwd ) {
        //获取用户信息
        Object tag = UserInfo.getInstance().getUserInfo(context, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoginUserInfoBean loginUserInfoBean = JSON.parseObject(response.body(), LoginUserInfoBean.class);
                if (loginUserInfoBean.isSuccess()) {
                    //设置以及登录信息
                    UserInfo.getInstance().setLogin(true);
                    UserInfo.getInstance().setmUsername(name);
                    UserInfo.getInstance().setmPassword(pwd);
                    UserInfo.getInstance().setLoginUserInfoBean(loginUserInfoBean);
                    contentView.loginSuccess();
                    EventBus.getDefault().post(new LoginSuccessEvent());
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
     * 获取余额
     */
    public  void  getBalance(Context context){
       HttpManager.get(context, URL.BASE_URL + URL.meminfo, null, new StringCallback() {
           @Override
           public void onSuccess(Response<String> response) {
               BalanceBean balanceBean = JSON.parseObject(response.body(),BalanceBean.class);
               if(balanceBean.isSuccess()){
                 contentView.updateBalance(subZeroAndDot(balanceBean.getContent().getBalance()+""));
               }
           }
       });
    }
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
