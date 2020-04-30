package com.ypp.newsapp.Presenter;

import android.util.Log;

import com.ypp.newsapp.Contract;
import com.ypp.newsapp.DB.ListBean;
import com.ypp.newsapp.IwebCallback;
import com.ypp.newsapp.Model.Model;

import java.util.List;

/**
 * 作者：create by Administrator on 2020/4/22
 * TIME BY 10:40
 */
public class Presenter extends BasePresenter<Contract.iview> {
    private Model model;
    private static final String TAG = "ssssss";

    public Presenter(){
        model=new Model();
    }

    @Override
    public void attachView(Contract.iview view) {
        super.attachView(view);
    }

    @Override
    protected Contract.iview getView() {
        return super.getView();
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }

    @Override
    public void detachView() {
        super.detachView();
    }
    public void getdata(){
        model.ongettingData(new IwebCallback() {
            @Override
            public void onRuccess(List<ListBean> listBeans) {
                if (listBeans!=null){
                   getView().onNewsshowSuccess(listBeans);
                    Log.i(TAG, "获取成功");
                }
            }

            @Override
            public void fail(String errMSG) {
                Log.i(TAG, "获取失败");
            }
        });
    }
    public void upDatadata(){
        model.ongettingData(new IwebCallback() {
            @Override
            public void onRuccess(List<ListBean> listBeans) {
                if (listBeans!=null){
                  //  getView().onNewsshowSuccess(listBeans);
                    getView().updataSuccess(listBeans);
                    Log.i(TAG, "获取成功");
                }
            }

            @Override
            public void fail(String errMSG) {
                Log.i(TAG, "获取失败");
            }
        });
    }
}
