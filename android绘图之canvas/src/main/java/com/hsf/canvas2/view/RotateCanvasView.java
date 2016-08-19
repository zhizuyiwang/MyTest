package com.hsf.canvas2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/8/18.
 */
public class RotateCanvasView extends View {
    private Paint p1;
    private Paint p2;
    public RotateCanvasView(Context context) {
        super(context);
        init();
    }

    public RotateCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotateCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        p1 = new Paint();
        p2 = new Paint();
        p1.setColor(Color.RED);
        p2.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(200,200,400,400,p1);
        canvas.rotate(30,getMeasuredWidth()/2,getMeasuredHeight()/2);
        canvas.drawRect(300,500,500,700,p2);
    }
}
