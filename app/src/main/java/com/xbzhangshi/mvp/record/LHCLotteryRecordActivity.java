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
import com.xbzhangshi.mvp.record.adapter.LotterytRecordAdapter;
import com.xbzhangshi.mvp.record.baseview.ILHCLotteryBaseView;
import com.xbzhangshi.mvp.record.baseview.ILotteryBaseView;
import com.xbzhangshi.mvp.record.bean.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;
import com.xbzhangshi.mvp.record.details.LotteryRecorDetailsActivity;
import com.xbzhangshi.mvp.record.presenter.LHCLotteryRecordPresenter;
import com.xbzhangshi.mvp.record.presenter.LotteryRecordPresenter;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.dialog.TipDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 六合彩投注记录
 */
public class LHCLotteryRecordActivity extends BaseActivity implements ILHCLotteryBaseView, OnLoadMoreListener {



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
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.query_layout)
    LinearLayout queryLayout;
    @BindView(R.id.kong)
    FrameLayout kong;
    @BindView(R.id.betting)
    TextView betting;
    @BindView(R.id.prize)
    TextView prize;
    @BindView(R.id.profit)
    TextView profit;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;

    @BindView(R.id.lottery_state)
    AppCompatSpinner lotteryState;
    @BindView(R.id.query)
    TextView query;

    public static void start(Context context) {
        Intent intent = new Intent(context, LHCLotteryRecordActivity.class);
        context.startActivity(intent);
    }


    //  PopupWindow selectPopupWindow;//查询选中

    LHCLotteryRecordPresenter lotteryRecordPresenter;
    LotterytRecordAdapter recordAdapter;

    @Override
    protected int getlayout() {
        return R.layout.note_lhcrecord_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        lotteryRecordPresenter = LHCLotteryRecordPresenter.newInstance(this);
        ltMainTitle.setText("六合彩投注记录");
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
        recordAdapter = new LotterytRecordAdapter(new ArrayList<>());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(recordAdapter);
        recordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LotterytRecordAdapter recordAdapter = (LotterytRecordAdapter) adapter;
                String id = recordAdapter.getData().get(position).getOrderId();
                if (view.getId() == R.id.cancel) {
                    cancelId(id);
                }
            }
        });
        recordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LotterytRecordAdapter recordAdapter1 = (LotterytRecordAdapter) adapter;
                LotteryRecorDetailsActivity.start(LHCLotteryRecordActivity.this,recordAdapter1.getData().get(position));
            }
        });
        initsearch();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lotteryRecordPresenter != null) {
            lotteryRecordPresenter.onDestory();
        }
    }

    @Override
    protected void initdata() {
        multipleStatusView.showLoading();
        lotteryRecordPresenter.initData(this);
    }

    private void cancelId(String id) {
        TipDialog tipDialog = new TipDialog(LHCLotteryRecordActivity.this, "您确定要撤单？", "", "", new TipDialog.ClickListener() {
            @Override
            public void but1(Dialog dialog, View v) {
                dialog.dismiss();
            }

            @Override
            public void but2(Dialog dialog, View v) {
                dialog.dismiss();
                lotteryRecordPresenter.cancelOrder(LHCLotteryRecordActivity.this, id);
            }
        });
        tipDialog.show();
    }




    @Override
    public void successMore(List<ResultLotteryRecordBean.PageBean.ListBean> listBeans, boolean ismore) {
        smartRefreshLayout.finishLoadMore();
        recordAdapter.addData(listBeans);
        recordAdapter.notifyDataSetChanged();
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
    public void successData(List<ResultLotteryRecordBean.PageBean.ListBean> listBeans, boolean ismore) {
        multipleStatusView.showContent();
        recordAdapter = new LotterytRecordAdapter(listBeans);
        recordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LotterytRecordAdapter recordAdapter = (LotterytRecordAdapter) adapter;
                String id = recordAdapter.getData().get(position).getOrderId();
                if (view.getId() == R.id.cancel) {
                    cancelId(id);
                }
            }
        });
        recordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LotterytRecordAdapter recordAdapter1 = (LotterytRecordAdapter) adapter;
                LotteryRecorDetailsActivity.start(LHCLotteryRecordActivity.this,recordAdapter1.getData().get(position));
            }
        });
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
    public void setprofit(double sbetting, double sprize, double sprofit) {
        betting.setText(sbetting + "");
        prize.setText(sprize + "");
        profit.setText(sprofit + "");
    }

    @Override
    public void cancalSuccess(String id) {
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            LotterytRecordAdapter recordAdapter = (LotterytRecordAdapter) recyclerView.getAdapter();
            if (recordAdapter.getData() != null && recordAdapter.getData().size() > 0) {
                for (int i = 0; i < recordAdapter.getData().size(); i++) {
                    if (id.equals(recordAdapter.getData().get(i).getOrderId())) {
                        recordAdapter.getData().get(i).setStatus(4);
                        recordAdapter.notifyItemChanged(i);
                    }
                }
            }
        }
    }

    @Override
    public void cancalError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        lotteryRecordPresenter.query(LHCLotteryRecordActivity.this, startTime.getText().toString(), endTime.getText().toString());

    }

    /**
     * 显示日期选择
     */
    public void showDateDialog(TextView textView) {
        String data = textView.getText().toString();
        if (TextUtils.isEmpty(data)) {
            DatePickerDialog dp = new DatePickerDialog(LHCLotteryRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }
            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
            dp.getDatePicker().setMaxDate((new Date()).getTime());
            dp.show();
        } else {
            String[] strs = data.split("-");
            DatePickerDialog dp = new DatePickerDialog(LHCLotteryRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        if (lotteryRecordPresenter != null) {
            multipleStatusView.showLoading();
            if (recordAdapter != null) {
                recordAdapter.getData().clear();
                recordAdapter.notifyDataSetChanged();
            }
            queryLayout.setVisibility(View.GONE);
            lotteryRecordPresenter.curpage = 1;
            smartRefreshLayout.setNoMoreData(false);
            smartRefreshLayout.setEnableLoadMore(true);
            lotteryRecordPresenter.query(LHCLotteryRecordActivity.this, startTime.getText().toString(), endTime.getText().toString());
        }
    }

    /***
     * 初始化搜索框
     * @param
     */

    public void initsearch() {
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

        List<String> list2 = new ArrayList<>();
        list2.add("全部");
        list2.add("未开奖");
        list2.add("已中奖");
        list2.add("未中奖");
        list2.add("撤单");
        lotteryState.setAdapter(new ArrayAdapter<String>(this,R.layout.spinner_layout_item, list2));
        lotteryState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    lotteryRecordPresenter.curlotterystate = "";
                } else {
                    lotteryRecordPresenter.curlotterystate = list2.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
