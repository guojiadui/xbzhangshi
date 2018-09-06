package com.xbzhangshi.mvp.home.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.adapter.PurchaseFragmentAdapter;
import com.xbzhangshi.mvp.home.event.SwithEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 采购大厅
 */
public class HomePurchaseFragment extends BaseFragment {
    public static final String GRID = "grid";
    public static final String VER = "ver";
    public static String SHOWTYPE = VER;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    String[] tabNames = {"全部彩种", "高频彩", "低频彩"};
    @BindView(R.id.select1)
    ImageView select1;
    @BindView(R.id.select2)
    ImageView select2;


    public static HomePurchaseFragment newInstance() {
        HomePurchaseFragment fragment = new HomePurchaseFragment();
        return fragment;
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
                SHOWTYPE = GRID;
                EventBus.getDefault().post(new SwithEvent());
            }
        });
        select2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SHOWTYPE = VER;
                EventBus.getDefault().post(new SwithEvent());
            }
        });
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(new PurchaseFragmentAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewpager);
        //自定义tab的item
        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //注意！！！这里就是添加我们自定义的布局
            tab.setCustomView(R.layout.tab_purchase_item);
            //这里是初始化时，默认item0被选中，setSelected（true）是为了给图片和文字设置选中效果，代码在文章最后贴出
            TextView tabName = (TextView) tab.getCustomView().findViewById(R.id.tab_name);
            tabName.setText(tabNames[i]);
        }


    }


}
