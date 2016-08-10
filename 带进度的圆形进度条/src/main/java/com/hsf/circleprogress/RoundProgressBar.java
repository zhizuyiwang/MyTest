package com.hsf.circleprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 带有进度的圆形进度条，线程安全的自定义控件，可以直接在子线程中更新
 * Created by Administrator on 2016/7/26.
 */
public class RoundProgressBar extends View{
    //画笔对象的引用
    private Paint paint;
    //圆环的颜色
    private int roundColor;
    //圆环进度的颜色
    private int roundProgressColor;
    //圆环的宽度
    private float roundWidth;
    //中间进度百分比的字符串颜色
    private int textColor;
    //中间进度百分比的字符串字体大小
    private float textSize;
    //进度条的最大值
    private int max;
    //当前进度
    private int progress;
    //是否显示中间进度百分比
    private boolean textIsDisplayable;
    //进度的风格，是实心或空心
    private int style;

    private static final int STROKE = 0;//空心
    private static final int FILL = 1;//实心

    public RoundProgressBar(Context context) {
        super(context);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundProgressBar);
        //获取自定义属性和默认值
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplay, true);
        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);

        mTypedArray.recycle();
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundProgressBar);
        //获取自定义属性和默认值
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplay, true);
        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);

        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画最外层的大圆环
        int center = getWidth()/2;//获取圆心的x坐标
        int radius = (int) (center - roundWidth/2);//获取大圆环的半径
        paint.setColor(roundColor);//设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE);//设置空心
        paint.setStrokeWidth(roundWidth);//设置圆环的宽度
        paint.setAntiAlias(true);//消除锯齿
        canvas.drawCircle(center,center,radius,paint);//画出圆环
        Log.e("TAG",center+"");

        //画进度百分比
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体
        //中间的进度百分比，先转换成float运算，不然得到的为0
        int percent = (int) (((float)progress / (float)max)*100);
        //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
        float textWidth = paint.measureText(percent+"%");
        if(textIsDisplayable && percent!=0 && style==STROKE){
            canvas.drawText(percent+"%",center-textWidth/2,center+textSize/2,paint);//画出进度百分比

        }

        //画圆弧，画圆环的进度
        paint.setStrokeWidth(roundWidth);
        paint.setColor(roundProgressColor);
        //定义圆弧的形状和大小界限
        RectF oval = new RectF(center-radius,center-radius,center+radius,center+radius);
        switch (style){
            case STROKE:{
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval, 0, 360 * progress / max, false, paint);
                break;
            }
            case FILL:{
                paint.setStyle(Paint.Style.FILL);
                if(progress!=0){
                    canvas.drawArc(oval, 0, 360 * progress / max,true,paint);
                }
                break;
            }
        }
    }

    public synchronized int getMax(){
        return max;
    }

    /**设置进度最大值
     * @param max
     */
    public synchronized void setMax(int max){
        if(max < 0){
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }
    /**
     * 获取进度.需要同步
     * @return
     */
    public synchronized int getProgress() {
        return progress;
    }
    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if(progress < 0){
            throw new IllegalArgumentException("progress not less than 0");
        }
        if(progress > max){
            progress = max;
        }
        if(progress <= max){
            this.progress = progress;
            postInvalidate();
        }
    }
    public int getCricleColor() {
        return roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return roundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }
    public int getStyle(){
        return style;
    }
    public void setStyle(int style){
        this.style = style;
    }
    public void setTextIsDisplayable(boolean flag){
        this.textIsDisplayable = flag;
    }
    public boolean getTextIsDisplay(){
        return textIsDisplayable;
    }

}
