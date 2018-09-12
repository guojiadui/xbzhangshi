package com.xbzhangshi.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.xbzhangshi.R;

/**
 * 首页的服务提示
 */
public class HomeTipDialog    extends Dialog {
    Context context;
    public HomeTipDialog(Context context ) {
        super(context, R.style.MyDialog);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_tip_dialog, null);
        setContentView(view);

    }
}
