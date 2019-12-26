package com.mic.librxcore;


public interface Consumer<T> {
    void onNext(T item) throws Exception;
}
