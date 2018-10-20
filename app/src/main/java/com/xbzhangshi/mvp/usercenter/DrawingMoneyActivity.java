package com.xbzhangshi.mvp.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.usercenter.BaseView.IDrawingMoneyBaseView;
import com.xbzhangshi.mvp.usercenter.bean.DrawMoneyInfoBean;
import com.xbzhangshi.mvp.usercenter.presenter.DrawingMoneyPresenter;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.dialog.ExchangeDialog;
import com.xbzhangshi.view.dialog.TipDialog;

import butterknife.BindView;

/**
 * 提款
 */
public class DrawingMoneyActivity extends BaseActivity implements IDrawingMoneyBaseView {

    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.text_tip1)
    TextView textTip1;
    @BindView(R.id.text_tip2)
    TextView textTip2;
    @BindView(R.id.text_tip3)
    TextView textTip3;
    @BindView(R.id.text_tip4)
    TextView textTip4;
    @BindView(R.id.consumption_tip1)
    TextView consumptionTip1;
    @BindView(R.id.consumption_tip2)
    TextView consumptionTip2;
    @BindView(R.id.icon1)
    ImageView icon1;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.blank)
    TextView blank;
    @BindView(R.id.icon2)
    ImageView icon2;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.icon3)
    ImageView icon3;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.icon4)
    ImageView icon4;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.amount_money)
    EditText amountMoney;
    @BindView(R.id.icon5)
    ImageView icon5;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.commit_layout)
    LinearLayout commitLayout;

    public static void start(Context context) {
        Intent intent = new Intent(context, DrawingMoneyActivity.class);
        context.startActivity(intent);
    }

    DrawingMoneyPresenter drawingMoneyPresenter;
    LoadingDailog loadingDialog;

    @Override
    protected int getlayout() {
        return R.layout.drawing_money_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        drawingMoneyPresenter = DrawingMoneyPresenter.newInstance(this);
        ltMainTitle.setText("提款");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initdata() {
        super.initdata();
        drawingMoneyPresenter.getConfigInfo(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (drawingMoneyPresenter != null) {
            drawingMoneyPresenter.onDestory();
        }
    }

    @Override
    public void setConfigInfo(DrawMoneyInfoBean bean) {
        String s1 = "1. 每天的提款处理时间为：<font color = \"red\">" + bean.getContent().getStartTime() + "</font> 至 <font color = \"red\">" + bean.getContent().getEndTime() + ";";
        textTip1.setText(Html.fromHtml(s1));
        textTip2.setText("2. 提款3分钟内到账。(如遇高峰期，可能需要延迟到十分钟内到帐);");
        String s2 = "3. 用户每日最小提款<font color = \"red\">" + bean.getContent().getMinPickMoney() + "</font> 元，最大提款 <font color = \"red\">" + bean.getContent().getMinPickMoney() + "元;";
        textTip3.setText(Html.fromHtml(s2));
        textTip4.setText("4. 今日可提款" + bean.getContent().getCurWnum() + "  次，已提款 " + bean.getContent().getWnum() + " 次 ;");


        String s4 = "1. 出款需达投注量：<font color = \"red\">" + bean.getContent().getCheckBetNum() + "</font> ,当前有效投注金额： <font color = \"red\">" + bean.getContent().getValidBetMoney();
        consumptionTip1.setText(Html.fromHtml(s4));
        String s5=null;
        if (bean.getContent().isEnablePick()) {
            s5 = "2. 是否能取款：<font color = \"red\">能</font>";
        } else {
            s5 = "2. 是否能取款：<font color = \"red\"不能</font>";
        }
        consumptionTip2.setText(Html.fromHtml(s5));

        String s6 = "(尾数" + bean.getContent().getCardNo() + ")";
        blank.setText(s6);
        name.setText(bean.getContent().getUserName());
        balance.setText(bean.getContent().getAccountBalance()+"");
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = amountMoney.getText().toString();
                if (TextUtils.isEmpty(num)) {
                    Toast.makeText(DrawingMoneyActivity.this, "请输入提款金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                long sum = Long.parseLong(num);
                if (sum < 100) {
                    Toast.makeText(DrawingMoneyActivity.this, "取款金额不能小于100元", Toast.LENGTH_LONG).show();
                    return;
                }
                String spwd = pwd.getText().toString();
                if (TextUtils.isEmpty(spwd)) {
                    Toast.makeText(DrawingMoneyActivity.this, "请输入提款密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (loadingDialog == null) {
                    LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(DrawingMoneyActivity.this)
                            .setMessage("加载中...")
                            .setCancelable(true)
                            .setCancelOutside(true);
                    loadingDialog = loadBuilder.create();
                }
                loadingDialog.show();
                drawingMoneyPresenter.commit(DrawingMoneyActivity.this, num, spwd);
            }
        });
        commitLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setConfigError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void drawSuccess() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        ExchangeDialog exchangeDialog = new ExchangeDialog(this, "提款成功");
        exchangeDialog.show();
        amountMoney.setText("");
    }

    @Override
    public void drawError(String s) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}
