package com.hsf.mypullToRefresh.activity;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.hsf.mypullToRefresh.R;
import com.hsf.mypullToRefresh.view.JdFirstView;
import com.hsf.mypullToRefresh.view.JdSecondRefreshView;

public class SeekBarActivity3 extends AppCompatActivity {
    private SeekBar sb;
    private JdFirstView firstView;
    private JdSecondRefreshView secondView;
    private AnimationDrawable anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar3);
        sb = (SeekBar) findViewById(R.id.seekBar);
        firstView = (JdFirstView) findViewById(R.id.first_view);
        secondView = (JdSecondRefreshView) findViewById(R.id.second_view);
        secondView.setBackgroundResource(R.drawable.jd_second_refresh);
        anim = (AnimationDrawable) secondView.getBackground();

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float currentProgress = (float) sb.getProgress()/sb.getMax();
                Log.e("TAG",currentProgress+"");
                firstView.setProgress(currentProgress);
                firstView.postInvalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void start(View v){
        anim.start();

    }
    public void stop(View v){
        anim.stop();

    }
}
