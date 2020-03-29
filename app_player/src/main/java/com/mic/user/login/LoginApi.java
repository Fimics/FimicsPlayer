package com.mic.user.login;

import com.mic.core.Result;
import com.mic.user.model.User;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginApi {

    @GET("/v1/login")
    Observable<Result<User>> login(@Query("name") String name, @Query("password") String password);
}
