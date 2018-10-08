package com.xbzhangshi.http;

import com.lzy.okgo.model.Response;

public abstract class OkGoCallback<T> {

public  void onSuccess(T response){}
public  void parseError(){}
public  void onError(Response<String> response){}

  }
