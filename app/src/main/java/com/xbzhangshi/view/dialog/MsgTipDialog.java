package com.xbzhangshi.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xbzhangshi.R;

public class MsgTipDialog extends Dialog {
    Context context;
    View.OnClickListener clickListener;
    public MsgTipDialog(Context context ,View.OnClickListener clickListener ) {
        super(context, R.style.MyDialog);
        this.context = context;
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
