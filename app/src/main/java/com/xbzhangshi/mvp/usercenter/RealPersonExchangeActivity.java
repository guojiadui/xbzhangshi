package com.xbzhangshi.mvp.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.usercenter.BaseView.IRealPersonExchangeBaseView;
import com.xbzhangshi.mvp.usercenter.adapter.RealPersonExchangeAdapter;
import com.xbzhangshi.mvp.usercenter.bean.ExchangeBean;
import com.xbzhangshi.mvp.usercenter.bean.RealExhangeBean;
import com.xbzhangshi.mvp.usercenter.presenter.RealPersonExcahngePresenter;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.DividerGridItemDecoration;
import com.xbzhangshi.view.dialog.BindingDialog;
import com.xbzhangshi.view.dialog.RealExhangeDialog;

import java.util.List;

import butterknife.BindView;

/**
 * 真人额度转换
 */
public class RealPersonExchangeActivity extends BaseActivity implements IRealPersonExchangeBaseView {

    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.balance)
    TextView mBalance;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static void start(Context context) {
        Intent intent = new Intent(context, RealPersonExchangeActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getlayout() {
        return R.layout.real_person_exchange_activity;
    }

    RealPersonExcahngePresenter excahngePresenter;
    LoadingDailog loadingdialog;

    @Override
    protected void initView(Bundle savedInstanceState) {

        excahngePresenter = RealPersonExcahngePresenter.newInstance(this);
        ltMainTitle.setText("真人额度转换");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multipleStatusView.showLoading();
                excahngePresenter.init(RealPersonExchangeActivity.this);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (excahngePresenter != null) {
            excahngePresenter.onDestory();
        }
    }

    @Override
    protected void initdata() {
        multipleStatusView.showLoading();
        excahngePresenter.init(this);
    }


    @Override
    public void updateBalance(String balance) {
        mBalance.setText(balance);
    }

    @Override
    public void success(List<RealExhangeBean> list) {
        multipleStatusView.showContent();
        RealPersonExchangeAdapter exchangeAdapter = new RealPersonExchangeAdapter(this, list);
        exchangeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RealExhangeBean realExhangeBean = (RealExhangeBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.input:
                        RealExhangeDialog dialog = new RealExhangeDialog(RealPersonExchangeActivity.this,
                                "从系统转到" + realExhangeBean.getKey(), "转入金额", new RealExhangeDialog.ClickListener() {
                            @Override
                            public void onClickListener(String content) {
                                if (TextUtils.isEmpty(content)) {
                                    Toast.makeText(RealPersonExchangeActivity.this, "金额不能为空", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(loadingdialog==null){
                                    LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(RealPersonExchangeActivity.this)
                                            .setMessage("加载中...")
                                            .setCancelable(true)
                                            .setCancelOutside(true);
                                    loadingdialog = loadBuilder.create();
                                }
                                loadingdialog.show();
                                excahngePresenter.transMoney(RealPersonExchangeActivity.this, "sys", realExhangeBean.key, content);
                            }
                        });
                        dialog.show();
                        break;
                    case R.id.out:
                        dialog = new RealExhangeDialog(RealPersonExchangeActivity.this,
                                "从" + realExhangeBean.getKey() + "转到系统", "转出金额", new RealExhangeDialog.ClickListener() {
                            @Override
                            public void onClickListener(String content) {
                                if (TextUtils.isEmpty(content)) {
                                    Toast.makeText(RealPersonExchangeActivity.this, "金额不能为空", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(loadingdialog==null){
                                    LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(RealPersonExchangeActivity.this)
                                            .setMessage("加载中...")
                                            .setCancelable(true)
                                            .setCancelOutside(true);
                                    loadingdialog = loadBuilder.create();
                                }
                                loadingdialog.show();
                                excahngePresenter.transMoney(RealPersonExchangeActivity.this, realExhangeBean.key, "sys", content);
                            }
                        });
                        dialog.show();
                        break;
                    case R.id.refresh:
                        if(loadingdialog==null){
                            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(RealPersonExchangeActivity.this)
                                    .setMessage("加载中...")
                                    .setCancelable(true)
                                    .setCancelOutside(true);
                            loadingdialog = loadBuilder.create();
                        }
                        loadingdialog.show();
                        excahngePresenter.getBalanceItem(RealPersonExchangeActivity.this, realExhangeBean.key);
                        break;
                    case R.id.enter_game:
                        break;
                }
            }
        });
        recyclerView.setAdapter(exchangeAdapter);
    }

    @Override
    public void empty() {
        multipleStatusView.showEmpty();
    }

    @Override
    public void error(String msg) {
        multipleStatusView.showError();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void transMoneySuccess() {
        if(loadingdialog!=null&&loadingdialog.isShowing()){
            loadingdialog.dismiss();
        }
        Toast.makeText(this,"转换成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void transMoneyError(String msg) {
        if(loadingdialog!=null&&loadingdialog.isShowing()){
            loadingdialog.dismiss();
        }
        if(!TextUtils.isEmpty(msg)){
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void updateBalanceItem(String type, String balance) {
        if(loadingdialog!=null&&loadingdialog.isShowing()){
            loadingdialog.dismiss();
        }
        if(recyclerView!=null&&recyclerView.getAdapter()!=null){
            RealPersonExchangeAdapter adapter = (RealPersonExchangeAdapter) recyclerView.getAdapter();
            for(int i =0;i<adapter.getData().size();i++){
                RealExhangeBean exchangeBean  =adapter.getItem(i);
                if(type.equals(exchangeBean.key)){
                    exchangeBean.setBalance(balance);
                    adapter.notifyItemChanged(i);
                    return;
                }
            }
        }
    }

    @Override
    public void updateBalanceError(String msg) {
        if(loadingdialog!=null&&loadingdialog.isShowing()){
            loadingdialog.dismiss();
        }
        if(!TextUtils.isEmpty(msg)){
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
    }
}
