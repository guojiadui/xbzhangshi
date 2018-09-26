package com.xbzhangshi.mvp.record.presenter;

import android.content.Context;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.record.baseview.ILotteryBaseView;

public class LotteryRecordPresenter extends BasePresenter {

    public static LotteryRecordPresenter newInstance(ILotteryBaseView contentView) {
        return new LotteryRecordPresenter(contentView);
    }
    ILotteryBaseView contentView;

    public LotteryRecordPresenter(ILotteryBaseView contentView) {
        this.contentView = contentView;
    }


    public  void  initData(Context context){
        HttpManager.get(context,Url.BASE_URL+ Url.getLotteryList, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
    }

}
