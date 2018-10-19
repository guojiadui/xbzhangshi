package com.xbzhangshi.chat.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.xbzhangshi.R;

public class ImgLeftHolder extends BaseLeftHolder {

    ImageView img;

    public ImgLeftHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);

    }

    @Override
    public void setHolder() {
        super.setHolder();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击图片查看
               // mContext.startActivity(BGAPhotoPreviewActivity.newIntent(mContext, null, list, curImg));
            }
        });
    }
}
