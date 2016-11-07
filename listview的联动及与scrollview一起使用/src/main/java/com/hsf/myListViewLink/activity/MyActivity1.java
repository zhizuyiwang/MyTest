package com.hsf.myListViewLink.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hsf.myListViewLink.R;

import java.util.ArrayList;
import java.util.List;

public class MyActivity1 extends AppCompatActivity {

    private ListView mListView1;
    private ListView mListView2;

    private List<String> stringList1 =new ArrayList<>();
    private List<String> stringList2 =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my1);

        initData();
        initView();

        mListView1.setAdapter(new ArrayAdapter<String>(this,R.layout.item_listview_1,R.id.textview1,stringList1));
        mListView2.setAdapter(new ArrayAdapter<String>(this,R.layout.item_listview_2,R.id.textview2,stringList2));

    }

    private void initData() {
        for(int x=0 ; x<26 ; x++ ){
            stringList1.add("左边"+x+"→");
        }
        for(int x=0 ; x<26 ; x++ ){
            stringList2.add("←右边"+x);
        }
    }

    private void initView() {
        mListView1= (ListView) findViewById(R.id.listview1);
        mListView2= (ListView) findViewById(R.id.listview2);
    }
}
