package com.hsf.tab.activtiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hsf.tab.R;

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
                /*Intent activity1 = new Intent(this,TabActivity1.class);
                startActivity(activity1);*/
                break;
            case R.id.tv2:
                Intent activity2 = new Intent(this,TabActivity2.class);
                startActivity(activity2);
                break;
            case R.id.tv3:
                Intent activity3 = new Intent(this,TabActivity3.class);
                startActivity(activity3);
                break;
            case R.id.tv4:
                Intent activity4 = new Intent(this,TabActivity4.class);
                startActivity(activity4);
        }

    }
}
