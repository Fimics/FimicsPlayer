package com.mic.libretrofit.http;

import android.util.Log;
import java.io.IOException;

public class OkHttpCall<T> implements FCall<T>{
    final ServiceMethod serviceMethod;
    final Object[] args;
    public OkHttpCall(ServiceMethod serviceMethod, Object[] args) {
        this.serviceMethod = serviceMethod;
        this.args = args;
    }

    @Override
    public void enqueue(final Callback<T> callback) {
        // 发起一个请求，给一个回调就完结了
        Log.e("TAG","正式发起请求");
        okhttp3.Call call = serviceMethod.createNewCall(args);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                if(callback != null){
                    callback.onFailure(OkHttpCall.this,e);
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                // 解析 Response -> Response<T> 回调
                // Log.e("TAG",response.body().string());
                // 涉及到解析，不能在这里写死，ConvertFactory
                Response rResponse = new Response();
                rResponse.body = serviceMethod.parseBody(response.body());

                if(callback != null){
                    callback.onResponse(OkHttpCall.this,rResponse);
                }
            }
        });

        /*OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall()*/
    }
}
