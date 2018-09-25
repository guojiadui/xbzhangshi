package com.xbzhangshi.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xbzhangshi.R;

public class ExchangeDialog extends Dialog {
    Context context;
    public ExchangeDialog(Context context  ) {
        super(context, R.style.MyDialog);
        this.context = context;

    }
    String content;
    public ExchangeDialog(Context context ,String content ) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.content =content;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exchange_dialog_layout, null);
        setContentView(view);
        TextView mContent = view.findViewById(R.id.content);
        if(!TextUtils.isEmpty(content)){
            mContent.setText(content);
        }
        TextView yes = view.findViewById(R.id.yes_action);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
