package com.mic.bb.libretrofit.http;

public interface FCall<T> {

    void enqueue(Callback<T> callback);
}
