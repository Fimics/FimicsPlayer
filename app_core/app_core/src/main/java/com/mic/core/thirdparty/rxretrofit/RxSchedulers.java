package com.mic.core.thirdparty.rxretrofit;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@SuppressWarnings("unused")
public class RxSchedulers {

        public static <T> ObservableTransformer<T, T> io_main() {
            return upstream ->
                    upstream.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
        }
}
