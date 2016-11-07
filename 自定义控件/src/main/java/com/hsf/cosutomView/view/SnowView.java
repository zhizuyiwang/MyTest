package com.hsf.cosutomView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hsf.cosutomView.modle.SnowFlake;

/**
 * 雪花视图, DELAY时间重绘, 绘制NUM_SNOWFLAKES个雪花
 * Created by Administrator on 2016/10/28.
 */

public class SnowView extends View{
    private static final int NUM_SNOWFLAKES = 150; // 雪花数量
    private static final int DELAY = 5; // 延迟
    private SnowFlake[] mSnowFlakes; // 雪花
    public SnowView(Context context) {
        super(context);
    }

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public SnowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w!=oldw || h!=oldh){
            init(w,h);
        }
    }

    private void init(int width,int height) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); // 抗锯齿
        paint.setColor(Color.WHITE); // 白色雪花
        paint.setStyle(Paint.Style.FILL); // 填充;
        mSnowFlakes = new SnowFlake[NUM_SNOWFLAKES];
        for (int i = 0; i < NUM_SNOWFLAKES; ++i) {
            mSnowFlakes[i] = SnowFlake.createSnowFlake(width, height, paint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(SnowFlake s: mSnowFlakes){
            s.draw(canvas);
        }
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        },DELAY);
    }

}
