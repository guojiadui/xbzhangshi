package com.xbzhangshi.mvp.usercenter.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.usercenter.bean.MsgBean;
import com.xbzhangshi.mvp.usercenter.presenter.MessageListPresenter;

import java.util.List;

public class MsgAdapter extends BaseQuickAdapter<MsgBean.ContentBean.DatasBean, BaseViewHolder> {
    Context context;
    MessageListPresenter messageListPresenter;

    public MsgAdapter(Context context, MessageListPresenter messageListPresenter, @Nullable List<MsgBean.ContentBean.DatasBean> data) {
        super(R.layout.msg_adapter_item, data);
        this.messageListPresenter = messageListPresenter;
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgBean.ContentBean.DatasBean item) {
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.title, item.getTitle());
        }

        if (messageListPresenter.isEdit) {
            helper.getView(R.id.radio).setVisibility(View.VISIBLE);
            RadioButton radioButton = helper.getView(R.id.radio);
            radioButton.setChecked(item.ischeck);
        } else {
            helper.getView(R.id.radio).setVisibility(View.GONE);
        }
        if (item.getStatus() == 1) {
            helper.setTextColor(R.id.title, 0xff171719);
        } else {
            helper.setTextColor(R.id.title, 0xff888888);
        }
    }
}
