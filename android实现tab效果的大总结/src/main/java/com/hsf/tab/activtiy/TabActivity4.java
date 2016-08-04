package com.hsf.tab.activtiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hsf.tab.R;

/**
 * TabPageIndicator+ViewPage+FragmentPagerAdapter来实现
 */
public class TabActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_tab4);
    }
}
