package com.xbzhangshi.mvp.record;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;

public class LotteryRecorDetailsActivity extends BaseActivity {

    public static  void start(Context context){
        Intent intent = new Intent(context,LotteryRecorDetailsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getlayout() {
        return R.layout.lottery_record_details_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
