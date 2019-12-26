package com.mic.frame.rxretrofit;



import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public  abstract class RxObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        // e ：网络异常，解析异常，结果处理过程中异常
        e.printStackTrace();
        if(e instanceof ErrorHandle.ServiceError){
            ErrorHandle.ServiceError serviceError = (ErrorHandle.ServiceError) e;
            onError("",serviceError.getMessage());
        }else {
            // 自己处理
            onError("","未知异常");
        }
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onError(String errorCode, String errorMessage);
}
