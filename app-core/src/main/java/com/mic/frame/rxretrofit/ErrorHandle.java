package com.mic.frame.rxretrofit;

/**
 * Created by hcDarren on 2017/12/23.
 */

public class ErrorHandle {

    public static class ServiceError extends Throwable{
        String errorCode;
        public ServiceError(String errorCode, String errorMsg) {
            super(errorMsg);
            this.errorCode = errorCode;
        }
    }
}
