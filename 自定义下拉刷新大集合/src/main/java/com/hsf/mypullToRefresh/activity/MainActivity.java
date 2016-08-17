package com.hsf.mypullToRefresh.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hsf.mypullToRefresh.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(this);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setOnClickListener(this);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setOnClickListener(this);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv1:
                Intent intent1 = new Intent(MainActivity.this,seekBarActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv2:
                Intent intent2 = new Intent(MainActivity.this,MeiTuanRefreshActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv3:
                Intent intent3 = new Intent(MainActivity.this,SeekBarActivity2.class);
                startActivity(intent3);
                break;
            case R.id.tv4:
                Intent intent4 = new Intent(MainActivity.this,AutoHomeRefreshActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
