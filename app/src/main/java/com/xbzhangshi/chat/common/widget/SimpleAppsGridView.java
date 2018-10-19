package com.xbzhangshi.chat.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;


import com.xbzhangshi.R;
import com.xbzhangshi.chat.common.adapter.AppsAdapter;
import com.xbzhangshi.chat.common.data.AppBean;

import java.util.ArrayList;

public class SimpleAppsGridView extends RelativeLayout {

    protected View view;
    AppsAdapter.OnFuncItemClickListener onFuncItemClickListener;
    public SimpleAppsGridView(Context context,AppsAdapter.OnFuncItemClickListener onFuncItemClickListener) {
        this(context, null, onFuncItemClickListener);
    }

    public SimpleAppsGridView(Context context, AttributeSet attrs,AppsAdapter.OnFuncItemClickListener onFuncItemClickListener) {
        super(context, attrs);
        this.onFuncItemClickListener = onFuncItemClickListener;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.chat_view_apps, this);
        init();
    }

    protected void init(){
        GridView gv_apps = (GridView) view.findViewById(R.id.gv_apps);
        ArrayList<AppBean> mAppBeanList = new ArrayList<>();
        mAppBeanList.add(new AppBean(R.mipmap.icon_photo, "图片"));
        mAppBeanList.add(new AppBean(R.mipmap.icon_camera, "拍照"));
      /*  mAppBeanList.add(new AppBean(R.mipmap.icon_audio, "视频"));
        mAppBeanList.add(new AppBean(R.mipmap.icon_qzone, "空间"));
        mAppBeanList.add(new AppBean(R.mipmap.icon_contact, "联系人"));
        mAppBeanList.add(new AppBean(R.mipmap.icon_file, "文件"));
        mAppBeanList.add(new AppBean(R.mipmap.icon_loaction, "位置"));*/
        AppsAdapter adapter = new AppsAdapter(getContext(), mAppBeanList);
        if(onFuncItemClickListener!=null){
           adapter.setOnFuncItemClickListener(onFuncItemClickListener);
        }
        gv_apps.setAdapter(adapter);
    }
}
