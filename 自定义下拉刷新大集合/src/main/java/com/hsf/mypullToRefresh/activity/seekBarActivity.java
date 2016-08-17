package com.hsf.mypullToRefresh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hsf.mypullToRefresh.R;
import com.hsf.mypullToRefresh.view.MeiTuanRefreshFirstView;

public class seekBarActivity extends AppCompatActivity {
    private SeekBar sb;
    private TextView tv;
    private MeiTuanRefreshFirstView meiTuanRefreshFirstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);
        sb = (SeekBar) findViewById(R.id.sb);
        tv = (TextView) findViewById(R.id.tv);
        meiTuanRefreshFirstView = (MeiTuanRefreshFirstView) findViewById(R.id.meituan);
        sb.setMax(20);
        sb.setProgress(0);
        tv.setText(0+"");
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int progress = sb.getProgress();
                tv.setText(progress+"");
                float currentProgress = (float)i/(float) seekBar.getMax();
                meiTuanRefreshFirstView.setProgress(currentProgress);
                meiTuanRefreshFirstView.postInvalidate();
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
