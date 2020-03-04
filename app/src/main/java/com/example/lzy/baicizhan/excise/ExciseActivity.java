package com.example.lzy.baicizhan.excise;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lzy.baicizhan.R;
import com.example.lzy.baicizhan.excise.Presenter.ExcisePresenter;
import com.example.lzy.baicizhan.excise.view.IExciseView;

public class ExciseActivity extends AppCompatActivity implements IExciseView{
    private FragmentManager fm;
    private final String TAG="ExciseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excise);
        fm=getSupportFragmentManager();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int type=bundle.getInt("type");
        fm=getSupportFragmentManager();
        Log.d(TAG,"onCreate");

        if(type==1){
            ExcisePresenter excisePresenter=new ExcisePresenter(this);
            Log.d(TAG,"before load ...");
            excisePresenter.load();
        }

    }
    @Override
    public void replaceFragment(Fragment fragment){
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fl_content,fragment);
        ft.addToBackStack(null);
        ft.commit();
        Log.d(TAG,"replace Fragment success");
    }
}
