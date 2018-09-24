package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IHomeBaseView;
import com.xbzhangshi.mvp.home.bean.MsgCountBean;
import com.xbzhangshi.single.UserInfo;


public class HomePresenter extends BasePresenter {

    public static HomePresenter newInstance(IHomeBaseView contentView) {
        return new HomePresenter(contentView);
    }

    IHomeBaseView contentView;


    public HomePresenter(IHomeBaseView contentView) {
        this.contentView = contentView;

    }

    public boolean isLogin() {
        return UserInfo.getInstance().isLogin();
    }



    public void getMsgCount(Context context) {
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.getMsgCount, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                MsgCountBean msgCountBean = JSON.parseObject(response.body(),MsgCountBean.class);
                if(msgCountBean.isSuccess()){
                    if(msgCountBean.getContent()>0){
                        contentView.upMsgCount(msgCountBean.getContent());
                    }else {
                        contentView.upMsgCount(0);
                    }
                }
            }
        });
        addNet(tag);
    }
}
