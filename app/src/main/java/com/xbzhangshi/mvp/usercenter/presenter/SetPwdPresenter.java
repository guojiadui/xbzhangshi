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
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.usercenter.BaseView.ISetPwdBaseView;
import com.xbzhangshi.mvp.usercenter.BaseView.IUpPwdBaseView;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;

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
        HttpManager.post(context, Url.drawing_money_pwd, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ResultBean resultBean  = JSON.parseObject(response.body(),ResultBean.class);
                if(resultBean.isSuccess()){
                    contentView.success();
                }else {
                    if(!TextUtils.isEmpty(resultBean.getMsg())){
                        contentView.error(resultBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.error("请求出错");
            }
        });
    }

}