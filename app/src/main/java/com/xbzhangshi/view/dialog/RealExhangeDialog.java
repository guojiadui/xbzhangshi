package com.xbzhangshi.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xbzhangshi.R;

public class RealExhangeDialog extends Dialog {
    Context context;
    public RealExhangeDialog(@NonNull Context context, String title, String contentTip, ClickListener clickListener) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.title =title;
        this.contentTip = contentTip;
        this.clickListener = clickListener;
    }

    String title;
    String contentTip;
    ClickListener clickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.real_exhange_dialog_layout, null);
        setContentView(view);
        TextView mTitle = view.findViewById(R.id.title);
        TextView mContentTip = view.findViewById(R.id.content_tip);
        TextView cancel = view.findViewById(R.id.cancel_action);
        TextView yes = view.findViewById(R.id.yes_action);
        EditText c = view.findViewById(R.id.content);
        if(!TextUtils.isEmpty(title)){
         mTitle.setText(title);
        }
        if(!TextUtils.isEmpty(contentTip)){
            mContentTip.setText(contentTip);
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(clickListener!=null){
                    clickListener.onClickListener(c.getText().toString());
                }
            }
        });

    }


public  interface  ClickListener{
        public  void  onClickListener(String content);
}

}
