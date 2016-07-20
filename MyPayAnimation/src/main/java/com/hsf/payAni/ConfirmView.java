package com.hsf.payAni;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ConfirmView extends View {

    private State mCurrentState = State.Success;
//    绘制X需要的path数量
    private static final int PATH_SIZE_TWO = 2;
//    绘制偏移角度的动画时间
    private static final long NORMAL_CIRCLE_ANIMATION_DURATION = 2000L;
//    绘制正常弧形的动画时间
    private static final long NORMAL_ANGLE_ANIMATION_DURATION = 1000l;
//    绘制对勾和叉号及快速旋转弧形的动画时间
    private static final long NORMAL_ANIMATION_DURATION = 350L;
    private ArrayList<Path> mRenderPaths;

    public static final int STROKEN_WIDTH = 20;
    private Paint mPaint;

    private int mCenterX;
    private int mCenterY;
    private float mCircleAngle;
    private float mEndAngle;
    private float mStartAngle;
    private float mPare;
    private int mRadius;
    private int mSignRadius;
    private RectF oval;
    private Path mSuccessPath;
    private PathMeasure mPathMeasure;

    public ValueAnimator mPhareAnimator;
    public ValueAnimator mStartAngleAnimator;
    public ValueAnimator mEndAngleAnimator;
    public ValueAnimator mCircleAnimator;

    public ConfirmView(Context context) {
        super(context);
    }

    public ConfirmView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConfirmView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w/2;
        mCenterY = h/2;

        mRadius = mCenterX > mCenterY ? mCenterX : mCenterY;
        mSignRadius = (int) (mRadius * 0.55F);
        int realRadius = mRadius - (STROKEN_WIDTH / 2);

        oval.left = mCenterX - realRadius;
        oval.top = mCenterY - realRadius;
        oval.right = mCenterX + realRadius;
        oval.bottom = mCenterY + realRadius;

        updatePath();

    }

    public void animatedWithState(State state){

        if(mCurrentState != state){
            mCurrentState = state;
        }
        if(mPhareAnimator != null && mPhareAnimator.isRunning()){
            stopPhareAnimation();
        }

        switch (state){
            case Fail:
            case Success:
                updatePath();
                if(mCircleAnimator != null && mCircleAnimator.isRunning()){
                    mCircleAngle = (float) mCircleAnimator.getAnimatedValue();
                    mCircleAnimator.end();
                }
                if((mStartAngleAnimator == null || !mStartAngleAnimator.isRunning() || !mStartAngleAnimator.isStarted()) &&
                       mEndAngleAnimator == null || !mEndAngleAnimator.isRunning() || !mEndAngleAnimator.isStarted() ){

                    mStartAngle = 360;
                    mEndAngle = 0;
                    startPhareAnimation();

                }
                break;
            case Progressing:
                mCircleAngle = 0;
                startCircleAnimation();
                break;
        }
    }

    /**
     *
     */
    private void startCircleAnimation() {


    }

    /**
     *开始动画
     */
    private void startPhareAnimation() {

        if(mPhareAnimator != null){
            mPhareAnimator = ValueAnimator.ofFloat(0.0F,1.0F);
            mPhareAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (float) valueAnimator.getAnimatedValue();
                    setAlpha(value);
                }
            });

            mPhareAnimator.setDuration(NORMAL_ANIMATION_DURATION);
            mPhareAnimator.setInterpolator(new LinearInterpolator());
        }
        mPare = 0;
        mPhareAnimator.start();
    }

    /**
     *停止动画
     */
    private void stopPhareAnimation() {
        if(mPhareAnimator != null){
            mPhareAnimator.end();
        }
    }

    private void updatePath() {
        int offset = (int) (mSignRadius * 0.15F);
        mRenderPaths.clear();
        switch (mCurrentState){
            case Success:
                mSuccessPath.reset();
                mSuccessPath.reset();
                mSuccessPath.moveTo(mCenterX - mSignRadius,mCenterY + offset);
                mSuccessPath.lineTo(mCenterX - offset,mCenterY + mSignRadius - offset);
                mSuccessPath.lineTo(mCenterX + mSignRadius,mCenterY - mSignRadius + offset);
                mRenderPaths.add(new Path());
                break;
            case Fail:
                mSuccessPath.reset();
                float failRadius = mSignRadius * 0.8F;
                mSuccessPath.moveTo(mCenterX - failRadius,mCenterY - failRadius);
                mSuccessPath.lineTo(mCenterX + failRadius,mCenterY + failRadius);
                mSuccessPath.moveTo(mCenterX + failRadius, mCenterX - failRadius);
                mSuccessPath.lineTo(mCenterX - failRadius,mCenterY + failRadius);
                for (int i = 0;i < PATH_SIZE_TWO;i++){
                    mRenderPaths.add(new Path());
                }
                break;
            default:
                mSuccessPath.reset();
        }

        mPathMeasure.setPath(mSuccessPath,false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mCurrentState){
            case Fail:
                for (int i = 1 ;i<PATH_SIZE_TWO;i++){
                    Path p = mRenderPaths.get(i);
                    if(p != null){
                        canvas.drawPath(p,mPaint);
                    }
                }
                drawCircle(canvas);
                break;
            case Success:
                Path p = mRenderPaths.get(0);
                if(p != null){
                    canvas.drawPath(p,mPaint);
                }
                drawCircle(canvas);
                break;
            case Progressing:
                drawCircle(canvas);
                break;
        }
    }

    /**
     * 画圆
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        float offsetAngle = mCircleAngle * 360;
        float startAngle = mEndAngle * 360;
        float sweepAngle = mStartAngle * 360;

        if(startAngle == 360){
            startAngle = 0;
        }
        sweepAngle = sweepAngle - startAngle;
        startAngle += offsetAngle;
        if(sweepAngle < 0){
            sweepAngle = 1;
        }
        canvas.drawArc(oval,startAngle,sweepAngle,false,mPaint);
    }


    public enum State{

        Success, Fail, Progressing
    }
}
