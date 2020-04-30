package com.ypp.newsapp.Factory;

import com.ypp.newsapp.Iwebmanager;
import com.ypp.newsapp.Utls.RetrofitManager;

/**
 * 作者：create by Administrator on 2020/4/22
 * TIME BY 16:09
 */
public class WebFactory {
    public static Iwebmanager getWebManager(){
        return RetrofitManager.getInstance();
    }
}
