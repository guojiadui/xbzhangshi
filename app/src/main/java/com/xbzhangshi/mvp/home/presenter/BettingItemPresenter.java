package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
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
        Object tag = HttpManager.getObject(context, BesidesLotteryBean.class,
                Url.BASE_URL + Url.DatasBesidesLotterys, httpParams, new OkGoCallback<BesidesLotteryBean>() {
                    @Override
                    public void onSuccess(BesidesLotteryBean response) {
                        if (response.isSuccess()) {
                            if (response.getContent() != null && response.getContent().size() > 0) {
                                contentView.onSuccess(response.getContent());
                            } else {
                                contentView.onEmpty();
                            }
                        } else {
                            contentView.onError();
                        }
                    }

                    @Override
                    public void parseError() {
                        super.parseError();
                        contentView.onError();
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
        Object tag = HttpManager.getObject(context, LoctteryBean.class,
                Url.BASE_URL + Url.Loctterys, null, new OkGoCallback<LoctteryBean>() {
                    @Override
                    public void onSuccess(LoctteryBean response) {
                        if (response.isSuccess()) {
                            if (response.getContent() != null && response.getContent().size() > 0) {
                                contentView.onSuccess2(response.getContent());
                            } else {
                                contentView.onEmpty();
                            }
                        } else {
                            contentView.onError();
                        }
                    }

                    @Override
                    public void parseError() {
                        super.parseError();
                        contentView.onError();
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
