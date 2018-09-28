package com.xbzhangshi.mvp.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.home.bean.USerCenterOnOffBean;

import java.util.List;

public class UserCenterAdapter  extends BaseQuickAdapter<USerCenterOnOffBean,BaseViewHolder> {
    public UserCenterAdapter( @Nullable List<USerCenterOnOffBean> data) {
        super(R.layout.user_center_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, USerCenterOnOffBean item) {
         helper.setText(R.id.name,item.name);
    }
}
