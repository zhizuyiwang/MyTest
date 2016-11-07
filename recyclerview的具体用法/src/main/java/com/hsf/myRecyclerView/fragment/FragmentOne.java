package com.hsf.myRecyclerView.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsf.myRecyclerView.R;
import com.hsf.myRecyclerView.adapter.MyToolBarHintadapter;

import java.util.ArrayList;
/**
 * Created by Administrator on 2016/10/13.
 */

public class FragmentOne extends Fragment{
    private static FragmentOne instance = null;
    private MyToolBarHintadapter adapter;
    public static FragmentOne newInstance(){
        if(instance==null){
            instance = new FragmentOne();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(i);
        }
        adapter = new MyToolBarHintadapter(this.getContext(),data);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
