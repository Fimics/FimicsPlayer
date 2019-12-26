package com.mic.libretrofit.http;


import com.google.gson.Gson;
import com.mic.libretrofit.annotation.GET;
import com.mic.libretrofit.annotation.POST;
import com.mic.libretrofit.annotation.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;


@SuppressWarnings("all")
public class ServiceMethod {


    FRetrofit fRetrofit;
    Method method;
    String httpMethod;
    String relativeUrl;
    final ParameterHandler<?>[] parameterHandlers;


    public ServiceMethod(Builder builder) {
        this.fRetrofit=builder.fRetrofit;
        this.method=builder.method;
        this.httpMethod=builder.httpMethod;
        this.relativeUrl=builder.relativeUrl;
        this.parameterHandlers = builder.parameterHandlers;
    }


    public okhttp3.Call createNewCall(Object[] args) {
        // 还需要一个对象，专门用来添加参数的
        RequestBuilder requestBuilder = new RequestBuilder(fRetrofit.baseUrl, relativeUrl, httpMethod, parameterHandlers, args);
        return fRetrofit.factory.newCall(requestBuilder.build());
    }

    public <T> T parseBody(ResponseBody responseBody) {
        // 获取解析类型 T 获取方法返回值的类型
        Type returnType = method.getGenericReturnType();// 返回值对象
        Class <T> dataClass = (Class <T>) ((ParameterizedType) returnType).getActualTypeArguments()[0];
        // 解析工厂去转换
        Gson gson = new Gson();
        T body = gson.fromJson(responseBody.charStream(),dataClass);
        return body;
    }

    public static class Builder {

        FRetrofit fRetrofit;
        Method method;
        final Annotation[] methodAnnotations;
        String httpMethod;
        String relativeUrl;
        //一个参数上可以有多个注解
        Annotation[][] parameterAnnotations;
        final ParameterHandler<?>[] parameterHandlers;

        public Builder(FRetrofit fRetrofit, Method method) {
            this.fRetrofit=fRetrofit;
            this.method=method;
            this.methodAnnotations=method.getAnnotations();
            this.parameterAnnotations = method.getParameterAnnotations();
            this.parameterHandlers = new ParameterHandler[parameterAnnotations.length];
        }

        public okhttp3.Call createNewCall(Object[] args) {
            // 还需要一个对象，专门用来添加参数的
            RequestBuilder requestBuilder = new RequestBuilder(fRetrofit.baseUrl, relativeUrl, httpMethod, parameterHandlers, args);
            return fRetrofit.factory.newCall(requestBuilder.build());
        }

        public <T> T parseBody(ResponseBody responseBody) {
            // 获取解析类型 T 获取方法返回值的类型
            Type returnType = method.getGenericReturnType();// 返回值对象
            Class <T> dataClass = (Class <T>) ((ParameterizedType) returnType).getActualTypeArguments()[0];
            // 解析工厂去转换
            Gson gson = new Gson();
            T body = gson.fromJson(responseBody.charStream(),dataClass);
            return body;
        }

        public ServiceMethod build(){
            // 解析，OkHttp 请求的时候 ，url = baseUrl + relativeUrl，method
            for (Annotation methodAnnotation : methodAnnotations) {
                // 解析 POST  GET
                parseAnnotationMethod(methodAnnotation);
            }

            // 解析参数注解
            int count = parameterHandlers.length;
            for (int i = 0; i < count; i++) {
                Annotation parameter = parameterAnnotations[i][0];
                // Query 等等
                // 会涉及到一个模板和策略两种设计模式
                if (parameter instanceof Query) {
                    // 一个一个封装成 ParameterHandler ，不同的参数注解选择不同的策略
                    parameterHandlers[i] = new ParameterHandler.Query<>(((Query) parameter).value());
                }
            }

            return new ServiceMethod(this);
        }


        private void parseAnnotationMethod(Annotation methodAnnotation) {
            // value ,请求方法
            if (methodAnnotation instanceof GET) {
                parseMethodAndPath("GET", ((GET) methodAnnotation).value());
            } else if (methodAnnotation instanceof POST) {
                parseMethodAndPath("POST", ((POST) methodAnnotation).value());
            }
            // 还有一大堆解析 if else
        }


        private void parseMethodAndPath(String method, String value) {
            this.httpMethod = method;
            this.relativeUrl = value;
        }
    }


}
