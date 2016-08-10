package com.hsf.canvas2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/8/8.
 */
public class MyGraphic extends View implements Runnable{
    private Paint paint;
    public MyGraphic(Context context) {
        super(context);
        paint=new Paint();                              //构建对象
        //new Thread(this).start();                           //开启线程
    }
    public MyGraphic(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        //new Thread(this).start();
    }

    public MyGraphic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        //new Thread(this).start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);                           //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                        //设置画笔颜色
        paint.setTextSize((float) 30.0);                    //设置字体大小
        canvas.drawColor(Color.WHITE);                      //白色背景

        canvas.clipRect(50, 50, 400, 700);                  //设置裁剪区
        canvas.save();                                  //锁定画布
        canvas.rotate(45, 230, 250);                        //旋转45
        paint.setColor(Color.BLUE);                     //设置画笔颜色
        canvas.drawText("Hello Android!", 130, 250, paint); //绘制字符串
        canvas.restore();                                   //解除锁定

        paint.setColor(Color.RED);                          //设置画笔颜色
        canvas.drawText("Hello Android!", 130, 250, paint); //绘制字符串
        RectF oval=new RectF();                         //RectF对象
        oval.left=150;                                  //左边
        oval.top=500;                                       //上边
        oval.right=350;                                 //右边
        oval.bottom=600;                                    //下边
        canvas.drawOval(oval, paint);                       //绘制椭圆
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        postInvalidate();//更新界面
    }
}
