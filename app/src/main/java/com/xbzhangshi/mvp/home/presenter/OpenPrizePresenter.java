package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IHomeBaseView;
import com.xbzhangshi.mvp.home.baseView.IOpenPrizeBaseView;
import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;
import com.xbzhangshi.mvp.home.bean.OpenPrizeBean;
import com.xbzhangshi.single.ServiceTime;

import java.util.Iterator;

public class OpenPrizePresenter extends BasePresenter {

    public static OpenPrizePresenter newInstance(IOpenPrizeBaseView contentView) {
        return new OpenPrizePresenter(contentView);
    }

    IOpenPrizeBaseView contentView;


    public OpenPrizePresenter(IOpenPrizeBaseView contentView) {
        this.contentView = contentView;

    }
    public void getLoadData(Context context) {
        Object tag = HttpManager.get(context, URL.OpenPrize  , null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                OpenPrizeBean openPrizeBean = JSON.parseObject(response.body(), OpenPrizeBean.class);
                if (openPrizeBean.isSuccess()) {
                    if (openPrizeBean.getData() != null && openPrizeBean.getData().size() > 0) {
                        //获取彩所以码
                        contentView.onSuccess(openPrizeBean.getData());
                    } else {
                        contentView.onEmpty();
                    }
                } else {
                    contentView.onError();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.onError();
            }
        });
        addNet(tag);
    }


}
