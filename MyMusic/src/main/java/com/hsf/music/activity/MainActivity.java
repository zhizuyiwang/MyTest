package com.hsf.music.activity;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hsf.music.R;

import java.io.File;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Runnable{
    private TextView status;
    private Button start;
    private Button pause;
    private Button stop;
    private Button next;
    private Button previous;
    private SeekBar progress;
    private LinearLayout ll;
    private Button change;
    private boolean isrun = false;//是否正在播放,默认是没有播放
    private boolean isPause;//是否暂停
    private boolean isAuto = false;
    private MediaPlayer player;//音乐播放器对象
    private File file;
    private int[] musicList;//raw目录下的音乐资源
    private String [] musicName;//对应音乐的名称
    private String[] theme;//每一个音乐对应于一个主题，此主题可以是文字、图片的组合
    private String[] color;//对应于颜色
    private int currentPosition ;//当前歌曲的下标
    private final static int LOOP_MODEL = 1;//循环模式
    private final static int SEQUENCE_MODEL = 2;//顺序模式
    private final static int RANDOM_MODEL = 3;//随机模式
    private int playModel = 3;//播放模式，默认是顺序模式
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * requestWindowFeature(Window.FEATURE_NO_TITLE);这句失效了。

         解决方法有两种
         1、将AppCompatActivity改为Activity，此时 requestWindowFeature(Window.FEATURE_NO_TITLE);是有效的
         2、在onCreate()方法中加入如下代码：
         if (getSupportActionBar() != null){
         getSupportActionBar().hide();
         }
         现在就可以隐藏标题栏了。
        * */
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        playMusic(currentPosition);
        showTheme(currentPosition);
    }

    //每一个下标都代表一个不同的fragment
    private void showTheme(int currentPosition) {
        if(currentPosition==0){


        }
        if(currentPosition==1){

        }
        if(currentPosition==2){

        }
        if(currentPosition==3){

        }
        if(currentPosition==4){

        }
        if(currentPosition==5){

        }
        if(currentPosition==6){

        }
        if(currentPosition==7){

        }
        if(currentPosition==8){

        }
        if(currentPosition==9){

        }
        if(currentPosition==10){

        }
        if(currentPosition==11){

        }
        if(currentPosition==12){

        }
        if(currentPosition==13){

        }
        if(currentPosition==14){

        }





    }

    private void initData() {
        musicList = new int[]{R.raw.music1,R.raw.music2,R.raw.music3,R.raw.music4,R.raw.music5,
        R.raw.music6,R.raw.music7,R.raw.music8,R.raw.music9,R.raw.music10,R.raw.music11,
        R.raw.music12,R.raw.music13,R.raw.music14,R.raw.music15};
        musicName = new String[]{

        };
        random = new Random();
        currentPosition = random.nextInt(15);

    }

    private void initView() {
        status = (TextView) findViewById(R.id.tv_status);
        status.setText("");
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
        start.setVisibility(View.GONE);
        pause =(Button) findViewById(R.id.pause);
        pause.setOnClickListener(this);
        pause.setVisibility(View.GONE);
        pause.setEnabled(false);
        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(this);
        stop.setVisibility(View.GONE);
        stop.setEnabled(false);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
        next.setVisibility(View.GONE);
        previous = (Button) findViewById(R.id.previous);
        previous.setVisibility(View.GONE);
        previous.setOnClickListener(this);
        progress = (SeekBar) findViewById(R.id.progress);
        ll = (LinearLayout) findViewById(R.id.ll);
        ll.setBackgroundResource(R.color.colorAccent);
        change = (Button) findViewById(R.id.change);
        change.setOnClickListener(this);
        progress.setVisibility(View.GONE);
        progress.setOnClickListener(this);
        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(!isAuto && isrun){

                    player.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                isAuto = false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                isAuto = true;
            }
        });

        new Thread(this).start();


        player = new MediaPlayer();

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start ://开始
                //startPlay();

                playMusic(currentPosition);

                break;
            case R.id.pause://对暂停、继续按钮操作
                pausePlay();

                break;
            case R.id.stop://停止播放
                stopPlay();

                break;
            case R.id.previous://上一首

                previousPlay();

                break;
            case R.id.next://下一首

                nextPlay();
                break;
            case R.id.change://切换主题
                currentPosition = random.nextInt(15);
                showTheme(currentPosition);//展示主题
                playMusic(currentPosition);//播放音乐
                break;
        }

    }

    private void startPlay() {
        play();
        if(isPause){
            pause.setText("暂停");
            isPause = false;
        }
        start.setEnabled(false);
        pause.setEnabled(true);
        stop.setEnabled(true);
    }

    private void pausePlay() {
        if(player.isPlaying() && !isPause){
            player.pause();
            isPause = true;
            isAuto = false;
            pause.setText("继续");
            start.setEnabled(false);
        }else{
            player.start();
            isPause = false;
            isAuto = true;
            pause.setText("暂停");
            start.setEnabled(false);
        }
    }

    private void stopPlay() {//停止播放使音乐置为开头
        progress.setProgress(0);
        isrun = false;
        isAuto = false;
        if(player.isPlaying()){

            player.stop();
        }
        pause.setText("暂停");

        pause.setEnabled(false);
        stop.setEnabled(false);
        start.setEnabled(true);
    }

    private void nextPlay() {
        stopPlay();
        start.setEnabled(true);
        pause.setEnabled(true);
        stop.setEnabled(true);

        if(playModel == RANDOM_MODEL){
            currentPosition = random.nextInt(15);
        }else if(playModel == SEQUENCE_MODEL || playModel == LOOP_MODEL){
            currentPosition++;
            if(currentPosition > musicList.length-1){
                currentPosition = 0;
            }
        }

        playMusic(currentPosition);
        showTheme(currentPosition);

    }

    private void previousPlay() {
        stopPlay();
        start.setEnabled(true);
        pause.setEnabled(true);
        stop.setEnabled(true);
        if(playModel == RANDOM_MODEL){
            currentPosition = random.nextInt(15);//取值在0到14之间
        }else if(playModel == SEQUENCE_MODEL || playModel == LOOP_MODEL){
            currentPosition--;
            if(currentPosition < 0){
                currentPosition = 0;
            }
        }

        playMusic(currentPosition);
    }

    /**
     * 播放应用资源音乐的核心代码
     */
    private void playMusic(int currentPosition) {
            if(isPause){
                pause.setText("暂停");
                isPause = false;
            }
            start.setEnabled(false);
            pause.setEnabled(true);
            stop.setEnabled(true);
            player.reset();
            player = MediaPlayer.create(this,musicList[currentPosition]);
            status.setText("正在播放的歌曲：");
            progress.setMax(player.getDuration());//先设置进度条的最大值
            progress.setProgress(0);//每换一首新音乐，都把进度条设置为0
            isAuto = true;
            isrun = true;
            //给mediaPlayer添加播放完毕的监听器
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //在这里判断是什么播放模式，若是循环模式则重新播放，若是随机模式，则有Ransom类
                    //若是顺序模式，则next播放，注意到最后一首时把当前位置置为初始值
                    if(playModel == LOOP_MODEL){
                        //循环播放
                        loopPlayMusic();
                    }else{

                        nextPlay();
                    }

                }
            });


            player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    Toast.makeText(MainActivity.this,"播放出出现错误,重新播放",Toast.LENGTH_SHORT).show();
                    player.reset();
                    return true;
                }
            });
            player.start();
    }

    /**
     * 循环播放音乐
     */
    private void loopPlayMusic() {
        player.setLooping(true);
        player.start();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 此线程的主要作用是为了让进度条和音乐播放进度同步，注意，在mediaplayer.stop()，后不能再给mediaplayer添加进度
     */
    @Override
    public void run() {
        while(true){
            if(isAuto){
                int n = player.getCurrentPosition();
                Message msg = new Message();
                msg.what = n;
                handler.sendMessage(msg);
            }
            SystemClock.sleep(100);
        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress.setProgress(msg.what);
            progress.invalidate();
        }
    };
}
