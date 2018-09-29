package com.xbzhangshi.mvp.record;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.record.adapter.AcountChangeAdapter;
import com.xbzhangshi.mvp.record.adapter.LotterytRecordAdapter;
import com.xbzhangshi.mvp.record.baseview.IAountChangeBaseView;
import com.xbzhangshi.mvp.record.baseview.ILotteryBaseView;
import com.xbzhangshi.mvp.record.bean.AcountChangeRecordBean;
import com.xbzhangshi.mvp.record.bean.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;
import com.xbzhangshi.mvp.record.details.LotteryRecorDetailsActivity;
import com.xbzhangshi.mvp.record.presenter.AcountChangeRecordPresenter;
import com.xbzhangshi.mvp.record.presenter.LotteryRecordPresenter;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.dialog.TipDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

/**
 * 彩票投注记录
 */
public class AcountChangeActivity extends BaseActivity implements IAountChangeBaseView, OnLoadMoreListener {


    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;

    @BindView(R.id.query_layout)
    LinearLayout queryLayout;
    @BindView(R.id.kong)
    FrameLayout kong;

    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.lottery_state)
    AppCompatSpinner lotteryState;
    @BindView(R.id.query)
    TextView query;

    public static void start(Context context) {
        Intent intent = new Intent(context, AcountChangeActivity.class);
        context.startActivity(intent);
    }


    //  PopupWindow selectPopupWindow;//查询选中

    AcountChangeRecordPresenter recordPresenter;


    @Override
    protected int getlayout() {
        return R.layout.note_acount_change_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        recordPresenter = AcountChangeRecordPresenter.newInstance(this);
        ltMainTitle.setText("账变记录");
        ltMainTitleRight.setText("筛选");
        ltMainTitleLeft.setText("返回");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        kong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryLayout.setVisibility(View.GONE);
            }
        });
        ltMainTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (queryLayout.getVisibility() == View.GONE) {
                    queryLayout.setVisibility(View.VISIBLE);
                } else {
                    queryLayout.setVisibility(View.GONE);
                }
            }
        });
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setOnLoadMoreListener(this);
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (recordPresenter != null) {
            recordPresenter.onDestory();
        }
    }

    @Override
    protected void initdata() {
        multipleStatusView.showLoading();
        recordPresenter.query(this, "", "");
    }


    @Override
    public void setInitSearch(List<String> keys) {
        initsearch(keys);
    }

    @Override
    public void successMore(List<AcountChangeRecordBean.ListBean> listBeans, boolean ismore) {
        smartRefreshLayout.finishLoadMore();
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            AcountChangeAdapter acountChangeAdapter = (AcountChangeAdapter) recyclerView.getAdapter();
            acountChangeAdapter.addData(listBeans);
        }
        if (!ismore) {
            smartRefreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void errorMore(String msg) {
        smartRefreshLayout.finishLoadMore();
    }

    @Override
    public void emptyMore(boolean ismore) {
        smartRefreshLayout.finishLoadMore();
        if (!ismore) {
            smartRefreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void successData(List<AcountChangeRecordBean.ListBean> listBeans, Map<String, String> keys, boolean ismore) {
        multipleStatusView.showContent();
        AcountChangeAdapter recordAdapter = new AcountChangeAdapter(listBeans, keys);
        recyclerView.setAdapter(recordAdapter);
        if (!ismore) {
            smartRefreshLayout.setNoMoreData(true);
        }
    }


    @Override
    public void empty() {
        multipleStatusView.showEmpty();
    }

    @Override
    public void error(String msg) {
        multipleStatusView.showError();
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        recordPresenter.query(AcountChangeActivity.this, startTime.getText().toString(), endTime.getText().toString());

    }

    /**
     * 显示日期选择
     */
    public void showDateDialog(TextView textView) {
        String data = textView.getText().toString();
        if (TextUtils.isEmpty(data)) {
            DatePickerDialog dp = new DatePickerDialog(AcountChangeActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }
            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
            dp.getDatePicker().setMaxDate((new Date()).getTime());
            dp.show();
        } else {
            String[] strs = data.split("-");
            DatePickerDialog dp = new DatePickerDialog(AcountChangeActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }
            }, Integer.parseInt(strs[0]), Integer.parseInt(strs[1]) - 1, Integer.parseInt(strs[2]));
            dp.getDatePicker().setMaxDate((new Date()).getTime());
            dp.show();
        }


    }

    public void query() {
        //点击查询
        if (recordPresenter != null) {
            multipleStatusView.showLoading();
            queryLayout.setVisibility(View.GONE);
            recordPresenter.curpage = 1;
            smartRefreshLayout.setNoMoreData(false);
            smartRefreshLayout.setEnableLoadMore(true);
            recordPresenter.query(AcountChangeActivity.this, startTime.getText().toString(), endTime.getText().toString());
        }
    }

    /***
     * 初始化搜索框
     * @param
     */

    public void initsearch(List<String> keys) {
        // PopupWindow浮动下拉框布局

        //查询
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
        //默认时间
        startTime.setText(Calendar.getInstance().get(Calendar.YEAR) + "-" +
                (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" +
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        endTime.setText(Calendar.getInstance().get(Calendar.YEAR) + "-" +
                (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" +
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog((TextView) v);
            }
        });
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog((TextView) v);
            }
        });

        keys.add(0, "全部");
        recordPresenter.curtype = keys.get(0);
        lotteryState.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_layout_item, keys));
        lotteryState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recordPresenter.curtype = keys.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
