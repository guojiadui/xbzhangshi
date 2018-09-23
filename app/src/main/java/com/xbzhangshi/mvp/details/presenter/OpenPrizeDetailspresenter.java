package com.xbzhangshi.mvp.details.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
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
        Object tag = HttpManager.get(context, Url.OpenPrizeResultList, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                OpenPrizeListBean listBean = JSON.parseObject(response.body(), OpenPrizeListBean.class);
                if (listBean.isSuccess()) {
                    if (listBean.getData().getList().size() > 0) {
                        if (curPage == 1) {
                            contentView.onSuccess(listBean.getData().getList(), listBean.getData().isHasNext());
                        } else {
                            contentView.onMOre(listBean.getData().getList(), listBean.getData().isHasNext());
                        }
                        curPage = listBean.getData().getNextPage();
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
            public void onError(Response<String> response) {
                super.onError(response);
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
