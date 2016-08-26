package com.hsf.mypullToRefresh.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.hsf.mypullToRefresh.R;

/**
 * Created by Administrator on 2016/8/19.
 */
public class JdSecondRefreshView extends View {
    private Bitmap endBitmap;
    public JdSecondRefreshView(Context context) {
        super(context);
        init();
    }

    public JdSecondRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JdSecondRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //拿到帧动画第三章图片，我们的FirstStepView的宽高也是根据这张图片来测量的，所以我们就能
        //保证两个View的宽高一致了
        endBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.app_refresh_people_3));
    }

    //只需要测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measuredWidth(widthMeasureSpec),measuredHeight(heightMeasureSpec));

    }

    private int measuredHeight(int heightMeasureSpec) {
        int result;
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = endBitmap.getHeight();
            if(mode == MeasureSpec.UNSPECIFIED){
                result = Math.min(size,result);
            }
        }
        return  result;
    }

    private int measuredWidth(int widthMeasureSpec) {
        int result;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = endBitmap.getWidth();
            if(mode == MeasureSpec.UNSPECIFIED){
                result = Math.min(size,result);
            }
        }
        return  result;
    }


}
