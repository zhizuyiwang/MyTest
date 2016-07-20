package com.hsf.music.activity;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hsf.music.R;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView status;
    private Button start;
    private Button pause;
    private Button stop;
    private boolean isPause;//是否暂停
    private MediaPlayer player;//音乐播放器对象
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        status = (TextView) findViewById(R.id.tv_status);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
        pause =(Button) findViewById(R.id.pause);
        pause.setOnClickListener(this);
        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(this);
        player = MediaPlayer.create(this, R.raw.my);

        //给mediaPlayer添加播放完毕的监听器
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });

		/*if(!isFileExist()){
			start.setEnabled(false);
		}*/

    }

    /**判断音乐文件是否存在
     * @return
     */
    private boolean isFileExist() {
        // 判断文件是否存在

        file = new File(Environment.getExternalStorageDirectory()
                + File.separator + "myMP32.mp3");
        if (file.exists()) {
            // mediaPlayer=new MediaPlayer();
            player = MediaPlayer.create(this, R.raw.my);
            try {

                // mediaPlayer.setDataSource(file.getAbsolutePath());
                // mediaPlayer.prepare();//预加载音频
                // mediaPlayer.start();//播放音乐
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "file exist", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(this, "file don't exist", Toast.LENGTH_LONG).show();
        }
        return false;

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("TAG", "wobepp");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start :
                play();
                if(isPause){
                    pause.setText("暂停");
                    isPause = false;
                }
                start.setEnabled(false);
                pause.setEnabled(true);
                stop.setEnabled(true);

                break;
            case R.id.pause://对暂停、继续按钮操作
                if(player.isPlaying() && !isPause){
                    player.pause();
                    isPause = true;
                    pause.setText("继续");
                    status.setText("暂停中。。。");
                    start.setEnabled(true);
                }else{
                    player.start();
                    isPause = false;
                    pause.setText("暂停");
                    status.setText("播放中");
                    start.setEnabled(false);
                }
                break;
            case R.id.stop:
                player.stop();
                status.setText("停止播放");
                pause.setText("暂停");
                pause.setEnabled(false);
                stop.setEnabled(false);
                start.setEnabled(true);
                break;
        }

    }
    @Override
    protected void onDestroy() {
        if(player.isPlaying()){
            player.stop();
        }
        player.release();
        super.onDestroy();

    }

    private void play() {
        try {
            player.reset();// 重新设置要播放的音乐
            //player.setDataSource(file.getAbsolutePath());
            //player.prepare();// 预加载音频
            player = MediaPlayer.create(this, R.raw.my);
            player.start();
            status.setText("正在播放音乐");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
