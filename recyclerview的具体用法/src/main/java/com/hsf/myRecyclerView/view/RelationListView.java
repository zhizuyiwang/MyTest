package com.hsf.myRecyclerView.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/10/31.
 */

public class RelationListView extends ListView {
    private View mView;
    private RelationListView mListView;
    public RelationListView(Context context) {
        super(context);
    }

    public RelationListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelationListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void onTouch(MotionEvent ev) {
        super.onTouchEvent(ev);
    }

    /**
     * 要让ListView的width有wrap_content的能力，主要是去重写ListView的onMeasure方法
     * 我们主要是改变了width的值，让width的值等于最宽的那个item的宽度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = getMeasuredHeight();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if(widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }else if(widthMode == MeasureSpec.AT_MOST) {
            final int childCount = getChildCount();
            for(int i=0;i<childCount;i++) {
                View item = getChildAt(i);
                measureChild(item, widthMeasureSpec, heightMeasureSpec);
                width = Math.max(width, item.getMeasuredWidth());
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(null != mListView) {
            mListView.onTouch(ev);
        }

        return super.onTouchEvent(ev);
    }

    /**如何让两个ListView关联，肯定是要保存另一个ListView的实例
     * @param listView
     */
    public void setRelatedListView(RelationListView listView) {
        mListView = listView;
    }



}

