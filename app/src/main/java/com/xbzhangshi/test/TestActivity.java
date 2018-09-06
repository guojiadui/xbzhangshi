package com.xbzhangshi.test;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends BaseActivity {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected int getlayout() {
        return R.layout.test_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1") );
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1") );
        tabLayout.addTab(tabLayout.newTab().setText("Tab 5"));



    }

    @Override
    protected void initdata() {

    }


}
