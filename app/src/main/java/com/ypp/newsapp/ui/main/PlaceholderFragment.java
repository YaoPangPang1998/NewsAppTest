package com.ypp.newsapp.ui.main;

import android.content.Intent;
import android.location.SettingInjectorService;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ypp.newsapp.Contract;
import com.ypp.newsapp.DB.ListBean;
import com.ypp.newsapp.NewsShowAdapter;
import com.ypp.newsapp.Presenter.Presenter;
import com.ypp.newsapp.R;
import com.ypp.newsapp.View.MainActivity;
import com.ypp.newsapp.View.WebActivity;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView recyclerView;
  //  private SwipeRefreshLayout swipeRefreshLayout;
    private NewsShowAdapter adapter;
    private LinearLayoutManager layoutManager;
    private   boolean isFisrtLoad;
    private PageViewModel pageViewModel;
    private List<ListBean> listBeans;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private static final String TAG = "ssssss";
//反射
    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
       // Log.i(TAG, "newInstance: "+index);
        fragment.setArguments(bundle);
        return fragment;
    }
//Fragment生命周期
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel=new PageViewModel();

    }

  //  private boolean isFisrtLoad=false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&isFisrtLoad){
            Log.i(TAG, "setUserVisibleHint: 选中状态发生变化");
            //改变
          //  isFisrtLoad=false;
            //可进行获取数据操作
        }
       // Log.i(TAG, "setUserVisibleHint: "+isVisibleToUser);
    }

    //填充View
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main,null);
        recyclerView=root.findViewById(R.id.news_show_recycler);
      //  swipeRefreshLayout=root.findViewById(R.id.swipe_refresh);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        NewsShowAdapter adapter=new NewsShowAdapter(getActivity(),getContext(),listBeans);
        recyclerView.setAdapter(adapter);
        return root;
    }
//打气筒操作
//进行findviewbyid操作 View为打气筒生成的对象
//    @Override
//   protected void initFragmentChildView(View view) {
//    recyclerView=view.findViewById(R.id.news_show_recycler);
//    swipeRefreshLayout=view.findViewById(R.id.swipe_refresh);
   // }
//    // mPresenter.
//}
//
//    @Override
//    protected View initFragmentView(LayoutInflater inflater) {
//        View root = inflater.inflate(R.layout.fragment_main,null);
//       // final TextView textView = root.findViewById(R.id.section_label);
//
//
////        pageViewModel.getText().observe(this, new Observer<String>() {
////            @Override
////            public void onChanged(@Nullable String s) {
////                textView.setText(s);
////            }
////        });
//
////        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
////        recyclerView.setLayoutManager(layoutManager);
////        NewsShowAdapter adapter=new NewsShowAdapter(getActivity(),getContext(),listBeans);
////        recyclerView.setAdapter(adapter);
//        return root;
//    }
//    //网络数据填充的操作
//    @Override
//    protected void initFragmentData(Bundle savedInstanceState) {
////        mPresenter.getdata();
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle=getArguments();
        if (bundle!=null){
            int a=bundle.getInt(ARG_SECTION_NUMBER);
            Log.i(TAG, "onCreate: "+a);
        }
    }
    public void shownews(List<ListBean> listBeans){
        adapter=new NewsShowAdapter(getActivity(),getContext(),listBeans);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),layoutManager.getOrientation()));
        recyclerView.setAdapter(adapter);
        adapter.setItemClickLisener(new NewsShowAdapter.ItemClickLisener() {
            @Override
            public void OnItemCLick(String url) {
                if (url!=null){
                    Intent intent=new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                }
            }
        });


    }
}