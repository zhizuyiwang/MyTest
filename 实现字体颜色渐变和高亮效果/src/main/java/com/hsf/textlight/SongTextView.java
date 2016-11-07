package com.hsf.textlight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * 实现歌词进度效果
 * Created by Administrator on 2016/9/6.
 */
public class SongTextView extends TextView {
    private Paint mPaint;
    private int postIndex;
    private int delta = 15;
    private float mTextWidth;
    private float mTextHeight;
    private String text = "锄禾日当午，汗滴禾下土";
    private PorterDuffXfermode xfermode;
    
    public SongTextView(Context context) {
        super(context);
        init();
    }

    public SongTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SongTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint.setColor(Color.CYAN);
        mPaint.setTextSize(60.0f);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setXfermode(null);
        mPaint.setTextAlign(Paint.Align.LEFT);

        //得到文字的实际高
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom - fontMetrics.descent - fontMetrics.ascent;
        mTextWidth = mPaint.measureText(text);
    }

    /**
     * 计算控件的宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(setTextViewWidth(widthMeasureSpec),setTextViewHeight(heightMeasureSpec));
    }

    private int setTextViewHeight(int heightMeasureSpec) {
        int result;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(heightMode == MeasureSpec.EXACTLY){
            result = heightSize;
        }else{
            int size = getPaddingBottom() + getPaddingTop() + getMeasuredHeight();
            if(heightMode == MeasureSpec.AT_MOST){
                result = Math.min(heightSize,size);

            }else{
                result = size;
            }
        }

        Log.e("TAG","height"+result);
        return result;
    }

    private int setTextViewWidth(int widthMeasureSpec) {
        int result;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        Log.e("TAG","widthSize"+widthSize);
        Log.e("TAG","getMeasuredWidth"+getMeasuredWidth());
        if(widthMode == MeasureSpec.EXACTLY){//match_parent , accurate
            result = widthSize;
        }else{
            int size = getPaddingLeft()+getPaddingRight()+getMeasuredWidth();

            if(widthMode == MeasureSpec.AT_MOST){//
                result = Math.min(size,widthSize);
            }else{
                result = size;
            }

        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap srcBitMap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas srcCanvas = new Canvas(srcBitMap);

        srcCanvas.drawText(text,0,mTextHeight,mPaint);
        mPaint.setXfermode(xfermode);
        mPaint.setColor(Color.RED);
        RectF rectF = new RectF(0,0,postIndex,getMeasuredHeight());
        srcCanvas.drawRect(rectF, mPaint);
        canvas.drawBitmap(srcBitMap, 0, 0, null);
        init();
        if(postIndex<mTextWidth)
        {
            postIndex+=10;
        }else{
            postIndex=0;
        }
        postInvalidateDelayed(30);

    }

}
