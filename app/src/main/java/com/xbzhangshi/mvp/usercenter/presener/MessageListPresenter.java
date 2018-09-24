package com.xbzhangshi.mvp.usercenter.presener;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.usercenter.BaseView.IExchangeBaseView;
import com.xbzhangshi.mvp.usercenter.BaseView.IMesssageListBaseView;
import com.xbzhangshi.mvp.usercenter.bean.ExchangeConfigBean;
import com.xbzhangshi.mvp.usercenter.bean.MsgBean;
import com.xbzhangshi.mvp.usercenter.bean.ReadBean;
import com.xbzhangshi.mvp.usercenter.event.UpdateMsgCount;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.view.dialog.MsgTipDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MessageListPresenter extends BasePresenter {
    public static MessageListPresenter newInstance(IMesssageListBaseView contentView) {
        return new MessageListPresenter(contentView);
    }

    IMesssageListBaseView contentView;

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean isEdit = false;


    public MessageListPresenter(IMesssageListBaseView contentView) {
        this.contentView = contentView;
    }


    public void loadData(Context context) {
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.message_list, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                MsgBean msgBean = JSON.parseObject(response.body(), MsgBean.class);
                if (msgBean.isSuccess()) {
                    if (msgBean.getContent().getDatas().size() > 0) {
                        contentView.success(MessageListPresenter.this, msgBean.getContent().getDatas());
                    } else {
                        contentView.empty();
                    }
                } else {
                    if (!TextUtils.isEmpty(msgBean.getMsg())) {
                        contentView.Error(msgBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.Error("请求出错");
            }
        });
        addNet(tag);
    }

    MsgTipDialog tipDialog;

    public void setdel(Context context, List<MsgBean.ContentBean.DatasBean> datasBeans) {
        StringBuilder stringBuilder = new StringBuilder();

        for (MsgBean.ContentBean.DatasBean datasBean : datasBeans) {
            if (datasBean.ischeck) {
                stringBuilder.append(datasBean.getId() + ",");

            }
        }
        String value = stringBuilder.toString();
        if (TextUtils.isEmpty(value)) {
            Toast.makeText(context, "请选择", Toast.LENGTH_SHORT).show();
            return;
        }
        tipDialog = new MsgTipDialog(context,"确定删除选中的选项？", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipDialog.dismiss();
                del(context, value.substring(0, value.length() - 1));
            }
        });
        tipDialog.show();


    }
    public void setRead(Context context, List<MsgBean.ContentBean.DatasBean> datasBeans) {
        StringBuilder stringBuilder = new StringBuilder();
        List<MsgBean.ContentBean.DatasBean> list = new ArrayList<>();
        for (MsgBean.ContentBean.DatasBean datasBean : datasBeans) {
            if (datasBean.ischeck) {
                stringBuilder.append(datasBean.getId() + ",");
                list.add(datasBean);
            }
        }
        String value = stringBuilder.toString();
        if (TextUtils.isEmpty(value)) {
            Toast.makeText(context, "请选择", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean isAllReaded = true;//是否是全是已读

        for (MsgBean.ContentBean.DatasBean d : list) {
            if (d.getStatus() == 1) {//未读
                isAllReaded = false;
                break;
            }
        }
        if (isAllReaded) {
            Toast.makeText(context, "该信息是已读状态", Toast.LENGTH_SHORT).show();
            return;
        }
        tipDialog = new MsgTipDialog(context, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipDialog.dismiss();
                read(context, value.substring(0, value.length() - 1));
            }
        });
        tipDialog.show();


    }

    public void read(Context context, String value) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", value);
        Object tag = HttpManager.get(context, Url.read, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ReadBean readBean = JSON.parseObject(response.body(), ReadBean.class);
                if (readBean.isSuccess()) {
                    contentView.readSuccess(value);
                    EventBus.getDefault().post(new UpdateMsgCount());
                } else {
                    if (!TextUtils.isEmpty(readBean.getMsg())) {
                        contentView.readError(readBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.readError("请求出错");
            }
        });
        addNet(tag);
    }
    public void del(Context context, String value) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", value);
        Object tag = HttpManager.post(context, Url.del_msg, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ReadBean readBean = JSON.parseObject(response.body(), ReadBean.class);
                if (readBean.isSuccess()) {
                    contentView.delSuccess(value);
                    EventBus.getDefault().post(new UpdateMsgCount());
                } else {
                    if (!TextUtils.isEmpty(readBean.getMsg())) {
                        contentView.delError(readBean.getMsg());
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.delError("请求出错");
            }
        });
        addNet(tag);
    }


    public void setEdit() {
        isEdit = !isEdit;
        contentView.ishowEditLayout(isEdit);
    }

}
