package com.hsf.myRecyclerView.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hsf.myRecyclerView.R;
import com.hsf.myRecyclerView.adapter.Myadapter;

import java.util.ArrayList;
import java.util.List;

public class FloatingButtonActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_floating_button2);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"我是SnackBar",Snackbar.LENGTH_SHORT).setAction("action",null).show();
            }
        });

        ArrayList<String> list=new ArrayList<>();
        int i=0;
        while (i<30){
            list.add("第"+i+"项");
            System.out.print(""+i);
            i++;
        }

        recyclerView.setAdapter(new Myadapter(this,list));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
