package com.hsf.canvas2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/8/18.
 */
public class SaveCanvasView extends View {
    private Paint p;
    private int x;
    private int y;
    public SaveCanvasView(Context context) {
        super(context);
        init();
    }

    public SaveCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SaveCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        p = new Paint();
        p.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        x = getMeasuredWidth();
        y = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        canvas.drawCircle(x/2,y/2,x/2,p);
        p.setColor(Color.BLUE);
        //画刻度线
        canvas.drawLine(x/2,y/8,x/2,0,p);
        //在旋转前调用save()方法来保存canvas的状态
        for(int i = 0;i<3;i++){
            canvas.rotate(30,x/2,y/2);
            canvas.drawLine(x/2,y/8,x/2,0,p);
        }
        //画中间的黄线
        //给画笔加粗，换成黄色
        p.setStrokeWidth(10);
        p.setColor(Color.YELLOW);
        //画一条黄色粗线，便于区分
        canvas.drawLine(x / 2, y / 2, x / 2, y / 4, p);

        //先把保存的状态给恢复出来
        p.setColor(Color.GREEN);
        canvas.restore();
        canvas.drawLine(x/2,y/2,x/2,y/4,p);
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.GRAY);
        canvas.drawCircle(x/2,y/2,10,p);
    }
}
