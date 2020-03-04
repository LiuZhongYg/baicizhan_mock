package com.example.lzy.baicizhan.home.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lzy on 2020/2/29.
 */

public class AvatorBehavior extends CoordinatorLayout.Behavior<CircleImageView>{

    private float mAppBarStartY;
    private float mOriginalY;

    public AvatorBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
       if(mAppBarStartY==0) mAppBarStartY=dependency.getY();
       if(mOriginalY==0) mOriginalY=child.getY();

        child.setY(mOriginalY-mAppBarStartY+dependency.getY());
        return true;
    }
}
