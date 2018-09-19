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
import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;
import com.xbzhangshi.single.ServiceTime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PurchaseItemPesenter {
    public static PurchaseItemPesenter newInstance(IPurchaseItemView contentView, int curPosition) {
        return new PurchaseItemPesenter(contentView, curPosition);
    }

    IPurchaseItemView contentView;
    int curPosition = 0;

    public PurchaseItemPesenter(IPurchaseItemView contentView, int curPosition) {
        this.contentView = contentView;
        this.curPosition = curPosition;
    }

    public void loadData(Context context) {
        if (ServiceTime.getInstance() != null) {
            if (ServiceTime.getInstance().getContentBeanList().size() > 0) {
                boolean looKMode = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.GRID_STATE_KEY, true);
                if (curPosition == 0) {
                    //全部
                    contentView.onSuccess(ServiceTime.getInstance().getContentBeanList(), looKMode, curPosition);
                } else if (curPosition == 1) {
                    List<LoctteryBean.ContentBean> list = new ArrayList<>();
                    //高频
                    for (LoctteryBean.ContentBean contentBean : ServiceTime.getInstance().getContentBeanList()) {
                        //写死的彩种
                        if (!"LHC".equals(contentBean.getCode()) && !"FC3D".equals(contentBean.getCode()) && !"PL3".equals(contentBean.getCode())) {
                            list.add(contentBean);
                        }
                    }
                    if(list.size()>0){
                        contentView.onSuccess(list, looKMode, curPosition);
                    }else {
                        contentView.onEmpty();
                    }
                } else {
                    //低频
                    List<LoctteryBean.ContentBean> list = new ArrayList<>();
                    for (LoctteryBean.ContentBean contentBean : ServiceTime.getInstance().getContentBeanList()) {
                        //写死的彩种
                        if ("LHC".equals(contentBean.getCode()) || "FC3D".equals(contentBean.getCode()) || "PL3".equals(contentBean.getCode())) {
                            list.add(contentBean);
                        }
                    }
                    if(list.size()>0){
                        contentView.onSuccess(list, looKMode, curPosition);
                    }else {
                        contentView.onEmpty();
                    }
                }
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
            if (curPosition == 0) {
                //全部
                contentView.swtih(ServiceTime.getInstance().getContentBeanList(), looKMode, curPosition);
            } else if (curPosition == 1) {
                List<LoctteryBean.ContentBean> list = new ArrayList<>();
                //高频
                for (LoctteryBean.ContentBean contentBean : ServiceTime.getInstance().getContentBeanList()) {
                    //写死的彩种
                    if (!"LHC".equals(contentBean.getCode()) && !"FC3D".equals(contentBean.getCode()) && !"PL3".equals(contentBean.getCode())) {
                        list.add(contentBean);
                    }
                }
                if(list.size()>0){
                    contentView.swtih(list, looKMode, curPosition);
                }else {
                    contentView.onEmpty();
                }
            } else {
                //低频
                List<LoctteryBean.ContentBean> list = new ArrayList<>();
                for (LoctteryBean.ContentBean contentBean : ServiceTime.getInstance().getContentBeanList()) {
                    //写死的彩种
                    if ("LHC".equals(contentBean.getCode()) || "FC3D".equals(contentBean.getCode()) || "PL3".equals(contentBean.getCode())) {
                        list.add(contentBean);
                    }
                }
                if(list.size()>0){
                    contentView.swtih(list, looKMode, curPosition);
                }else {
                    contentView.onEmpty();
                }
            }

        }
    }

}
