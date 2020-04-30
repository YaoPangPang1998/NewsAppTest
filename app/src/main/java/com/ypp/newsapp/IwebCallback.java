package com.ypp.newsapp;

import com.ypp.newsapp.DB.ListBean;

import java.util.List;

/**
 * 作者：create by Administrator on 2020/4/22
 * TIME BY 15:49
 */
public interface IwebCallback {
    void onRuccess(List<ListBean> listBeans);
    void fail(String errMSG);
}
