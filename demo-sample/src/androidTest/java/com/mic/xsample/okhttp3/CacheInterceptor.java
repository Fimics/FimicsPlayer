package com.mic.xsample.okhttp3;


import android.content.Context;
import android.os.Environment;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.util.Log;

import com.mic.libcore.okhttp.cache.CacheRequestInterceptor;
import com.mic.libcore.okhttp.cache.CacheResponseInterceptor;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RunWith(AndroidJUnit4.class)
public class CacheInterceptor {


    @Test
    public void addCache(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        // 自定义缓存（要求：有网 30s 内请求读缓存，无网直接读缓存）
        // OkHttp 自带的扩展有坑，我们之前自己写过这个缓存管理，与 OkHttp 结合就可以了

        // 思路？拦截器?分为两种
        File file = new File(Environment.getExternalStorageDirectory(),"cache");
        Cache cache = new Cache(file,100*1024*1024);
        OkHttpClient mHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                // 加载最前 过期时间缓存多少秒
                .addInterceptor(new CacheRequestInterceptor(appContext))
                // 加载最后,数据缓存 过期时间 30s
                .addNetworkInterceptor(new CacheResponseInterceptor())
                .build();

        String url = "https://api.saiwuquan.com/api/appv2/sceneModel";
        // 构建一个请求
        final Request request = new Request.Builder()
                .url(url).build();
        // new RealCall 发起请求
        Call call = mHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG",response.body().string());
                // 都是有 第一把，第二把没有网络的了只有缓存的 (30s 以内)，过了 30s 之后又会有网络的了（会再请求更新）
                Log.e("TAG", response.cacheResponse()+" ; "+response.networkResponse());
            }
        });
    }
}
