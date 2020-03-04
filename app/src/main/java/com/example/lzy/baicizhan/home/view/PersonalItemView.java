package com.example.lzy.baicizhan.home.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lzy.baicizhan.R;

/**
 * Created by lzy on 2020/2/27.
 */

public class PersonalItemView extends LinearLayout {
    private boolean isbootom;
    private ImageView icon;
    private ImageView bottomLine;
    private TextView item;
    public PersonalItemView(Context context) {
        this(context,null);
    }

    public PersonalItemView(Context context, AttributeSet attrs) {
        this(context,attrs,-1);
    }

    public PersonalItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.fragment_personal_item,this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PersonalItemView);
        isbootom = ta.getBoolean(R.styleable.PersonalItemView_showBottomLine, true);
        icon=findViewById(R.id.icon);
        bottomLine=findViewById(R.id.bottom_line);
        item=findViewById(R.id.name);
        item.setText(ta.getString(R.styleable.PersonalItemView_showText));
        icon.setBackgroundResource(ta.getResourceId(R.styleable.PersonalItemView_showLeftImg,
                R.drawable.settings));
        ta.recycle();
        initview();
    }
    private void initview() {
        //如果isbootom为true，显示下划线，否则隐藏
        if (isbootom) {
            bottomLine.setVisibility(View.VISIBLE);
        } else {
            bottomLine.setVisibility(View.GONE);
        }
    }
}
