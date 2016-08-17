package com.hsf.mypullToRefresh.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.hsf.mypullToRefresh.R;

/**
 * Created by Administrator on 2016/8/12.
 */
public class MeiTuanRefreshFirstView extends View{
    private int measuredWidth;
    private int measuredHeight;
    private float currentProgress;
    private Bitmap initBitmap;
    private Bitmap endBitmap;
    private Bitmap scaleBitmap;


    public MeiTuanRefreshFirstView(Context context) {
        super(context);
        init();
    }


    public MeiTuanRefreshFirstView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MeiTuanRefreshFirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {

        initBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pull_image));
        //这个是第二个状态娃娃的图片，之所以要这张图片，是因为第二个状态和第三个状态的图片的大小是一致的，而第一阶段
        //椭圆形图片的大小与第二阶段和第三阶段不一致，因此我们需要根据这张图片来决定第一张图片的宽高，来保证
        //第一阶段和第二、三阶段的View的宽高一致
        endBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.pull_end_image_frame_05));
    }


    //重写onMeasure方法主要是设置wrap_content时 View的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        //根据设置的宽度来计算高度,  设置为符合第二阶段娃娃图片的宽高比例
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureWidth(widthMeasureSpec)*endBitmap.getHeight()/endBitmap.getWidth());

    }
    /**
     * 当wrap_content的时候，宽度即为第二阶段娃娃图片的宽度
     * @param widthMeasure
     * @return
     */
    private int measureWidth(int widthMeasure) {
        int result = 0;
        int width = MeasureSpec.getSize(widthMeasure);
        int mode = MeasureSpec.getMode(widthMeasure);
        if(mode==MeasureSpec.EXACTLY){
            result = width;
        }else{
            result = endBitmap.getWidth();
            if(mode==MeasureSpec.AT_MOST){
                result = Math.min(width,result);
            }
        }
        return result;
    }

    /**
     * 在onLayout里面获得测量后View的宽高
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
        //根据第二阶段娃娃宽高, 给椭圆形图片进行等比例的缩放
        scaleBitmap = Bitmap.createScaledBitmap(initBitmap,measuredWidth,measuredWidth*initBitmap.getHeight()/initBitmap.getWidth(),true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //缩放画布
        canvas.scale(currentProgress,currentProgress,measuredWidth/2,measuredHeight/2);
        canvas.drawBitmap(scaleBitmap,0,measuredHeight/4,null);
    }

    public void setProgress(float progress){
        this.currentProgress = progress;
    }
}
