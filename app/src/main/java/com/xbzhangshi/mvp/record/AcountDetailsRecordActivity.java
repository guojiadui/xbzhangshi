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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.record.adapter.AcountDetailsAdapter;
import com.xbzhangshi.mvp.record.adapter.LotterytRecordAdapter;
import com.xbzhangshi.mvp.record.baseview.IAcountDetailsBaseView;
import com.xbzhangshi.mvp.record.bean.AcountDetailsRecordBean;
import com.xbzhangshi.mvp.record.bean.LotteryRecordBean;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;
import com.xbzhangshi.mvp.record.details.LotteryRecorDetailsActivity;
import com.xbzhangshi.mvp.record.presenter.AcountDetailsRecordPresenter;
import com.xbzhangshi.view.dialog.TipDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 账户明细
 */
public class AcountDetailsRecordActivity extends BaseActivity implements IAcountDetailsBaseView, OnLoadMoreListener {


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

    @BindView(R.id.query)
    TextView query;
    @BindView(R.id.acount_type)
    AppCompatSpinner acountType;
    @BindView(R.id.transaction_type)
    AppCompatSpinner transactionType;
    @BindView(R.id.processor_state)
    AppCompatSpinner processorState;
    @BindView(R.id.transaction_layout)
    RelativeLayout transactionLayout;
    @BindView(R.id.transaction_line)
    View transactionLine;
    @BindView(R.id.total_money_tip)
    TextView moneyTip;
    @BindView(R.id.total_money)
    TextView totalMoney;



    public static void start(Context context) {
        Intent intent = new Intent(context, AcountDetailsRecordActivity.class);
        context.startActivity(intent);
    }


    //  PopupWindow selectPopupWindow;//查询选中

    AcountDetailsRecordPresenter recordPresenter;


    @Override
    protected int getlayout() {
        return R.layout.note_acount_details_record_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        recordPresenter = AcountDetailsRecordPresenter.newInstance(this);
        ltMainTitle.setText("账户明细");
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
        initsearch();
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
        recordPresenter.initData(this);
    }


    @Override
    public void successMore(List<AcountDetailsRecordBean.ListBean> listBeans, boolean ismore) {
        smartRefreshLayout.finishLoadMore();
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            AcountDetailsAdapter acountDetailsAdapter = (AcountDetailsAdapter) recyclerView.getAdapter();
            acountDetailsAdapter.addData(listBeans);
            acountDetailsAdapter.notifyDataSetChanged();
        }
        if (!ismore) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
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
            smartRefreshLayout.finishLoadMoreWithNoMoreData( );
        }
    }

    @Override
    public void successData(List<AcountDetailsRecordBean.ListBean> listBeans, int type, boolean ismore) {
        multipleStatusView.showContent();
        AcountDetailsAdapter acountDetailsAdapter = new AcountDetailsAdapter(listBeans, type);
        recyclerView.setAdapter(acountDetailsAdapter);
        if (!ismore) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData( );
        }
    }


    @Override
    public void empty() {
        multipleStatusView.showEmpty();
    }

    @Override
    public void setTotalMpney(int type, int sum) {
        if (type == 1) {
            moneyTip.setText("总充值");
             totalMoney.setText(sum+"元");
        } else if (type == 2) {
            moneyTip.setText("总取款");
             totalMoney.setText(sum+"元");
        }
    }

    @Override
    public void error(String msg) {
        multipleStatusView.showError();
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }




    /*@Override
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
    }*/

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        recordPresenter.query(AcountDetailsRecordActivity.this, startTime.getText().toString(), endTime.getText().toString());

    }

    /**
     * 显示日期选择
     */
    public void showDateDialog(TextView textView) {
        String data = textView.getText().toString();
        if (TextUtils.isEmpty(data)) {
            DatePickerDialog dp = new DatePickerDialog(AcountDetailsRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }
            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
            dp.getDatePicker().setMaxDate((new Date()).getTime());
            dp.show();
        } else {
            String[] strs = data.split("-");
            DatePickerDialog dp = new DatePickerDialog(AcountDetailsRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
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
            recordPresenter.query(AcountDetailsRecordActivity.this, startTime.getText().toString(), endTime.getText().toString());
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
        List<String> list1 = new ArrayList<>();
        list1.add("充值记录");
        list1.add("取款记录");
        recordPresenter.curacountType = list1.get(0);
        acountType.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_layout_item, list1));
        acountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recordPresenter.curacountType = list1.get(position);
                if (position == 1) {
                    transactionLayout.setVisibility(View.GONE);
                    transactionLine.setVisibility(View.GONE);


                } else {
                    transactionLayout.setVisibility(View.VISIBLE);
                    transactionLine.setVisibility(View.VISIBLE);
                    query.setVisibility(View.VISIBLE);
                    query.requestLayout();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> list2 = new ArrayList<>();
        list2.add("全部");
        list2.add("在线存款");
        list2.add("快速入款");
        list2.add("一般存款");
        recordPresenter.curtransactionType = list2.get(0);
        transactionType.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_layout_item, list2));
        transactionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recordPresenter.curtransactionType = list2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> list3 = new ArrayList<>();//  2处理成功  其他
        list3.add("全部");
        list3.add("处理中");
        list3.add("处理成功");
        list3.add("处理失败");
        recordPresenter.curprocessorState = list3.get(0);
        processorState.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_layout_item, list3));
        processorState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recordPresenter.curprocessorState = list3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
