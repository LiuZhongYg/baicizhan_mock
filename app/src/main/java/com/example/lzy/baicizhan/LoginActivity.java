package com.example.lzy.baicizhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lzy.baicizhan.login.presenter.LoginPresenter;
import com.example.lzy.baicizhan.login.view.ILoginView;

/**
 * Created by lzy on 2020/2/24.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,ILoginView {

    private TextInputLayout tilAccount;
    private TextInputLayout tilPassword;
    private TextInputEditText tieAccount;
    private TextInputEditText tiePassword;
    private Button loginButton;
    private Button registerButton;

    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter=new LoginPresenter(this,this);
        init();
    }
    public void init(){
        //初始化控件
        tilAccount=findViewById(R.id.til_account);
        tilPassword=findViewById(R.id.til_password);
        tieAccount=findViewById(R.id.tie_account);
        tiePassword=findViewById(R.id.tie_password);
        loginButton=findViewById(R.id.btn_login);
        registerButton=findViewById(R.id.btn_register);

        //添加监听
        loginButton.setOnClickListener(this);
        //注册监听
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
                break;
            case R.id.btn_login:
                String account=tieAccount.getText().toString();
                String password=tiePassword.getText().toString();
                loginPresenter.doLogin(account,password);
        }
    }

    @Override
    public void successView() {
        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failedView() {
        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
    }
}
