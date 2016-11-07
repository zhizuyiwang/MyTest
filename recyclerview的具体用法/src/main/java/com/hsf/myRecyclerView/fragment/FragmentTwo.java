package com.hsf.myRecyclerView.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsf.myRecyclerView.R;

/**
 * Created by Administrator on 2016/10/13.
 */

public class FragmentTwo extends Fragment{
    private static FragmentTwo instance = null;
    public static FragmentTwo newInstance(){
        if(instance==null){
            instance = new FragmentTwo();
        }
        return instance;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout,container,false);
        return view;
    }
}
