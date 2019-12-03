package com.mic.training.service;

import com.mic.libretrofit.annotation.GET;
import com.mic.libretrofit.annotation.Query;
import com.mic.libretrofit.http.FCall;

public interface FUserApi {
        @GET("login")
        FCall<?> login(@Query("name") String name, @Query("password") String password);
    }