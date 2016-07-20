package com.hsf.propertyAnimator.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hsf.propertyAnimator.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn_between);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn_value);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn_value1);
        btn3.setOnClickListener(this);
        btn4 = (Button) findViewById(R.id.btn_value2);
        btn4.setOnClickListener(this);
        btn5 = (Button) findViewById(R.id.btn_value3);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_between:
                Intent betweent = new Intent(this,BetweenAnimatorActivity.class);
                startActivity(betweent);
                break;
            case R.id.btn_value:
                Intent value = new Intent(this,PropertyAnimatorActivity.class);
                startActivity(value);
                break;
            case R.id.btn_value1:
                Intent value1 = new Intent(this,ProperTyAnimatorActivity1.class);
                startActivity(value1);
                break;
            case R.id.btn_value2:
                Intent value2 = new Intent(this,ViewPropertyAnimatorActivity.class);
                startActivity(value2);
                break;
            case R.id.btn_value3:
                Intent value3 = new Intent(this,PropertyAnimatorActivity2.class);
                startActivity(value3);
                break;
        }
    }
}
