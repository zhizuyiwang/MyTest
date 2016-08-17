package com.hsf.mypullToRefresh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.hsf.mypullToRefresh.R;
import com.hsf.mypullToRefresh.view.AutoHomeFirstView;

public class SeekBarActivity2 extends AppCompatActivity {
    private SeekBar sb;
    private AutoHomeFirstView mAutoHomeFirstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar2);
        mAutoHomeFirstView = (AutoHomeFirstView) findViewById(R.id.firstView);
        sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float currentProgress = (float) sb.getProgress()/(float) sb.getMax();
                mAutoHomeFirstView.setProgress(currentProgress);
                mAutoHomeFirstView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
