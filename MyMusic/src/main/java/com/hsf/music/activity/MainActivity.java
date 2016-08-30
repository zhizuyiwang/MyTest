package com.hsf.music.activity;

import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hsf.music.R;
import com.hsf.music.fragment.Fragment1;
import com.hsf.music.fragment.Fragment10;
import com.hsf.music.fragment.Fragment11;
import com.hsf.music.fragment.Fragment12;
import com.hsf.music.fragment.Fragment13;
import com.hsf.music.fragment.Fragment14;
import com.hsf.music.fragment.Fragment15;
import com.hsf.music.fragment.Fragment2;
import com.hsf.music.fragment.Fragment3;
import com.hsf.music.fragment.Fragment4;
import com.hsf.music.fragment.Fragment5;
import com.hsf.music.fragment.Fragment6;
import com.hsf.music.fragment.Fragment7;
import com.hsf.music.fragment.Fragment8;
import com.hsf.music.fragment.Fragment9;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Runnable{
    private TextView status;
    private Button start;
    private Button pause;
    private Button stop;
    private Button next;
    private Button previous;
    private SeekBar progress;
    private FrameLayout content;
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

    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;
    private Fragment fragment5;
    private Fragment fragment6;
    private Fragment fragment7;
    private Fragment fragment8;
    private Fragment fragment9;
    private Fragment fragment10;
    private Fragment fragment11;
    private Fragment fragment12;
    private Fragment fragment13;
    private Fragment fragment14;
    private Fragment fragment15;

    private FragmentManager mFragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * requestWindowFeature(Window.FEATURE_NO_TITLE);这句失效了。

         解决方法有
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



    //每一个下标都代表一个不同的fragment,在这里切换主题，也就是切换Fragment有两种方式，一是用replace()来替换
    //另一个就是用add(),hide(),show(),来添加显示和隐藏Fragment,replace（）的用法相当于先remove掉在add()，
    //是remove和add的结合体，在这里我们用replace（）方法来切换Fragment
    private void showTheme(int currentPosition1) {

        transaction = mFragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (currentPosition1){
            case 0:
                if(fragment1==null){
                    fragment1 = new Fragment1();
                    transaction.add(R.id.content,fragment1);
                    Log.e("TAG","添加第一个Fragment");

                }else{

                    transaction.show(fragment1);
                    Log.e("TAG","显示第一个Fragment");
                }
                break;
            case 1:
                if(fragment2==null){
                    fragment2 = new Fragment2();
                    transaction.add(R.id.content,fragment2);
                    Log.e("TAG","添加第二个Fragment");
                }else{

                    transaction.show(fragment2);
                    Log.e("TAG","显示第二个Fragment");
                }
                break;
            case 2:
                if(fragment3==null){
                    fragment3 = new Fragment3();
                    transaction.add(R.id.content,fragment3);
                    Log.e("TAG","添加第三个Fragment");
                }else{
                    transaction.show(fragment3);
                    Log.e("TAG","显示第三个Fragment");
                }

                break;
            case 3:
                if(fragment4==null){
                    fragment4 = new Fragment4();
                    transaction.add(R.id.content,fragment4);
                    Log.e("TAG","添加第四个Fragment");
                }else{
                    transaction.show(fragment4);
                    Log.e("TAG","显示第四个Fragment");
                }

                break;
            case 4:
                if(fragment5==null){
                    fragment5 = new Fragment5();
                    transaction.add(R.id.content,fragment5);
                }else{
                    transaction.show(fragment5);
                }
                break;
            case 5:
                if(fragment6==null){
                    fragment6 = new Fragment6();
                    transaction.add(R.id.content,fragment6);
                }else{
                    transaction.show(fragment6);
                }
                break;
            case 6:
                if(fragment7==null){
                    fragment7 = new Fragment7();
                    transaction.add(R.id.content,fragment7);
                }else{
                    transaction.show(fragment7);
                }
                break;
            case 7:
                if(fragment8==null){
                    fragment8 = new Fragment8();
                    transaction.add(R.id.content,fragment8);
                }else{
                    transaction.show(fragment8);
                }
                break;
            case 8:
                if(fragment9==null){
                    fragment9 = new Fragment9();
                    transaction.add(R.id.content,fragment9);
                }else{
                    transaction.show(fragment9);
                }
                break;
            case 9:
                if(fragment10==null){
                    fragment10 = new Fragment10();
                    transaction.add(R.id.content,fragment10);
                }else{
                    transaction.show(fragment10);
                }
                break;
            case 10:
                if(fragment11==null){
                    fragment11 = new Fragment11();
                    transaction.add(R.id.content,fragment11);
                }else{
                    transaction.show(fragment11);
                }
                break;
            case 11:
                if(fragment12==null){
                    fragment12 = new Fragment12();
                    transaction.add(R.id.content,fragment12);
                }else{
                    transaction.show(fragment12);
                }
                break;
            case 12:
                if(fragment13==null){
                    fragment13 = new Fragment13();
                    transaction.add(R.id.content,fragment13);
                }else{
                    transaction.show(fragment13);
                }
                break;
            case 13:
                if(fragment14==null){
                    fragment14 = new Fragment14();
                    transaction.add(R.id.content,fragment14);
                }else{
                    transaction.show(fragment14);
                }
                break;
            case 14:
                if(fragment15==null){
                    fragment15 = new Fragment15();
                    transaction.add(R.id.content,fragment15);
                }else{
                    transaction.show(fragment15);
                }
                break;

        }
        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction) {
        if(fragment1!=null){
            transaction.hide(fragment1);
        }
        if(fragment2!=null){
            transaction.hide(fragment2);

        }
        if(fragment3!=null){
            transaction.hide(fragment3);
        }
        if(fragment4!=null){
            transaction.hide(fragment4);

        }
        if(fragment5!=null){
            transaction.hide(fragment5);
        }
        if(fragment6!=null){
            transaction.hide(fragment6);

        }
        if(fragment7!=null){
            transaction.hide(fragment7);
        }
        if(fragment8!=null){
            transaction.hide(fragment8);

        }
        if(fragment9!=null){
            transaction.hide(fragment9);
        }
        if(fragment10!=null){
            transaction.hide(fragment10);

        }
        if(fragment11!=null){
            transaction.hide(fragment11);
        }
        if(fragment12!=null){
            transaction.hide(fragment12);

        }
        if(fragment13!=null){
            transaction.hide(fragment13);
        }
        if(fragment14!=null){
            transaction.hide(fragment14);

        }
        if(fragment15!=null){
            transaction.hide(fragment15);
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
        mFragmentManager = getSupportFragmentManager();
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
        content = (FrameLayout) findViewById(R.id.content);
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
                //change.setEnabled(false);
                nextPlay();//播放音乐
               /* new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        SystemClock.sleep(1500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                change.setEnabled(true);
                            }
                        });
                    }
                }.start();*/
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
    private void nextPlay1() {
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

                        nextPlay1();
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
