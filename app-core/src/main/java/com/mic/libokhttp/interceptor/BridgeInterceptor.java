package com.mic.libokhttp.interceptor;

import android.util.Log;


import com.mic.libokhttp.FRequest;
import com.mic.libokhttp.FRequestBody;
import com.mic.libokhttp.FResponse;

import java.io.IOException;


/**
 * 处理公共参数
 */
public class BridgeInterceptor implements Interceptor{

    @Override
    public FResponse intercept(Chain chain) throws IOException {
        Log.e("TAG","BridgeInterceptor");
        FRequest request = chain.request();
        // 添加一些请求头
        //request.header("Connection","keep-alive");
        // 做一些其他处理
        if(request.requestBody()!=null){
            FRequestBody requestBody = request.requestBody();
            request.header("Content-Type",requestBody.getContentType());
            request.header("Content-Length",Long.toString(requestBody.getContentLength()));
        }
        FResponse response = chain.proceed(request);

        return response;
    }
}
