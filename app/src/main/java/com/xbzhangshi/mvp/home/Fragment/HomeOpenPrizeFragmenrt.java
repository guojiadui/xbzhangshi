package com.xbzhangshi.mvp.home.Fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.adapter.OpenPrizeAdapter;
import com.xbzhangshi.mvp.home.bean.OpenPrizeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 开奖公告
 */
public class HomeOpenPrizeFragmenrt extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerView.ItemDecoration decoration;

    public static HomeOpenPrizeFragmenrt newInstance() {
        HomeOpenPrizeFragmenrt fragment = new HomeOpenPrizeFragmenrt();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_open_prize_fragment_layout;
    }

    @Override
    protected void initView(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.removeItemDecoration(decoration);
        decoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        List<OpenPrizeBean> list = new ArrayList<>();
        list.add(new OpenPrizeBean());
        list.add(new OpenPrizeBean());
        list.add(new OpenPrizeBean());
        list.add(new OpenPrizeBean());
        list.add(new OpenPrizeBean());
        list.add(new OpenPrizeBean());
        list.add(new OpenPrizeBean());
        list.add(new OpenPrizeBean());
        list.add(new OpenPrizeBean());
        list.add(new OpenPrizeBean());
        list.add(new OpenPrizeBean());
        recyclerView.setAdapter(new OpenPrizeAdapter(mActivity,list));
    }

}
