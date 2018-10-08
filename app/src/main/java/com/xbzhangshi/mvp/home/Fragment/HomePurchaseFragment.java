package com.xbzhangshi.mvp.home.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.se.omapi.SEService;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.adapter.PurchaseFragmentAdapter;
import com.xbzhangshi.mvp.home.baseView.IPurchaseView;
import com.xbzhangshi.mvp.home.bean.LoctteryBean;
import com.xbzhangshi.mvp.home.event.SwithEvent;
import com.xbzhangshi.mvp.home.presenter.PurchasePesenter;
import com.xbzhangshi.single.ServiceTime;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 采购大厅
 */
public class HomePurchaseFragment extends BaseFragment implements IPurchaseView {


    public static int curVisPage = 0;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;

    String[] tabNames = {"全部彩种", "高频彩", "低频彩"};
    int[] tabIcons = {R.mipmap.shoping_cart, R.mipmap.time_clock2, R.mipmap.time_clock1};
    @BindView(R.id.select1)
    ImageView select1;
    @BindView(R.id.select2)
    ImageView select2;

    PurchasePesenter purchasePesenter;

    public static HomePurchaseFragment newInstance() {
        HomePurchaseFragment fragment = new HomePurchaseFragment();
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(purchasePesenter!=null){
            purchasePesenter.onDestory();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(ServiceTime.getInstance()!=null){
            ServiceTime.getInstance().setIsvisable(!hidden);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(ServiceTime.getInstance()!=null ){
            ServiceTime.getInstance().setResume(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(ServiceTime.getInstance()!=null){
            ServiceTime.getInstance().setResume(true);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_purchase_fragment_layout;
    }

    @Override
    protected void initView(View view) {
        select1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (purchasePesenter != null)
                    purchasePesenter.setLookMode(false);
            }
        });
        select2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (purchasePesenter != null)
                    purchasePesenter.setLookMode(true);
            }
        });
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multipleStatusView.showLoading();
                purchasePesenter.getLoadData(mActivity);
            }
        });
        purchasePesenter = PurchasePesenter.newInstance(this);
        purchasePesenter.init();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        multipleStatusView.showLoading();
        purchasePesenter.getLoadData(mActivity);
    }

    @Override
    public void setLookModeViewBg(boolean mode) {
        if (mode) {
            select2.setBackgroundColor(mActivity.getResources().getColor(R.color.deep_red));
            select1.setBackgroundColor(0x00ffffff);

        } else {
            select1.setBackgroundColor(mActivity.getResources().getColor(R.color.deep_red));
            select2.setBackgroundColor(0x00ffffff);
        }
    }

    @Override
    public void onSuccess() {
        multipleStatusView.showContent();
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(new PurchaseFragmentAdapter(getChildFragmentManager()));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curVisPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewpager);
        //自定义tab的item
        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //注意！！！这里就是添加我们自定义的布局
            tab.setCustomView(R.layout.tab_purchase_item);
            //这里是初始化时，默认item0被选中，setSelected（true）是为了给图片和文字设置选中效果，代码在文章最后贴出
            TextView tabName = (TextView) tab.getCustomView().findViewById(R.id.tab_name);
            ImageView tabImg = (ImageView) tab.getCustomView().findViewById(R.id.tab_icon);
            tabName.setText(tabNames[i]);
            tabImg.setImageResource(tabIcons[i]);
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
