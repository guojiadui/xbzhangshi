package com.xbzhangshi.mvp.home.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.adapter.LotteryTypeFraggmentAdapter;
import com.xbzhangshi.mvp.home.baseView.IBettingBaseView;
import com.xbzhangshi.mvp.home.event.SideOpenEvent;
import com.xbzhangshi.mvp.home.presenter.BettingPresenter;
import com.xbzhangshi.view.CustomViewPager;
import com.xbzhangshi.view.MarqueeTextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 投注大厅
 */
public class HomeBettingFragment extends BaseFragment implements IBettingBaseView, ViewPager.OnPageChangeListener {

    @BindView(R.id.fragment_tabmain_viewPager)
    CustomViewPager fragmentTabmainViewPager;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    String[] tabNames = {"彩票", "体育", "真人","电子","棋牌"};
    BettingPresenter bettingPresenter;
    @BindView(R.id.notice)
    MarqueeTextView notice;
    Unbinder unbinder;

    public static HomeBettingFragment newInstance() {
        HomeBettingFragment fragment = new HomeBettingFragment();

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_betting_fragment_layout;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initView(View view) {
        fragmentTabmainViewPager.setOffscreenPageLimit(tabNames.length);
        fragmentTabmainViewPager.addOnPageChangeListener(this);

        fragmentTabmainViewPager.setAdapter(new LotteryTypeFraggmentAdapter(mActivity, fragmentTabmainViewPager, getChildFragmentManager()));
        tabLayout.setupWithViewPager(fragmentTabmainViewPager);
        //自定义tab的item
        for (int i = 0; i < tabNames.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //注意！！！这里就是添加我们自定义的布局
            tab.setCustomView(R.layout.tab_hall_item);
            //这里是初始化时，默认item0被选中，setSelected（true）是为了给图片和文字设置选中效果，代码在文章最后贴出
            TextView tabName = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
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
        bettingPresenter = BettingPresenter.newInstance(this);
        bettingPresenter.init();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        bettingPresenter.loadData(mActivity);
    }

    @OnClick(R.id.side_icon)
    public void sendEvent(View v) {
        EventBus.getDefault().post(new SideOpenEvent());
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


    @Override
    public void setNotice(String content) {
        notice.setText(Html.fromHtml(content));
    }


}
