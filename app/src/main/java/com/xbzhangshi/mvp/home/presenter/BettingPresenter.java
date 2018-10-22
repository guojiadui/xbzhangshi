package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IBettingBaseView;
import com.xbzhangshi.mvp.home.bean.BalanceBean;
import com.xbzhangshi.mvp.home.bean.HomeDialogBean;
import com.xbzhangshi.mvp.home.bean.HomeSwithBean;
import com.xbzhangshi.mvp.home.bean.NoticeBean;
import com.xbzhangshi.mvp.home.bean.VersionBean;
import com.xbzhangshi.mvp.home.event.BalanceEvent;
import com.xbzhangshi.mvp.login.LoginSuccessEvent;
import com.xbzhangshi.mvp.login.bean.LoginBean;
import com.xbzhangshi.mvp.login.bean.LoginUserInfoBean;
import com.xbzhangshi.mvp.update.UpVersion;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.util.DES;
import com.xbzhangshi.view.dialog.UpVersionDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import io.reactivex.internal.operators.parallel.ParallelDoOnNextTry;
import okhttp3.internal.Version;


public class BettingPresenter extends BasePresenter {
    public static BettingPresenter newInstance(IBettingBaseView contentView) {
        return new BettingPresenter(contentView);
    }

    IBettingBaseView contentView;


    public BettingPresenter(IBettingBaseView contentView) {
        this.contentView = contentView;

    }


    public void init() {

    }

    public static boolean isLogin() {
        return UserInfo.getInstance().isLogin();
    }


    //加载公告
    public void loadData(Context context) {
        //加载公告
        loadNotice(context);
        getHomeTip(context);
        getVersionUpdate(context);
    }


    public void getVersionUpdate(Context context) {

        //判断文件是否可以解析。下载是否完整
       /* String apkPath = com.xbzhangshi.util.FileUtils.createAppStoragePath("apk").getAbsolutePath()
                + File.separator + context.getPackageName() + ".apk";
        Log.e("Up", apkPath);
        File file = new File(apkPath);
        if(file.exists()){
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
            if (packageInfo == null) {
                Log.e("Up", "解析异常");
            }
        }*/
        HttpParams httpParams = new HttpParams();
        httpParams.put("flag", "android");

        HttpManager.getObjectNoLogin(context, VersionBean.class, Url.Version, httpParams, new OkGoCallback<VersionBean>() {
            @Override
            public void onSuccess(VersionBean response) {
                super.onSuccess(response);
                int cur = UpVersion.getVersionCode(context);
                Integer ver = Integer.parseInt(response.getContent().getVersion());
                if (ver > cur) {
                    UpVersion.upVersion(context, response);
                } else {
                    File file = com.xbzhangshi.util.FileUtils.createAppStoragePath("apk");
                    if (file.exists()) {
                        FileUtils.deleteAllInDir(file);
                    }
                }
            }
        });
    }


    boolean isRepeatNotice = false;

    private void loadNotice(Context context) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("code", "13");
        Object tag = HttpManager.getObjectNoLogin(context, NoticeBean.class,
                Url.BASE_URL + Url.notice, httpParams, new OkGoCallback<NoticeBean>() {
                    @Override
                    public void onSuccess(NoticeBean response) {
                        if (response.isSuccess()) {
                            if (!TextUtils.isEmpty(response.getContent())) {
                                contentView.setNotice(response.getContent());
                            }
                        } else {
                            if (TextUtils.isEmpty(response.getContent()) && !isRepeatNotice) {
                                isRepeatNotice = true;
                                loadNotice(context);
                            }
                        }
                    }

                    @Override
                    public void parseError() {
                        super.parseError();
                        if (!isRepeatNotice) {
                            isRepeatNotice = true;
                            loadNotice(context);
                        }
                    }
                });
        addNet(tag);
        Object tag2 = HttpManager.getObjectNoLogin(context, HomeSwithBean.class,
                Url.BASE_URL + Url.getUniversalSwitch, null, new OkGoCallback<HomeSwithBean>() {
                    @Override
                    public void onSuccess(HomeSwithBean response) {

                        boolean dzp, qd;
                        if (TextUtils.isEmpty(response.getIsDzpOnOff())) {
                            dzp = true;
                        } else if (response.getIsDzpOnOff().equals("on")) {
                            dzp = true;
                        } else {
                            dzp = false;
                        }
                        if (TextUtils.isEmpty(response.getIsQdOnOff())) {
                            qd = true;
                        } else if (response.getIsQdOnOff().equals("on")) {
                            qd = true;
                        } else {
                            qd = false;
                        }
                        contentView.setSwith(dzp, qd);
                    }

                    @Override
                    public void parseError() {
                        super.parseError();
                        contentView.setSwith(true, true);

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        contentView.setSwith(true, true);
                    }
                });
        addNet(tag2);
    }


    public void getHomeTip(Context context) {
        //是否登出提窗口
        boolean ishow = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.HOME_WINDOW_TIP, true);
        if (ishow) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("code", 19);
            Object tag = HttpManager.post(context, Url.getArticle, httpParams, new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    try {
                        List<HomeDialogBean> list = JSON.parseArray(response.body(), HomeDialogBean.class);
                        if (list.size() > 0) {
                            contentView.showDialog(list.get(0).getContent());
                        }
                    } catch (Exception e) {

                    }

                }
            });
            addNet(tag);
        }
    }

    /**
     * 自动登录
     *
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
        if (!TextUtils.isEmpty(pwd)) {
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
    private <T> void getUserInfo(Context context, String name, String pwd) {
        //获取用户信息
        Object tag = UserInfo.getInstance().getUserInfo(context, LoginUserInfoBean.class, new OkGoCallback<LoginUserInfoBean>() {
            @Override
            public void onSuccess(LoginUserInfoBean response) {

                if (response.isSuccess()) {
                    //设置以及登录信息
                    UserInfo.getInstance().setLogin(true);
                    UserInfo.getInstance().setLoginUserInfoBean(response);
                    contentView.loginSuccess();
                    EventBus.getDefault().post(new LoginSuccessEvent());
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

    boolean isLoadingBalance = false;

    /**
     * 获取余额
     */
    public void getBalance(Context context) {
        if (!UserInfo.getInstance().isLogin) {
            return;
        }
        if (isLoadingBalance) {
            return;
        }
        isLoadingBalance = true;
        Object tag = HttpManager.getObject(context, BalanceBean.class,
                Url.BASE_URL + Url.meminfo, null, new OkGoCallback<BalanceBean>() {
                    @Override
                    public void onSuccess(BalanceBean response) {
                        isLoadingBalance = false;
                        if (response.isSuccess()) {
                            String b = subZeroAndDot(response.getContent().getBalance() + "元");
                            contentView.updateBalance(b);
                            EventBus.getDefault().post(new BalanceEvent(b));

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        isLoadingBalance = false;
                    }

                    @Override
                    public void parseError() {
                        super.parseError();
                        isLoadingBalance = false;
                    }
                });
        addNet(tag);
    }

    public void getBalanceNoLogin(Context context) {
        if (!UserInfo.getInstance().isLogin) {
            return;
        }
        if (isLoadingBalance) {
            return;
        }
        isLoadingBalance = true;
        Object tag = HttpManager.getObjectNoLogin(context, BalanceBean.class,
                Url.BASE_URL + Url.meminfo, null, new OkGoCallback<BalanceBean>() {
                    @Override
                    public void onSuccess(BalanceBean response) {
                        isLoadingBalance = false;
                        if (response.isSuccess()) {
                            String b = subZeroAndDot(response.getContent().getBalance() + "元");
                            contentView.updateBalance(b);
                            EventBus.getDefault().post(new BalanceEvent(b));

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        isLoadingBalance = false;
                    }

                    @Override
                    public void parseError() {
                        super.parseError();
                        isLoadingBalance = false;
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
