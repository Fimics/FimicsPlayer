package com.mic.libokhttp.interceptor;


import com.mic.libokhttp.FRequest;
import com.mic.libokhttp.FResponse;

import java.io.IOException;

/**
 * 处理缓存
 */
public class CacheInterceptor implements Interceptor{
    @Override
    public FResponse intercept(Chain chain) throws IOException {
        FRequest request = chain.request();
        // 本地有没有缓存，如果有没过期
        /*if(true){
            return new Response(new );
        }*/

        return chain.proceed(request);
    }
}
