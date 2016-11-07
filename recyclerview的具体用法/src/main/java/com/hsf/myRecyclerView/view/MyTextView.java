package com.hsf.myRecyclerView.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/10/17.
 */

public class MyTextView extends View {
    private int lastX;
    private int lastY;
    private int mWidth;
    private int mHeight;

    private int screenWidth;
    private int screenHeight;

    public MyTextView(Context context) {
        super(context);
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        screenWidth = display.widthPixels;
        screenHeight = display.heightPixels;
        init();
    }


    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();
                int left = layoutParams.leftMargin + x - lastX;
                int top = layoutParams.topMargin + y - lastY;


                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                setLayoutParams(layoutParams);
                requestLayout();
                break;
            }

            case MotionEvent.ACTION_UP: {

                break;
            }
        }
        lastX = x;
        lastY = y;
        return true;
    }

}
