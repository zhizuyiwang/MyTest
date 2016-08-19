package com.hsf.canvas2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.hsf.canvas2.R;
import com.hsf.canvas2.view.DrawView;

public class DrawActivity extends AppCompatActivity {
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        ll = (LinearLayout) findViewById(R.id.ll);
        View v = new DrawView(this);
        v.setMinimumWidth(500);
        v.setMinimumHeight(600);
        ll.addView(v);

    }
}
