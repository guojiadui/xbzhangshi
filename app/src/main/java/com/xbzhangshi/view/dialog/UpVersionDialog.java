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

public class UpVersionDialog extends Dialog {

    public UpVersionDialog(@NonNull Context context ) {
        super(context, R.style.MyDialog);
        setContentView(R.layout.up_version_dialog_layout);
        this.context = context;

    }

    String title;
    String contentTip;
    Context context;


   /* public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.up_version_dialog_layout, null);
        setContentView(view);
        TextView mTitle = view.findViewById(R.id.title);
        TextView mContentTip = view.findViewById(R.id.content_tip);
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        }
        if (!TextUtils.isEmpty(contentTip)) {
            mContentTip.setText(contentTip);
        }
    } */
}
