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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv1:
                Intent intent1 = new Intent(MainActivity.this,seekBarActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
