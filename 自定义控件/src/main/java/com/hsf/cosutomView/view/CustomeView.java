package com.hsf.cosutomView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/8/11.
 */
public class CustomeView extends View implements Runnable{
    private Paint mPaint;
    private float radius = 30;
    public CustomeView(Context context) {
        super(context);
        initPaint();
    }



    public CustomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode == MeasureSpec.AT_MOST){
            Log.e("TAG","AT_MOST");
        }else if(mode == MeasureSpec.EXACTLY){
            Log.e("TAG","EXACTLY");
        }
        Log.e("哈哈哈","先测量");
        Log.e("宽1",MeasureSpec.getSize(widthMeasureSpec)+"");
        Log.e("高2",MeasureSpec.getSize(heightMeasureSpec)+"");
        Log.e("宽",getWidth()+"");
        Log.e("高",getHeight()+"");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("哈哈哈","先绘制");
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,mPaint);

    }
    public synchronized void setRadius(float radius){
        this.radius = radius;
        invalidate();//重绘
    }
    public float getRadius(){
        return radius;
    }

    @Override
    public void run() {
        while(true){
            if(radius>200){
                radius = 0;
            }
            radius+=10;
            postInvalidate();
            SystemClock.sleep(100);
        }
    }
}
