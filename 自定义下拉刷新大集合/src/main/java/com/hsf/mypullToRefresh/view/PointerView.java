package com.hsf.mypullToRefresh.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.hsf.mypullToRefresh.R;

/**
 * Created by Administrator on 2016/8/18.
 */
public class PointerView extends View {
    private int x;
    private int y;
    private Bitmap finalPointerBitmap;
    private Bitmap pointerBitmap;
    public PointerView(Context context) {
        super(context);
        init();
    }

    public PointerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        pointerBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.load_icon_pointer2x));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
        x = getMeasuredWidth();
        y = getMeasuredHeight();
        finalPointerBitmap = Bitmap.createScaledBitmap(pointerBitmap,x,y,true);
    }

    private int measureHeight(int heightMeasureSpec) {
        int result;
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = pointerBitmap.getHeight();
            if(mode == MeasureSpec.AT_MOST){
                result = Math.min(size,result);
            }
        }
        return result;
    }

    private int measureWidth(int widthMeasureSpec) {
        int result;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = pointerBitmap.getWidth();
            if(mode == MeasureSpec.AT_MOST){
                result = Math.min(size,result);
            }
        }
        return result;
    }

    //注意，onSizeChanged()是在View大小发生变化时调用，它会调用onMeasure()方法重新对view进行测量
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.rotate(270,x/2,y/2);
        canvas.drawBitmap(finalPointerBitmap,0,0,null);
    }

}
