package com.example.lzy.baicizhan.excise.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.lzy.baicizhan.R;
import com.example.lzy.baicizhan.excise.bean.ArticleInfo;
import com.example.lzy.baicizhan.excise.fragment.ExciseContentFragment;
import com.example.lzy.baicizhan.excise.view.IExciseView;
import com.example.lzy.baicizhan.utils.Constant;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lzy on 2020/3/2.
 */

public class exciseAdapter extends RecyclerView.Adapter<exciseAdapter.ViewHolder>{
    private final String TAG="exciseAdapter";
    private List<ArticleInfo> articleInfos;
    private List<String> path;
    private ExciseContentFragment exciseContentFragment;
    private IExciseView exciseView;
    public exciseAdapter(List<String> path,IExciseView exciseView){
        articleInfos = new ArrayList<>();
        this.path=path;
        this.exciseView=exciseView;
        exciseContentFragment=new ExciseContentFragment();
    }
    @Override
    public exciseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_listen_train_item,
                parent, false);

        return new ViewHolder(view);
    }
    public void setArticleInfo(ArticleInfo articleInfo){
        articleInfos.add(articleInfo);
        notifyDataSetChanged();//不知道什么意思
    }
    @Override
    public void onBindViewHolder(exciseAdapter.ViewHolder holder, int position) {
        holder.title.setText(articleInfos.get(position).getArticle());
        final String textUrl= Constant.URL_RESOURCE+path.get(position)+"/"+articleInfos.get(position).getName();
        final String musicUrl=Constant.URL_RESOURCE+path.get(position)+"/"+articleInfos.get(position).getMusic();
        Log.d(TAG,path+","+articleInfos);
        Log.d(TAG,"textUrl :"+textUrl);
        Log.d(TAG,"musicUrl :"+musicUrl);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
                                               @Override
                                               public void onClick(View view) {
                                                   exciseContentFragment.setUrl(textUrl,musicUrl);
                                                   exciseView.replaceFragment(exciseContentFragment);
                                               }
                                           }
        );
    }

    @Override
    public int getItemCount() {
        return articleInfos.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tv_article_title);
        }
    }
}
