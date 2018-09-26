package com.xbzhangshi.mvp.usercenter.presenter;

import android.content.Context;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.usercenter.BaseView.IDrawingMoneyBaseView;
import com.xbzhangshi.mvp.usercenter.BaseView.IExchangeBaseView;
import com.xbzhangshi.mvp.usercenter.bean.ExchangeConfigBean;
import com.xbzhangshi.single.UserInfo;

public class DrawingMoneyPresenter extends BasePresenter{
    public static DrawingMoneyPresenter newInstance(IDrawingMoneyBaseView contentView) {
        return new DrawingMoneyPresenter(contentView);
    }

    IDrawingMoneyBaseView contentView;

    public DrawingMoneyPresenter(IDrawingMoneyBaseView contentView) {
        this.contentView = contentView;
    }


    /**
     * 获取提款信息
     */

    public  void  getConfigInfo(Context context){
        Object tag =HttpManager.get(context,Url.BASE_URL+ Url.withDrawData, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

            }
        });
        addNet(tag);
    }

}
