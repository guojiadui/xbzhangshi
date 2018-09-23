package com.xbzhangshi.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;


public class HttpManager {

    public static Object get(Context context, String url, HttpParams params, StringCallback back) {
        Log.e("net",url);
        OkGo.<String>get(url).tag(url).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e("net", response.body());
                if (back != null) {
                    back.onSuccess(response);
                }
            }

            @Override
            public void onError(Response<String> response) {
                Log.e("net", response.body());
                super.onError(response);
                if (back != null) {
                    back.onError(response);
                }
            }
        });
        return url;
    }

    public static Object post(Context context, String url, HttpParams params, StringCallback back) {
        Log.e("net",url);
        OkGo.<String>post(url).tag(url).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e("net", response.body());
                if (back != null) {
                    back.onSuccess(response);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.e("net", response.body());
                if (back != null) {
                    back.onError(response);
                }
            }
        });
        return url;
    }
    public static Object getBitmap(Context context, String url, HttpParams params,   BitmapCallback bitmapCallback) {
        Log.e("net",url);
        OkGo.<Bitmap>post(url).tag(url).params(params).execute(new BitmapCallback() {
            @Override
            public void onSuccess(Response<Bitmap> response) {
                if(bitmapCallback!=null){
                    bitmapCallback.onSuccess(response);
                }
            }
        });
        return url;
    }



}
