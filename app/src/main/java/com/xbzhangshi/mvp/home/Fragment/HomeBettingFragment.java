package com.xbzhangshi.mvp.home.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.adapter.LotteryTypeFraggmentAdapter;
import com.xbzhangshi.view.CustomViewPager;

import butterknife.BindView;

/**
 * 投注大厅
 */
public class HomeBettingFragment extends BaseFragment implements ViewPager.OnPageChangeListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_tabmain_viewPager)
    CustomViewPager fragmentTabmainViewPager;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    String[] tabNames = {"彩票","体育","电子","真人"};

    public static HomeBettingFragment newInstance() {
        HomeBettingFragment fragment = new HomeBettingFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_betting_fragment_layout;
    }

    @Override
    protected void initView(View view) {

        fragmentTabmainViewPager.setOffscreenPageLimit(4);
        fragmentTabmainViewPager.addOnPageChangeListener(this);

        fragmentTabmainViewPager.setAdapter(new LotteryTypeFraggmentAdapter(mActivity, fragmentTabmainViewPager, getChildFragmentManager()));
        tabLayout.setupWithViewPager(fragmentTabmainViewPager);
        //自定义tab的item
         for (int i = 0; i < 4; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //注意！！！这里就是添加我们自定义的布局
            tab.setCustomView(R.layout.tab_hall_item);
            //这里是初始化时，默认item0被选中，setSelected（true）是为了给图片和文字设置选中效果，代码在文章最后贴出
             TextView tabName =(TextView) tab.getCustomView().findViewById(R.id.tab_text);
             tabName.setText(tabNames[i]);
            if (i == 0) {
                tabName.setSelected(true);
            }
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.tab_text)).setSelected(true);
                fragmentTabmainViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.tab_text)).setSelected(false);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //延时测量
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                fragmentTabmainViewPager.resetHeight(0);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        fragmentTabmainViewPager.resetHeight(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
