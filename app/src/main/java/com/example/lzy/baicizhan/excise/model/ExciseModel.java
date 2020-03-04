package com.example.lzy.baicizhan.excise.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.lzy.baicizhan.excise.bean.ArticleInfo;
import com.example.lzy.baicizhan.utils.Constant;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lzy on 2020/3/1.
 */

public class ExciseModel implements IExciseModel{
    private Handler handler;
    private final String TAG="ExciseModel";
    public ExciseModel(Handler handler){
        this.handler=handler;
    }
    @Override
    public void loadArticalPath(){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(Constant.URL+"ArticleServlet")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                Log.d(TAG,"loadArticlePath failed ...");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //异步线程 ,用Handler把消息传出去
                Log.d(TAG,"onResponse ...");
                if(response.isSuccessful()){
                    Log.d(TAG,"onResponse is Successfull...");
                    String pathJson=response.body().string();
                    Log.d(TAG,"pathJson is "+pathJson);
                    List<String> resPath=getResPath(pathJson);
                    Log.d(TAG,"resPaht is "+resPath);
                    Message msg=Message.obtain();
                    msg.what=0;
                    msg.obj=resPath;
                    handler.sendMessage(msg);
                }
            }
        });

    }
    private List<String> getResPath(String pathJson){
        List<String> list=new ArrayList<>();
        try{
            JSONArray jsonArray=new JSONArray(pathJson);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //list={read_1,read_2,read_3}
                list.add(jsonObject.getString("path"));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void loadContent(List<String> resPath){
        final List<String> reaPath=getReadPath(resPath);
        final OkHttpClient client=new OkHttpClient();


            new Thread(new Runnable(){
                @Override
                public void run() {
                    for(int i=0;i<reaPath.size();i++) {
                        Request request = new Request.Builder()
                                .url(reaPath.get(i))
                                .build();
                        try {
                            Response response = client.newCall(request).execute();
                            String res = response.body().string();
                            ArticleInfo articleInfo   = getArticleInfo(res);
                            Message msg = Message.obtain();
                            msg.what = 1;
                            msg.obj = articleInfo;
                            handler.sendMessage(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

           /* client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if(response.isSuccessful()){
                        String res=response.body().string();
                        ArticleInfo articleInfo=getArticleInfo(res);

                        Message msg=Message.obtain();
                        msg.what=1;
                        msg.obj=articleInfo;
                        handler.sendMessage(msg);
                    }
                }
            });*/
        }

    private List<String> getReadPath(List<String> resPath){
        List<String> readPath=new ArrayList<>();
        Log.d(TAG,"resPath size is "+readPath.size());

        for(int i=0;i<resPath.size();i++){
            readPath.add(Constant.URL_RESOURCE+resPath.get(i)+"/read.json");
            Log.d(TAG, "资源路径"+ Constant.URL_RESOURCE+resPath.get(i)+"/read.json");
        }
        return readPath;
    }
    public ArticleInfo getArticleInfo(String res){
        ArticleInfo articleInfo=new ArticleInfo();
        try{
            JSONObject jsonObject=new JSONObject(res);
            articleInfo.setName(jsonObject.getString("name"));
            articleInfo.setArticle(jsonObject.getString("article"));
            articleInfo.setMusic(jsonObject.getString("music"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        return articleInfo;
    }
}
