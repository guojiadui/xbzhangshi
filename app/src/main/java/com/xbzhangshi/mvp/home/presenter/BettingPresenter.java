package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IBettingBaseView;
import com.xbzhangshi.mvp.home.bean.NoticeBean;


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
}
