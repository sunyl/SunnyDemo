package com.widget.sun.demo.http;

import com.widget.sun.demo.entity.MoveSubject;
import com.widget.sun.demo.entity.base.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface APIService {

    @GET("top250")
    Observable<HttpResult<List<MoveSubject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
