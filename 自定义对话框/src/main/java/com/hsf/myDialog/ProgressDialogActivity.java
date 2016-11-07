package com.hsf.myDialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProgressDialogActivity extends AppCompatActivity {

    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.btn3)
    Button btn3;
    @Bind(R.id.btn4)
    Button btn4;
    @Bind(R.id.btn5)
    Button btn5;
    @Bind(R.id.btn6)
    Button btn6;
    private ProgressDialog pd3;
    private int hasData = 0;
    private int progressStatus = 0;
    final static int MAX_PROGRESS = 100;
    private int[] data = new int[50];
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pd3.setProgress(progressStatus);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,R.id.btn6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                final ProgressDialog pgd1 = ProgressDialog.show(ProgressDialogActivity.this, null, "任务执行中，请稍后",
                        true, false);
                pgd1.setIndeterminate(true);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        SystemClock.sleep(5000);
                        pgd1.dismiss();
                    }
                }.start();
                break;
            case R.id.btn2:
                final ProgressDialog pd2 = new ProgressDialog(this);
                // pd2.setTitle("我是一个进度条");
                pd2.setMessage("正在下载，请稍等");
                // 设置对话框可以用取消按钮关闭
                pd2.setCancelable(false);

               /* pd2.setButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });*/

                // 设置对话框的进度条风格
                pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //设置对话框的进度条是否显示进度
                pd2.setIndeterminate(true);
                //显示控件
                pd2.show();

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {

                        pd2.dismiss();
                    }
                };
                //5秒后关闭
                timer.schedule(task, 5000);

                break;
            case R.id.btn3:
                pd3 = new ProgressDialog(this);
                pd3.setTitle("再看这个进度条 , 任务完成百分比");
                pd3.setMessage("耗时任务的完成百分比");
                //将进度条的完成进度设为0
                progressStatus = 0;
                hasData = 0;
                pd3.setProgress(progressStatus);
                pd3.setMax(MAX_PROGRESS);
                pd3.setCancelable(false);
                pd3.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd3.setIndeterminate(false);
                pd3.show();
                new Thread() {
                    @Override
                    public void run() {
                        while (progressStatus <= MAX_PROGRESS) {
                            SystemClock.sleep(50);
                            progressStatus++;
                            handler.sendEmptyMessage(100);
                        }
                        if (progressStatus > MAX_PROGRESS) {
                            pd3.dismiss();
                        }

                    }
                }.start();
                break;
            case R.id.btn4:

                //在按照5.0以上系统左右俩边留白了，对话框实际宽度大于我们布局宽度
                /* 自定义style，继承于Theme.AppCompat.Dialog，重写俩个属性，其中
                 * android:backgroundDimEnabled：表示页面时候变暗，我们设置false，不变暗
                 * android:windowBackground：表示背景颜色，我们这种为透明，因为我们给布局文件背景设置了圆角，
                 * 如果不设置对话框为透明的话，圆角部分会留白
                * */
                final CustomDialog c = new CustomDialog(this, R.style.CustomDialog);
                c.show();
                Timer timer1 = new Timer();
                TimerTask task1 = new TimerTask() {
                    @Override
                    public void run() {

                        c.dismiss();
                    }
                };
                //5秒后关闭
                timer1.schedule(task1, 5000);
                break;
            case R.id.btn5:
                MyDialog md = new MyDialog(this,R.style.CustomDialog);
                md.show();
                break;
            case R.id.btn6:
                MyProgressDialog mDialog = new MyProgressDialog(this,R.style.CustomDialog);
                mDialog.setMessage("正在下载");
                mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        //cancel(true);
                    }
                });
                mDialog.show();
                mDialog.setMax(100*1024*1024);
                mDialog.setProgress(65*1024*1024);
                break;

        }
    }


}
