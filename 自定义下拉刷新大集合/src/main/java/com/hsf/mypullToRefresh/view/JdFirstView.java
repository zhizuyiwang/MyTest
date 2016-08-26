package com.hsf.mypullToRefresh.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hsf.mypullToRefresh.R;

/**
 * Created by Administrator on 2016/8/19.
 */
public class JdFirstView extends View {
    private Paint paint;
    private float currentProgress;
    private int currentAlph;
    private Bitmap people;
    private Bitmap goods;
    private Bitmap scalePeople;
    private Bitmap scaleGoods;
    private Bitmap refreshBitmap;
    private int x;
    private int y;

    public JdFirstView(Context context) {
        super(context);
        init();
    }

    public JdFirstView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JdFirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        people = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.app_refresh_people_0));
        goods = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.app_refresh_goods_0));
        //这是后面动画中的最后一张图片，拿这张图片的作用是用它的宽高来测量,我们这个自定义View的宽高,保证在下拉刷新最后的图片和刷新中的图片
        //的大小一致
        refreshBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.app_refresh_people_3));
        //注意到在人、货物缩放的过程中。透明度是有所变化的
        paint = new Paint();
        paint.setAlpha(0);//默认中设置为全透明
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measuredWidth(widthMeasureSpec),measuredHeight(heightMeasureSpec));
        x = getMeasuredWidth();
        y = getMeasuredHeight();
        Log.e("TAG+++",y+"==========================");

        //对快递员图片做相应的缩放
        scalePeople = Bitmap.createScaledBitmap(people,x,y,true);
        //对货物做相应的缩放
        scaleGoods = Bitmap.createScaledBitmap(goods,scalePeople.getWidth()*10/27, scalePeople.getHeight()/5, true);
    }

    //测量高度
    private int measuredHeight(int heightMeasureSpec) {
        int result;
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = refreshBitmap.getHeight();
            if(mode == MeasureSpec.UNSPECIFIED){
                result = Math.min(size,result);
            }
        }
        return  result;
    }

    //测量宽度
    private int measuredWidth(int widthMeasureSpec) {
        int result;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = refreshBitmap.getWidth();
            if(mode == MeasureSpec.UNSPECIFIED){
                result = Math.min(size,result);
            }
        }
        return  result;
    }

    //在这里面拿到测量后的宽和高，w就是测量后的宽，h是测量后的高
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //分别绘制人和货物
        Log.e("TAG+++",y+"==========================");
        canvas.save();
        canvas.scale(currentProgress,currentProgress,x-scaleGoods.getWidth()/2,y/2);
        paint.setAlpha(currentAlph);
        canvas.drawBitmap(scaleGoods,x-scaleGoods.getWidth(),y/2 - scaleGoods.getHeight()/2,paint);
        canvas.restore();
        canvas.save();
        canvas.scale(currentProgress, currentProgress , 0, y/2);
        paint.setAlpha(currentAlph);
        canvas.drawBitmap(scalePeople, 0,0,paint);
        canvas.restore();
    }

    public void setProgress(float currentProgress){
        this.currentProgress = currentProgress;
        this.currentAlph = (int) (currentProgress*255);
    }


}
