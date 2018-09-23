package com.xbzhangshi.mvp.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 绑定银行卡
 */
public class BindingBankCardActivity extends BaseActivity {

    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;

    public static void start(Context context) {
        Intent intent = new Intent(context, BindingBankCardActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getlayout() {
        return R.layout.binding_bank_car_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
          ltMainTitle.setText("个人中心");
          ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  finish();
              }
          });
    }

    @Override
    protected void initdata() {

    }


}
