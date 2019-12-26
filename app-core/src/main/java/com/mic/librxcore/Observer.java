package com.mic.librxcore;

/**
 * Created by hcDarren on 2017/12/2.
 * 观察者
 */
public interface Observer<T> {
    void onSubscribe();
    void onNext( T item);
    void onError(Throwable e);
    void onComplete();
}
