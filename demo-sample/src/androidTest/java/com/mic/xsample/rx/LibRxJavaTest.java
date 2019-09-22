package com.mic.xsample.rx;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.util.Log;

import com.mic.librxcore.Consumer;
import com.mic.librxcore.Function;
import com.mic.librxcore.Observable;
import com.mic.librxcore.Observer;
import com.mic.librxcore.Schedulers;

import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(AndroidJUnit4.class)
public class LibRxJavaTest {

    private static final String TAG ="LibRxJavaTest";

    @Test
    public void testLibRxJust(){

        Observable.just("")
        .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                Log.d(TAG,"onSubscribe");
            }

            @Override
            public void onNext(String item) {
                Log.d(TAG,"onNext");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete");
            }
        });

    }

    @Test
    public void testConsumer(){
        Observable.just("xxx")
        .map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.concat("ddd");
            }
        })
        .subscribeOn(Schedulers.io())
        .subscribe(new Consumer<String>() {
            @Override
            public void onNext(String s) {
               Log.d(TAG,"onNext");
            }
        });
    }
}
