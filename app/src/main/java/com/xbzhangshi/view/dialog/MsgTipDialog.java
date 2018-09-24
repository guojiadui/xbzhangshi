package com.xbzhangshi.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xbzhangshi.R;

public class MsgTipDialog extends Dialog {
    Context context;
    View.OnClickListener clickListener;
    String content;
    public MsgTipDialog(Context context ,View.OnClickListener clickListener ) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.clickListener = clickListener;
    }
    public MsgTipDialog(Context context ,String content,View.OnClickListener clickListener ) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.clickListener = clickListener;
        this.content = content;
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
        if(!TextUtils.isEmpty(content)){
            mContent.setText(content);
        }
        TextView yes = view.findViewById(R.id.yes_action);
        TextView cancel = view.findViewById(R.id.cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        if(clickListener!=null){
            yes.setOnClickListener(clickListener);
        }
    }
}
