package com.hsf.mypullToRefresh.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hsf.mypullToRefresh.R;

/**
 * Created by Administrator on 2016/8/17.
 */
public class AutoHomeFirstView extends View {
    private Bitmap backgroudBitmap;
    private Bitmap pointerBitmap;
    private Bitmap finalBackgroudBitmap;
    private Bitmap finalPointerBitmap;
    private int x;
    private int y;
    private float currentProgress;
    public AutoHomeFirstView(Context context) {
        super(context);
        init();
    }

    public AutoHomeFirstView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoHomeFirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        backgroudBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.load_icon_dial2x));
        pointerBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.load_icon_pointer2x));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置View的宽、高
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
        //测量View的宽、高
        x = getMeasuredWidth();
        y = getMeasuredHeight();
        Log.e("TAG1,",x+","+y);
        Log.e("TAG2,",backgroudBitmap.getWidth()+","+backgroudBitmap.getHeight());
        finalBackgroudBitmap = Bitmap.createScaledBitmap(backgroudBitmap,x,y,true);
        finalPointerBitmap = Bitmap.createScaledBitmap(pointerBitmap,x,y,true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(finalBackgroudBitmap,0,0,null);
        Log.e("进度",currentProgress+"");
        canvas.rotate(currentProgress*2.7f,x/2,y/2);
        canvas.drawBitmap(finalPointerBitmap,0,0,null);
    }

    /**
     * 设置View的宽
     * @param widthMeasureSpec
     * @return
     */
    private int measureWidth(int widthMeasureSpec) {
        int result;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = backgroudBitmap.getWidth();
            if(mode == MeasureSpec.AT_MOST){
                result = Math.min(size,result);
            }
        }
        return result;
    }

    /**
     * 设置View的高
     * @param widthMeasureSpec
     * @return
     */
    private int measureHeight(int widthMeasureSpec) {
        int result;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = backgroudBitmap.getHeight();
            if(mode == MeasureSpec.AT_MOST){
                result = Math.min(size,result);
            }
        }
        return result;
    }
    public void setProgress(float currentProgress){
        this.currentProgress = currentProgress*100;
    }
}
