package com.xbzhangshi.mvp.home.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;

import com.xbzhangshi.mvp.home.Fragment.BettingItemFragment;
import com.xbzhangshi.mvp.home.Fragment.BettingItemFragment2;
import com.xbzhangshi.view.CustomViewPager;

public class LotteryTypeFraggmentAdapter extends FragmentPagerAdapter {
    Context context;
    private LayoutInflater inflate;
    CustomViewPager customViewPager;
    public LotteryTypeFraggmentAdapter(Context context,CustomViewPager customViewPager, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
        inflate = LayoutInflater.from(context);
        this.customViewPager =customViewPager;
    }



    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {
         if(position==0){
             return BettingItemFragment2.newInstance(customViewPager,position);
         }
        return BettingItemFragment.newInstance(customViewPager,position);
    }
}
