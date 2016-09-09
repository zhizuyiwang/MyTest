package com.hsf.ping;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private TextView tv;
    private String pingString;
    private Runtime runtime;
    private int flag;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et_ping);
        tv = (TextView) findViewById(R.id.tv_result);
        runtime = Runtime.getRuntime();



    }
    public void startPing(View view){
        tv.setText("");
        pingString = et.getText().toString().trim();
        if(TextUtils.isEmpty(pingString)){
            Toast.makeText(this,"ping值不能为空",Toast.LENGTH_SHORT).show();
        }
        new Thread(){
            @Override
            public void run() {
                super.run();

                Process p;
                BufferedReader buf;
                String line;
                try {
                    p = runtime.exec("ping -c 5 -w 5000 "+ pingString);
                    buf = new BufferedReader(new InputStreamReader(
                            p.getInputStream()));
                    if ((line = buf.readLine()) != null){
                        flag = 1;
                        text = line;
                        Log.e("TAG","通了");
                    }else{
                        flag = 0;
                        Log.e("TAG","无异常，但没通");

                    }

                } catch (IOException e) {
                    Log.e("TAG","出现异常没通");
                    flag = 0;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(flag==1){
                            tv.setText("ping通了，返回的数据为："+text);
                        }else{
                            tv.setText("没ping通");
                        }
                    }
                });
            }
        }.start();
    }
}
