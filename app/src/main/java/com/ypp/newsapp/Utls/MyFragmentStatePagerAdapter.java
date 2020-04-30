package com.ypp.newsapp.Utls;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：create by Administrator on 2020/4/23
 * TIME BY 21:49
 */
public abstract class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mList;
    private Map<Integer, Fragment> map = new HashMap<Integer, Fragment>();

    public MyFragmentStatePagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        //记录每个position位置最后显示的Fragment
        map.put(position, fragment);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).toString();
    }

    //获取指定位置最后显示的Fragment
    public Fragment getCurrentFragment(int index) {
        return map.get(index);
    }

}
