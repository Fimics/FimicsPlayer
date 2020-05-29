package com.mic.core.thirdparty.okhttp.cache;

import android.content.Context;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheRequestInterceptor implements Interceptor{
    private Context mContext;
    public CacheRequestInterceptor(Context context){
        this.mContext = context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(isNetWork(mContext)){
            // 只读缓存
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE).build();
        }
        return chain.proceed(request);
    }

    private boolean isNetWork(Context mContext) {
        // 自己去完善
        return true;
    }
}
