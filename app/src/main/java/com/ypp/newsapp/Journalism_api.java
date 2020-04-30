package com.ypp.newsapp;

import com.ypp.newsapp.DB.ListBean;
import com.ypp.newsapp.DB.getnews;
import com.ypp.newsapp.DB.search;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;

/**
 * 作者：create by Administrator on 2020/4/22
 * TIME BY 15:55
 */
public interface Journalism_api {
    @GET("search")
    Observable<search> getsearchnews(@HeaderMap Map<String, String> headers, @QueryMap Map<String,String> keyword);
//    @GET("channel")
//    Observable<NewsCategory> getNewsCategory( @HeaderMap Map<String, String> headers);
    @GET("get")
    Observable<getnews> getgetnews(@HeaderMap Map<String,String> headers, @QueryMap Map<String, String> QueryParams);

}
