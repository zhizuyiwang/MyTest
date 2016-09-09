package com.hsf.bezierWarp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 桃心线极坐标方程式为：
   x=16×sin3α
   y=13×cosα−5×cos2α−2×cos3α−cos4α
 * Created by Administrator on 2016/9/9.
 */
public class HeartLine extends View {
    private Paint mPaint;
    private int width;
    private int height;
    private int offSetX;
    private int offSetY;

    public HeartLine(Context context) {
        super(context);
        init();
    }

    public HeartLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        offSetX = width/2;
        offSetY = height/2 - 55;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float angle = 10;
        while (angle < 180){
            Point p = getHeartPoint(angle);
            canvas.drawPoint(p.x,p.y,mPaint);
            Log.e("TAG",p.x+"");
            Log.e("TAG",p.y+"");
            angle = angle + 0.02f;
        }


    }
    private Point getHeartPoint(float angle){

        float t = (float) (angle / Math.PI);
        float x = (float) (19.5 * (16 * Math.pow(Math.sin(t), 3)));
        float y = (float) (-20 * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)));
        return new Point(offSetX + (int) x, offSetY + (int) y);
    }
}
