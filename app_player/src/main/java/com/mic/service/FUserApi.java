package com.mic.service;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FUserApi {
        @GET("login")
        Call<?> login(@Query("name") String name, @Query("password") String password);
    }