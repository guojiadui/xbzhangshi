package com.xbzhangshi.mvp.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MsgActivity extends BaseActivity {


    public  static  void  start(Context context,String title,String content){
        Intent intent = new Intent( context,MsgActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        context.startActivity(intent);
    }


    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.content)
    TextView mContent;

    @Override
    protected int getlayout() {
        return R.layout.msg_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String  title = getIntent().getStringExtra("title");
        String  content = getIntent().getStringExtra("content");
        ltMainTitle.setText("站内信详情");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(!TextUtils.isEmpty(title)){
            mTitle.setText(title);
        }
        if(!TextUtils.isEmpty(content)){
            mContent.setText(content);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
