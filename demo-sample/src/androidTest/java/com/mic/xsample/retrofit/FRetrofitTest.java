package com.mic.xsample.retrofit;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mic.libretrofit.annotation.GET;
import com.mic.libretrofit.annotation.Query;
import com.mic.libretrofit.http.FCall;
import com.mic.libretrofit.http.FRetrofit;
import com.mic.libretrofit.http.FRetrofitClient;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FRetrofitTest {



    public interface  FUserApi{
        @GET("login")
        FCall<?> login(@Query("name") String name, @Query("password") String password);
    }


    @Test
    public void testFRetrofit(){
        FRetrofit fRetrofit = FRetrofitClient.getInstance().getfRetrofit();
        fRetrofit.create(FUserApi.class);
    }
}
