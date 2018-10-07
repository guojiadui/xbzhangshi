package com.xbzhangshi.mvp.webview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
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
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.usercenter.bean.ExchangeConfigBean;
import com.xbzhangshi.mvp.webview.bean.PrzeListBean;
import com.xbzhangshi.mvp.webview.bean.WheelPrizeLsitbean;
import com.xbzhangshi.single.UserInfo;
import com.xbzhangshi.view.dialog.SignInDialog;
import com.xbzhangshi.view.luckpan.RotateListener;
import com.xbzhangshi.view.luckpan.WheelSurfView;
import com.xbzhangshi.view.pwdview.SmoothScrollLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 签到
 */
public class WheelActivity extends BaseActivity {
    @BindView(R.id.wheelSurfView2)
    WheelSurfView wheelSurfView2;
    @BindView(R.id.smoothscroll)
    SmoothScrollLayout smoothscroll;
    @BindView(R.id.prize_sum)
    TextView prizeSum;
    @BindView(R.id.back_home)
    TextView backHome;
    @BindView(R.id.acount)
    TextView acount;
    @BindView(R.id.integral)
    TextView integral;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private List<Object> urlList;

    public void addNet(Object tag) {
        if (urlList == null) {
            urlList = new ArrayList<>();
        }
        if (!urlList.contains(tag))
            urlList.add(tag);
    }
  /*  @BindView(R.id.smoothscroll)
    SmoothScrollLayout smoothscroll;*/

    public static void start(Context context) {
        Intent intent = new Intent(context, WheelActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getlayout() {
        return R.layout.wheel_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        backHome.setFocusable(true);
        backHome.setFocusableInTouchMode(true);
        backHome.requestFocus();
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (UserInfo.getInstance() != null && UserInfo.getInstance().getLoginUserInfoBean() != null) {
            acount.setText(UserInfo.getInstance().getLoginUserInfoBean().getContent().getAccount());
        }
        initPrizeList();
        initLuckPan();
        getConfigure();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (urlList != null) {
            for (Object o : urlList) {
                OkGo.getInstance().cancelTag(o);
            }
        }
    }

    /**
     * 获取积分
     */
    public void getConfigure() {
        Object tag = HttpManager.post(this, Url.BASE_URL + Url.exchangeConfig, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ExchangeConfigBean configBean = JSON.parseObject(response.body(), ExchangeConfigBean.class);
                if (configBean.isSuccess()) {
                    DecimalFormat df = new DecimalFormat("#0.00");
                    integral.setText("积分" + df.format(configBean.getContent().getScore()));
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
        addNet(tag);
    }

    /**
     * 获奖名单
     */
    private void initPrizeList() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("activeId", 2);
        Object tag = HttpManager.post(this, Url.lastrd, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                List<PrzeListBean> przeListBeans = JSON.parseArray(response.body(), PrzeListBean.class);
                if (przeListBeans != null) {
                    String s = "最近<font color=\"red\">" + przeListBeans.size() + "</font>人中奖";
                    prizeSum.setText(Html.fromHtml(s));
                }
                for (PrzeListBean bean : przeListBeans) {
                    bean.setAccount(replaceString2Star(bean.getAccount(), 3, 0));
                }
                smoothscroll.setData(przeListBeans);
            }
        });
        addNet(tag);
    }

    String[] des = new String[]{"100", "10", "1000", "100", "100"};

    /**
     * 转盘初始化
     */
    public void initLuckPan() {
        //文字

        //图标
        List<Bitmap> mListBitmap = new ArrayList<>();
        List<Integer> mListcolors = new ArrayList<>();
        boolean flag = true;
        for (int i = 0; i < des.length; i++) {
            mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.lightning));
            if (flag) {
                flag = !flag;
                mListcolors.add(Color.parseColor("#FFF4D6"));
            } else {
                flag = !flag;
                mListcolors.add(Color.parseColor("#FFFFFF"));
            }
        }
        Integer[] integers = new Integer[mListcolors.size()];
        for (int i = 0; i < mListcolors.size(); i++) {
            integers[i] = mListcolors.get(i);
        }
        //主动旋转一下图片
        mListBitmap = WheelSurfView.rotateBitmaps(mListBitmap);
        WheelSurfView.Builder build = new WheelSurfView.Builder()
                .setmColors(integers)
                .setmDeses(des)
                .setmIcons(mListBitmap)
                .setmType(1)
                .setmTypeNum(des.length)
                .build();
        wheelSurfView2.setConfig(build);

        //添加滚动监听
        wheelSurfView2.setRotateListener(new RotateListener() {
            @Override
            public void rotateEnd(int position, String des1) {
                SignInDialog signInDialog = new SignInDialog(WheelActivity.this, "消息", des[position]);
                signInDialog.show();
            }

            @Override
            public void rotating(ValueAnimator valueAnimator) {

            }

            @Override
            public void rotateBefore(ImageView goImg) {
                getPrize();

            }
        });
    }

    //抽奖
    public void getPrize() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("activeId", 2);
        Object tag = HttpManager.post(this, Url.award, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    WheelPrizeLsitbean wheelPrizeLsitbean = JSON.parseObject(response.body(), WheelPrizeLsitbean.class);
                    if (TextUtils.isEmpty(wheelPrizeLsitbean.getMsg()) && wheelPrizeLsitbean.getIndex() > 0) {
                        /**
                         * 启动转盘
                         */
                        if (wheelPrizeLsitbean.getIndex() < des.length) {
                            wheelSurfView2.startRotate(wheelPrizeLsitbean.getIndex());
                        }
                        getConfigure();
                    } else {
                        if (!TextUtils.isEmpty(wheelPrizeLsitbean.getMsg())) {
                            SignInDialog signInDialog = new SignInDialog(WheelActivity.this, "消息", wheelPrizeLsitbean.getMsg());
                            signInDialog.show();
                        } else {
                            Toast.makeText(WheelActivity.this, "请求出错", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    SignInDialog signInDialog = new SignInDialog(WheelActivity.this, "消息", "抽奖失败");
                    signInDialog.show();
                }

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(WheelActivity.this, "请求出错", Toast.LENGTH_SHORT).show();
            }
        });
        addNet(tag);
    }


    /**
     * 字符转星号
     *
     * @param content
     * @param frontNum
     * @param endNum
     * @return
     */
    public static String replaceString2Star(String content, int frontNum, int endNum) {
        if (content == null || content.trim().isEmpty())
            return content;
        int len = content.length();
        if (frontNum >= len || frontNum < 0 || endNum >= len || endNum < 0)
            return content;
        if (frontNum + endNum >= len)
            return content;
        int beginIndex = frontNum;
        int endIndex = len - endNum;
        char[] cardChar = content.toCharArray();
        for (int j = beginIndex; j < endIndex; j++) {
            cardChar[j] = '*';
        }
        return new String(cardChar);
    }


}
