package com.xbzhangshi.mvp.record.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.classic.common.MultipleStatusView;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.record.baseview.IThreeLotteryBaseView;
import com.xbzhangshi.mvp.record.bean.ThreeLotteryRecordBean;
import com.xbzhangshi.mvp.record.presenter.ElectronicsLotteryRecordPresenter;
import com.xbzhangshi.mvp.record.presenter.ThreeLotteryRecordPresenter;
import com.xbzhangshi.view.CustomToolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 电子投注记录
 */
public class ElectronicsLotteryRecordActivity extends BaseActivity implements IThreeLotteryBaseView {



    public static void start(Context context ) {
        Intent intent = new Intent(context, ElectronicsLotteryRecordActivity.class);

        context.startActivity(intent);
    }

    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
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
    @BindView(R.id.type)
    AppCompatSpinner type;
    @BindView(R.id.query)
    TextView query;


    ElectronicsLotteryRecordPresenter recordPresenter;


    @Override
    protected int getlayout() {
        return R.layout.note_three_record_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


        recordPresenter = ElectronicsLotteryRecordPresenter.newInstance(this );
        ltMainTitle.setText("电子投注记录");
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
    public void empty() {
        multipleStatusView.showEmpty();
    }

    @Override
    public void successData(List<ThreeLotteryRecordBean.RowsBean> listBeans) {
        multipleStatusView.showContent();
        ThreeRecordAdapter recordAdapter = new ThreeRecordAdapter(listBeans);
        recyclerView.setAdapter(recordAdapter);
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


    /**
     * 显示日期选择
     */
    public void showDateDialog(TextView textView) {
        String data = textView.getText().toString();
        if (TextUtils.isEmpty(data)) {
            DatePickerDialog dp = new DatePickerDialog(ElectronicsLotteryRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }
            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
            dp.getDatePicker().setMaxDate((new Date()).getTime());
            dp.show();
        } else {
            String[] strs = data.split("-");
            DatePickerDialog dp = new DatePickerDialog(ElectronicsLotteryRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
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
            recordPresenter.query(ElectronicsLotteryRecordActivity.this, startTime.getText().toString(), endTime.getText().toString());
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
        list1.add("所有电子");
        list1.add("mg电子游艺");
        list1.add("ag电子游艺");
        list1.add("bbin电子游艺");
        list1.add("QT电子游艺");
        list1.add("PT电子游艺");
        list1.add("CQ9电子游艺");
        list1.add("JDB电子游艺");
        list1.add("TTG电子游艺");
        list1.add("MW电子游艺");
        list1.add("ISB电子游艺");
        list1.add("BG电子游艺");


        recordPresenter.curPlatformType = list1.get(0);
        type.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_layout_item, list1));
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recordPresenter.curPlatformType = list1.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


}
