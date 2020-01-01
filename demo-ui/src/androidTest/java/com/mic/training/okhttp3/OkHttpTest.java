package com.mic.training.okhttp3;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mic.libokhttp.FCall;
import com.mic.libokhttp.FCallback;
import com.mic.libokhttp.FOkhttpClient;
import com.mic.libokhttp.FRequest;
import com.mic.libokhttp.FResponse;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class OkHttpTest {

    @Test
    public void testGet(){
        FOkhttpClient client=new FOkhttpClient();
        final FRequest request = new FRequest.Builder()
                .url("http://192.168.31.204:8080/login")
                .build();
        FCall call =client.newCall(request);

//        FResponse response =call.execute();
//        String body =response.string();


        call.enqueue(new FCallback() {
            @Override
            public void onFailure(FCall call, IOException e) {

            }

            @Override
            public void onResponse(FCall call, FResponse response) {
                  String srt = response.string();
                  String a ="1";
            }
        });

    }
}
