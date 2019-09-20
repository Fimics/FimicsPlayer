package com.mic.user.userinfo;

import com.mic.frame.model.Result;
import com.mic.frame.model.user.User;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RxUserApi {

    @GET("login")
    Observable<Result<User>> login(@Query("name") String name, @Query("password") String password);
}
