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
import com.xbzhangshi.mvp.home.bean.PurchaseTypeBean;
import com.xbzhangshi.mvp.home.adapter.GPurchaseTypeAdapter;
import com.xbzhangshi.mvp.home.adapter.VPurchaseTypeAdapter;
import com.xbzhangshi.mvp.home.event.SwithEvent;
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
public class PurchaseItemFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static PurchaseItemFragment newInstance() {
        PurchaseItemFragment fragment = new PurchaseItemFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.purchase_item_fragment_layout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SwithEvent event){
        initdata();
    }
    @Override
    protected void initView(View view) {
        initdata();
    }

    RecyclerView.ItemDecoration decoration;
    public  void  initdata(){
        List<PurchaseTypeBean> list = new ArrayList<>();
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
        if(HomePurchaseFragment.SHOWTYPE==HomePurchaseFragment.VER){
            recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            recyclerView.removeItemDecoration(decoration);
            decoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
            recyclerView.setPadding(10,0,10,0);
            recyclerView.addItemDecoration(decoration);
            recyclerView.setAdapter(new VPurchaseTypeAdapter(list));
        }else if(HomePurchaseFragment.SHOWTYPE==HomePurchaseFragment.GRID){
            recyclerView.setLayoutManager(new GridLayoutManager(mActivity,3));
            recyclerView.removeItemDecoration(decoration);
            recyclerView.setPadding(0,0,0,0);
            decoration = new DividerGridItemDecoration(mActivity);
            recyclerView.addItemDecoration(decoration);
            recyclerView.setAdapter(new GPurchaseTypeAdapter(list));
        }
    }
}
