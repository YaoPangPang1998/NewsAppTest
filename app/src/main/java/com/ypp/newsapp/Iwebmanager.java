package com.ypp.newsapp;

/**
 * 作者：create by Administrator on 2020/4/22
 * TIME BY 15:48
 */
public interface Iwebmanager {
    /**
     * 获取新闻请求
     * */
//    void get(String url, Map<String, String> headers,Map<String,String> options, IgetNews igetNews);
    void get(String url,IwebCallback iwebCallback);
    /**
     * 按关键字搜索请求
     * */

}
