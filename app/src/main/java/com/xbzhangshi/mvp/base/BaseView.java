package com.xbzhangshi.mvp.base;

public abstract class BaseView<T> {

    public abstract void showProgress();

    public abstract void hideProgress();

    public abstract void complete(T t);

    public abstract void emptyDatas();

    public abstract void error();
}
