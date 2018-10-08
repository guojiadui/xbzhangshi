package com.xbzhangshi.mvp.details.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.details.baseview.IOpenPrizeDetailsBaseView;
import com.xbzhangshi.mvp.details.bean.OpenPrizeListBean;

public class OpenPrizeDetailspresenter extends BasePresenter {
    public static OpenPrizeDetailspresenter newInstance(IOpenPrizeDetailsBaseView contentView, String code) {
        return new OpenPrizeDetailspresenter(contentView, code);
    }

    IOpenPrizeDetailsBaseView contentView;
    String code;
    int curPage = 1;

    public OpenPrizeDetailspresenter(IOpenPrizeDetailsBaseView contentView, String code) {
        this.contentView = contentView;
        this.code = code;

    }

    public void LoadDaata(Context context) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("lotCode", code);
        httpParams.put("page", curPage);
        httpParams.put("rows", 10);
        Object tag = HttpManager.<OpenPrizeListBean>getObject(context, OpenPrizeListBean.class,
                Url.OpenPrizeResultList, httpParams, new OkGoCallback<OpenPrizeListBean>() {
                    @Override
                    public void onSuccess(OpenPrizeListBean response) {

                        if (response.isSuccess()) {
                            if (response.getData().getList().size() > 0) {
                                if (curPage == 1) {
                                    contentView.onSuccess(response.getData().getList(), response.getData().isHasNext());
                                } else {
                                    contentView.onMore(response.getData().getList(), response.getData().isHasNext());
                                }
                                curPage = response.getData().getNextPage();
                            } else {
                                if (curPage == 1) {
                                    contentView.onEmpty();
                                } else {
                                    contentView.onMoreEmpty();
                                }
                            }
                        } else {
                            if (curPage == 1) {
                                contentView.onError();
                            } else {
                                contentView.onMoreError();
                            }
                        }
                    }

                    @Override
                    public void parseError() {
                        super.parseError();
                        if (curPage == 1) {
                            contentView.onError();
                        } else {
                            contentView.onMoreError();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (curPage == 1) {
                            contentView.onError();
                        } else {
                            contentView.onMoreError();
                        }
                    }
                });
        addNet(tag);
    }


}
