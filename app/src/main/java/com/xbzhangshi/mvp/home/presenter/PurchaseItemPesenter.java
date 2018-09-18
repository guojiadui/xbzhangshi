package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.URL;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.home.baseView.IPurchaseItemView;
import com.xbzhangshi.mvp.home.baseView.IPurchaseView;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;
import com.xbzhangshi.single.ServiceTime;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class PurchaseItemPesenter {
    public static PurchaseItemPesenter newInstance(IPurchaseItemView contentView) {
        return new PurchaseItemPesenter(contentView);
    }

    IPurchaseItemView contentView;

    public PurchaseItemPesenter(IPurchaseItemView contentView) {
        this.contentView = contentView;

    }

    public void loadData(Context context) {
        if (ServiceTime.getInstance() != null) {
            if (ServiceTime.getInstance().getContentBeanList().size() > 0) {
                boolean looKMode = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.GRID_STATE_KEY, true);
                contentView.onSuccess(ServiceTime.getInstance().getContentBeanList(), looKMode);
            } else {
                contentView.onEmpty();
            }
        } else {
            contentView.onError();
        }
    }

    /**
     * 更改模式
     */
    public void setLookMode() {
        if (ServiceTime.getInstance() != null &&
                ServiceTime.getInstance().getContentBeanList() != null &&
                ServiceTime.getInstance().getContentBeanList().size() > 0) {
            boolean looKMode = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.GRID_STATE_KEY, true);
            contentView.swtih(ServiceTime.getInstance().getContentBeanList(), looKMode);
        }
    }

}
