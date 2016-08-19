package com.hsf.mypullToRefresh.activity;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.hsf.mypullToRefresh.R;
import com.hsf.mypullToRefresh.view.AutoHomeListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoHomeRefreshActivity extends AppCompatActivity {
    private AutoHomeListView listView;
    private  List<String> data;
    private ArrayAdapter<String> adapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            listView.setRefreshComplete();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_home_refresh);
        listView = (AutoHomeListView) findViewById(R.id.listView);
        String[] data1 = new String[]{"hello World","hello World","hello World","hello World","hello World"};
        data = new ArrayList<String>(Arrays.asList(data1));
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(new AutoHomeListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(){
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        data.add(0,"new data");
                        handler.sendEmptyMessage(100);

                    }
                }.start();
            }
        });
    }
}
