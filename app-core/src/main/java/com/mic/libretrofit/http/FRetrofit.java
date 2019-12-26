package com.mic.libretrofit.http;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.OkHttpClient;

@SuppressWarnings("all")
public class FRetrofit {

    private static final String TAG ="FRetrofit";
    private Map<Method,ServiceMethod> serviceMethodMapCache = new ConcurrentHashMap<>();

    final String baseUrl;
    final Call.Factory factory;

    public FRetrofit(Builder builder) {
          this.baseUrl=builder.baseUrl;
          this.factory=builder.factory;
    }

    /**
     * 重点
     * @param serice
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> serice){

        return (T) Proxy.newProxyInstance(serice.getClassLoader(), new Class[]{serice}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               // Log.e(TAG,"method--name:"+method);

                // 每执行一个方法都会来这里
                // 判断是不是 Object 的方法 ？
                if(method.getDeclaringClass() == Object.class){
                    return method.invoke(this,args);
                }


                //解析注解参数
                ServiceMethod serviceMethod =loadServiceMethod(method);

                return null;
            }
        });
    }

    private ServiceMethod loadServiceMethod(Method method) {

        // 享元设计模式
        ServiceMethod serviceMethod = serviceMethodMapCache.get(method);
        if(serviceMethod == null){
            serviceMethod = new ServiceMethod.Builder(this,method).build();
            serviceMethodMapCache.put(method,serviceMethod);
        }
        return serviceMethod;

    }


    static class Builder{
        String baseUrl;
        Call.Factory factory;
        public Builder baseUrl(String baseUrl){
            this.baseUrl=baseUrl;
            return this;
        }


        public Builder client(Call.Factory factory){
            this.factory=factory;
            return this;
        }


        public FRetrofit build(){
            if(factory==null){
                factory = new OkHttpClient();
            }
            return new FRetrofit(this);
        }


    }
}
