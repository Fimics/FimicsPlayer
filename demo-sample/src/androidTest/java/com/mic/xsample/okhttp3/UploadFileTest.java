package com.mic.xsample.okhttp3;

import android.os.Environment;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.util.Log;

import com.mic.libcore.okhttp.upload.ExMultipartBody;
import com.mic.libcore.okhttp.upload.UploadProgressListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.mic.libcore.okhttp.OkUtils.guessMimeType;

@RunWith(AndroidJUnit4.class)
public class UploadFileTest {

    @Test
    public void uploadFile() {
        // 这个是 Okhttp 上传文件的用法
        String url = "https://api.saiwuquan.com/api/upload";
        File file = new File(Environment.getExternalStorageDirectory(), "test.apk");
        OkHttpClient httpClient = new OkHttpClient();
        // 构建请求 Body , 这个我们之前自己动手写过
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        builder.addFormDataPart("platform", "android");
        builder.addFormDataPart("file", file.getName(),
                RequestBody.create(MediaType.parse(guessMimeType(file.getAbsolutePath())), file));

        ExMultipartBody exMultipartBody = new ExMultipartBody(builder.build()
                ,new UploadProgressListener(){

            @Override
            public void onProgress(long total, long current) {

            }
        });

        // 怎么监听上传文件的进度？
        // 构建一个请求
        final Request request = new Request.Builder()
                .url(url)
                .post(exMultipartBody).build();
        // new RealCall 发起请求
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", response.body().string());
            }
        });

    }

}
