package com.mic.frame.rxretrofit;

import com.mic.frame.model.Result;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

public class RxResult {

    public static <T> ObservableTransformer<Result<T>, T> result() {
        return upstream -> {
            return upstream.flatMap(result -> {
                        if (result.isOk()) {
                            return createData(result.data);
                        } else{
                            return Observable.error(new Exception(result.getMsg()));
                        }
                    }

            );
        };
    }

    private static <T> Observable<T> createData(final T t) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(t);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

}
