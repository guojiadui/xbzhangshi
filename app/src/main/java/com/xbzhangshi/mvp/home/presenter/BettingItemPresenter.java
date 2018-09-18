package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IBettingBaseView;
import com.xbzhangshi.mvp.home.baseView.IBettingItemBaseView;
import com.xbzhangshi.mvp.home.bean.BesidesLotteryBean;
import com.xbzhangshi.mvp.home.bean.LoctteryBean;

public class BettingItemPresenter extends BasePresenter {

    public static BettingItemPresenter newInstance(IBettingItemBaseView contentView) {
        return new BettingItemPresenter(contentView);
    }

    IBettingItemBaseView<BesidesLotteryBean.ContentBean> contentView;

    public BettingItemPresenter(IBettingItemBaseView contentView) {
        this.contentView = contentView;
    }

    public void loadDataBesidesLotterys(Context context, int position) {
        //加载彩种
        HttpParams httpParams = new HttpParams();
        httpParams.put("code", position - 1);
        Object tag = HttpManager.get(context, URL.BASE_URL + URL.DatasBesidesLotterys, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                BesidesLotteryBean besidesLotteryBean = JSON.parseObject(response.body(), BesidesLotteryBean.class);
                if (besidesLotteryBean.isSuccess()) {
                    if (besidesLotteryBean.getContent() != null && besidesLotteryBean.getContent().size() > 0) {
                        contentView.onSuccess(besidesLotteryBean.getContent());
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

    public void loadDataLotterys(Context context) {
        //加载彩种
        Object tag = HttpManager.get(context, URL.BASE_URL + URL.Loctterys, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoctteryBean loctteryBean = JSON.parseObject(response.body(), LoctteryBean.class);
                if (loctteryBean.isSuccess()) {
                    if (loctteryBean.getContent() != null && loctteryBean.getContent().size() > 0) {
                        contentView.onSuccess2(loctteryBean.getContent());
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
