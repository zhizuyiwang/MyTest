package com.hsf.myRecyclerView.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

/**
 *
 * Created by Administrator on 2016/10/17.
 */

public class MyCoordinatorLayoutBehavior extends CoordinatorLayout.Behavior<Button>{

    private int width;

    public MyCoordinatorLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyCoordinatorLayoutBehavior(Context context) {
        init(context);
    }

    private void init(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
    }

    /**
     *
     * 判断child的布局是否依赖dependency
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        //如果dependency是TempView的实例，说明它就是我们所需要的Dependency
        return dependency instanceof MyTextView;
    }

    /**
     * 当dependency发生改变时（位置、宽高等），执行这个函数
     * 返回true表示child的位置或者是宽高要发生改变，否则就返回false
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {
        int left = dependency.getLeft();
        int top = dependency.getTop();
        int x = width - left - child.getWidth();
        int y = top;
        setPosition(child,x,y);
        return true;
    }

    private void setPosition(View child, int x, int y) {
        CoordinatorLayout.MarginLayoutParams params = (CoordinatorLayout.MarginLayoutParams) child.getLayoutParams();
        params.leftMargin = x;
        params.topMargin = y;
        child.setLayoutParams(params);
    }

}
