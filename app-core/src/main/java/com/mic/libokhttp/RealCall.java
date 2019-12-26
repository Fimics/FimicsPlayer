package com.mic.libokhttp;

import android.util.Log;

import com.mic.libokhttp.interceptor.BridgeInterceptor;
import com.mic.libokhttp.interceptor.CacheInterceptor;
import com.mic.libokhttp.interceptor.CallServerInterceptor;
import com.mic.libokhttp.interceptor.Interceptor;
import com.mic.libokhttp.interceptor.RealInterceptorChain;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RealCall implements  FCall{

    private static final String TAG ="RealCall";

    private final FRequest orignalRequest;
    private final FOkhttpClient client;

    public RealCall(FRequest request, FOkhttpClient client) {
        this.orignalRequest=request;
        this.client=client;
    }

    public static FCall newCall(FRequest request, FOkhttpClient client) {
        return new RealCall(request,client);
    }

    @Override
    public void enqueue(FCallback callback) {

        AsynCall asynCall = new AsynCall(callback);
        //添加到线程池中
        client.dispatcher.enqueue(asynCall);
    }

    @Override
    public FResponse execute() {
        AsynCall asynCall = new AsynCall();
        return asynCall.exe();
    }

    final class AsynCall extends  NamedRunnable{

        FCallback callback;
        FResponse response;

        public AsynCall() {
        }

        public AsynCall(FCallback callback) {
            this.callback = callback;
        }

        private FResponse exe(){
            execute();
            return response;
        }

        @Override
        public void execute() {
            // 来这里，开始访问网络 Request -> Response
            Log.e("TAG","execute");
            // Volley xUtils Afinal AsyHttpClient
            // 基于 HttpUrlConnection , OkHttp = Socket + okio(IO)
            final FRequest request = orignalRequest;
            try {
                List<Interceptor> interceptors = new ArrayList<>();
                interceptors.add(new BridgeInterceptor());
                interceptors.add(new CacheInterceptor());
                interceptors.add(new CallServerInterceptor());
                Interceptor.Chain chain = new RealInterceptorChain(interceptors,0,orignalRequest);
                FResponse response = chain.proceed(request);
                callback.onResponse(RealCall.this,response);
            } catch (IOException e) {
                callback.onFailure(RealCall.this,e);
            }

        }
    }

}
