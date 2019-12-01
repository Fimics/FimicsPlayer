package com.mic.libokhttp.interceptor;



import com.mic.libokhttp.FRequest;
import com.mic.libokhttp.FResponse;

import java.io.IOException;
import java.util.List;


public class RealInterceptorChain implements Interceptor.Chain {
    final List<Interceptor> interceptors;
    final int index;
    final FRequest request;
    public RealInterceptorChain(List<Interceptor> interceptors, int index, FRequest request){
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }
    @Override
    public FRequest request() {
        return request;
    }

    @Override
    public FResponse proceed(FRequest request) throws IOException {
        RealInterceptorChain next = new RealInterceptorChain(interceptors,  index + 1, request);
        Interceptor interceptor = interceptors.get(index);
        FResponse response = interceptor.intercept(next);
        return response;
    }
}
