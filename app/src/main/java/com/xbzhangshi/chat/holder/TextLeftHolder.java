package com.xbzhangshi.chat.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.chat.common.SimpleCommonUtils;

public class TextLeftHolder extends  BaseLeftHolder {
    TextView content;
    public TextLeftHolder(@NonNull View itemView) {
        super(itemView);
        content = itemView.findViewById(R.id.content);
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
