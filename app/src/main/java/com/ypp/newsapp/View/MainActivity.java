package com.ypp.newsapp.View;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ypp.newsapp.Config.ModelConfig;
import com.ypp.newsapp.Contract;
import com.ypp.newsapp.DB.ListBean;
import com.ypp.newsapp.Presenter.Presenter;
import com.ypp.newsapp.R;
import com.ypp.newsapp.ui.main.PlaceholderFragment;
import com.ypp.newsapp.ui.main.SectionsPagerAdapter;

import java.util.List;

public class MainActivity extends BaseActivity<Contract.iview, Presenter> implements Contract.iview {
    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;
    TabLayout tabs;
    private int viewpagercurrent;
    private SwipeRefreshLayout swipeRefreshLayout;
   // private List<Fragment> fragmentList;
    private  FragmentManager fragmentManager;
    private PlaceholderFragment fragment;
   private int[] TAB_TITLES= new int[]{R.string.news_cat_1,R.string.news_cat_2,R.string.news_cat_3,R.string.news_cat_4,R.string.news_cat_5,R.string.news_cat_6,R.string.news_cat_7,R.string.news_cat_8,R.string.news_cat_9,R.string.news_cat_10,R.string.news_cat_11,R.string.news_cat_12,R.string.news_cat_13};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (savedInstanceState==null){
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.view_pager, PlaceholderFragment.newInstance(1))
//                    .commit();
//        }
//        for (int i=0;i<TAB_TITLES.length;i++){
//            Fragment fragment=new
//        }
        //获取Fragmentid
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        fragmentList=fragmentManager.getFragments();

    }

    //加载布局
    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        swipeRefreshLayout=fragment.getView().findViewById(R.id.swipe_refresh);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                //刷新数据
////                mPresenter.upDatadata();
//
//            }
//        });
    }

    //查找控件
    @Override
    protected void findViewById() {
        viewPager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.tabs);

        fragmentManager=getSupportFragmentManager();
//        fragment= (PlaceholderFragment) fragmentManager.findFragmentByTag(("android:switcher:"+viewPager.getId()+":"+viewpagercurrent));
//        swipeRefreshLayout=getSupportFragmentManager().findFragmentByTag(("android:switcher:"+viewPager.getId()+":"+viewpagercurrent)).getView().findViewById(R.id.swipe_refresh);

       // fab = findViewById(R.id.fab);

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),TAB_TITLES);
        sectionsPagerAdapter.setOnItemCLick(new SectionsPagerAdapter.OnItemCLick() {
            @Override
            public void OnCiclk(String item) {
                Toast.makeText(mContext, "" + item, Toast.LENGTH_SHORT).show();
            }
        });
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                viewpagercurrent=position;
               // Log.i(TAG, "当前界面为"+position);
              //  Toast.makeText(mContext, "当前页面为"+position, Toast.LENGTH_SHORT).show();
                ModelConfig.CHANNEL=mContext.getResources().getString(TAB_TITLES[position]);
                //通知P层调用刷新数据
                mPresenter.getdata();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //创建Presenter对象
    @Override
    protected Presenter createPresenter() {
        return new Presenter();
    }

    //获取数据
    @Override
    protected void getData() {
        mPresenter.getdata();

    }
//获取数据成功
    @Override
    public void onNewsshowSuccess(List<ListBean> listBeans) {

        //将获取的数据传递给Fragment
        fragment= (PlaceholderFragment) fragmentManager.findFragmentByTag(("android:switcher:"+viewPager.getId()+":"+viewpagercurrent));
        fragment.shownews(listBeans);
      //  swipeRefreshLayout.setRefreshing(false);
    }

    //获取失败
    @Override
    public void onNewsshowFail(String errMsg) {

    }

    @Override
    public void updataSuccess(List<ListBean> listBeans) {
        fragment= (PlaceholderFragment) fragmentManager.findFragmentByTag(("android:switcher:"+viewPager.getId()+":"+viewpagercurrent));
        fragment.shownews(listBeans);
    }

    //更新数据成功

    //更新数据失败
    @Override
    public void updataFail(String errMsg) {

    }
}