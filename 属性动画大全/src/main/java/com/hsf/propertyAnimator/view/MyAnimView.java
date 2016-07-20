package com.hsf.propertyAnimator.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by Administrator on 2016/7/19.
 */
public class MyAnimView extends View{
    private final static float RADIUS = 50f;
    private String color;
    private Point currentPoint;
    private Paint paint;

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(currentPoint == null){
            currentPoint = new Point(RADIUS,RADIUS);
            drawCir(canvas);
            startAnimator();
        }else{
            drawCir(canvas);
        }

    }

    private void startAnimator() {
        Point point1 = new Point(RADIUS,RADIUS);
        Point point2 = new Point(getWidth()-RADIUS,getHeight()-RADIUS);
        Point point3 = new Point(RADIUS,getHeight()-RADIUS);
        Point point4 = new Point(getWidth()-RADIUS,RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(),point1,point2,point3,point4);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentPoint = (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        anim.setDuration(5000);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatMode(Animation.REVERSE);
        anim.start();

    }

    private void drawCir(Canvas canvas) {
        
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x,y,RADIUS,paint);
    }

    public void setColor(String color){
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }
    public String getColor(){
        return color;
    }

}
