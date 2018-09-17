package com.xbzhangshi.mvp.login.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.mvp.home.baseView.IPurchaseItemView;
import com.xbzhangshi.mvp.home.presenter.PurchaseItemPesenter;
import com.xbzhangshi.mvp.login.BaseView.ILoginView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LogInPresenter {

    public static LogInPresenter newInstance(ILoginView contentView) {
        return new LogInPresenter(contentView);
    }

    ILoginView contentView;

    public List<String> getNamelist() {
        return namelist;
    }

    public void setNamelist(List<String> namelist) {
        this.namelist = namelist;
    }

    List<String> namelist;

    public LogInPresenter(ILoginView contentView) {
        this.contentView = contentView;
    }


    public void init() {
        boolean check = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.LOGIN_C_ISCHECK_PWD, false);
        contentView.setRemmberPwd(check);
        String userName = SPUtils.getInstance(Key.APP_USER_INFO_NAME).getString(Key.USER_NAME);
        String pwd = SPUtils.getInstance(Key.APP_USER_INFO_NAME).getString(Key.USER_PWD);
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

    public void login(Context context, String name, String pwd, boolean remmber) {

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(context, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (remmber) {
            SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.USER_NAME, name);

            SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.USER_PWD, pwd);
        } else {
            SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.USER_NAME, "");
            SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.USER_PWD, "");
        }
    }

    public void saveHistoryName(String name) {
        if (namelist == null) {
            namelist = new ArrayList<>();
        }
        namelist.add(name);
        Iterator<String> it =namelist.iterator();
        StringBuilder sb = new StringBuilder();
        for (; ; ) {
            String e = it.next();
            sb.append(e);
            if (!it.hasNext()){
                SPUtils.getInstance(Key.APP_USER_INFO_NAME).put(Key.USER_HISTORY_LSIT,sb.toString());
                return ;
            }
            sb.append('/');
        }


    }
}
