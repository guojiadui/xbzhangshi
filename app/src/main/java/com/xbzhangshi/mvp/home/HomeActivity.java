package com.xbzhangshi.mvp.home;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.xbzhangshi.R;
import com.xbzhangshi.app.MyApplication;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.home.Fragment.HomeBettingFragment;
import com.xbzhangshi.mvp.home.Fragment.HomeOpenPrizeFragmenrt;
import com.xbzhangshi.mvp.home.Fragment.HomePurchaseFragment;
import com.xbzhangshi.mvp.home.Fragment.HomeUserCenterFragment;
import com.xbzhangshi.mvp.home.baseView.IHomeBaseView;
import com.xbzhangshi.mvp.home.event.ClearHomeMsgEvent;
import com.xbzhangshi.mvp.home.event.LogoutEvent;
import com.xbzhangshi.mvp.home.event.SelectEvent;
import com.xbzhangshi.mvp.home.event.SideOpenEvent;
import com.xbzhangshi.mvp.home.event.SwithEvent;
import com.xbzhangshi.mvp.home.presenter.HomePresenter;
import com.xbzhangshi.mvp.home.presenter.SidePesenter;
import com.xbzhangshi.mvp.login.LoginActivity;
import com.xbzhangshi.mvp.login.LoginSuccessEvent;
import com.xbzhangshi.view.BottomBar;
import com.xbzhangshi.view.BottomBarTab;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;


public class HomeActivity extends BaseActivity implements IHomeBaseView {

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    HomeBettingFragment homeBettingFragment;//投注
    HomePurchaseFragment homePurchaseFragment;//购彩
    HomeOpenPrizeFragmenrt homeOpenPrizeFragmenrt;//开奖公告
    HomeUserCenterFragment homeUserCenterFragment;//个人中心

    View viewMenu;//菜单
    SidePesenter sidePesenter;
    HomePresenter homePresenter;

    @Override
    protected int getlayout() {
        return R.layout.home_activity_layout;
    }

    SlidingMenu menu;

    @Override
    protected void initView(Bundle savedInstanceState) {
        homePresenter = HomePresenter.newInstance(this);
        EventBus.getDefault().register(this);

        mBottomBar.addItem(new BottomBarTab(this, R.mipmap.sy_nor, "投注大厅"))
                .addItem(new BottomBarTab(this, R.mipmap.gcdt_nor, "采购大厅"))
                .addItem(new BottomBarTab(this, R.mipmap.kjdt_nor, "开奖公告"))
                .addItem(new BottomBarTab(this, R.mipmap.grzx_nor, "个人中心"));
        mBottomBar.setCurrentItem(0);
        switchFragment(0);
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public boolean onInterceptTouchEvent(int position) {
                //判断是否登录
                if (homePresenter == null) {
                    return true;
                }
                if (position == 3 && !homePresenter.isLogin()) {
                    LoginActivity.startLogin(HomeActivity.this);
                    return true;
                }
                return false;
            }

            @Override
            public void onTabSelected(int position, int prePosition) {
                LogUtils.e("onTabSelected:" + position + "+" + prePosition);
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
        initMenu();
    }

    @Override
    protected void initdata() {
        super.initdata();

    }

    public void initMenu() {
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
        viewMenu = LayoutInflater.from(this).inflate(R.layout.sideslip_layout, null);
        menu.setMenu(viewMenu);
        sidePesenter = SidePesenter.newInstance(viewMenu);
        sidePesenter.init(this);
        RelativeLayout sideExit = viewMenu.findViewById(R.id.side_app_exit);
        sideExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sidePesenter.exit();
            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SideOpenEvent event) {
        if (menu != null && !menu.isMenuShowing()) {
            menu.toggle();
        }
    }

    //退出
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogoutEvent event) {
        if (mBottomBar != null) {
            mBottomBar.setCurrentItem(0);
        }
        if (sidePesenter != null) {
            sidePesenter.loginout();
        }
        /**
         * 启动登录
         */
        LoginActivity.startLogin(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginSuccessEvent event) {
        if (sidePesenter != null) {
            sidePesenter.login(this);
        }
        //登录成功后获取没读
        if(homePresenter!=null){
            homePresenter.getMsgCount(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SelectEvent event) {
        if (mBottomBar != null) {
            mBottomBar.setCurrentItem(event.getPosition());
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ClearHomeMsgEvent event) {
        if (mBottomBar != null) {
            mBottomBar.getItem(3).setUnreadCount(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (homePresenter != null) {
            homePresenter.onDestory();
        }
        if (sidePesenter != null) {
            sidePesenter.onDestory();
        }
        EventBus.getDefault().unregister(this);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_UP && event.getRepeatCount() == 0) {
                if (menu != null && menu.isMenuShowing()) {
                    menu.toggle();
                    return true;
                }
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    MyApplication.getInstance().exit();
                }
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
        if (position == 0) {
            SlidingMenu.isCanMove = true;
        } else {
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
    public void upMsgCount(int msg) {
        if(mBottomBar==null){
            return;
        }
        mBottomBar.getItem(3).setUnreadCount(msg);
    }

}
