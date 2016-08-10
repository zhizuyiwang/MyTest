package com.hsf.circleprogress;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private RoundProgressBar progressBar1;
    private RoundProgressBar progressBar2;
    private RoundProgressBar progressBar3;
    private RoundProgressBar progressBar4;
    private int currentProgress;
    private static final int STROKE = 0;//空心
    private static final int FILL = 1;//实心

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            progressBar1.setProgress(currentProgress);
            progressBar2.setProgress(currentProgress);
            progressBar3.setProgress(currentProgress);
            progressBar4.setProgress(currentProgress);
            currentProgress++;
            if(currentProgress>=100){
                return;
            }else{
                handler.sendEmptyMessageDelayed(100,2000);
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar1 = (RoundProgressBar) findViewById(R.id.roundProgressBar1);
        progressBar2 = (RoundProgressBar) findViewById(R.id.roundProgressBar2);
        progressBar3 = (RoundProgressBar) findViewById(R.id.roundProgressBar3);
        progressBar4 = (RoundProgressBar) findViewById(R.id.roundProgressBar4);
        currentProgress = 1;
        initProgress();
    }

    private void initProgress() {
        progressBar1.setMax(100);
        progressBar1.setStyle(STROKE);
        progressBar1.setTextIsDisplayable(true);

        progressBar2.setMax(100);
        progressBar2.setRoundWidth(2);
        progressBar2.setStyle(FILL);

        progressBar3.setRoundWidth(5);
        progressBar3.setCricleProgressColor(Color.RED);
        progressBar3.setCricleColor(Color.GREEN);
        progressBar3.setTextSize(40);
        progressBar3.setStyle(STROKE);
        progressBar3.setTextIsDisplayable(true);

        progressBar4.setRoundWidth(5);
        progressBar4.setCricleProgressColor(Color.RED);
        progressBar4.setCricleColor(Color.GREEN);
        progressBar4.setTextSize(40);
        progressBar4.setStyle(FILL);
        progressBar4.setTextIsDisplayable(true);

        handler.sendEmptyMessage(100);
    }
}
