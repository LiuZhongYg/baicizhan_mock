package com.example.lzy.baicizhan.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lzy.baicizhan.R;
import com.example.lzy.baicizhan.excise.ExciseActivity;


import android.support.v7.widget.CardView;
import android.widget.Toast;

public class exciseFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CardView listenerExcise;
    private CardView wordApply;
    private CardView listensingWrite;

    private Activity mActivity;

    public exciseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment exciseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static exciseFragment newInstance(String param1, String param2) {
        exciseFragment fragment = new exciseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_excise, container, false);
        init(view);
        return view;
    }
    public void init(View view){
        listenerExcise=view.findViewById(R.id.listener_excise);
        wordApply=view.findViewById(R.id.word_apply);
        listensingWrite=view.findViewById(R.id.listensing_write);

        listenerExcise.setOnClickListener(this);
        wordApply.setOnClickListener(this);
        listensingWrite.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.listener_excise:
                Log.d("exciseFragment","listener_excise ");
                Intent intent=new Intent(mActivity, ExciseActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("type",1);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.word_apply:
                toast("词汇运用功能尚未完善");
                break;
            case R.id.listensing_write:
                toast("听写功能尚未完善");
                break;
        }
    }
    public void toast(String msg){
        Toast.makeText(mActivity,msg,Toast.LENGTH_SHORT).show();
    }
}
