package com.xbzhangshi.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xbzhangshi.R;

/**
 * 首页的服务提示
 */
public class SignInDialog extends Dialog {
    Context context;
    String title;
    String cotent;

    public SignInDialog(Context context, String title, String cotent) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.title = title;
        this.cotent = cotent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.signin_dialog, null);
        TextView cancel = view.findViewById(R.id.cancel_action);
        TextView content = view.findViewById(R.id.content);
        TextView notice = view.findViewById(R.id.notice);
        content.setText(cotent);
        notice.setText(title);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(view);

    }
}
