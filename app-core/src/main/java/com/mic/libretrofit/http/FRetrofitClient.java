package com.mic.libretrofit.http;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class FRetrofitClient {

    private static final String TAG ="FRetrofitClient";
    private static final FRetrofitClient instance = new FRetrofitClient();
    private static FRetrofit fRetrofit;
    private static OkHttpClient okHttpClient;


    static {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d(TAG,message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        fRetrofit = new FRetrofit.Builder()
                .baseUrl("http://192.168.31.204:8080/")
                .client(okHttpClient)
                .build();
    }

    private FRetrofitClient(){
    }

    public static FRetrofitClient getInstance(){
        return instance;
    }

    public FRetrofit getfRetrofit(){
        return  fRetrofit;
    }
}
