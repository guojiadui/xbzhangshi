package com.xbzhangshi.http;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;



public class HttpManager {

    public static Object get(Context context, String url, HttpParams params, StringCallback back) {
        OkGo.<String>get(url).tag(url).params(params).execute(back);
        return url;
    }


}
