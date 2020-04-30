package com.ypp.newsapp.ui.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;

import com.google.android.material.tabs.TabLayout;
import com.ypp.newsapp.Config.ModelConfig;
import com.ypp.newsapp.Model.Model;
import com.ypp.newsapp.R;
import com.ypp.newsapp.Utls.MyFragmentStatePagerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
   // TabLayout tabs;
    @StringRes
    private  int[] TAB_TITLES;
    private final Context mContext;
    public SectionsPagerAdapter(Context context, FragmentManager fm,int[] TAB_TITLES) {
        super(fm);
        mContext = context;
        this.TAB_TITLES=TAB_TITLES;
    }
    int posi = 0;
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
//        if(position != posi){
       // PagerTabStrip pagerTabStrip=tabs.get
          //  Toast.makeText(mContext, ""+position, Toast.LENGTH_SHORT).show();
//            posi= position;
//        }

        return PlaceholderFragment.newInstance(position+1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
       return TAB_TITLES.length;
    }
    private OnItemCLick onItemCLick;
    public interface OnItemCLick{
        void OnCiclk(String item);
    }

    public OnItemCLick getOnItemCLick() {
        return onItemCLick;
    }

    public void setOnItemCLick(OnItemCLick onItemCLick) {
        this.onItemCLick = onItemCLick;
    }
}