package com.hsf.myListViewLink.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hsf.myListViewLink.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                startActivity(new Intent(this,MyActivity1.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this,MyActivity2.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(this,MyActivity3.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(this,MyActivity4.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(this,MyActivity5.class));
                break;
            case R.id.btn6:
                startActivity(new Intent(this,MyActivity6.class));
                break;
            case R.id.btn7:
                startActivity(new Intent(this,MyActivity7.class));
                break;
        }
    }
}
