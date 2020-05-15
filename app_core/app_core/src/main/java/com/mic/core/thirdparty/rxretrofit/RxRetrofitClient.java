package com.mic.core.thirdparty.rxretrofit;


import android.util.Log;


import com.mic.BuildConfig;
import com.mic.core.utils.app.AppGlobals;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("")
public class RxRetrofitClient {

    private static final String TAG ="RetrofitClient";

    private  static final RxRetrofitClient instance = new RxRetrofitClient();

    private Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;

    // 各种套路和招式 ，发现问题解决问题，基础，源码的理解
    // 1. 没打印？
    // 2. 数据格式不一致？成功 data 是个对象，不成功 data 是个 String
    // 3. 还有就是 baseUrl 问题？ (Retrofit 找不到任何入口可以修改)
    //        3.1 不同的 baseUrl 构建不同的 Retrofit 对象 （直不应该首选）
    //        3.2 自己想办法，取巧也行走漏洞


    private RxRetrofitClient(){
        mOkHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                                @Override
                                public void log(String message) {
                                    Log.e("TAG",message);
                                }
                            }).setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build();

        String url = AppGlobals.baseurl();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                // 添加解析转换工厂,Gson 解析，Xml解析，等等
                .addConverterFactory(GsonConverterFactory.create())
                // 添加 OkHttpClient,不添加默认就是 光杆 OkHttpClient
                .client(mOkHttpClient)
                //rxJavacall-->Observable  adapter模式
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RxRetrofitClient getInstance(){
        return instance;
    }


    public Retrofit  getRetrofit(){
        return mRetrofit;
    }

}
