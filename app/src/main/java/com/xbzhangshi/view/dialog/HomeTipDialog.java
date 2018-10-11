package com.xbzhangshi.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.xbzhangshi.R;

/**
 * 首页的服务提示
 */
public class HomeTipDialog extends Dialog {
    Context context;
    String notice;

    public HomeTipDialog(Context context, String notice) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.notice = notice;
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
        WebView mNotice = view.findViewById(R.id.notice);
        // TextView notice2 = view.findViewById(R.id.notice2);
        TextView cancel = view.findViewById(R.id.cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mNotice.loadDataWithBaseURL(null, notice, "text/html", "utf-8", null);
        setContentView(view);

    }
}
