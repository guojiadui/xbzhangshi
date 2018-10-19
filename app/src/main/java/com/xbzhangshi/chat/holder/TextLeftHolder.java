package com.xbzhangshi.chat.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.xbzhangshi.R;

public class TextLeftHolder extends  BaseLeftHolder {
    TextView content;
    public TextLeftHolder(@NonNull View itemView) {
        super(itemView);
        content = itemView.findViewById(R.id.content);
    }
}
