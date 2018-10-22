package com.xbzhangshi.mvp.home.Fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.baseView.IPurchaseItemView;
import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.adapter.GPurchaseTypeAdapter;
import com.xbzhangshi.mvp.home.adapter.VPurchaseTypeAdapter;
import com.xbzhangshi.mvp.home.event.SwithEvent;
import com.xbzhangshi.mvp.home.event.UpdateLotteryEvent;
import com.xbzhangshi.mvp.home.presenter.PurchaseItemPesenter;
import com.xbzhangshi.mvp.webview.BettingDetailsActivity;
import com.xbzhangshi.mvp.threegame.ThreeGameActivity;
import com.xbzhangshi.view.DividerGridItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;


/**
 * 采购类型item页
 */
public class PurchaseItemFragment extends BaseFragment implements IPurchaseItemView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    PurchaseItemPesenter purchaseItemPesenter;


    public static PurchaseItemFragment newInstance(int curPosiition) {
        PurchaseItemFragment fragment = new PurchaseItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("curPosiition", curPosiition);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.purchase_item_fragment_layout;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SwithEvent event) {

        if (purchaseItemPesenter != null) {
            purchaseItemPesenter.setLookMode();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UpdateLotteryEvent event) {
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            if (recyclerView.getAdapter() instanceof VPurchaseTypeAdapter) {
                VPurchaseTypeAdapter vPurchaseTypeAdapter = (VPurchaseTypeAdapter) recyclerView.getAdapter();
                List<LoctteryBean.ContentBean> contentBeans = vPurchaseTypeAdapter.getData();
                for (int i = 0; i < contentBeans.size(); i++) {
                    String code = contentBeans.get(i).getCode();
                    if (code.equals(event.getCode())) {
                        vPurchaseTypeAdapter.notifyItemChanged(i);
                        break;
                    }
                }
            } else if (recyclerView.getAdapter() instanceof GPurchaseTypeAdapter) {
                GPurchaseTypeAdapter gPurchaseTypeAdapter = (GPurchaseTypeAdapter) recyclerView.getAdapter();
                List<LoctteryBean.ContentBean> contentBeans = gPurchaseTypeAdapter.getData();
                for (int i = 0; i < contentBeans.size(); i++) {
                    String code = contentBeans.get(i).getCode();
                    if (code.equals(event.getCode())) {
                        gPurchaseTypeAdapter.notifyItemChanged(i);
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
        int curPosition = getArguments().getInt("curPosiition");
        purchaseItemPesenter = PurchaseItemPesenter.newInstance(this, curPosition);

    }

    /**
     * 可见加载数据
     */
    @Override
    public void fetchData() {
        super.fetchData();

        multipleStatusView.showLoading();
        purchaseItemPesenter.loadData(mActivity);
    }


    DividerItemDecoration vdecoration;
    DividerGridItemDecoration gdecoration;
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    VPurchaseTypeAdapter vPurchaseTypeAdapter;
    GPurchaseTypeAdapter gPurchaseTypeAdapter;

    @Override
    public void onSuccess(List<LoctteryBean.ContentBean> list, boolean mode, int curPosition) {
        multipleStatusView.showContent();
        if (mode) {
            if (linearLayoutManager == null) {
                linearLayoutManager = new LinearLayoutManager(mActivity);
            }
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.removeItemDecoration(vdecoration);
            recyclerView.removeItemDecoration(gdecoration);
            if (vdecoration == null) {
                vdecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
            }
            recyclerView.setPadding(10, 0, 10, 0);
            recyclerView.addItemDecoration(vdecoration);
            if (vPurchaseTypeAdapter == null) {
                vPurchaseTypeAdapter = new VPurchaseTypeAdapter(mActivity, list, curPosition);
                vPurchaseTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        VPurchaseTypeAdapter adapter1 = (VPurchaseTypeAdapter) adapter;
                      //  String code = adapter1.getData().get(position).getCode();
                        if(adapter1.getData().get(position).getIsThird()==2){
                            //三方彩票
                            ThreeGameActivity.start(mActivity, adapter1.getData().get(position).getCode());
                        }else {
                            BettingDetailsActivity.start(mActivity, adapter1.getData().get(position).getCode());
                        }
                    }
                });
            }
            recyclerView.setAdapter(vPurchaseTypeAdapter);
        } else {
            if (gridLayoutManager == null) {
                gridLayoutManager = new GridLayoutManager(mActivity, 3);
            }
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.removeItemDecoration(vdecoration);
            recyclerView.removeItemDecoration(gdecoration);
            if (gdecoration == null) {
                gdecoration = new DividerGridItemDecoration(mActivity);
            }
            recyclerView.setPadding(0, 0, 0, 0);
            recyclerView.addItemDecoration(gdecoration);
            if (gPurchaseTypeAdapter == null) {
                gPurchaseTypeAdapter = new GPurchaseTypeAdapter(mActivity, list, curPosition);
                gPurchaseTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        GPurchaseTypeAdapter adapter1 = (GPurchaseTypeAdapter) adapter;
                        String code = adapter1.getData().get(position).getCode();
                        if(adapter1.getData().get(position).getIsThird()==2){
                            //三方彩票
                            ThreeGameActivity.start(mActivity, adapter1.getData().get(position).getCode());
                        }else {
                            BettingDetailsActivity.start(mActivity, adapter1.getData().get(position).getCode());
                        }
                    }
                });
            }
            recyclerView.setAdapter(gPurchaseTypeAdapter);
        }
    }

    @Override
    public void swtih(List<LoctteryBean.ContentBean> list, boolean mode, int curPosition) {
        if (recyclerView.getAdapter() == null) {
            return;
        }
        if (mode) {
            if (recyclerView.getAdapter() instanceof VPurchaseTypeAdapter) {
                return;
            }
            if (linearLayoutManager == null) {
                linearLayoutManager = new LinearLayoutManager(mActivity);
            }
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.removeItemDecoration(vdecoration);
            recyclerView.removeItemDecoration(gdecoration);
            if (vdecoration == null) {
                vdecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
            }
            recyclerView.setPadding(10, 0, 10, 0);
            recyclerView.addItemDecoration(vdecoration);
            if (vPurchaseTypeAdapter == null) {
                vPurchaseTypeAdapter = new VPurchaseTypeAdapter(mActivity, list, curPosition);
                vPurchaseTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        VPurchaseTypeAdapter adapter1 = (VPurchaseTypeAdapter) adapter;
                        String code = adapter1.getData().get(position).getCode();
                        if(adapter1.getData().get(position).getIsThird()==2){
                            //三方彩票
                            ThreeGameActivity.start(mActivity, adapter1.getData().get(position).getCode());
                        }else {
                            BettingDetailsActivity.start(mActivity, adapter1.getData().get(position).getCode());
                        }
                    }
                });
            }
            recyclerView.setAdapter(vPurchaseTypeAdapter);
        } else {
            if (recyclerView.getAdapter() instanceof GPurchaseTypeAdapter) {
                return;
            }
            if (gridLayoutManager == null) {
                gridLayoutManager = new GridLayoutManager(mActivity, 3);

            }
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.removeItemDecoration(vdecoration);
            recyclerView.removeItemDecoration(gdecoration);
            if (gdecoration == null) {
                gdecoration = new DividerGridItemDecoration(mActivity);
            }
            recyclerView.setPadding(0, 0, 0, 0);
            recyclerView.addItemDecoration(gdecoration);
            if (gPurchaseTypeAdapter == null) {
                gPurchaseTypeAdapter = new GPurchaseTypeAdapter(mActivity, list, curPosition);
                gPurchaseTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        GPurchaseTypeAdapter adapter1 = (GPurchaseTypeAdapter) adapter;
                        String code = adapter1.getData().get(position).getCode();
                        if(adapter1.getData().get(position).getIsThird()==2){
                            //三方彩票
                            ThreeGameActivity.start(mActivity, adapter1.getData().get(position).getCode());
                        }else {
                            BettingDetailsActivity.start(mActivity, adapter1.getData().get(position).getCode());
                        }
                    }
                });
            }
            recyclerView.setAdapter(gPurchaseTypeAdapter);
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
}
