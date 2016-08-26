package com.hsf.canvas2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hsf.canvas2.R;

/**
 * Created by Administrator on 2016/8/22.
 */
public class WordPadView extends View {
    //画图的画笔引用
    private Paint mPaint;
    //画笔的颜色
    private int paint_color;
    //画笔的宽度
    private float paint_width;
    //写字板的颜色
    private int wordPad_color;
    //橡皮擦的宽度
    private float rubber_width;
    //画图的路径对象
    private Path mPath;
    //使用画笔或橡皮擦的标志
    private boolean flag;

    public WordPadView(Context context) {
        super(context);
    }

    public WordPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public WordPadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.WordPadView);
        //获取自定义属性和默认值
        paint_color = mTypedArray.getColor(R.styleable.WordPadView_paint_color, Color.RED);
        wordPad_color = mTypedArray.getColor(R.styleable.WordPadView_wordPad_color, Color.GREEN);
        rubber_width = mTypedArray.getDimension(R.styleable.WordPadView_rubber_stroke,10);
        paint_width = mTypedArray.getDimension(R.styleable.WordPadView_paint_stroke,2);
        mTypedArray.recycle();

        mPaint = new Paint();
        mPath = new Path();

        setBackgroundColor(wordPad_color);
        mPaint.setColor(paint_color);
        mPaint.setStrokeWidth(paint_width);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(touchX,touchY);//重新设置即将出现的线的起点
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(touchX,touchY);//连线
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();//通知系统重绘
        return true;//要处理当前事件
    }

    public void setPaintColor(int color){
        this.paint_color = color;
        mPaint.setColor(color);
    }
    public int getPaintColr(){
        return paint_color;
    }

    public void setPaintWidth(float size){
        this.paint_width = size;
        mPaint.setStrokeWidth(size);

    }
    public float getPaintWidth(){
        return paint_width;

    }
    public void setWordPadColor(int color){
        this.wordPad_color = color;
        setBackgroundColor(color);
        if(!flag){
            mPaint.setColor(wordPad_color);
        }

    }
    public int getWordPadColor(){
        return wordPad_color;
    }
    public void setRubberWidth(float size){
        this.rubber_width = size;
        if(!flag){
            mPaint.setStrokeWidth(rubber_width);
        }
    }
    public float getRubberWidth(){
        return rubber_width;

    }
    public void setFlag(boolean flag){
        this.flag = flag;
        if(flag){
            mPaint.setColor(paint_color);
            mPaint.setStrokeWidth(paint_width);
        }else {
            mPaint.setColor(wordPad_color);
            mPaint.setStrokeWidth(rubber_width);
        }
    }
}
