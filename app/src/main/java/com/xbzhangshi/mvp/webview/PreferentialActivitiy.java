package com.xbzhangshi.mvp.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.webview.adpater.ActAdapter;
import com.xbzhangshi.mvp.webview.bean.ActInfoBean;
import com.xbzhangshi.view.CustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 活动列表
 */
public class PreferentialActivitiy extends BaseActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, PreferentialActivitiy.class);
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

    @Override
    protected int getlayout() {
        return R.layout.preferntial_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ltMainTitle.setText("优惠活动");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getList();
    }


    public void getList() {
        HttpManager.get(this, Url.active_infos, null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    ActInfoBean actInfoBean = JSON.parseObject(response.body(), ActInfoBean.class);
                    if (actInfoBean.isSuccess()) {
                        ActAdapter actAdapter = new ActAdapter(PreferentialActivitiy.this, actInfoBean.getContent());
                        actAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                ActAdapter actAdapter1 = (ActAdapter) adapter;
                                ActDescActivity.start(PreferentialActivitiy.this, actAdapter1.getItem(position).getId()+"" );
                            }
                        });
                        recyclerView.setAdapter(actAdapter);
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
    }

}
