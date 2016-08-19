package com.hsf.mypullToRefresh.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;

import com.hsf.mypullToRefresh.R;

/**
 * Created by Administrator on 2016/8/12.
 */
public class MeiTuanRefreshSecondView extends View {
    private Bitmap endBitmap;
    public MeiTuanRefreshSecondView(Context context) {
        super(context);
        init();
    }

    public MeiTuanRefreshSecondView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MeiTuanRefreshSecondView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        endBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pull_end_image_frame_05));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureWidth(widthMeasureSpec)*endBitmap.getHeight()/endBitmap.getWidth());
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode==MeasureSpec.EXACTLY){
            result = width;
        }else{
            int size = endBitmap.getWidth();
            if(mode == MeasureSpec.AT_MOST){
                result = Math.min(width,size);
            }
        }
        return result;
    }
}
