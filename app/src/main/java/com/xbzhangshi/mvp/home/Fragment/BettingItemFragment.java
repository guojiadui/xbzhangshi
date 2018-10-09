package com.xbzhangshi.mvp.home.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.login.LoginActivity;
import com.xbzhangshi.mvp.webview.BettingDetailsActivity;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.baseView.IBettingItemBaseView;
import com.xbzhangshi.mvp.home.bean.BesidesLotteryBean;
import com.xbzhangshi.mvp.home.adapter.BettingTypeAdapter;


import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.presenter.BettingItemPresenter;
import com.xbzhangshi.mvp.webview.ThreeGameActivity;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.view.CustomViewPager;

import java.util.List;

import butterknife.BindView;


/**
 * 投注类型
 */
@SuppressLint("ValidFragment")
public class BettingItemFragment extends BaseFragment implements IBettingItemBaseView<BesidesLotteryBean.ContentBean> {

    public static BettingItemFragment newInstance(CustomViewPager vp, int fragmentID) {
        BettingItemFragment fragment = new BettingItemFragment(vp, fragmentID);
        Bundle bundle = new Bundle();
        bundle.putInt("postion", fragmentID);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.loading_progress)
    MultipleStatusView loadingProgress;
    @BindView(R.id.tip)
    TextView tip;
    CustomViewPager vp;
    int fragmentID;


    View contentView = null;
    BettingItemPresenter bettingItemPresenter;

    public BettingItemFragment() {
    }


    public BettingItemFragment(CustomViewPager vp, int fragmentID) {
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
        if (bettingItemPresenter != null) {
            bettingItemPresenter.onDestory();
        }
    }

    @Override
    public void fetchData() {
        super.fetchData();
        int p = getArguments().getInt("postion");
        loadingProgress.showLoading();
        bettingItemPresenter.loadDataBesidesLotterys(mActivity, p);
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
                int p = getArguments().getInt("postion");
                bettingItemPresenter.loadDataBesidesLotterys(mActivity, p);
            }
        });
        int postion = getArguments().getInt("postion");
        if(postion==1){
            tip.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onSuccess(List<BesidesLotteryBean.ContentBean> list) {
        loadingProgress.showContent();
        BettingTypeAdapter bettingTypeAdapter = new BettingTypeAdapter(mActivity, list);
        bettingTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                BettingTypeAdapter bettingTypeAdapter1 = (BettingTypeAdapter) adapter;
                BesidesLotteryBean.ContentBean contentBean = bettingTypeAdapter1.getData().get(position);
                ThreeGameActivity.start(mActivity, contentBean.getPlayCode());
            }
        });
        recyclerView.setAdapter(bettingTypeAdapter);
        vp.setObjectForPosition(contentView, fragmentID);
    }

    @Override
    public void onSuccess2(List<LoctteryBean.ContentBean> list) {

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
