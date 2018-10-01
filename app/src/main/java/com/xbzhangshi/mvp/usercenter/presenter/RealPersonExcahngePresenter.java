package com.xbzhangshi.mvp.usercenter.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
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

        Object tag = HttpManager.post(context, Url.thirdRealTransMoney, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    ResultBean resultBean = JSON.parseObject(response.body(), ResultBean.class);
                    if (resultBean.isSuccess()) {
                        contentView.transMoneySuccess();
                    } else {
                        if (!TextUtils.isEmpty(resultBean.getMsg())) {
                            contentView.transMoneyError(resultBean.getMsg());
                        }
                    }
                } catch (Exception e) {
                    contentView.transMoneyError("转换失败");
                }

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
        Object tag = HttpManager.get(context, Url.getRealNameType, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                List<RealExhangeBean> list;
                try {
                    LogUtils.e("TAG", response.body());
                    list = JSON.parseArray(response.body(), RealExhangeBean.class);
                    if (list.size() > 0) {
                        contentView.success(list);
                    } else {
                        contentView.empty();
                    }
                } catch (Exception e) {
                    LogUtils.e("TAG", e.toString());
                    contentView.error("请求出错");
                }

            }
        });
        addNet(tag);
    }

    public void getBalanceItem(Context context, String type) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("type", type);
        HttpManager.post(context, Url.getBalanceitem, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    ItemBalanceBean itemBalanceBean = JSON.parseObject(response.body(), ItemBalanceBean.class);
                    if (!TextUtils.isEmpty(itemBalanceBean.getBalance())) {
                       contentView.updateBalanceItem(type,itemBalanceBean.getBalance());
                    } else {
                        contentView.updateBalanceError("");
                    }
                } catch (Exception e) {
                    contentView.updateBalanceError("刷新失败");
                }
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
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.meminfo, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                BalanceBean balanceBean = JSON.parseObject(response.body(), BalanceBean.class);
                if (balanceBean.isSuccess()) {
                    try {
                        DecimalFormat df = new DecimalFormat("#0.00");
                        contentView.updateBalance(df.format(balanceBean.getContent().getBalance()));
                    } catch (Exception e) {

                    }

                }
            }
        });
        addNet(tag);
    }

}
