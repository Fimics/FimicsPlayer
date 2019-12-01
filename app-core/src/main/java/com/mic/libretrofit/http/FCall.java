package com.mic.libretrofit.http;

public interface FCall<T> {

    void enqueue(Callback<T> callback);
}
