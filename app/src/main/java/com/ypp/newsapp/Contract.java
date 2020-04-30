package com.ypp.newsapp;

import com.ypp.newsapp.DB.ListBean;

import java.util.List;

/**
 * 作者：create by Administrator on 2020/4/22
 * TIME BY 13:48
 */
public class Contract {
    public interface iview{
        void onNewsshowSuccess(List<ListBean> listBeans);
        void onNewsshowFail(String errMsg);
        void updataSuccess(List<ListBean> listBeans);
        void updataFail(String errMsg);
    }
    public interface iModel{
        void ongettingData(IwebCallback iwebCallback);
        void onsearchData(IwebCallback iwebCallback);
    }
    public interface dataCallBack{
        void getdataSuccess();
        void getdataFali(String errMsg);
    }
}
