package com.xbzhangshi.mvp.usercenter.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.usercenter.BaseView.IMesssageListBaseView;
import com.xbzhangshi.mvp.usercenter.bean.MsgBean;
import com.xbzhangshi.mvp.usercenter.bean.ReadBean;
import com.xbzhangshi.mvp.usercenter.event.UpdateMsgCount;
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
    public int curpage = 1;

    public MessageListPresenter(IMesssageListBaseView contentView) {
        this.contentView = contentView;
    }


    public void loadData(Context context) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("pageNumber", curpage);
        httpParams.put("status", 0);
        httpParams.put("pageSize", 100);
        Object tag = HttpManager.getObject(context, MsgBean.class,Url.message_list, null, new OkGoCallback<MsgBean>() {
            @Override
            public void onSuccess(MsgBean response) {

                if (response.getCurrentPageNo() > 0) {
                    if (response.getList().size() > 0) {
                        if (curpage == 1) {
                            contentView.success(MessageListPresenter.this, response.getList(), response.isHasNext());
                        } else {
                            contentView.successMore(MessageListPresenter.this, response.getList(), response.isHasNext());
                        }

                    } else {
                        if (curpage == 1) {
                            contentView.empty(response.isHasNext());
                        } else {
                            contentView.emptyMore(response.isHasNext());
                        }

                    }
                    curpage = response.getCurrentPageNo();
                } else {
                    contentView.Error("请求出错");
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.Error("请求出错");
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

    public void setdel(Context context, List<MsgBean.ListBean> datasBeans) {
        StringBuilder stringBuilder = new StringBuilder();

        for (MsgBean.ListBean datasBean : datasBeans) {
            if (datasBean.ischeck) {
                stringBuilder.append(datasBean.getId() + ",");

            }
        }
        String value = stringBuilder.toString();
        if (TextUtils.isEmpty(value)) {
            Toast.makeText(context, "请选择", Toast.LENGTH_SHORT).show();
            return;
        }
        tipDialog = new MsgTipDialog(context, "确定删除选中的选项？", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipDialog.dismiss();
                del(context, value.substring(0, value.length() - 1));
            }
        });
        tipDialog.show();


    }

    public void setRead(Context context, List<MsgBean.ListBean> datasBeans) {
        StringBuilder stringBuilder = new StringBuilder();
        List<MsgBean.ListBean> list = new ArrayList<>();
        for (MsgBean.ListBean datasBean : datasBeans) {
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

        for (MsgBean.ListBean d : list) {
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

    public void readitem(Context context, MsgBean.ListBean datasBean) {
        if (datasBean.getStatus() != 1) {
            return;
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", datasBean.getId());
        Object tag = HttpManager.getObject(context,ReadBean.class, Url.read, httpParams, new OkGoCallback<ReadBean>() {
            @Override
            public void onSuccess(ReadBean response) {
                if (response.isSuccess()) {
                    contentView.readSuccess(datasBean.getId() + "");
                    EventBus.getDefault().post(new UpdateMsgCount());
                } else {
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        contentView.readError(response.getMsg());
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.readError("请求出错");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                contentView.readError("请求出错");
            }
        });
        addNet(tag);
    }

    public void read(Context context, String value) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", value);
        Object tag = HttpManager.getObject(context,ReadBean.class, Url.read, httpParams, new OkGoCallback<ReadBean>() {
            @Override
            public void onSuccess(ReadBean response) {
                if (response.isSuccess()) {
                    contentView.readSuccess(value);
                    EventBus.getDefault().post(new UpdateMsgCount());
                } else {
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        contentView.readError(response.getMsg());
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.readError("请求出错");
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
        Object tag = HttpManager.getObject(context,ReadBean.class, Url.del_msg, httpParams, new OkGoCallback<ReadBean>() {
            @Override
            public void onSuccess(ReadBean response) {
                if (response.isSuccess()) {
                    contentView.delSuccess(value);
                    EventBus.getDefault().post(new UpdateMsgCount());
                } else {
                    if (!TextUtils.isEmpty(response.getMsg())) {
                        contentView.delError(response.getMsg());
                    }
                }
            }

            @Override
            public void parseError() {
                super.parseError();
                contentView.delError("请求出错");
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
