package com.xbzhangshi.mvp.home.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.suke.widget.SwitchButton;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Key;
import com.xbzhangshi.app.MyApplication;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BasePresenter;
import com.xbzhangshi.mvp.home.bean.BalanceBean;
import com.xbzhangshi.single.UserInfo;

import java.text.DecimalFormat;

public class SidePesenter extends BasePresenter {
    public static SidePesenter newInstance(View sideView) {
        return new SidePesenter(sideView);
    }

    View sideView;

    public SidePesenter(View sideView) {
        this.sideView = sideView;
    }


    public  void  init(Context context){
      boolean red =  SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.RED_ENVELOPPES_STATE);
      boolean window =  SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.HOME_WINDOW_TIP);
      boolean animation =  SPUtils.getInstance(Key.APP_SET_NAME).getBoolean(Key.ANIMATION_STATE);
        SwitchButton switchButton1  = sideView.findViewById(R.id.check1);
        SwitchButton switchButton2  = sideView.findViewById(R.id.check2);
        SwitchButton switchButton3  = sideView.findViewById(R.id.check3);

        switchButton1.setChecked(red);
        switchButton2.setChecked(window);
        switchButton3.setChecked(animation);

        switchButton1.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                SPUtils.getInstance(Key.APP_SET_NAME).put(Key.RED_ENVELOPPES_STATE,isChecked);
            }
        });
        switchButton2.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                SPUtils.getInstance(Key.APP_SET_NAME).put(Key.HOME_WINDOW_TIP,isChecked);
            }
        });
        switchButton3.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                SPUtils.getInstance(Key.APP_SET_NAME).put(Key.ANIMATION_STATE,isChecked);
            }
        });

    }


    /***
     * 退出程序
     */

    public void exit() {
        MyApplication.getInstance().exit();
    }

    public  void  login(Context context){
        if(UserInfo.getInstance().isLogin()){
            LinearLayout l = sideView.findViewById(R.id.user_info_layout);
            l.setVisibility(View.VISIBLE);
            TextView name = l.findViewById(R.id.sideslip_user_name);
            TextView balance = l.findViewById(R.id.sideslip_balance);
            if(!TextUtils.isEmpty(UserInfo.getInstance().mUsername))
            name.setText(UserInfo.getInstance().mUsername.replace("\n","").replace(" ",""));
            getBalance(context,balance);
        }

    }
    public  void  loginout(){
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
    public void getBalance(Context context,TextView textView) {
        Object tag = HttpManager.get(context, Url.BASE_URL + Url.meminfo, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                BalanceBean balanceBean = JSON.parseObject(response.body(), BalanceBean.class);
                if (balanceBean.isSuccess()) {
                    DecimalFormat df = new DecimalFormat("#0.00");
                    textView.setText(df.format(balanceBean.getContent().getBalance())+"元");

                }
            }
        });
        addNet(tag);
    }
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
