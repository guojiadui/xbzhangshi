package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.baseView.IPurchaseView;
import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.event.SwithEvent;
import com.xbzhangshi.single.ServiceTime;

import org.greenrobot.eventbus.EventBus;

/**
 * 采购大厅
 */
public class PurchasePesenter extends BasePresenter {

    public static PurchasePesenter newInstance(IPurchaseView contentView) {
        return new PurchasePesenter(contentView);
    }

    IPurchaseView contentView;


    public PurchasePesenter(IPurchaseView contentView) {
        this.contentView = contentView;

    }

    public void init() {
        boolean looKMode = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.GRID_STATE_KEY, true);
        //设置查看秘模式的背景
        contentView.setLookModeViewBg(looKMode);
    }

    public void getLoadData(Context context) {
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.Loctterys, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoctteryBean loctteryBean = JSON.parseObject(response.body(), LoctteryBean.class);
                if (loctteryBean.isSuccess()) {
                    if (loctteryBean.getContent() != null && loctteryBean.getContent().size() > 0) {
                        //获取彩所以码
                        ServiceTime.getInstance(context, loctteryBean.getContent());
                        contentView.onSuccess();
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



    /**
     * 更改查看模式
     */
    public void setLookMode(boolean flag) {
        SPUtils.getInstance(Key.APP_SET_NAME).put(Key.GRID_STATE_KEY, flag);
        contentView.setLookModeViewBg(flag);
        EventBus.getDefault().post(new SwithEvent());
    }


}
