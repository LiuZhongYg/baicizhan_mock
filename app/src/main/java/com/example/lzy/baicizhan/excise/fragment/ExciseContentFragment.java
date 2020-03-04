package com.example.lzy.baicizhan.excise.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lzy.baicizhan.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ExciseContentFragment extends Fragment {
    private final String TAG="ExciseContentFragment";
    private String textUrl;
    private String musicUrl;
    private Activity mActivity;
    private TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_excise_content, container, false);
        textView=view.findViewById(R.id.excise_content);
        return view;
    }
    public void setUrl(String textUrl,String musicUrl){
        this.textUrl=textUrl;
        this.musicUrl=musicUrl;
        loadText();
    }
    public void loadText(){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(textUrl)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG,"content : onResponse...");
                if(response.isSuccessful()){
                    final String content=response.body().string();
                    Log.d(TAG,"content :"+content);
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(content);
                        }
                    });
                }
            }
        });
    }
}