package com.xbzhangshi.mvp.usercenter.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.usercenter.BaseView.IDrawingMoneyBaseView;
import com.xbzhangshi.mvp.usercenter.BaseView.IExchangeBaseView;
import com.xbzhangshi.mvp.usercenter.bean.DrawMoneyInfoBean;
import com.xbzhangshi.mvp.usercenter.bean.ExchangeConfigBean;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;
import com.xbzhangshi.single.UserInfo;

public class DrawingMoneyPresenter extends BasePresenter {
    public static DrawingMoneyPresenter newInstance(IDrawingMoneyBaseView contentView) {
        return new DrawingMoneyPresenter(contentView);
    }

    IDrawingMoneyBaseView contentView;

    public DrawingMoneyPresenter(IDrawingMoneyBaseView contentView) {
        this.contentView = contentView;
    }

    /**
     * 提款
     */
    public void commit(Context context, String num, String pwd) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("money", num);
        httpParams.put("repPwd", pwd);
        Object tag = HttpManager.postObject(context, ResultBean.class, Url.BASE_URL + Url.drawcommit, httpParams, new OkGoCallback<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) {

                if (response.isSuccess()) {
                    contentView.drawSuccess();
                } else {
                    if(!TextUtils.isEmpty(response.getMsg())){
                        contentView.drawError(response.getMsg());
                    }else {
                        contentView.drawError("提款失败");
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.drawError("请求出错");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.drawError("请求出错");
            }
        });
        addNet(tag);
    }

    /**
     * 获取提款信息
     */

    public void getConfigInfo(Context context) {
        Object tag = HttpManager.getObject(context, DrawMoneyInfoBean.class,
                Url.BASE_URL + Url.withDrawData, null, new OkGoCallback<DrawMoneyInfoBean>() {
                    @Override
                    public void onSuccess(DrawMoneyInfoBean response) {
                        if (response.getCommit() != null) {
                            contentView.setConfigInfo(response);
                        } else {
                            contentView.setConfigError("请求出错");
                        }
                    }

                    @Override
                    public void parseError() {
                        super.parseError();
                        contentView.setConfigError("请求出错");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        contentView.setConfigError("请求出错");
                    }
                });
        addNet(tag);
    }

}
