package com.ypp.newsapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ypp.newsapp.DB.ListBean;

import java.util.List;

/**
 * 作者：create by Administrator on 2020/4/22
 * TIME BY 17:07
 */
public class NewsShowAdapter extends RecyclerView.Adapter<NewsShowAdapter.ViewHolder> {
    //存储返回数据中的新闻信息
    private Context mContext;
    private Activity activity;
    private List<ListBean> newslist;
    private static final String TAG = "NewsShowAdapter";
    private LinearLayout mlinearLayout;
    private ItemClickLisener itemClickLisener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        //标题
        private TextView newstitle;

        //作者
        private TextView newsauthor;
        //预览图片
        private ImageView newspreview;
        View NewsShowView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //绑定标题组件
            NewsShowView=itemView;

            newstitle=(TextView)itemView.findViewById(R.id.newsshow_title);
            newsauthor=(TextView)itemView.findViewById(R.id.newsshow_author);
            newspreview=(ImageView)itemView.findViewById(R.id.newsshow_preview);
        }
    }
    public NewsShowAdapter( Activity activity,Context mContext , List<ListBean> newslist){
        if (newslist!=null){
            this.newslist=newslist;
            this.mContext=mContext;
            this.activity=activity;
        }
//        else {
//            Log.i(TAG, "NewsShowAdapter: listBeans为kong");
//        }

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_show_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        // SearchNews searchNews=msearchNewsList.get(position);
        ListBean news=newslist.get(position);
        holder.newsauthor.setText(news.getSrc());
        holder.newstitle.setText(news.getTitle());
        // holder.newspreview.
//        Glide.with(okApp.getContext())
//                .load(news.getPic())
//        .into(holder.newspreview);
        Glide.with(mContext)
                .load(news.getPic())
                .into(holder.newspreview);
        //Log.i(TAG, "sssss: "+news.getUrl());
        //为子Item设置点击事件
        holder.NewsShowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                ListBean listBean=newslist.get(position);
                //   Toast.makeText(mContext, "你点击了"+listBean.getTitle(), Toast.LENGTH_SHORT).show();
                //通过Intent将网址传给显示页面
//                Intent intent=new Intent(mContext, webActivity.class);
//                intent.putExtra("context",listBean.getUrl());
//                activity.startActivity(intent);
                if (listBean!=null){
                    itemClickLisener.OnItemCLick(listBean.getUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (newslist!=null){
            return newslist.size();
        }
        else {
//            Log.i(TAG, "getItemCount:mlistBeans为空 ");
            return 0;

        }

    }
    public interface ItemClickLisener{
        void OnItemCLick(String url);
    }
    public void setItemClickLisener(ItemClickLisener itemClickLisener){
        this.itemClickLisener=itemClickLisener;
    }
}
