package com.xbzhangshi.mvp.record;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.record.adapter.SBSportsRecordAdapter;
import com.xbzhangshi.mvp.record.adapter.SportsRecordAdapter;
import com.xbzhangshi.mvp.record.baseview.ISportsBaseView;
import com.xbzhangshi.mvp.record.bean.SBSportsRecordBean;
import com.xbzhangshi.mvp.record.bean.HGSportsRecordBean;
import com.xbzhangshi.mvp.record.details.SBSportsRecordDetailsActivity;
import com.xbzhangshi.mvp.record.details.SportsRecordDetailsActivity;
import com.xbzhangshi.mvp.record.presenter.SportsRecordPresenter;
import com.xbzhangshi.view.CustomToolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 彩票投注记录
 */
public class SportsRecordActivity extends BaseActivity implements ISportsBaseView {

    @BindView(R.id.type_layout_line)
    View typeLayoutLine;
    @BindView(R.id.type_layout)
    RelativeLayout typeLayout;
    @BindView(R.id.state_layout_line)
    View stateLayoutLine;
    @BindView(R.id.state_layout)
    RelativeLayout stateLayout;

    public static void start(Context context) {
        Intent intent = new Intent(context, SportsRecordActivity.class);
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
    @BindView(R.id.platform)
    AppCompatSpinner platform;
    @BindView(R.id.lottery_state)
    AppCompatSpinner lotteryState;
    @BindView(R.id.query)
    TextView query;


    //  PopupWindow selectPopupWindow;//查询选中

    SportsRecordPresenter lotteryRecordPresenter;
    //SportsRecordAdapter recordAdapter;

    @Override
    protected int getlayout() {
        return R.layout.note_sports_record_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        lotteryRecordPresenter = SportsRecordPresenter.newInstance(this);
        ltMainTitle.setText("体育投注记录");
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
        if (lotteryRecordPresenter != null) {
            lotteryRecordPresenter.onDestory();
        }
    }

    @Override
    protected void initdata() {
        multipleStatusView.showLoading();
        lotteryRecordPresenter.initData(this);
    }

    @Override
    public void HGsuccess(List<HGSportsRecordBean.RowsBean> listBeans) {
        multipleStatusView.showContent();
        SportsRecordAdapter recordAdapter = new SportsRecordAdapter(listBeans);
        recordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SportsRecordDetailsActivity.start(SportsRecordActivity.this,((SportsRecordAdapter) adapter).getItem(position));
            }
        });
        recyclerView.setAdapter(recordAdapter);
    }

    @Override
    public void BSsuccess(List<SBSportsRecordBean.RowsBean> listBeans) {
        multipleStatusView.showContent();
        SBSportsRecordAdapter recordAdapter = new SBSportsRecordAdapter(listBeans);
        recordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SBSportsRecordDetailsActivity.start(SportsRecordActivity.this,((SBSportsRecordAdapter) adapter).getItem(position));
            }
        });
        recyclerView.setAdapter(recordAdapter);
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


    /**
     * 显示日期选择
     */
    public void showDateDialog(TextView textView) {
        String data = textView.getText().toString();
        if (TextUtils.isEmpty(data)) {
            DatePickerDialog dp = new DatePickerDialog(SportsRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }
            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
            dp.getDatePicker().setMaxDate((new Date()).getTime());
            dp.show();
        } else {
            String[] strs = data.split("-");
            DatePickerDialog dp = new DatePickerDialog(SportsRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
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
            queryLayout.setVisibility(View.GONE);
            lotteryRecordPresenter.curpage = 1;
            lotteryRecordPresenter.query(SportsRecordActivity.this, startTime.getText().toString(), endTime.getText().toString());
        }
    }

    /***
     * 初始化搜索框
     * @param
     */

    public void initsearch() {
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
        list1.add("皇冠体育");
        list1.add("沙巴体育");
        lotteryRecordPresenter.curplatform = list1.get(0);
        platform.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_layout_item, list1));
        platform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lotteryRecordPresenter.curplatform = list1.get(position);
                if (position == 0) {
                    typeLayout.setVisibility(View.VISIBLE);
                    typeLayoutLine.setVisibility(View.VISIBLE);
                    stateLayout.setVisibility(View.VISIBLE);
                    stateLayoutLine.setVisibility(View.VISIBLE);
                } else {
                    typeLayout.setVisibility(View.GONE);
                    typeLayoutLine.setVisibility(View.GONE);
                    stateLayout.setVisibility(View.GONE);
                    stateLayoutLine.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<String> list2 = new ArrayList<>();
        list2.add("所有球种");
        list2.add("足球");
        list2.add("篮球");
        lotteryRecordPresenter.curtype = list2.get(0);
        type.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_layout_item, list2));
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lotteryRecordPresenter.curtype = list2.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> list3 = new ArrayList<>();
        list3.add("全部");
        list3.add("已中奖");
        list3.add("未开奖");
        list3.add("未成功");
        lotteryRecordPresenter.curstate = list3.get(0);
        lotteryState.setAdapter(new ArrayAdapter<String>(this,R.layout.spinner_layout_item, list3));
        lotteryState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lotteryRecordPresenter.curstate = list3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
