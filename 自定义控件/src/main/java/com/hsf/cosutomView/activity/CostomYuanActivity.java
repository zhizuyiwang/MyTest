package com.hsf.cosutomView.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hsf.cosutomView.R;
import com.hsf.cosutomView.view.CustomeView;

public class CostomYuanActivity extends AppCompatActivity {
    private CustomeView customeView;
    private float radius;
    private Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            customeView.setRadius(radius);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costom_yuan2);
        customeView = (CustomeView) findViewById(R.id.customView);
        /*new Thread(){
            @Override
            public void run() {
                while (true){
                    if(radius>200){
                        radius=0;
                    }
                    radius+=10;
                    handle.sendEmptyMessage(100);
                    SystemClock.sleep(100);
                }
            }
        }.start();*/
        new Thread(customeView).start();


    }
}
