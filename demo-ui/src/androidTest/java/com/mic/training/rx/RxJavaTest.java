package com.mic.training.rx;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@RunWith(AndroidJUnit4.class)
public class RxJavaTest {

    private static final String TAG = "RxJavaTest";

    @Test
    public void testJust(){

        /**
         * 正常的观察者模式
         * observable 被观察者
         * register   observer.register(observable)  所有的被观察者都注册到被观察者里，当被观察者发生改变，去通知观察者
         * observer   观察者
         */


        /**
         * 被观察者  订阅 观察者
         */
        Observable.just("url")
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError");
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG,"onNext");
                    }
                });
    }
}
