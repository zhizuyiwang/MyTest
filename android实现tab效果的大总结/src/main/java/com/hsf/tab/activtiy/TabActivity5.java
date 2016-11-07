package com.hsf.tab.activtiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hsf.tab.R;

public class TabActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_tab5);
    }
}
