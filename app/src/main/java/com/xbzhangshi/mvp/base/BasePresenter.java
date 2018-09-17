package com.xbzhangshi.mvp.base;



import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<T extends  IBaseView> {

    private List<Disposable> disposableList;



    /**
     * 添加网络请求
     * @param disposable
     */

    public void addNet(Disposable disposable) {
        if (disposableList == null) {
            disposableList = new ArrayList<>();
        }
        if (!disposableList.contains(disposable))
            disposableList.add(disposable);
    }

    /**
     * 取消网络请求
     */
    public void OnDestory() {
        if (disposableList != null) {
            for (Disposable d : disposableList) {

            }
            disposableList.clear();
        }
    }
}
