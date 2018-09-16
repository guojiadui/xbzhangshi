package com.xbzhangshi.mvp.home.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.baseView.IPurchaseItemView;
import com.xbzhangshi.mvp.home.bean.PurchaseTypeBean;
import com.xbzhangshi.mvp.home.adapter.GPurchaseTypeAdapter;
import com.xbzhangshi.mvp.home.adapter.VPurchaseTypeAdapter;
import com.xbzhangshi.mvp.home.event.SwithEvent;
import com.xbzhangshi.mvp.home.presenter.PurchaseItemPesenter;
import com.xbzhangshi.view.DividerGridItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 采购类型item页
 */
public class PurchaseItemFragment extends BaseFragment implements IPurchaseItemView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    PurchaseItemPesenter purchaseItemPesenter;
    RecyclerView.ItemDecoration decoration;
    List<PurchaseTypeBean> list;

    public static PurchaseItemFragment newInstance() {
        PurchaseItemFragment fragment = new PurchaseItemFragment();
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

    @Override
    protected void initView(View view) {
        initdata();
        purchaseItemPesenter = PurchaseItemPesenter.newInstance(this);
        purchaseItemPesenter.init();
    }


    public void initdata() {
        list = new ArrayList<>();
        list.add(new PurchaseTypeBean());
        list.add(new PurchaseTypeBean());
        list.add(new PurchaseTypeBean());
        list.add(new PurchaseTypeBean());
        list.add(new PurchaseTypeBean());
        list.add(new PurchaseTypeBean());
        list.add(new PurchaseTypeBean());
        list.add(new PurchaseTypeBean());
        list.add(new PurchaseTypeBean());
        list.add(new PurchaseTypeBean());
        list.add(new PurchaseTypeBean());
        list.add(new PurchaseTypeBean());


    }

    @Override
    public void switchMode(boolean mode) {
        if (mode) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            recyclerView.removeItemDecoration(decoration);
            decoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
            recyclerView.setPadding(10, 0, 10, 0);
            recyclerView.addItemDecoration(decoration);
            recyclerView.setAdapter(new VPurchaseTypeAdapter(list));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3));
            recyclerView.removeItemDecoration(decoration);
            recyclerView.setPadding(0, 0, 0, 0);
            decoration = new DividerGridItemDecoration(mActivity);
            recyclerView.addItemDecoration(decoration);
            recyclerView.setAdapter(new GPurchaseTypeAdapter(list));
        }
    }
}
