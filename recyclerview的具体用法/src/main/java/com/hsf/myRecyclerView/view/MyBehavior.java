package com.hsf.myRecyclerView.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/10/12.
 */

public class MyBehavior extends FloatingActionButton.Behavior {
    //加一个加速器实现弹跳效果
    private FastOutLinearInInterpolator interpolator = new FastOutLinearInInterpolator();

    public MyBehavior(Context context, AttributeSet attr) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        //开始滑监听---当观察recyclerView开始发生滑动的时候回调
        //nestedScrollAxes滑动关联的方向
        return nestedScrollAxes == ViewGroup.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }
    boolean isAnimatingOut = false;//正在滑出来

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        //不断调用
        //判断滑动的方向，dyConsumed是在y轴方向上的距离增量，当为正说明向下滑动，为负说明向上滑动
        if(dyConsumed > 0 && !isAnimatingOut && child.getVisibility()==View.VISIBLE){
            //悬浮按钮滑出去（隐藏）
            animatOut(child);

        }else if(dyConsumed < 0 && child.getVisibility()!=View.VISIBLE){
            //悬浮按钮滑进来（显现）
            animatIn(child);
        }
    }

    private void animatIn(FloatingActionButton child) {
        child.setVisibility(View.VISIBLE);
        //属性动画
        ViewCompat.animate(child).translationX(0).setInterpolator(interpolator).setListener(null).start();
    }

    private void animatOut(final FloatingActionButton child) {
        //属性动画
        //设置监听判断状态
        ViewCompat.animate(child).translationX(child.getHeight()).setInterpolator(interpolator).setListener(new ViewPropertyAnimatorListenerAdapter(){
            @Override
            public void onAnimationStart(View view) {
                super.onAnimationStart(view);
                isAnimatingOut = true;
            }

            @Override
            public void onAnimationCancel(View view) {
                super.onAnimationCancel(view);
                isAnimatingOut = false;
            }

            @Override
            public void onAnimationEnd(View view) {
                child.setVisibility(View.GONE);
                isAnimatingOut = false;
                super.onAnimationEnd(view);
            }
        }).start();
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }


}
