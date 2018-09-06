package com.xbzhangshi.mvp.home.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.details.BettingDetailsActivity;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.HomeActivity;
import com.xbzhangshi.mvp.home.bean.BettingTypeBean;
import com.xbzhangshi.mvp.home.adapter.BettingTypeAdapter;


import com.xbzhangshi.view.CustomViewPager;
import com.xbzhangshi.view.LoadStateView;
import com.xbzhangshi.view.PermissionsSetDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 投注类型
 */
@SuppressLint("ValidFragment")
public class BettingItemFragment extends BaseFragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    CustomViewPager vp;
    int fragmentID;
    @BindView(R.id.loading_progress)
    LoadStateView loadingProgress;

    public static BettingItemFragment newInstance(CustomViewPager vp, int fragmentID) {
        BettingItemFragment fragment = new BettingItemFragment(vp, fragmentID);
        return fragment;
    }
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

    View contentView = null;

    @Override
    protected void initView(View view) {

        this.contentView = view;
        vp.setObjectForPosition(view, fragmentID);
        final List<BettingTypeBean> lotteryTypeBeans = new ArrayList<>();
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());
        lotteryTypeBeans.add(new BettingTypeBean());

        recyclerView.setLayoutManager(new GridLayoutManager(mActivity,3));
        recyclerView.setNestedScrollingEnabled(false);//解决scrollview的recyclerView卡顿问题
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                BettingTypeAdapter bettingTypeAdapter = new BettingTypeAdapter(lotteryTypeBeans);
                bettingTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                      /*  Intent intent = new Intent(mActivity, BettingDetailsActivity.class);
                        mActivity.startActivity(intent);*/
                        RxPermissions rxPermissions = new RxPermissions((FragmentActivity) mActivity);
                        rxPermissions
                                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(permission -> { // will emit 2 Permission objects
                                    if (permission.granted) {
                                        // `permission.name` is granted !
                                    } else if (permission.shouldShowRequestPermissionRationale) {
                                        // Denied permission without ask never again
                                    } else {
                                        // Denied permission with ask never again
                                        // Need to go to the settings
                                        PermissionsSetDialog permissionsSetDialog = new PermissionsSetDialog(mActivity,null,"d");
                                        permissionsSetDialog.show();
                                    }
                                });
                    }
                });
                recyclerView.setAdapter(bettingTypeAdapter);
                vp.setObjectForPosition(contentView, fragmentID);

            }
        }, 2000);
    }

}
