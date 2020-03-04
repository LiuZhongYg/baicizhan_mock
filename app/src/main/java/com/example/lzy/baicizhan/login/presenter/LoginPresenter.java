package com.example.lzy.baicizhan.login.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.lzy.baicizhan.MainActivity;
import com.example.lzy.baicizhan.login.model.ILoginModel;
import com.example.lzy.baicizhan.login.model.ILoginModelListener;
import com.example.lzy.baicizhan.login.model.LoginModel;
import com.example.lzy.baicizhan.login.view.ILoginView;

/**
 * Created by lzy on 2020/2/24.
 */

public class LoginPresenter implements ILoginModelListener{
    private ILoginModel loginModel;
    private ILoginView loginView;
    private Context context;
    public LoginPresenter(ILoginView loginView, Context context){
        loginModel=new LoginModel(this);
        this.loginView=loginView;
        this.context=context;
    }
    public void doLogin(String account,String password){
        loginModel.login(account,password);
    }

    @Override
    public void loginSuccess() {
        loginView.successView();
        context.startActivity(new Intent(context, MainActivity.class));

    }

    @Override
    public void loginFailed() {
       loginView.failedView();
    }
}
