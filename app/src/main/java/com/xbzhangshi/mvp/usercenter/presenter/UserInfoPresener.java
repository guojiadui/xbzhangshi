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
import com.xbzhangshi.mvp.login.bean.LoginUserInfoBean;
import com.xbzhangshi.mvp.usercenter.BaseView.IUserInfoBaseView;
import com.xbzhangshi.mvp.usercenter.bean.UpDataUserbean;
import com.xbzhangshi.single.UserInfo;

public class UserInfoPresener extends BasePresenter {

   /* String realname_match ="^[\\\\u4E00-\\\\u9FA5]+(·[\\\\u4E00-\\\\u9FA5]+)*$";//真是姓名
    String qq_match ="^[0-9]*$";//真是姓名
    String phone_match ="^1[3,4,5,7,8]\\d{9}$";//手机
    String phone_match ="^1[3,4,5,7,8]\\d{9}$";//手机*/

    public static UserInfoPresener newInstance(IUserInfoBaseView contentView) {
        return new UserInfoPresener(contentView);
    }

    IUserInfoBaseView contentView;

    public UserInfoPresener(IUserInfoBaseView contentView) {
        this.contentView = contentView;
    }

    public void initData() {
        LoginUserInfoBean loginBean = UserInfo.getInstance().loginUserInfoBean;
        if (loginBean == null) {
            return;
        }
        contentView.setAccount(loginBean.getContent().getAccount());
        contentView.setRealName(loginBean.getContent().getUserName());
        contentView.setRPhoneNumber(loginBean.getContent().getPhone());
        contentView.setEmail(loginBean.getContent().getEmail());
        contentView.setQQ(loginBean.getContent().getQq());
        contentView.setBankNum(loginBean.getContent().getCardNo());
        contentView.setOpeningBank(loginBean.getContent().getBankName());
        contentView.setBankAddress(loginBean.getContent().getBankAddress());
    }


    public void upDateInfo(Context context, String key, String value) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("typeStr", key);
        httpParams.put("str", value);
        Object tag = HttpManager.post(context, Url.BASE_URL + Url.updateuserInfo, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                UpDataUserbean upDataUserbean = JSON.parseObject(response.body(), UpDataUserbean.class);
                if (upDataUserbean.isSuccess()) {
                    getUserInfo(context);
                    Toast.makeText(context, "绑定成功", Toast.LENGTH_SHORT).show();
                } else {
                    if (!TextUtils.isEmpty(upDataUserbean.getMsg())) {
                        Toast.makeText(context, upDataUserbean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(context, "请求出错", Toast.LENGTH_SHORT).show();
            }
        });
        addNet(tag);
    }

    /**
     * 获取用户信息
     */

    public void getUserInfo(Context context) {
        Object tag = UserInfo.getInstance().getUserInfo(context, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoginUserInfoBean loginUserInfoBean = JSON.parseObject(response.body(), LoginUserInfoBean.class);
                if (loginUserInfoBean.isSuccess()) {
                    UserInfo.getInstance().setLoginUserInfoBean(loginUserInfoBean);
                    initData();
                } else {
                    if (!TextUtils.isEmpty(loginUserInfoBean.getMsg())) {
                        Toast.makeText(context, loginUserInfoBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(context, "请求出错", Toast.LENGTH_SHORT).show();
            }
        });
        addNet(tag);
    }
}
