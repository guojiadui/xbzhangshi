package com.xbzhangshi.mvp.home.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.xbzhangshi.R;

import com.xbzhangshi.mvp.base.BaseFragment;
import com.xbzhangshi.mvp.home.HomeActivity;
import com.xbzhangshi.mvp.home.adapter.LotteryTypeFraggmentAdapter;
import com.xbzhangshi.mvp.home.baseView.IBettingBaseView;
import com.xbzhangshi.mvp.home.event.SelectEvent;
import com.xbzhangshi.mvp.home.event.SideOpenEvent;
import com.xbzhangshi.mvp.home.presenter.BettingPresenter;
import com.xbzhangshi.mvp.login.LoginActivity;
import com.xbzhangshi.mvp.login.LoginSuccessEvent;
import com.xbzhangshi.mvp.login.RegisterUserActivity;
import com.xbzhangshi.view.CustomViewPager;
import com.xbzhangshi.view.GlideCircleBorderTransform;
import com.xbzhangshi.view.MarqueeTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    String[] tabNames = {"彩票", "体育", "真人", "电子", "棋牌"};
    BettingPresenter bettingPresenter;
    @BindView(R.id.notice)
    MarqueeTextView notice;
    Unbinder unbinder;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.no_login_layout)
    LinearLayout noLoginLayout;
    @BindView(R.id.balance)
    TextView balance;
    Unbinder unbinder1;
    @BindView(R.id.user_icon)
    ImageView userIcon;
    Unbinder unbinder2;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        if (bettingPresenter != null) {
            bettingPresenter.onDestory();
        }
    }

     @OnClick({R.id.menu1,R.id.menu2,R.id.menu3,R.id.menu4,R.id.login,R.id.register})
     public   void  click(View v){
        switch (v.getId()){
            case  R.id.menu1:
            case  R.id.menu2:
                //判断是否登录
                if(bettingPresenter==null){
                    return  ;
                }
                if(!bettingPresenter.isLogin() ){
                    LoginActivity.startLogin(mActivity );
                    return;
                }
                EventBus.getDefault().post(new SelectEvent(3));
                break;
            case   R.id.login:
                LoginActivity.startLogin(mActivity );
                break;
            case   R.id.register:
                RegisterUserActivity.start(mActivity);
                break;
        }
     }

    //成功是否
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginSuccessEvent event) {
        if (noLoginLayout != null) {
            noLoginLayout.setVisibility(View.GONE);
            balance.setVisibility(View.VISIBLE);
            balance.setText("");
            if (bettingPresenter != null) {
                //获取余额
                bettingPresenter.getBalance(mActivity);
            }
        }
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
        //加载数据
        bettingPresenter.loadData(mActivity);
        //自动登录
        bettingPresenter.outologin(mActivity);
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
    public void loginSuccess() {
        if (noLoginLayout != null) {
            noLoginLayout.setVisibility(View.GONE);
            balance.setVisibility(View.VISIBLE);
            balance.setText("");
            if (bettingPresenter != null) {
                //获取余额
                bettingPresenter.getBalance(mActivity);
            }
        }
    }

    @Override
    public void LoginonError(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNotice(String content) {
        notice.setText(Html.fromHtml(content));
    }

    @Override
    public void updateBalance(String msg) {
        if (noLoginLayout != null) {
            noLoginLayout.setVisibility(View.GONE);
            balance.setVisibility(View.VISIBLE);
            balance.setText(msg);
            RequestOptions requestOptions = new RequestOptions().transform(new GlideCircleBorderTransform(9, 0xffff5555));
            Glide.with(this).load("http://xbzhanshi.com/mobile/v3/images/touxiang.png").apply(requestOptions).into(userIcon);
           /* GlideApp.with(this)
                    .load("http://xbzhanshi.com/mobile/v3/images/touxiang.png")
                    .centerCrop()
                    .transform(new GlideCircleBorderTransform(9, 0xffff5555))
                    .into(userIcon);*/


        }
    }


}
