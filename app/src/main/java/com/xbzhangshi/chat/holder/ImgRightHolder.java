package com.xbzhangshi.chat.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.xbzhangshi.R;

public class ImgRightHolder extends  BaseRightHolder {

    ImageView img;
    ProgressBar progress;
    ImageView fail;

    public ImgRightHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
        fail = itemView.findViewById(R.id.fail);
        progress = itemView.findViewById(R.id.progress);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击图片查看
                // mContext.startActivity(BGAPhotoPreviewActivity.newIntent(mContext, null, list, curImg));
            }
        });
    }
}
