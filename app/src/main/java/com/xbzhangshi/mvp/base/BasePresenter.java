package com.xbzhangshi.mvp.base;



import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter {

    private List<Object> urlList;

    /**
     * 添加网络请求
     * @param
     */

    public void addNet(Object tag) {
        if (urlList == null) {
            urlList = new ArrayList<>();
        }
        if (!urlList.contains(tag))
            urlList.add(tag);
    }

    /**
     * 取消网络请求
     */
    public void onDestory() {
        if (urlList != null) {
            for (Object d : urlList) {
                OkGo.getInstance().cancelTag(d);
            }
            urlList.clear();
        }
    }
}
