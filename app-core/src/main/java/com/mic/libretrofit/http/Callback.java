package com.mic.libretrofit.http;

public interface Callback<T> {

    void onResponse(FCall<T> call, Response<T> response);


    void onFailure(FCall<T> call, Throwable t);
}