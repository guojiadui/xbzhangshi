package com.xbzhangshi.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.util.TimeUtils;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.HashSet;


public class HttpManager {

    // public static HashSet<String> loadingUrls = new HashSet<>();
    // public static HashSet<String> loadingparams = new HashSet<>();

    public static <T> Object getObject(Context context, Class<T> c, String url, HttpParams params, OkGoCallback<T> back) {
        Log.e("net", url);
        OkGo.<String>get(url).tag(url).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e("net", response.body());
                try {
                    T t = JSON.parseObject(response.body(), c);
                    if (back != null) {
                        back.onSuccess(t);
                    }
                } catch (Exception e) {
                    if (back != null) {
                        back.parseError();
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                if (response != null && !TextUtils.isEmpty(response.body()))
                    Log.e("net", response.body());
                super.onError(response);
                if (back != null) {
                    back.onError(response);
                }
            }
        });
        return url;
    }



    public static <T> Object postObject(Context context,Class<T> c, String url, HttpParams params, OkGoCallback<T> back) {
        Log.e("net", url);
        OkGo.<String>post(url).tag(url).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e("netuccess", response.body());
                try {
                    T t = JSON.parseObject(response.body(), c);
                    if (back != null) {
                        back.onSuccess(t);
                    }
                } catch (Exception e) {
                    if (back != null) {
                        back.parseError();
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                if (response != null && !TextUtils.isEmpty(response.body()))
                    Log.e("netonError", response.body());
                if (back != null) {
                    back.onError(response);
                }
            }
        });
        return url;
    }
    public static Object get(Context context, String url, HttpParams params, StringCallback back) {
        Log.e("net", url);
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
                if (response != null && !TextUtils.isEmpty(response.body()))
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
        Log.e("net", url);
        OkGo.<String>post(url).tag(url).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e("netuccess", response.body());
                if (back != null) {
                    back.onSuccess(response);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                if (response != null && !TextUtils.isEmpty(response.body()))
                    Log.e("netonError", response.body());
                if (back != null) {
                    back.onError(response);
                }
            }
        });
        return url;
    }

    public static Object getBitmap(Context context, String url, HttpParams params, BitmapCallback bitmapCallback) {
        Log.e("net", url);
        OkGo.<Bitmap>post(url).tag(url).params(params).execute(new BitmapCallback() {
            @Override
            public void onSuccess(Response<Bitmap> response) {
                if (bitmapCallback != null) {
                    bitmapCallback.onSuccess(response);
                }
            }
        });
        return url;
    }


}
