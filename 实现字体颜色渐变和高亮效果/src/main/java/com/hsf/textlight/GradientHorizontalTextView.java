package com.hsf.textlight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Android实现歌词渐变色和进度的效果
 * Created by Administrator on 2016/9/6.
 */
public class GradientHorizontalTextView extends TextView{
    private LinearGradient mGradient;
    private Matrix mGradientMatrix;//渐变矩阵
    private Paint mPaint;//画笔

    private int mWidth = 0;//textView的宽度
    private int mTranslate = 0;//平移量
    private boolean mAnimating = true;//是否动画
    private int delta = 15;//移动增量
    private boolean isArrayColors;
    public GradientHorizontalTextView(Context context) {
        super(context);
        init();
    }

    public GradientHorizontalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GradientHorizontalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mWidth==0){
            mWidth = getMeasuredWidth();
        }
        if(mWidth > 0){
            mPaint = getPaint();
            String text = getText().toString();
            int size;
            if(text.length()>0){
                size = mWidth*2/text.length();
            }else{
                size = mWidth;
            }
            if(isArrayColors){
                mGradient = new LinearGradient(-size,0,0,0,new int[]{getResources().getColor(R.color.purple), getResources().getColor(R.color.green),
                        getResources().getColor(R.color.blue)},
                    null, Shader.TileMode.CLAMP);

            }else{
                mGradient = new LinearGradient(-size,0,0,0,getResources().getColor(R.color.purple), getResources().getColor(R.color.green),
                        Shader.TileMode.CLAMP);
            }
            mPaint.setShader(mGradient);
            mGradientMatrix = new Matrix();

        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mAnimating && mGradientMatrix!=null){
            float mTextWidth = getPaint().measureText(getText().toString());//获得文字的宽度
            mTranslate+=delta;//默认颜色渐变向右侧移动
            if(mTranslate > mTextWidth+1 || mTranslate < 1){
                delta = -delta;
            }

            mGradientMatrix.setTranslate(mTranslate,0);
            mGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(30);
        }

    }
    public void setStyle(boolean isArrayColors){
        this.isArrayColors = isArrayColors;
    }

}
