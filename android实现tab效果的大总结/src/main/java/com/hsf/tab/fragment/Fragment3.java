package com.hsf.tab.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsf.tab.R;

/**
 * Created by Administrator on 2016/8/2.
 */
public class Fragment3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3_activity,container);
        initView();
        return view;
    }

    private void initView() {
    }
}
