package com.xbzhangshi.mvp.home;

import android.Manifest;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.home.adapter.HomePagerAdapter;
import com.xbzhangshi.view.BottomBar;
import com.xbzhangshi.view.BottomBarTab;
import com.xbzhangshi.view.NoScrollViewPager;

import butterknife.BindView;


public class HomeActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    @BindView(R.id.no_scroll_viewpager)
    NoScrollViewPager noScrollViewpager;
    HomePagerAdapter homePagerAdapter;


    @Override
    protected int getlayout() {
        return R.layout.home_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        noScrollViewpager.setNoScroll(true);
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        noScrollViewpager.setAdapter(homePagerAdapter);
        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_launcher_background, getString(R.string.test)))
                .addItem(new BottomBarTab(this, R.drawable.ic_launcher_background, getString(R.string.test)))
                .addItem(new BottomBarTab(this, R.drawable.ic_launcher_background, getString(R.string.test)))
                .addItem(new BottomBarTab(this, R.drawable.ic_launcher_background, getString(R.string.test)));
        mBottomBar.setCurrentItem(0);
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {

                LogUtils.e("onTabSelected:" + position + "+" + prePosition);
                noScrollViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                LogUtils.e("onTabReselected:" + position + "+");
            }
        });

    }


    @Override
    protected void initdata() {

    }

}
