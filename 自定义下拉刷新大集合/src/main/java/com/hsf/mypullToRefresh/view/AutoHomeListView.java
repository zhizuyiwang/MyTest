package com.hsf.mypullToRefresh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hsf.mypullToRefresh.R;

/**
 * Created by Administrator on 2016/8/17.
 */
public class AutoHomeListView extends ListView implements AbsListView.OnScrollListener{
    private static final int DONE = 0;
    private static final int PULL_TO_REFRESH = 1;
    private static final int RELEASE_REFRESH = 2;
    private static final int REFRESHING = 3;
    private static final int RATIO = 3;

    private LinearLayout headerView;
    private TextView pull_to_refresh;
    private FrameLayout refreshView;
    private int headerViewHeight;

    private float startY;
    private float offSetY;
    private int currentPosition;
    private float currentProgress;
    private int state;
    private boolean isEnd;
    private boolean isRefreshable;
    private boolean isRecord;

    private AutoHomeFirstView firstView;
    private PointerView pointerView;
    private OnRefreshListener mListener;
    private Animation animation;




    public AutoHomeListView(Context context) {
        super(context);
        init();
    }

    public AutoHomeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoHomeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        setOnScrollListener(this);
        headerView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.autohome_item,null,false);

        firstView = (AutoHomeFirstView) headerView.findViewById(R.id.auto_home);
        pointerView = (PointerView) headerView.findViewById(R.id.anim_pointer);
        refreshView = (FrameLayout) headerView.findViewById(R.id.anim_container);
        pull_to_refresh = (TextView) headerView.findViewById(R.id.tv_pull_to_refresh);

        animation = AnimationUtils.loadAnimation(getContext(),R.anim.pointer_rotate);
        measureSize(headerView);
        addHeaderView(headerView);
        headerViewHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0,-headerViewHeight,0,0);

        isEnd = true;
        state = DONE;//初始化为隐藏状态
        isRefreshable = false;

    }

    public interface OnRefreshListener{
        void onRefresh();

    }
    public void setOnRefreshListener(OnRefreshListener mListener){
        this.mListener = mListener;
        isRefreshable = true;
    }

    public void setRefreshComplete(){
        isEnd = true;
        state = DONE;
        changeState(state);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        currentPosition = i;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(isEnd){
            if (isRefreshable && state != REFRESHING) {
                switch (ev.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if (currentPosition == 0 && !isRecord) {
                            isRecord = true;
                            startY = ev.getY();
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float tempY = ev.getY();
                        if (currentPosition == 0 && !isRecord) {
                            isRecord = true;
                            startY = tempY;
                        }
                        if (state!=REFRESHING && isRecord ) {
                            offSetY = tempY - startY;
                            float currentHeight = (-headerViewHeight+offSetY/RATIO);
                            float currentProgress = 1+currentHeight/headerViewHeight;
                            if (currentProgress>=1) {
                                currentProgress = 1;
                            }
                            if (state == RELEASE_REFRESH && isRecord) {
                                setSelection(0);
                                if (-headerViewHeight+offSetY/RATIO<0) {
                                    state = PULL_TO_REFRESH;
                                    changeState(state);
                                }else if (offSetY<=0) {
                                    state = DONE;
                                    changeState(state);
                                }
                            }
                            if (state == PULL_TO_REFRESH && isRecord) {
                                setSelection(0);
                                if (-headerViewHeight+offSetY/RATIO>=0) {
                                    state = RELEASE_REFRESH;
                                    changeState(state);
                                }else if (offSetY<=0) {
                                    state = DONE;
                                    changeState(state);
                                }
                            }
                            if (state == DONE && isRecord) {
                                if (offSetY>=0) {
                                    state = PULL_TO_REFRESH;
                                }
                            }
                            if (state == PULL_TO_REFRESH) {
                                headerView.setPadding(0,(int)(-headerViewHeight+offSetY/RATIO) ,0,0);
                                firstView.setProgress(currentProgress);
                                firstView.postInvalidate();
                            }
                            if (state == RELEASE_REFRESH) {
                                headerView.setPadding(0,(int)(-headerViewHeight+offSetY/RATIO) ,0, 0);
                                firstView.setProgress(currentProgress);
                                firstView.postInvalidate();
                            }
                        }


                        break;
                    case MotionEvent.ACTION_UP:
                        if (state == PULL_TO_REFRESH) {
                            Log.e("TAG","下拉刷新");
                           // this.smoothScrollBy((int)offSetY/RATIO, 500);
                            headerView.setPadding(0,-headerViewHeight,0,0);
                            changeState(state);
                        }
                        if (state == RELEASE_REFRESH) {
                            headerView.setPadding(0,0,0 ,0);
                            state = REFRESHING;
                            mListener.onRefresh();
                            changeState(state);
                        }
                        isRecord = false;
                        break;
                }

            }
        }
        return super.onTouchEvent(ev);
    }

    public void changeState(int state){
        switch (state){
            case DONE:
                pull_to_refresh.setText("下拉刷新");
                headerView.setPadding(0,-headerViewHeight,0,0);
                firstView.setVisibility(VISIBLE);
                refreshView.setVisibility(GONE);
                pointerView.clearAnimation();
                break;
            case PULL_TO_REFRESH:
                firstView.setVisibility(VISIBLE);
                pull_to_refresh.setText("下拉刷新");

                refreshView.setVisibility(GONE);
                pointerView.clearAnimation();
                break;
            case RELEASE_REFRESH:
                firstView.setVisibility(GONE);
                refreshView.setVisibility(VISIBLE);
                pull_to_refresh.setText("释放刷新");
                pointerView.clearAnimation();
                break;
            case REFRESHING:
                firstView.setVisibility(GONE);
                refreshView.setVisibility(VISIBLE);
                pull_to_refresh.setText("正在刷新");
                pointerView.clearAnimation();
                pointerView.startAnimation(animation);
                break;
        }
    }
    //测量ListView Item的合适大小
    private void measureSize(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if(p == null){
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int measuredWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0,p.width);
        int height = p.height;
        int measuredHeightSpec;
        if(height>0){
            measuredHeightSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        }else{
            measuredHeightSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        }

        child.measure(measuredWidthSpec,measuredHeightSpec);

    }
}
