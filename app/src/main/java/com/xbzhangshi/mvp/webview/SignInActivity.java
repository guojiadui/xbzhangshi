package com.xbzhangshi.mvp.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.home.HomeActivity;
import com.xbzhangshi.mvp.login.LoginActivity;
import com.xbzhangshi.mvp.webview.adpater.DateAdpater;
import com.xbzhangshi.mvp.webview.bean.SignDayBean;
import com.xbzhangshi.mvp.webview.bean.SignInBean;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.view.dialog.SignInDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 签到
 */
public class SignInActivity extends BaseActivity {

    @BindView(R.id.up)
    TextView up;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.continuity_day)
    TextView continuityDay;
    @BindView(R.id.back_home)
    TextView backHome;
    @BindView(R.id.sign)
    TextView sign;

    public static void start(Context context) {
        if(!UserInfo.userInfo.isLogin){
            LoginActivity.startLogin(context);
            return;
        }
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Calendar calendar = Calendar.getInstance();
    //今天所在的年月日信息
    private int currentYear, currentMonth, currentDay;
    private int currentPosition; //当前所看得到月份、从1970
    private boolean flag = true;

    @Override
    protected int getlayout() {
        return R.layout.sigin_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        recyclerView.setLayoutManager(new GridLayoutManager(this, 7));

        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentPosition = (currentYear - 1970) * 12 + currentMonth + 1; //1970到现在第一个月
        getMonthDays();

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastDate();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nexttDate();
            }
        });
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.start(SignInActivity.this);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(Url.signByMonth);
        OkGo.getInstance().cancelTag(Url.signIn);
    }

    //改月份
    final void lastDate() {
        currentPosition = currentPosition - 1;
        getMonthDays();

    }

    final void nexttDate() {
        currentPosition = currentPosition + 1;
        getMonthDays();
    }

    private void getMonthDays() {
        //获取某个月的时间
        int year = 1970 + currentPosition / 12;
        int month = currentPosition % 12;
        if (month == 0) {
            year = year - 1;
            month = 12;
        }
        calendar.set(year, month - 1, 1);
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK) - 1; //一号是这个月第一周的星期几

        Log.e("TAg", "firstDay=" + firstDay);
        int selectMonthMaxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//这与月的天数
        List<String> list = new ArrayList<>();
        for (int i = 0; i < firstDay; i++) {
            list.add("0");
        }
        for (int i = 1; i <= selectMonthMaxDay; i++) {
            list.add(i + "");
        }

        date.setText(year + "年" + month + "月");
        recyclerView.setAdapter(new DateAdpater(this, year, month, list));
        recyclerView.setTag(year + "-" + month);
        getsignByMonth(year, month, selectMonthMaxDay);
    }

    public void signIn() {

        HttpManager.getObject(this, SignInBean.class, Url.signIn, null, new OkGoCallback<SignInBean>() {
            @Override
            public void onSuccess(SignInBean response) {
                if (response.isSuccess()) {
                    SignInDialog signInDialog = new SignInDialog(SignInActivity.this, "签到成功",
                            "获得" + response.getScore() + "积分");
                    signInDialog.show();
                    sign.setText("已签到");
                    sign.setBackgroundResource(R.drawable.bg_rectangle_grayandwhite);
                    continuityDay.setText("连续签到" + response.getDays() + "天");
                    getMonthDays();
                } else {
                    Toast.makeText(SignInActivity.this, "签到失败", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void parseError() {
                super.parseError();
                Toast.makeText(SignInActivity.this, "请求出错", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(SignInActivity.this, "请求出错", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getsignByMonth(int year, int month, int day) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("signYear", year);
        httpParams.put("signMonth", month);
        httpParams.put("signDay", day);
        HttpManager.post(this, Url.signByMonth, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                List<SignDayBean> list = null;
                try {
                    list = JSON.parseArray(response.body(), SignDayBean.class);
                } catch (Exception e) {
                    Toast.makeText(SignInActivity.this, "请求出错", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (flag) {
                    flag = false;
                    sign.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String s = sign.getText().toString();
                            if ("已签到".equals(s)) {
                                return;
                            }
                            signIn();
                        }
                    });
                }
                if (list.size() <= 0) {
                    return;
                }
                List<String> strings = new ArrayList<>();
                for (SignDayBean dayBean : list) {
                    strings.add(dayBean.getSignDay());
                }
                String tag = year + "-" + month;
                String rtag = (String) recyclerView.getTag();
                //判断是否已经签到//在当前月判断
                if (year == currentYear && month == (currentMonth + 1)) {
                    if (strings.size() > 0) {
                        if (currentDay < 10) {
                            String day = "0" + currentDay;
                            if (strings.contains(day)) {
                                sign.setText("已签到");
                                sign.setBackgroundResource(R.drawable.bg_rectangle_grayandwhite);
                            }
                        } else {
                            String day = "" + currentDay;
                            if (strings.contains(day)) {
                                sign.setText("已签到");
                                sign.setBackgroundResource(R.drawable.bg_rectangle_grayandwhite);
                            }
                        }
                    }
                }
                if (tag.equals(rtag)) {
                    if (recyclerView.getAdapter() != null) {
                        DateAdpater dateAdpater = (DateAdpater) recyclerView.getAdapter();
                        dateAdpater.setSignDays(strings);
                        dateAdpater.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(SignInActivity.this, "请求出错", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
