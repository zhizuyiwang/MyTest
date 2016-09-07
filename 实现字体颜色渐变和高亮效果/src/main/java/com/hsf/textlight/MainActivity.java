package com.hsf.textlight;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private GradientHorizontalTextView myTv1;
    private GradientHorizontalTextView myTv2;
    private LinearGradient mShaper;
    private int mWidth;
    private int mHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        myTv1 = (GradientHorizontalTextView) findViewById(R.id.myTv1);
        myTv2 = (GradientHorizontalTextView) findViewById(R.id.myTv2);
        myTv1.setStyle(true);
        myTv2.setStyle(false);
        tv1.post(new Runnable() {
            @Override
            public void run() {
                mWidth = tv1.getWidth();
                //水平渲染
                LinearGradient mShaper = new LinearGradient(mWidth/4, 0 , mWidth, 0, Color.RED,Color.BLUE, Shader.TileMode.CLAMP);
                tv1.getPaint().setShader(mShaper);
            }
        });
        tv2.post(new Runnable() {
            @Override
            public void run() {
                mHeight = tv2.getHeight();
                //垂直渐变渲染
                LinearGradient mShaper1 = new LinearGradient(0,mHeight/4,0,mHeight,Color.RED,Color.BLUE,Shader.TileMode.CLAMP);
                tv2.getPaint().setShader(mShaper1);
            }
        });

    }
}
