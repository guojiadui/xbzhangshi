package com.xbzhangshi.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.Utils;
import com.xbzhangshi.R;

/**
 * 权限提示dialog
 */
public class PermissionsSetDialog extends Dialog {
    private Context context;
    private String title;
    private String content;


    public PermissionsSetDialog(Context context, String title, String content) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.title = title;
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
        View view = inflater.inflate(R.layout.permission_set_dialog_layout, null);
        TextView mTitle = view.findViewById(R.id.title);
        TextView mContent = view.findViewById(R.id.content);
        TextView cancel = view.findViewById(R.id.cancel_action);
        TextView yes = view.findViewById(R.id.yes_action);
        if(!TextUtils.isEmpty(title)){
            mTitle.setText(title);
        }
        if(!TextUtils.isEmpty(content)){
            mContent.setText(content);
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
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
                Utils.getApp().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        setContentView(view);


    }


}
