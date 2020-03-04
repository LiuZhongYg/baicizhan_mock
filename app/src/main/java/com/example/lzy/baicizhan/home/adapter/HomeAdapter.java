package com.example.lzy.baicizhan.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Adapter;

import com.example.lzy.baicizhan.home.fragment.exciseFragment;
import com.example.lzy.baicizhan.home.fragment.homefragment;
import com.example.lzy.baicizhan.home.fragment.personalFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzy on 2020/2/27.
 */

public class HomeAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments=new ArrayList<>();
    public HomeAdapter(FragmentManager fm){
        super(fm);
        init();
    }
    public void init(){
        fragments.add(new homefragment());
        fragments.add(new exciseFragment());
        fragments.add(new personalFragment());
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
