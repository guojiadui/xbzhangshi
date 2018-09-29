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

import java.text.SimpleDateFormat;
import java.util.List;

public class MsgAdapter extends BaseQuickAdapter<MsgBean.ListBean, BaseViewHolder> {
    Context context;
    MessageListPresenter messageListPresenter;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public MsgAdapter(Context context, MessageListPresenter messageListPresenter, @Nullable List<MsgBean.ListBean> data) {
        super(R.layout.msg_adapter_item, data);
        this.context = context;
        this.messageListPresenter = messageListPresenter;
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgBean.ListBean item) {
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
        String t = formatter.format(item.getCreateDatetime());
        helper.setText(R.id.time, t);
    }
}
