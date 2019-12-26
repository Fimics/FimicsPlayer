package com.mic.libokhttp.interceptor;

import android.util.Log;


import com.mic.libokhttp.FRequest;
import com.mic.libokhttp.FRequestBody;
import com.mic.libokhttp.FResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;





public class CallServerInterceptor implements Interceptor {
    @Override
    public FResponse intercept(Chain chain) throws IOException {
        Log.e("TAG","CallServerInterceptor");
        FRequest request = chain.request();
        URL url = new URL(request.url());

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(3000);

//        if (urlConnection instanceof HttpsURLConnection) {
//            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;
//            // https 的一些操作
//            // httpsURLConnection.setHostnameVerifier();
//            // httpsURLConnection.setSSLSocketFactory();
//        }
        // urlConnection.setReadTimeout();

        // 没有做缓存 304 ，没有做重定向，没有特殊处理，没有连接复用

        // 写东西
        if(request.requestBody()!=null){
            urlConnection.setRequestMethod(request.method.getName());
            urlConnection.setDoOutput(request.method.doOutput());
        }


        urlConnection.connect();
        // 写内容
        FRequestBody requestBody = request.requestBody();
        if (requestBody != null) {
            requestBody.onWriteBody(urlConnection.getOutputStream());
        }

        int statusCode = urlConnection.getResponseCode();
        if (statusCode == 200) {
            InputStream inputStream = urlConnection.getInputStream();
            FResponse response = new FResponse(inputStream);
            return response;
        }
        // 进行一些列操作，状态码 200
        return chain.proceed(request);
    }
}
