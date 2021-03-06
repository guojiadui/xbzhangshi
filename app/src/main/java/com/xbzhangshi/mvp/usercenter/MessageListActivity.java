package com.xbzhangshi.mvp.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
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
import com.xbzhangshi.mvp.usercenter.BaseView.IMesssageListBaseView;
import com.xbzhangshi.mvp.usercenter.adapter.MsgAdapter;
import com.xbzhangshi.mvp.usercenter.bean.MsgBean;
import com.xbzhangshi.mvp.usercenter.presenter.MessageListPresenter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 站内信息
 */
public class MessageListActivity extends BaseActivity implements IMesssageListBaseView, OnLoadMoreListener {

    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.readed)
    TextView readed;
    @BindView(R.id.select_all)
    TextView selectAll;
    @BindView(R.id.del)
    TextView del;
    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.edit_layout)
    RelativeLayout editLayout;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    public static void start(Context context) {
        Intent intent = new Intent(context, MessageListActivity.class);
        context.startActivity(intent);
    }

    MessageListPresenter listPresenter;

    @Override
    protected int getlayout() {
        return R.layout.message_list_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        listPresenter = MessageListPresenter.newInstance(this);
        ltMainTitle.setText("站内信");
        ltMainTitleRight.setText("编辑");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        smartRefreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
        smartRefreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        smartRefreshLayout.setOnLoadMoreListener(this);
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multipleStatusView.showLoading();
                listPresenter.loadData(MessageListActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listPresenter != null) {
            listPresenter.onDestory();
        }
    }

    @OnClick({R.id.lt_main_title_right, R.id.readed, R.id.select_all, R.id.del})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.lt_main_title_right:
                if (listPresenter != null) {
                    listPresenter.setEdit();
                }
                break;
            case R.id.readed:
                if (recyclerView != null && recyclerView.getAdapter() != null) {
                    MsgAdapter msgAdapter = (MsgAdapter) recyclerView.getAdapter();
                    listPresenter.setRead(this, msgAdapter.getData());
                }
                break;
            case R.id.select_all:
                if (recyclerView != null && recyclerView.getAdapter() != null) {
                    MsgAdapter msgAdapter = (MsgAdapter) recyclerView.getAdapter();
                    for (MsgBean.ListBean datasBean : msgAdapter.getData()) {
                        datasBean.setIscheck(true);
                    }
                    msgAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.del:
                if (recyclerView != null && recyclerView.getAdapter() != null) {
                    MsgAdapter msgAdapter = (MsgAdapter) recyclerView.getAdapter();
                    listPresenter.setdel(this, msgAdapter.getData());
                }
                break;
        }
    }

    @Override
    protected void initdata() {
        super.initdata();
        multipleStatusView.showLoading();
        listPresenter.loadData(this);
    }

    @Override
    public void success(MessageListPresenter presenter, List<MsgBean.ListBean> contentBeans,boolean isMore) {
        multipleStatusView.showContent();
        if(!isMore){
            smartRefreshLayout.finishLoadMoreWithNoMoreData( );
        }
        MsgAdapter msgAdapter = new MsgAdapter(this, presenter, contentBeans);
        msgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (listPresenter == null) {
                    return;
                }
                if (listPresenter.isEdit) {
                    //编辑的选中
                    MsgAdapter adapter1 = (MsgAdapter) adapter;
                    MsgBean.ListBean datasBean = adapter1.getData().get(position);
                    datasBean.setIscheck(!datasBean.isIscheck());
                    RadioButton radioButton = view.findViewById(R.id.radio);
                    radioButton.setChecked(datasBean.ischeck);
                } else {
                    //查看
                    MsgAdapter adapter1 = (MsgAdapter) adapter;
                    MsgBean.ListBean datasBean = adapter1.getData().get(position);
                    MsgActivity.start(MessageListActivity.this, datasBean.getTitle(), datasBean.getMessage());
                    //把信息设置为已读状态
                    listPresenter.readitem(MessageListActivity.this,datasBean);
                }

            }
        });
        recyclerView.setAdapter(msgAdapter);
    }


    @Override
    public void empty(boolean isMore) {
        multipleStatusView.showEmpty();
        if(!isMore){
            smartRefreshLayout.finishLoadMoreWithNoMoreData( );
        }
    }

    @Override
    public void Error(String msg) {
        multipleStatusView.showError();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successMore(MessageListPresenter presenter, List<MsgBean.ListBean> contentBeans,boolean isMore) {
        if(!isMore){
            smartRefreshLayout.finishLoadMoreWithNoMoreData( );
        }
        if(recyclerView!=null&&recyclerView.getAdapter()!=null){
            MsgAdapter adapter = (MsgAdapter) recyclerView.getAdapter();
            adapter.addData(contentBeans);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void emptyMore(boolean isMore) {
        smartRefreshLayout.finishLoadMore();
        if(!isMore){
            smartRefreshLayout.finishLoadMoreWithNoMoreData( );
        }
    }

    @Override
    public void ErrorMore(String msg) {
        smartRefreshLayout.finishLoadMore();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ishowEditLayout(boolean isShow) {
        if (isShow) {
            editLayout.setVisibility(View.VISIBLE);
            ltMainTitleRight.setText("取消");
            if (recyclerView != null && recyclerView.getAdapter() != null) {
                MsgAdapter msgAdapter = (MsgAdapter) recyclerView.getAdapter();
                for (MsgBean.ListBean datasBean : msgAdapter.getData()) {
                    datasBean.setIscheck(false);
                }
                msgAdapter.notifyDataSetChanged();
            }
        } else {
            editLayout.setVisibility(View.GONE);
            ltMainTitleRight.setText("编辑");
            if (recyclerView != null && recyclerView.getAdapter() != null) {
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }

    @Override
    public void readSuccess(String s) {
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            String[] strs = s.split(",");
            List<String> strings = null;
            if (strs.length > 0) {
                strings = Arrays.asList(strs);
            }
            if (strings == null || strings.size() == 0) {
                return;
            }
            MsgAdapter msgAdapter = (MsgAdapter) recyclerView.getAdapter();
            List<MsgBean.ListBean> datasBeans = msgAdapter.getData();
            for (MsgBean.ListBean datasBean : datasBeans) {
                if (strings.contains(datasBean.getId() + "")) {
                    datasBean.setStatus(2);
                }
                datasBean.setIscheck(false);
            }
            msgAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void readError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void delSuccess(String s) {
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            String[] strs = s.split(",");
            List<String> strings = null;
            if (strs.length > 0) {
                strings = Arrays.asList(strs);
            }
            if (strings == null || strings.size() == 0) {
                return;
            }
            MsgAdapter msgAdapter = (MsgAdapter) recyclerView.getAdapter();
            List<MsgBean.ListBean> datasBeans = msgAdapter.getData();
            Iterator<MsgBean.ListBean> it = datasBeans.iterator();
            while (it.hasNext()) {
                MsgBean.ListBean x = it.next();
                if (strings.contains(x.getId() + "")) {
                    it.remove();
                    continue;
                }
                x.setIscheck(false);
            }
            msgAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void delError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }
}
