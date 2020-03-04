package com.example.lzy.baicizhan;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.lzy.baicizhan.home.adapter.HomeAdapter;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        bottomNavigationView=findViewById(R.id.bottom_navigation_view);
        viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(new HomeAdapter(getSupportFragmentManager()));
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                viewPager.setCurrentItem(0,true);
                break;
            case R.id.menu_excise:
                viewPager.setCurrentItem(1,true);
                break;
            case R.id.menu_personal:
                viewPager.setCurrentItem(2,true);
                break;
            default:
                break;
        }
        return true;
    }
}
