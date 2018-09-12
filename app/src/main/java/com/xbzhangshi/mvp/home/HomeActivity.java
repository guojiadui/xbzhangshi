package com.xbzhangshi.mvp.home;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.blankj.utilcode.util.LogUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.Fragment.HomeBettingFragment;
import com.xbzhangshi.mvp.home.Fragment.HomeOpenPrizeFragmenrt;
import com.xbzhangshi.mvp.home.Fragment.HomePurchaseFragment;
import com.xbzhangshi.mvp.home.Fragment.HomeUserCenterFragment;
import com.xbzhangshi.mvp.home.adapter.HomePagerAdapter;
import com.xbzhangshi.view.BottomBar;
import com.xbzhangshi.view.BottomBarTab;
import com.xbzhangshi.view.NoScrollViewPager;

import butterknife.BindView;


public class HomeActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    HomeBettingFragment homeBettingFragment;//投注
    HomePurchaseFragment homePurchaseFragment;//购彩
    HomeOpenPrizeFragmenrt homeOpenPrizeFragmenrt;//开奖公告
    HomeUserCenterFragment homeUserCenterFragment;//个人中心


    @Override
    protected int getlayout() {
        return R.layout.home_activity_layout;
    }

    SlidingMenu menu;

    @Override
    protected void initView(Bundle savedInstanceState) {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.LEFT);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.color.colorAccent);

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.sideslip_layout);
     /*   noScrollViewpager.setNoScroll(true);
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        noScrollViewpager.setAdapter(homePagerAdapter);*/
        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_launcher_background, getString(R.string.test)))
                .addItem(new BottomBarTab(this, R.drawable.ic_launcher_background, getString(R.string.test)))
                .addItem(new BottomBarTab(this, R.drawable.ic_launcher_background, getString(R.string.test)))
                .addItem(new BottomBarTab(this, R.drawable.ic_launcher_background, getString(R.string.test)));
        mBottomBar.setCurrentItem(0);
        switchFragment(0);
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {

                LogUtils.e("onTabSelected:" + position + "+" + prePosition);
                //noScrollViewpager.setCurrentItem(position);
                switchFragment(position);
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
   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      if(keyCode== KeyEvent.KEYCODE_BACK){
          if(menu!=null&&menu.isMenuShowing()){
              menu.showMenu(false);
              return true;
          }
      }
        return super.onKeyDown(keyCode, event);
    }*/
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_UP && event.getRepeatCount() == 0) {
//         showMenu();
//         showContent();
               menu.toggle(false);
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
    /**
     * 切换Fragment
     *
     * @param
     */
    private void switchFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(position==0){
            SlidingMenu.isCanMove = true;
        }else {
            SlidingMenu.isCanMove = false;
        }
        switch (position) {
            case 0:
                hideFragments(transaction);
                if (homeBettingFragment != null) {
                    transaction.show(homeBettingFragment);
                } else {
                    homeBettingFragment = HomeBettingFragment.newInstance();
                    transaction.add(R.id.fragment_content, homeBettingFragment, HomeBettingFragment.class.getName());
                }
                break;
            case 1:
                hideFragments(transaction);
                if (homePurchaseFragment != null) {
                    transaction.show(homePurchaseFragment);
                } else {
                    homePurchaseFragment = HomePurchaseFragment.newInstance();
                    transaction.add(R.id.fragment_content, homePurchaseFragment, HomePurchaseFragment.class.getName());
                }
                break;
            case 2:
                hideFragments(transaction);
                if (homeOpenPrizeFragmenrt != null) {
                    transaction.show(homeOpenPrizeFragmenrt);
                } else {
                    homeOpenPrizeFragmenrt = HomeOpenPrizeFragmenrt.newInstance();
                    transaction.add(R.id.fragment_content, homeOpenPrizeFragmenrt, HomeOpenPrizeFragmenrt.class.getName());
                }
                break;
            case 3:
                hideFragments(transaction);
                if (homeUserCenterFragment != null) {
                    transaction.show(homeUserCenterFragment);
                } else {
                    homeUserCenterFragment = HomeUserCenterFragment.newInstance();
                    transaction.add(R.id.fragment_content, homeUserCenterFragment, HomeUserCenterFragment.class.getName());
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }
  /*  private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 首页
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1  //发现
            -> mDiscoveryFragment?.let {
                transaction.show(it)
            } ?: DiscoveryFragment.getInstance(mTitles[position]).let {
                mDiscoveryFragment = it
                transaction.add(R.id.fl_container, it, "discovery") }
            2  //热门
            -> mHotFragment?.let {
                transaction.show(it)
            } ?: HotFragment.getInstance(mTitles[position]).let {
                mHotFragment = it
                transaction.add(R.id.fl_container, it, "hot") }
            3 //我的
            -> mMineFragment?.let {
                transaction.show(it)
            } ?: MineFragment.getInstance(mTitles[position]).let {
                mMineFragment = it
                transaction.add(R.id.fl_container, it, "mine") }

            else -> {

            }
        }


    }*/

    /**
     * 隐藏所有的Fragment
     *
     * @param transaction transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeBettingFragment != null) {
            transaction.hide(homeBettingFragment);
        }
        if (homePurchaseFragment != null) {
            transaction.hide(homePurchaseFragment);
        }
        if (homeOpenPrizeFragmenrt != null) {
            transaction.hide(homeOpenPrizeFragmenrt);
        }
        if (homeUserCenterFragment != null) {
            transaction.hide(homeUserCenterFragment);
        }

    }

    @Override
    protected void initdata() {

    }


}
