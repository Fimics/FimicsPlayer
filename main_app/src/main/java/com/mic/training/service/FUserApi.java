package com.mic.training.service;

import com.mic.bb.libretrofit.annotation.GET;
import com.mic.bb.libretrofit.annotation.Query;
import com.mic.bb.libretrofit.http.FCall;

public interface FUserApi {
        @GET("login")
        FCall<?> login(@Query("name") String name, @Query("password") String password);
    }