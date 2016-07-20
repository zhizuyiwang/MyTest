package com.hsf.music.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hsf.music.R;
import com.hsf.music.service.PlayerService;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private TextView tv;
    private Button btn;
    private Timer timer;
    private TimerTask task;
    private int statue = 1;
    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private boolean flag = false;//是否跳到主界面的判断标志

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if(!flag){
                        Intent main = new Intent(SplashActivity.this,MainActivity.class);
                        SplashActivity.this.startActivity(main);
                        if(timer!=null && task!=null){
                            timer.cancel();
                            task.cancel();
                        }
                        finish();
                    }
                    break;
                case 2:
                    //设置字体

                    text1 = "<font color=\"#ff00ff\">欢迎来到</font><br>";
                    text2 = "<font color=\"#00ffff\">音乐天堂</font><br>";
                    text3 = "<font color=\"#ffff00\">在音乐中</font><br>";
                    text4 = "<font color=\"#00ff00\">自由翱翔</font><br>";
                    tv.setText(Html.fromHtml(text1+text2+text3+text4));
                    break;
                case 3:
                    //设置字体
                    text1 = "<font color=\"#ffff00\">欢迎来到</font><br>";
                    text2 = "<font color=\"#00ff00\">音乐天堂</font><br>";
                    text3 = "<font color=\"#ff00ff\">在音乐中</font><br>";
                    text4 = "<font color=\"#00ffff\">自由翱翔</font><br>";
                    tv.setText(Html.fromHtml(text1+text2+text3+text4));
                    break;
                case 4:
                    //设置字体
                    text1 = "<font color=\"#00ffff\">欢迎来到</font><br>";
                    text2 = "<font color=\"#ff00ff\">音乐天堂</font><br>";
                    text3 = "<font color=\"#00ff00\">在音乐中</font><br>";
                    text4 = "<font color=\"#ffff00\">自由翱翔</font><br>";
                    tv.setText(Html.fromHtml(text1+text2+text3+text4));
                    break;
                case 5:
                    //设置字体
                    text1 = "<font color=\"#FF0097\">欢迎来到</font><br>";
                    text2 = "<font color=\"#00ff00\">音乐天堂</font><br>";
                    text3 = "<font color=\"#00ffff\">在音乐中</font><br>";
                    text4 = "<font color=\"#ff00ff\">自由翱翔</font><br>";
                    tv.setText(Html.fromHtml(text1+text2+text3+text4));
                    break;
                case 6:
                    //设置字体
                    text1 = "<font color=\"#DFFF00\">欢迎来到</font><br>";
                    text2 = "<font color=\"#00ff00\">音乐天堂</font><br>";
                    text3 = "<font color=\"#ffff00\">在音乐中</font><br>";
                    text4 = "<font color=\"#00ffff\">自由翱翔</font><br>";
                    tv.setText(Html.fromHtml(text1+text2+text3+text4));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏，即应用程序的名字
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏、电量栏、信号等
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initDate();
        initView();
        timer.schedule(task,0,1000);

        handler.sendEmptyMessageDelayed(1,5000);

        //在闪屏界面开启服务，先将服务启动起来，然后再进行绑定和解除绑定；否则解除绑定服务后，服务会停止
        Intent playerService  = new Intent(this, PlayerService.class);
        startService(playerService);

    }

    private void initDate() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                statue++;
                handler.sendEmptyMessage(statue);
            }
        };
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv_status);
        //设置字体
        String text1 = "<font color=\"#ffff00\">欢迎来到</font><br>";
        String text2 = "<font color=\"#00ff00\">音乐天堂</font><br>";
        String text3 = "<font color=\"#ff00ff\">在音乐中</font><br>";
        String text4 = "<font color=\"#00ffff\">自由翱翔</font><br>";
        tv.setText(Html.fromHtml(text1+text2+text3+text4));
        //在xml设置阴影
        //android:shadowColor="#000000"
        //android:shadowDx="15.0"
        //android:shadowDy="5.0"
        //android:shadowRadius=“5.0"

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                Intent main = new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(main);
                if(timer!=null && task!=null){
                    timer.cancel();
                    task.cancel();
                }
                finish();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
        if(timer!=null && task!=null){
            timer.cancel();
            task.cancel();
        }
    }
}
