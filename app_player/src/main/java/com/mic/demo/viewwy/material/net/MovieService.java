package com.mic.demo.viewwy.material.net;


import com.mic.demo.viewwy.material.bean.Movie;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieService {

    @GET("top250")
    Observable<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);
}
