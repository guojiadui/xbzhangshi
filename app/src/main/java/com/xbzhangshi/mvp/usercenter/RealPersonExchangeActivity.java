package com.xbzhangshi.mvp.usercenter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.usercenter.adapter.RealPersonExchangeAdapter;
import com.xbzhangshi.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 真人额度转换
 */
public class RealPersonExchangeActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getlayout() {
        return R.layout.real_person_exchange_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        List<String> list = new ArrayList<>();
        list.add("ddddddddddddd");
        list.add("ddddddddddddd");
        list.add("ddddddddddddd");
        list.add("ddddddddddddd");
      recyclerView.setAdapter(new RealPersonExchangeAdapter(list));
    }

    @Override
    protected void initdata() {

    }


}
