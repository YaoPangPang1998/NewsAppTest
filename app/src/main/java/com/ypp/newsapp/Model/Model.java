package com.ypp.newsapp.Model;

import android.util.Log;

import com.ypp.newsapp.Config.UrlConfig;
import com.ypp.newsapp.Contract;
import com.ypp.newsapp.DB.ListBean;
import com.ypp.newsapp.Factory.WebFactory;
import com.ypp.newsapp.IwebCallback;
import com.ypp.newsapp.Iwebmanager;

import java.util.List;

/**
 * 作者：create by Administrator on 2020/4/22
 * TIME BY 10:40
 */
public class Model implements Contract.iModel {
    //获取数据方法
    private static final String TAG = "sssss";
    //获取webmanager
    private Iwebmanager iwebmanager= WebFactory.getWebManager();
    @Override
    public void ongettingData(final IwebCallback iwebCallback) {

        iwebmanager.get(UrlConfig.baseurl, new IwebCallback() {
            @Override
            public void onRuccess(List<ListBean> listBeans) {
                iwebCallback.onRuccess(listBeans);
                Log.i(TAG, "网络请求成功: ");
            }

            @Override
            public void fail(String errMSG) {
                Log.i(TAG, "网络请求失败: ");
            }
        });

    }

    @Override
    public void onsearchData(IwebCallback iwebCallback) {

    }


}
