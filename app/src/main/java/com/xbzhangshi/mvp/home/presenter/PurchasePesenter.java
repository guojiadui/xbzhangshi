package com.xbzhangshi.mvp.home.presenter;

import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.mvp.home.baseView.IPurchaseView;
import com.xbzhangshi.mvp.home.event.SwithEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 采购大厅
 */
public class PurchasePesenter {

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

    /**
     * 更改查看模式
     */
    public void setLookMode() {
        boolean looKMode = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.GRID_STATE_KEY, true);
        looKMode = !looKMode;
        SPUtils.getInstance(Key.APP_SET_NAME).put(Key.GRID_STATE_KEY, looKMode);
        contentView.setLookModeViewBg(looKMode);
        EventBus.getDefault().post(new SwithEvent());
    }


}
