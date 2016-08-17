package com.hsf.mypullToRefresh.activity;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.hsf.mypullToRefresh.R;
import com.hsf.mypullToRefresh.view.MeiTuanListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MeiTuanRefreshActivity extends AppCompatActivity {
    private MeiTuanListView listView;
    private ArrayList<String> date;
    private ArrayAdapter<String> adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            listView.setOnRefreshComplete();
            adapter.notifyDataSetChanged();
            listView.setSelection(0);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mei_tuan_reflesh);

        listView = (MeiTuanListView) findViewById(R.id.listview);
        String[] data1 = new String[]{"hello world","hello world","hello world","hello world",
                "hello world","hello world","hello world","hello world","hello world",
                "hello world","hello world","hello world","hello world","hello world",};
        date = new ArrayList<String>(Arrays.asList(data1));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,date);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(new MeiTuanListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(){
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        date.add(0,"new data");
                        handler.sendEmptyMessage(0);
                    }
                }.start();
            }
        });


    }
}
