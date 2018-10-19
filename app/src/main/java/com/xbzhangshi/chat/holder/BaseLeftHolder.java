package com.xbzhangshi.chat.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbzhangshi.R;

public class BaseLeftHolder extends RecyclerView.ViewHolder  {

    TextView name;
    ImageView icon;
    TextView time;

    public BaseLeftHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        icon = itemView.findViewById(R.id.icon);
        time = itemView.findViewById(R.id.time);
    }

    public  void  setHolder(){}
}
