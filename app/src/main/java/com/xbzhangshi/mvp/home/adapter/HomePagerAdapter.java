package com.xbzhangshi.mvp.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xbzhangshi.mvp.home.Fragment.HomeOpenPrizeFragmenrt;
import com.xbzhangshi.mvp.home.Fragment.HomePurchaseFragment;
import com.xbzhangshi.mvp.home.Fragment.HomeBettingFragment;
import com.xbzhangshi.mvp.home.Fragment.HomeUserCenterFragment;

import java.util.ArrayList;
import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<Fragment>();
        fragments.add(HomeBettingFragment.newInstance());
        fragments.add(HomePurchaseFragment.newInstance());
        fragments.add(HomeOpenPrizeFragmenrt.newInstance());
        fragments.add(HomeUserCenterFragment.newInstance());
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
