package com.mic.frame.rxretrofit;


import android.util.Log;
import com.mic.frame.Host;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("all")
public class RetrofitClient {

    private static final String TAG ="RetrofitClient";

    private  static final  RetrofitClient instance = new RetrofitClient();

    private Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;

    // 各种套路和招式 ，发现问题解决问题，基础，源码的理解
    // 1. 没打印？
    // 2. 数据格式不一致？成功 data 是个对象，不成功 data 是个 String
    // 3. 还有就是 baseUrl 问题？ (Retrofit 找不到任何入口可以修改)
    //        3.1 不同的 baseUrl 构建不同的 Retrofit 对象 （直不应该首选）
    //        3.2 自己想办法，取巧也行走漏洞

    private RetrofitClient(){
        mOkHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                                @Override
                                public void log(String message) {
                                    Log.e("TAG",message);
                                }
                            }).setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Host.baseurl)
                // 添加解析转换工厂,Gson 解析，Xml解析，等等
                .addConverterFactory(GsonConverterFactory.create())
                // 添加 OkHttpClient,不添加默认就是 光杆 OkHttpClient
                .client(mOkHttpClient)
                .build();
    }

    public static RetrofitClient getInstance(){
        return instance;
    }


    public Retrofit  getRetrofit(){
        return mRetrofit;
    }
}
