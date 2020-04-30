package com.ypp.newsapp.Presenter;

import android.util.Log;
import android.widget.Toast;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * 作者：create by Administrator on 2020/4/22
 * TIME BY 11:01
 */
public abstract class BasePresenter<T> {
        //View类型接口软引用
        protected Reference<T> mViewRef;
        //构建与View的连接
        public void attachView(T view){
            mViewRef=new SoftReference<T>(view);

        }
        protected T getView(){
                return mViewRef.get();
        }
        public boolean isViewAttached(){
            return mViewRef!=null&&mViewRef.get()!=null;
        }
        public void detachView() {

            if (mViewRef != null) {
                mViewRef.clear();
            }

        }
}
