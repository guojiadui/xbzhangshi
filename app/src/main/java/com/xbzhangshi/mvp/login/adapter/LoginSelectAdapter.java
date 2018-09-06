package com.xbzhangshi.mvp.login.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.login.bean.LoginSelectBean;

import java.util.List;

public class LoginSelectAdapter extends BaseQuickAdapter<LoginSelectBean,BaseViewHolder> {
    public LoginSelectAdapter(  @Nullable List<LoginSelectBean> data) {
        super(R.layout.login_name_select_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoginSelectBean item) {

    }
}
