package com.xbzhangshi.mvp.home.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.mvp.home.baseView.IPurchaseItemView;
import com.xbzhangshi.mvp.home.baseView.IPurchaseView;

public class PurchaseItemPesenter {
    public static PurchaseItemPesenter newInstance(IPurchaseItemView contentView) {
        return new PurchaseItemPesenter(contentView);
    }

    IPurchaseItemView contentView;


    public PurchaseItemPesenter(IPurchaseItemView contentView) {
        this.contentView = contentView;

    }

    public void init() {
        boolean looKMode = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.GRID_STATE_KEY, true);
        //设置查看秘模式的背景
        contentView.switchMode(looKMode);
    }

    /**
     * 更改模式
     */
    public  void  setLookMode() {
        boolean looKMode = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.GRID_STATE_KEY, true);
        //设置查看秘模式的背景
        contentView.switchMode(looKMode);

    }

}
