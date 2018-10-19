package com.xbzhangshi.chat.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.chat.common.SimpleCommonUtils;

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

    @Override
    public void setHolder() {
        super.setHolder();
        try {
            SimpleCommonUtils.spannableEmoticonFilter(content, "");
        } catch (Exception e) {
            content.setText("");
        }
    }
}
