package com.hsf.canvas2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.hsf.canvas2.view.DrawView;
import com.hsf.canvas2.view.MyGraphic;

public class MainActivity extends AppCompatActivity {
    private MyGraphic myGraphic;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this.myGraphic=new MyGraphic(this);                   //创建自定义View对象
        //setContentView(myGraphic);
        ll = (LinearLayout) findViewById(R.id.ll);
        View v = new DrawView(this);
        v.setMinimumWidth(500);
        v.setMinimumHeight(600);
        ll.addView(v);
    }
}
