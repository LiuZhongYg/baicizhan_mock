package com.example.lzy.baicizhan.login.model;

import android.os.Handler;
import android.os.Message;

import com.example.lzy.baicizhan.login.presenter.LoginPresenter;
import com.example.lzy.baicizhan.utils.Constant;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lzy on 2020/2/24.
 */

public class LoginModel implements ILoginModel {
    private ILoginModelListener listener;
    private MyHandler myHandler;
    public LoginModel(ILoginModelListener listener){
        this.listener=listener;
        myHandler=new MyHandler();
    }
    @Override
    public void login(String account, String password) {
        OkHttpClient client = new OkHttpClient();

        FormBody.Builder formBody=new FormBody.Builder();
        formBody.add("account",account);
        formBody.add("password",password);

        //Request request=new Request.Builder
        Request request=new Request.Builder()
                .url(Constant.URL+"LoginServlet")
                .post(formBody.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //listener.loginFailed();
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String result=response.body().string();
                    Message message=myHandler.obtainMessage();
                    message.obj=result;
                    myHandler.sendMessage(message);
                }
            }
        });

    }
    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                listener.loginSuccess();

        }
    }
}
