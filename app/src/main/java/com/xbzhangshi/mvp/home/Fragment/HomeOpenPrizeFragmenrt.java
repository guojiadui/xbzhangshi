package com.xbzhangshi.mvp.home.Fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.classic.common.MultipleStatusView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.adapter.OpenPrizeAdapter;
import com.xbzhangshi.mvp.home.baseView.IOpenPrizeBaseView;
import com.xbzhangshi.mvp.home.bean.LotterysCountDownBean;
import com.xbzhangshi.mvp.home.bean.OpenPrizeBean;
import com.xbzhangshi.mvp.home.presenter.OpenPrizePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 开奖公告
 */
public class HomeOpenPrizeFragmenrt extends BaseFragment implements IOpenPrizeBaseView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    RecyclerView.ItemDecoration decoration;

    public static HomeOpenPrizeFragmenrt newInstance() {
        HomeOpenPrizeFragmenrt fragment = new HomeOpenPrizeFragmenrt();
        return fragment;
    }

    OpenPrizePresenter openPrizePresenter;
    RefreshLayout mRefreshlayout =null;
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
        smartRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        smartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mRefreshlayout = refreshlayout;
                openPrizePresenter.getLoadData(mActivity);
            }
        });
        openPrizePresenter = OpenPrizePresenter.newInstance(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        multipleStatusView.showLoading();
        openPrizePresenter.getLoadData(mActivity);
    }

    @Override
    public void onSuccess(List<LotterysCountDownBean.ContentBean> contentBeans) {
        multipleStatusView.showContent();
        if(mRefreshlayout!=null ){
            mRefreshlayout.finishRefresh();
            mRefreshlayout=null;
        }
        recyclerView.setAdapter(new OpenPrizeAdapter(mActivity, contentBeans));
    }

    @Override
    public void onEmpty() {
        if(mRefreshlayout!=null ){
            mRefreshlayout.finishRefresh();
            mRefreshlayout=null;
        }
        multipleStatusView.showEmpty();
    }

    @Override
    public void onError() {
        if(mRefreshlayout!=null ){
            mRefreshlayout.finishRefresh();
            mRefreshlayout=null;
        }
        multipleStatusView.showError();
    }
}
