package com.hsf.tab.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsf.tab.R;

/**
 * Created by Administrator on 2016/8/2.
 */
public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_activity,container,false);
        initView();
        return view;
    }

    private void initView() {

    }
}
