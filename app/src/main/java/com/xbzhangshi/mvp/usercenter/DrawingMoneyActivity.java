package com.xbzhangshi.mvp.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.usercenter.BaseView.IDrawingMoneyBaseView;
import com.xbzhangshi.mvp.usercenter.presenter.DrawingMoneyPresenter;

public class DrawingMoneyActivity extends BaseActivity implements IDrawingMoneyBaseView {

    public static void start(Context context) {
        Intent intent = new Intent(context, DrawingMoneyActivity.class);
        context.startActivity(intent);
    }

    DrawingMoneyPresenter drawingMoneyPresenter;

    @Override
    protected int getlayout() {
        return R.layout.drawing_money_activity_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        drawingMoneyPresenter = DrawingMoneyPresenter.newInstance(this);
    }

    @Override
    protected void initdata() {
        super.initdata();
        drawingMoneyPresenter.getConfigInfo(this);
    }
}
