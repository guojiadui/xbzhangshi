package com.xbzhangshi.mvp.home.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.details.BettingDetailsActivity;
import com.xbzhangshi.mvp.home.adapter.BettingTypeAdapter;
import com.xbzhangshi.mvp.home.adapter.BettingTypeAdapter2;
import com.xbzhangshi.mvp.home.baseView.IBettingItemBaseView;
import com.xbzhangshi.mvp.home.bean.BesidesLotteryBean;
import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.presenter.BettingItemPresenter;
import com.xbzhangshi.view.CustomViewPager;
import com.xbzhangshi.view.PermissionsSetDialog;

import java.util.List;

import butterknife.BindView;


/**
 * 投注类型
 */
@SuppressLint("ValidFragment")
public class BettingItemFragment2 extends BaseFragment implements IBettingItemBaseView<LoctteryBean.ContentBean> {

    public static BettingItemFragment2 newInstance(CustomViewPager vp, int fragmentID) {
        BettingItemFragment2 fragment = new BettingItemFragment2(vp, fragmentID);
        Bundle bundle = new Bundle();
        bundle.putInt("postion", fragmentID);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.loading_progress)
    MultipleStatusView loadingProgress;

    CustomViewPager vp;
    int fragmentID;


    View contentView = null;
    BettingItemPresenter bettingItemPresenter;

    public BettingItemFragment2() {
    }


    public BettingItemFragment2(CustomViewPager vp, int fragmentID) {
        this();
        this.vp = vp;
        this.fragmentID = fragmentID;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.betting_item_fragment_layout;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(bettingItemPresenter!=null){
            bettingItemPresenter.onDestory();
        }
    }
    @Override
    public void fetchData() {
        super.fetchData();
        loadingProgress.showLoading();
        bettingItemPresenter.loadDataLotterys(mActivity );
    }

    @Override
    protected void initView(View view) {
        this.contentView = view;
        vp.setObjectForPosition(view, fragmentID);
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3));
        recyclerView.setNestedScrollingEnabled(false);//解决scrollview的recyclerView卡顿问题
        bettingItemPresenter = BettingItemPresenter.newInstance(this);
        loadingProgress.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgress.showLoading();
                bettingItemPresenter.loadDataLotterys(mActivity );
            }
        });
    }


    @Override
    public void onSuccess(List<LoctteryBean.ContentBean> list) {

    }
    @Override
    public void onSuccess2(List<LoctteryBean.ContentBean> list) {
        loadingProgress.showContent();
        BettingTypeAdapter2 bettingTypeAdapter = new BettingTypeAdapter2(mActivity,list);
        bettingTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, BettingDetailsActivity.class);
                mActivity.startActivity(intent);

            }
        });
        recyclerView.setAdapter(bettingTypeAdapter);
        vp.setObjectForPosition(contentView, fragmentID);
    }

    @Override
    public void onEmpty() {
        loadingProgress.showEmpty();
    }

    @Override
    public void onError() {
        loadingProgress.showError();
    }
}
