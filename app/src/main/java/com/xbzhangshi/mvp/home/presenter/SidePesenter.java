package com.xbzhangshi.mvp.home.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.suke.widget.SwitchButton;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.MyApplication;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.bean.BalanceBean;
import com.xbzhangshi.mvp.home.bean.HomeDialogBean;
import com.xbzhangshi.mvp.home.event.RedPackEvent;
import com.xbzhangshi.mvp.home.event.ShowHomeEvent;
import com.xbzhangshi.mvp.login.LoginActivity;
import com.xbzhangshi.mvp.webview.HelpCneterActivity;
import com.xbzhangshi.mvp.webview.PreferentialActivitiy;
import com.xbzhangshi.single.UserInfo;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;

public class SidePesenter extends BasePresenter {
    public static SidePesenter newInstance(SlidingMenu menu, View sideView) {
        return new SidePesenter(menu, sideView);
    }

    View sideView;
    SlidingMenu menu;
    TextView balance;

    public SidePesenter(SlidingMenu menu, View sideView) {
        this.sideView = sideView;
        this.menu = menu;
    }


    public void init(Activity context) {

        RelativeLayout act_info = sideView.findViewById(R.id.act_info);
        RelativeLayout help_center = sideView.findViewById(R.id.help_center);
        act_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UserInfo.getInstance().isLogin) {
                    LoginActivity.startLogin(context);
                    return;
                }
                menu.toggle();
                PreferentialActivitiy.start(context);
            }
        });
        help_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();
                Intent intent = new Intent(context, HelpCneterActivity.class);
                context.startActivity(intent);
            }
        });

        boolean red = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.RED_ENVELOPPES_STATE, true);
        boolean window = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.HOME_WINDOW_TIP, true);
        boolean animation = SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.ANIMATION_STATE);
        SwitchButton switchButton1 = sideView.findViewById(R.id.check1);
        SwitchButton switchButton2 = sideView.findViewById(R.id.check2);
        SwitchButton switchButton3 = sideView.findViewById(R.id.check3);

        switchButton1.setChecked(red);
        switchButton2.setChecked(window);
        switchButton3.setChecked(animation);

        switchButton1.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                SPUtils.getInstance(Key.APP_SET_NAME).put(Key.RED_ENVELOPPES_STATE, isChecked);
                EventBus.getDefault().post(new RedPackEvent(isChecked));
            }
        });
        switchButton2.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                SPUtils.getInstance(Key.APP_SET_NAME).put(Key.HOME_WINDOW_TIP, isChecked);
                EventBus.getDefault().post(new ShowHomeEvent(isChecked));
            }
        });
        switchButton3.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                SPUtils.getInstance(Key.APP_SET_NAME).put(Key.ANIMATION_STATE, isChecked);
            }
        });

    }


    /***
     * 退出程序
     */

    public void exit() {
        MyApplication.getInstance().exit();
    }

    public void login(Context context) {
        if (UserInfo.getInstance().isLogin()) {
            LinearLayout l = sideView.findViewById(R.id.user_info_layout);
            l.setVisibility(View.VISIBLE);
            TextView name = l.findViewById(R.id.sideslip_user_name);
            balance = l.findViewById(R.id.sideslip_balance);
            if (UserInfo.getInstance().getLoginUserInfoBean() != null &&
                    !TextUtils.isEmpty(UserInfo.getInstance().getLoginUserInfoBean().getContent().getAccount())) {
                name.setText(UserInfo.getInstance().getLoginUserInfoBean().getContent().getAccount().replace("\n", "").replace(" ", ""));

            }
        }

    }

    public void loginout() {
        LinearLayout l = sideView.findViewById(R.id.user_info_layout);
        TextView name = l.findViewById(R.id.sideslip_user_name);
        TextView balance = l.findViewById(R.id.sideslip_balance);
        name.setText("");
        balance.setText("");
        l.setVisibility(View.GONE);
    }


    /**
     * 获取余额
     */
   /* public void getBalance(Context context, TextView textView) {
        Object tag = HttpManager.getObject(context, BalanceBean.class,
                Url.BASE_URL + Url.meminfo, null, new OkGoCallback<BalanceBean>() {
                    @Override
                    public void onSuccess(BalanceBean response) {
                        if (response.isSuccess()) {
                            DecimalFormat df = new DecimalFormat("#0.00");
                            textView.setText(df.format(response.getContent().getBalance()) + "元");

                        }
                    }
                });
        addNet(tag);
    }*/
    public void setBalance(String s) {
        if (balance != null&&!TextUtils.isEmpty(s)) {
            balance.setText(s);
        }
    }

    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
