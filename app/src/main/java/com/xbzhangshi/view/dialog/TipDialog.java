package com.xbzhangshi.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xbzhangshi.R;

public class TipDialog extends Dialog {
    Context context;

    public TipDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }

    String content;
    ClickListener clickListener;
    String btn1str;
    String btn2str;

    public TipDialog(Context context, String content, String btn1str, String btn2str, ClickListener clickListener) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.content = content;
        this.btn1str = btn1str;
        this.btn2str = btn2str;
        this.clickListener = clickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.msg_tip_dialog_layout, null);
        setContentView(view);
        TextView mContent = view.findViewById(R.id.content);
        TextView btn1 = view.findViewById(R.id.cancel_action);
        TextView btn2 = view.findViewById(R.id.yes_action);
        if (!TextUtils.isEmpty(content)) {
            mContent.setText(content);
        }
        if (!TextUtils.isEmpty(btn1str)) {
            btn1.setText(btn1str);
        }
        if (!TextUtils.isEmpty(btn2str)) {
            btn2.setText(btn2str);
        }
        if (clickListener != null) {
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.but1(TipDialog.this, v);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.but2(TipDialog.this, v);
                }
            });
        }
    }

    public interface ClickListener {

        public void but1(Dialog dialog, View v);

        public void but2(Dialog dialog, View v);
    }

}
