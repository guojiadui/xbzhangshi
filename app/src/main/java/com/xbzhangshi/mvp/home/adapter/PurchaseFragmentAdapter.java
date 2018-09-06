package com.xbzhangshi.mvp.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xbzhangshi.mvp.home.Fragment.PurchaseItemFragment;

public class PurchaseFragmentAdapter extends FragmentPagerAdapter {
    public PurchaseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PurchaseItemFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
