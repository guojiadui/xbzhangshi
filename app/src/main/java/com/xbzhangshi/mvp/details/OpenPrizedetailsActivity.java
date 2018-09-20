package com.xbzhangshi.mvp.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.classic.common.MultipleStatusView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.details.adapter.OPenPrizeDetailsAdapter;
import com.xbzhangshi.mvp.details.baseview.IOpenPrizeDetailsBaseView;
import com.xbzhangshi.mvp.details.bean.OpenPrizeListBean;
import com.xbzhangshi.mvp.details.presenter.OpenPrizeDetailspresenter;
import com.xbzhangshi.mvp.home.bean.OpenPrizeBean;
import com.xbzhangshi.view.CustomToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 开奖列表详情页
 */
public class OpenPrizedetailsActivity extends BaseActivity implements OnLoadMoreListener, IOpenPrizeDetailsBaseView {


    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.betting)
    TextView betting;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;

    public static void start(Context context, OpenPrizeBean.DataBean dataBean) {
        Intent intent = new Intent(context, OpenPrizedetailsActivity.class);
        intent.putExtra("name", dataBean.getLotName());
        intent.putExtra("code", dataBean.getLotCode());
        context.startActivity(intent);
    }

    @Override
    protected int getlayout() {
        return R.layout.open_prize_details_activity_layout;
    }

    String name;
    String code;
    OpenPrizeDetailspresenter openPrizeDetailspresenter;
    OPenPrizeDetailsAdapter penPrizeDetailsAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        name = getIntent().getStringExtra("name");
        code = getIntent().getStringExtra("code");
        customtoolbar.setMainTitle(name);
        customtoolbar.mTvMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        betting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multipleStatusView.showLoading();
                openPrizeDetailspresenter.LoadDaata(OpenPrizedetailsActivity.this);
            }
        });
        refreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnLoadMoreListener(this);
        openPrizeDetailspresenter = OpenPrizeDetailspresenter.newInstance(this, code);
    }

    @Override
    protected void initdata() {
        super.initdata();
        multipleStatusView.showLoading();
        openPrizeDetailspresenter.LoadDaata(this);

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        //  refreshLayout.finishLoadMore();
        if (openPrizeDetailspresenter != null)
            openPrizeDetailspresenter.LoadDaata(this);
    }


    @Override
    public void onSuccess(List<OpenPrizeListBean.DataBean.ListBean> contentBeans, boolean hasNext) {
        multipleStatusView.showContent();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        penPrizeDetailsAdapter = new OPenPrizeDetailsAdapter(this, contentBeans,code);
        recyclerView.setAdapter(penPrizeDetailsAdapter);
        if (!hasNext) {
            refreshLayout.setEnableLoadMore(hasNext);
        }
    }

    @Override
    public void onEmpty() {
        multipleStatusView.showEmpty();
    }

    @Override
    public void onError() {
        multipleStatusView.showError();
    }

    @Override
    public void onMOre(List<OpenPrizeListBean.DataBean.ListBean> contentBeans, boolean hasNext) {
        refreshLayout.finishLoadMore();
        if (penPrizeDetailsAdapter == null) {
            return;
        }
        penPrizeDetailsAdapter.addData(contentBeans);
        if (!hasNext) {
            refreshLayout.setEnableLoadMore(hasNext);
        }
    }

    @Override
    public void onMoreEmpty() {
        Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoreError() {
        Toast.makeText(this, "请求出错", Toast.LENGTH_SHORT).show();
    }
}
