package com.xbzhangshi.chat.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xbzhangshi.R;

public class TextRightHolder extends  BaseRightHolder {
    TextView content;
    ProgressBar progress;
    ImageView fail;
    public TextRightHolder(@NonNull View itemView) {
        super(itemView);
        content = itemView.findViewById(R.id.content);
        fail = itemView.findViewById(R.id.fail);
        progress = itemView.findViewById(R.id.progress);
    }
}
