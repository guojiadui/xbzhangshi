package com.xbzhangshi.mvp.record.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.record.adapter.LotteryDetailsAdapter;
import com.xbzhangshi.mvp.record.bean.ResultLotteryRecordBean;
import com.xbzhangshi.view.CustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LotteryRecorDetailsActivity extends BaseActivity {


    public static void start(Context context, ResultLotteryRecordBean.PageBean.ListBean listBean) {
        Intent intent = new Intent(context, LotteryRecorDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj", listBean);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.number)
    TextView number;
    ResultLotteryRecordBean.PageBean.ListBean listBean;


    @Override
    protected int getlayout() {
        return R.layout.lottery_record_details_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ltMainTitle.setText("订单详情");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listBean = (ResultLotteryRecordBean.PageBean.ListBean) getIntent().getSerializableExtra("obj");

        if (listBean != null) {
            if(!TextUtils.isEmpty(listBean.getOrderId())){
                String num = "订单号:  " + "<font color=\"red\">" + listBean.getOrderId() + "</font>";
                number.setText(Html.fromHtml(num));
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(new LotteryDetailsAdapter(this, listBean));
        }
    }


}
