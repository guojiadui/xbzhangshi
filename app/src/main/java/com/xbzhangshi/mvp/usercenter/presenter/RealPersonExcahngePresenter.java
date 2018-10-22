package com.xbzhangshi.mvp.usercenter.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.bean.BalanceBean;
import com.xbzhangshi.mvp.usercenter.BaseView.IRealPersonExchangeBaseView;
import com.xbzhangshi.mvp.usercenter.bean.ItemBalanceBean;
import com.xbzhangshi.mvp.usercenter.bean.RealExhangeBean;
import com.xbzhangshi.mvp.usercenter.bean.ResultBean;

import java.text.DecimalFormat;
import java.util.List;

public class RealPersonExcahngePresenter extends BasePresenter {

    public static RealPersonExcahngePresenter newInstance(IRealPersonExchangeBaseView contentView) {
        return new RealPersonExcahngePresenter(contentView);
    }

    IRealPersonExchangeBaseView contentView;

    public RealPersonExcahngePresenter(IRealPersonExchangeBaseView contentView) {
        this.contentView = contentView;

    }

    public void init(Context context) {
        getBalance(context);
        getList(context);
    }

    public void transMoney(Context context, String from, String to, String sum) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("changeFrom", from);
        httpParams.put("changeTo", to);//sys
        httpParams.put("quota", sum);

        Object tag = HttpManager.postObject(context, ResultBean.class, Url.thirdRealTransMoney, httpParams, new OkGoCallback<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response) {

                if (response.isSuccess()) {
                    contentView.transMoneySuccess();
                } else {
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        contentView.transMoneyError(response.getMsg());
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.transMoneyError("转换失败");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.transMoneyError("请求出错");
            }
        });
        addNet(tag);
    }

    /**
     * 获取列表
     */

    public void getList(Context context) {
        Object tag = HttpManager.getObject(context, RealExhangeBean.class, Url.getRealNameType, null, new OkGoCallback<RealExhangeBean>() {
            @Override
            public void onSuccess(RealExhangeBean response) {
                super.onSuccess(response);
                if (response.isSuccess()) {
                    for (RealExhangeBean.ContentBean contentBean : response.getContent()) {
                        contentBean.setBalance(-1.0);
                    }
                    contentView.success(response.getContent());
                } else {
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        contentView.error(response.getMsg());
                    }
                }
            }
        });
        addNet(tag);
    }

    public void getBalanceItem(Context context, String type) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("type", type);
        HttpManager.postObject(context, ItemBalanceBean.class, Url.getBalanceitem, httpParams, new OkGoCallback<ItemBalanceBean>() {
            @Override
            public void onSuccess(ItemBalanceBean response) {
                if(response.isSuccess()){
                    contentView.updateBalanceItem(type, response.getBalance());
                }else {
                    if(!TextUtils.isEmpty(response.getMsg())){
                        contentView.error(response.getMsg());
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.updateBalanceError("刷新失败");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.updateBalanceError("请求失败");
            }
        });
    }

    /**
     * 余额
     *
     * @param context
     */
    public void getBalance(Context context) {
        Object tag = HttpManager.getObject(context, BalanceBean.class, Url.BASE_URL + Url.meminfo, null, new OkGoCallback<BalanceBean>() {
            @Override
            public void onSuccess(BalanceBean response) {

                if (response.isSuccess()) {
                    try {
                        DecimalFormat df = new DecimalFormat("#0.00");
                        contentView.updateBalance(df.format(response.getContent().getBalance()) + "元");
                    } catch (Exception e) {

                    }

                }
            }
        });
        addNet(tag);
    }

}
