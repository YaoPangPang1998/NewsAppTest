package com.ypp.newsapp.View;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import com.ypp.newsapp.Presenter.BasePresenter;

/**
 * 作者：create by Administrator on 2020/4/22
 * TIME BY 11:02
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends FragmentActivity {

    public String TAG = getClass().getSimpleName() + "";

    protected T mPresenter;

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityView(savedInstanceState);

        mContext = BaseActivity.this;

        //创建presenter
        mPresenter = createPresenter();

        // presenter与view绑定
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }

        findViewById();

        getData();

    }


    /**
     * 关于Activity的界面填充的抽象方法，需要子类必须实现
     */
    protected abstract void initActivityView(Bundle savedInstanceState);

    /**
     * 加载页面元素
     */
    protected abstract void findViewById();

    /**
     * 创建Presenter 对象
     *
     * @return
     */
    protected abstract T createPresenter();

    protected abstract void getData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }
}


