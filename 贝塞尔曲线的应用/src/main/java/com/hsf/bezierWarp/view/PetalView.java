package com.hsf.bezierWarp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 花瓣的制作流程，使用三次贝塞尔曲线
 * Created by Administrator on 2016/9/9.
 */
public class PetalView extends View {
    private Paint mPaint;
    private Path path;
    public PetalView(Context context) {
        super(context);
        init();
    }

    public PetalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PetalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        path = new Path();
        path.moveTo(100,200);
        path.quadTo(200,100,200,300);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,mPaint);

    }
}
