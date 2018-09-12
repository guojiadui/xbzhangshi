package com.xbzhangshi.mvp.usercenter.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.usercenter.bean.BettingItemBean;

import java.util.List;

public class RecordSelectAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public RecordSelectAdapter(@Nullable List<String> data, String curString) {
        super(R.layout.record_select_adapter_item, data);
        this.curString = curString;
    }

    public int curSelect = 0;
    public String curString;

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (TextUtils.isEmpty(curString)) {
            if (curSelect == helper.getAdapterPosition()) {
                RadioButton radioButton = helper.getView(R.id.radio);
                radioButton.setChecked(true);
            } else {
                RadioButton radioButton = helper.getView(R.id.radio);
                radioButton.setChecked(false);
            }
        } else {
            if (curString.equals(item)) {
                RadioButton radioButton = helper.getView(R.id.radio);
                radioButton.setChecked(true);
                curString = null;
            } else {
                RadioButton radioButton = helper.getView(R.id.radio);
                radioButton.setChecked(false);
            }
        }
      helper.setText(R.id.type,item);

    }
}
