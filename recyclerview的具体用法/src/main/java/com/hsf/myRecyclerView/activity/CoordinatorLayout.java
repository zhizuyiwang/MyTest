package com.hsf.myRecyclerView.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.hsf.myRecyclerView.R;

import static com.hsf.myRecyclerView.R.id.toolBar;

public class CoordinatorLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_out_collapse);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("This is Title");
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
    }
}
