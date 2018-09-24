package com.xbzhangshi.mvp.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.usercenter.BaseView.IMesssageListBaseView;
import com.xbzhangshi.mvp.usercenter.adapter.MsgAdapter;
import com.xbzhangshi.mvp.usercenter.bean.MsgBean;
import com.xbzhangshi.mvp.usercenter.presener.MessageListPresenter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 站内信息
 */
public class MessageListActivity extends BaseActivity implements IMesssageListBaseView {

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(listPresenter!=null){
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
                if(recyclerView!=null&&recyclerView.getAdapter()!=null){
                    MsgAdapter msgAdapter = (MsgAdapter) recyclerView.getAdapter();
                      listPresenter.setRead(this,msgAdapter.getData());
                }
                break;
            case R.id.select_all:
                if(recyclerView!=null&&recyclerView.getAdapter()!=null){
                    MsgAdapter msgAdapter = (MsgAdapter) recyclerView.getAdapter();
                    for (MsgBean.ContentBean.DatasBean datasBean : msgAdapter.getData()) {
                        datasBean.setIscheck(true);
                    }
                    msgAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.del:
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
    public void success(MessageListPresenter presenter, List<MsgBean.ContentBean.DatasBean> contentBeans) {
        multipleStatusView.showContent();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        MsgAdapter msgAdapter = new MsgAdapter(this, presenter, contentBeans);
        msgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MsgAdapter adapter1 = (MsgAdapter) adapter;
                MsgBean.ContentBean.DatasBean datasBean = adapter1.getData().get(position);
                datasBean.setIscheck(!datasBean.isIscheck());
                RadioButton radioButton = view.findViewById(R.id.radio);
                radioButton.setChecked(datasBean.ischeck);
            }
        });
        recyclerView.setAdapter(msgAdapter);
    }






    @Override
    public void empty() {
        multipleStatusView.showEmpty();
    }

    @Override
    public void Error(String msg) {
        multipleStatusView.showError();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ishowEditLayout(boolean isShow) {
        if (isShow) {
            editLayout.setVisibility(View.VISIBLE);
            ltMainTitleRight.setText("取消");
            if (recyclerView != null && recyclerView.getAdapter() != null) {
                MsgAdapter msgAdapter = (MsgAdapter) recyclerView.getAdapter();
                for (MsgBean.ContentBean.DatasBean datasBean : msgAdapter.getData()) {
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
        if(recyclerView!=null&&recyclerView.getAdapter()!=null){
            String[] strs = s.split(",");
            List<String> strings=null;
            if(strs.length>0){
                 strings = Arrays.asList(strs);
            }
            if(strings==null||strings.size()==0){
                return;
            }
            MsgAdapter msgAdapter = (MsgAdapter) recyclerView.getAdapter();
            List<MsgBean.ContentBean.DatasBean> datasBeans = msgAdapter.getData();
            for(MsgBean.ContentBean.DatasBean datasBean:datasBeans){
                if(strings.contains(datasBean.getId()+"")){
                    datasBean.setStatus(2);
                }
                datasBean.setIscheck(false);
            }
            msgAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void readError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
