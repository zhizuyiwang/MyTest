package com.hsf.myRecyclerView.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/10/31.
 */

public class MyLinearLayout extends LinearLayout{
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 判断是否需要中断事件的传递
     * 默认返回false 意思是，不中断
     * 返回true 意思是，中断
     */
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    /**在这个方法中把事件同时交给两个ListView，作用就是当你滑动的时候两个ListView都能收到滑动事件；
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int count = getChildCount();//得到listView的数目
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.dispatchTouchEvent(event);
        }
        return true;
    }
}
