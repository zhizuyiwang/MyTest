package com.hsf.textlight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 实现歌词进度效果
 * Created by Administrator on 2016/9/6.
 */
public class SongTextView extends TextView {
    private Paint mPaint;
    private int postIndext;
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
        //setMeasuredDimension(setTextView);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
