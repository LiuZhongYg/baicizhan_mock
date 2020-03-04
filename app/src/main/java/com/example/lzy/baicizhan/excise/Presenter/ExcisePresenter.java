package com.example.lzy.baicizhan.excise.Presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.lzy.baicizhan.excise.Adapter.exciseAdapter;
import com.example.lzy.baicizhan.excise.bean.ArticleInfo;
import com.example.lzy.baicizhan.excise.fragment.TrainingListFragment;
import com.example.lzy.baicizhan.excise.model.ExciseModel;
import com.example.lzy.baicizhan.excise.model.IExciseModel;
import com.example.lzy.baicizhan.excise.view.IExciseView;

import java.util.List;

/**
 * Created by lzy on 2020/3/1.
 */

public class ExcisePresenter {
    private IExciseModel exciseModel;
    private TrainingListFragment trainingListFragment;
    private IExciseView exciseView;
    private exciseAdapter adapter;
    private final String TAG="ExcisePresenter";
    public ExcisePresenter(IExciseView exciseView)
    {
        exciseModel=new ExciseModel(new MyHandler());
        trainingListFragment=new TrainingListFragment();
        this.exciseView=exciseView;
    }
    public void load(){
        //加载数据
        exciseModel.loadArticalPath();
        Log.d(TAG,"after loadArticalPath...");
        exciseView.replaceFragment(trainingListFragment);
    }
    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    List<String> respath=(List<String>)msg.obj;
                    Log.d(TAG,"ExcisePresenter respath is "+respath.size());
                    exciseModel.loadContent(respath);
                    adapter=new exciseAdapter(respath,exciseView);
                    trainingListFragment.setAdapter(adapter);
                    break;
                case 1:
                    ArticleInfo articleInfo=(ArticleInfo)msg.obj;
                    Log.d(TAG,"articleInfo is "+articleInfo);
                    adapter.setArticleInfo(articleInfo);
                    break;
                default:
                    break;
            }
        }
    }
}
