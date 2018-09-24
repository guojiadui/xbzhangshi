package com.xbzhangshi.mvp.usercenter.presener;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.bean.BalanceBean;
import com.xbzhangshi.mvp.usercenter.BaseView.IExchangeBaseView;
import com.xbzhangshi.mvp.usercenter.BaseView.IUserInfoBaseView;
import com.xbzhangshi.mvp.usercenter.bean.ExchangeBean;
import com.xbzhangshi.mvp.usercenter.bean.ExchangeConfigBean;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.view.dialog.ExchangeDialog;

import java.text.DecimalFormat;

public class ExchangePresenter extends BasePresenter {
    public static ExchangePresenter newInstance(IExchangeBaseView contentView) {
        return new ExchangePresenter(contentView);
    }

    IExchangeBaseView contentView;
    int curposition = -1;
    int curType = -1;
    ExchangeConfigBean configBean;

    public ExchangePresenter(IExchangeBaseView contentView) {
        this.contentView = contentView;
    }

    public void init(Context context) {
        contentView.upDateAcount(UserInfo.getInstance().mUsername);
        getBalance(context);
        getConfigure(context);
    }

    public void select(int position) {
        curposition = position;
        if (position == 0) {
            if (configBean != null && configBean.isSuccess()) {
                ExchangeConfigBean.ContentBean.ConfigsBean configsBean = configBean.getContent().getConfigs().get(1);
                contentView.upTip(configsBean.getNumerator() + "现金可兑换" + configsBean.getDenominator() + "积分(兑换比例: " +
                        configsBean.getNumerator() + ":" + configsBean.getDenominator() + ")");
                curType = configsBean.getId();
            }
        } else if (position == 1) {
            if (configBean != null && configBean.isSuccess()) {
                ExchangeConfigBean.ContentBean.ConfigsBean configsBean = configBean.getContent().getConfigs().get(0);
                contentView.upTip(configsBean.getNumerator() + "积分可兑换" + configsBean.getDenominator() + "现金(兑换比例: " +
                        configsBean.getNumerator() + ":" + configsBean.getDenominator() + ")");
                curType = configsBean.getId();
            }
        }
    }

    public void getConfigure(Context context) {
        Object tag = HttpManager.post(context, Url.BASE_URL + Url.exchangeConfig, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                configBean = JSON.parseObject(response.body(), ExchangeConfigBean.class);
                if (configBean.isSuccess()) {
                    DecimalFormat df = new DecimalFormat("#0.00");
                    contentView.upDateIntegral(df.format(configBean.getContent().getScore()));
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
        addNet(tag);
    }


    public void commit(Context context, String value) {
        if (curType < 0) {
            Toast.makeText(context, "请选择兑换类型", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(value)) {
            Toast.makeText(context, "额度不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (curType == configBean.getContent().getConfigs().get(0).getId()) {
            long v = Long.parseLong(value);
            if (v < configBean.getContent().getConfigs().get(0).getMinVal()) {
                Toast.makeText(context, "额度必须大于等于" + configBean.getContent().getConfigs().get(0).getMinVal(), Toast.LENGTH_SHORT).show();
                return;
            } else if (v > configBean.getContent().getConfigs().get(0).getMaxVal()) {
                Toast.makeText(context, "额度必须小于等于" + configBean.getContent().getConfigs().get(0).getMaxVal(), Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            long v = Long.parseLong(value);
            if (v < configBean.getContent().getConfigs().get(1).getMinVal()) {
                Toast.makeText(context, "额度必须大于等于" + configBean.getContent().getConfigs().get(1).getMinVal(), Toast.LENGTH_SHORT).show();
                return;
            } else if (v > configBean.getContent().getConfigs().get(0).getMaxVal()) {
                Toast.makeText(context, "额度必须小于等于" + configBean.getContent().getConfigs().get(1).getMaxVal(), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("typeId", curType);
        httpParams.put("amount", value);

        Object tag = HttpManager.get(context, Url.BASE_URL + Url.exchange, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ExchangeBean exchangeBean = JSON.parseObject(response.body(), ExchangeBean.class);
                if (exchangeBean.isSuccess()) {
                    contentView.success();
                    init(context);
                } else {
                    if (TextUtils.isEmpty(exchangeBean.getMsg())) {
                        Toast.makeText(context, exchangeBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(context, "请求出错", Toast.LENGTH_SHORT).show();
            }
        });
        addNet(tag);
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
