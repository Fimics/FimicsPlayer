package com.mic.libokhttp.interceptor;



import com.mic.libokhttp.FRequest;
import com.mic.libokhttp.FResponse;

import java.io.IOException;



public interface Interceptor {
    FResponse intercept(Chain chain) throws IOException;
    interface Chain {
        FRequest request();

        FResponse proceed(FRequest request) throws IOException;
    }
}
