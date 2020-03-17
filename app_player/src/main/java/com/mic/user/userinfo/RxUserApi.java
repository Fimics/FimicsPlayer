package com.mic.user.userinfo;

import com.mic.user.model.Result;
import com.mic.user.model.user.User;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RxUserApi {

    @GET("login")
    Observable<Result<User>> login(@Query("name") String name, @Query("password") String password);
}
