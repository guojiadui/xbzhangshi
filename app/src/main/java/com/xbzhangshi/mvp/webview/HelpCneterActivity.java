package com.xbzhangshi.mvp.webview;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.http.OkGoCallback;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.webview.adpater.HelpAdapter;
import com.xbzhangshi.mvp.webview.bean.HelpListBean;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.DividerGridItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 帮助中心
 */
public class HelpCneterActivity extends BaseActivity {
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
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;

    @Override
    protected int getlayout() {
        return R.layout.help_center_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ltMainTitle.setText("帮助中心");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(Url.helplist);
    }

    public void init() {
        multipleStatusView.showLoading();
        HttpManager.getObject(this, HelpListBean.class, Url.helplist, null, new OkGoCallback<HelpListBean>() {
            @Override
            public void onSuccess(HelpListBean response) {
                multipleStatusView.showContent();
                HelpAdapter adapter = new HelpAdapter(response.getHplist());
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        HelpAdapter adapter1 = (HelpAdapter) adapter;
                        HelpDetailActivity.start(HelpCneterActivity.this, adapter1.getItem(position).getContent());
                    }
                });
                recyclerView.addItemDecoration(new DividerGridItemDecoration(HelpCneterActivity.this));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void parseError() {
                super.parseError();
                multipleStatusView.showError();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                multipleStatusView.showError();
            }
        });

    }


}
