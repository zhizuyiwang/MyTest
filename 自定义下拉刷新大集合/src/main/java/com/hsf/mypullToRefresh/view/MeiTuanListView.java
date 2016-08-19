package com.hsf.mypullToRefresh.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hsf.mypullToRefresh.R;

/**
 * Created by Administrator on 2016/8/12.
 */
public class MeiTuanListView extends ListView implements AbsListView.OnScrollListener{
    private static  final int DONE = 0;
    private static final int PULL_TO_REFRESH = 1;
    private static final int RELEASE_REFRESH = 2;
    private static final int REFRESHING = 3;
    private static final int RATIO = 3;//比例，这个参数的意义是下拉的距离是刷新头滑动距离的3倍

    private LinearLayout headerView;
    private int headerViewHeight;
    private float startY;
    private float offsetY;

    private TextView tv_pull_to_refresh;
    private TextView tv_refresh_time;

    private OnRefreshListener mOnRefreshListener;
    private int state;
    private int mFirstVisibleItem;

    private boolean isRecord;
    private boolean isEnd;
    private boolean isRefreable;

    private MeiTuanRefreshFirstView firstView;
    private MeiTuanRefreshSecondView secondView;
    private MeiTuanRefreshThirdView thirdView;
    private AnimationDrawable secondAnim;
    private AnimationDrawable thirdAnim;

    public MeiTuanListView(Context context) {
        super(context);
        init();

    }

    public MeiTuanListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MeiTuanListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        setOnScrollListener(this);
        headerView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.meituan_item,null,false);
        firstView = (MeiTuanRefreshFirstView) headerView.findViewById(R.id.first_view);
        tv_pull_to_refresh = (TextView) headerView.findViewById(R.id.tv_pull_to_refresh);
        //tv_refresh_time = (TextView) headerView.findViewById(R.id.tv_refresh_time);

        secondView = (MeiTuanRefreshSecondView) headerView.findViewById(R.id.second_view);
        secondView.setBackgroundResource(R.drawable.pull_to_refresh_second_anim);
        secondAnim = (AnimationDrawable) secondView.getBackground();

        thirdView = (MeiTuanRefreshThirdView) headerView.findViewById(R.id.third_view);
        thirdView.setBackgroundResource(R.drawable.pull_to_refresh_third_anim);
        thirdAnim = (AnimationDrawable) thirdView.getBackground();

        measureView(headerView);
        addHeaderView(headerView);//把刷新头加到ListView的头部，此时刷新头在ListView中处于第一条，下标为0
        headerViewHeight = headerView.getMeasuredHeight();//得到刷新头的高度
        headerView.setPadding(0,-headerViewHeight,0,0);//设置刷新头的padding值,即设置刷新头位置，默认情况下，刷新头是隐藏掉的

        state = DONE;//DONE为隐藏状态
        isEnd = true;//说明不是刷新状态
        isRefreable = false;//是否处于可刷新状态，也是是否用刷新的开关
    }

    public interface OnRefreshListener{
        void onRefresh();
    }
    //设置刷新监听器，调用者需要调用此方法来注册监听,然后在合适的地方调用实现接口类的方法onRefresh();
    public void setOnRefreshListener(OnRefreshListener mOnrefreshListener){
        this.mOnRefreshListener = mOnrefreshListener;
        isRefreable = true;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        mFirstVisibleItem = firstVisibleItem;
    }

    /**
     * 刷新完后的操作
     */
    public void setOnRefreshComplete(){
        //一定要将isEnd的值设置为true，以便于下次的下拉刷新
        isEnd = true;
        state = DONE;//刷新完后要隐藏
        changeState(state);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(isEnd){//如果现在是结束的状态，即刷新完毕了，可以再次刷新了，在onRefreshComplete中设置
            if(isRefreable && state!=REFRESHING){//如果现在是可刷新状态并在setOnRefreshListener中设置为true
                switch (ev.getAction()){
                    //用户按下时
                    case MotionEvent.ACTION_DOWN:
                        //如果当前是在ListView顶部并且没有记录y坐标
                        if (mFirstVisibleItem == 0 && !isRecord) {
                            //将isRecord置为true，说明现在已记录y坐标
                            isRecord = true;
                            //将当前y坐标赋值给startY起始y坐标
                            startY = ev.getY();
                        }
                        break;
                    //用户滑动时
                    case MotionEvent.ACTION_MOVE:
                        //再次得到y坐标，用来和startY相减来计算offsetY位移值
                        int tempY = (int) ev.getY();
                        //再判断一下是否为ListView顶部并且有没有记录y坐标
                        if(mFirstVisibleItem==0 && !isRecord){
                            isRecord = true;
                            startY = tempY;
                        }
                        //如果当前状态不是正在刷新的状态，并且已经记录了y坐标
                        if(state != REFRESHING && isRecord){
                            //计算y的偏移量
                            offsetY = tempY - startY;
                            //计算当前滑动的高度
                            float currentHeight = (-headerViewHeight+offsetY/RATIO);
                            //用当前滑动的高度和头部headerView的总高度进行比 计算出当前滑动的百分比 0到1
                            float currentProgress = 1+currentHeight/headerViewHeight;
                            //如果当前百分比大于1了，将其设置为1，目的是让第一个状态的椭圆不再继续变大
                            if (currentProgress>=1) {
                                currentProgress = 1;
                            }
                            if(state == RELEASE_REFRESH && isRecord){
                                setSelection(0);
                                if((-headerViewHeight+offsetY/3)<0){
                                    state = PULL_TO_REFRESH;
                                    changeState(state);
                                }else if(offsetY<=0){
                                    state = DONE;
                                    changeState(state);
                                }
                            }
                            if(state == PULL_TO_REFRESH && isRecord){
                                setSelection(0);
                                if((-headerViewHeight+offsetY/3)>0){
                                    state = RELEASE_REFRESH;
                                    Log.e("TAG","释放刷新");
                                    changeState(state);
                                    //如果当前y的位移值小于0，即为headerView隐藏了
                                }else if(offsetY<=0){
                                    state = DONE;
                                    changeState(state);
                                }
                            }
                            if(state == DONE && isRecord){
                                //如果位移值大于0
                                if(offsetY>0){
                                    //改为下拉刷新状态
                                    state = PULL_TO_REFRESH;
                                    changeState(state);
                                }
                            }
                            //如果当前是下拉刷新状态，则随着手指移动不断的设置刷新头的位置，并根据进度改变椭圆图的大小

                            if(state == PULL_TO_REFRESH){
                                headerView.setPadding(0, (int) (-headerViewHeight+offsetY/RATIO),0,0);
                                firstView.setProgress(currentProgress);
                                firstView.postInvalidate();
                            }
                            if(state == RELEASE_REFRESH){
                                headerView.setPadding(0, (int) (-headerViewHeight+offsetY/RATIO),0,0);
                            }
                        }else{

                        }


                        break;
                    case MotionEvent.ACTION_UP:
                        if(state == PULL_TO_REFRESH) {
                            this.smoothScrollBy((int) (offsetY / 3), 500);
                            changeState(state);
                        }
                        if(state == RELEASE_REFRESH){
                            this.smoothScrollBy((int) (-headerViewHeight+offsetY/3),500);
                            state = REFRESHING;
                            mOnRefreshListener.onRefresh();
                            changeState(state);
                        }
                        //这一套手势执行完，一定别忘了将记录y坐标的isRecord改为false，以便于下一次手势的执行
                        isRecord = false;
                        break;
                }
            }
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 更改刷新头的文字状态和动画
     */
    private void changeState(int state) {
        switch (state){
            case DONE:
                headerView.setPadding(0,-headerViewHeight,0,0);
                firstView.setVisibility(VISIBLE);
                secondView.setVisibility(GONE);
                secondAnim.stop();
                thirdView.setVisibility(GONE);
                thirdAnim.stop();
                break;
            case REFRESHING:
                tv_pull_to_refresh.setText("正在刷新");
                firstView.setVisibility(GONE);

                secondView.setVisibility(GONE);
                secondAnim.stop();
                thirdView.setVisibility(VISIBLE);
                thirdAnim.start();
                break;
            case PULL_TO_REFRESH:
                //设置文字为下拉刷新
                tv_pull_to_refresh.setText("下拉刷新");
                //第一状态view显示出来
                firstView.setVisibility(View.VISIBLE);

                //第二状态view隐藏起来
                secondView.setVisibility(View.GONE);
                //第二状态动画停止
                secondAnim.stop();
                //第三状态view隐藏起来
                thirdView.setVisibility(View.GONE);
                //第三状态动画停止
                thirdAnim.stop();

                break;
            case RELEASE_REFRESH:
                //文字显示为放开刷新
                tv_pull_to_refresh.setText("放开刷新");
                //第一状态view隐藏起来
                firstView.setVisibility(View.GONE);
                //第二状态view显示出来
                secondView.setVisibility(View.VISIBLE);
                //播放第二状态的动画
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                    secondAnim.start();
                    }
                }.start();
                //第三状态view隐藏起来
                thirdView.setVisibility(View.GONE);
                //停止第三状态的动画
                thirdAnim.stop();
                break;
        }

    }

    /**为什么要先测量一下刷新头的宽高，这是因为如果不测量的话，headerView.getMeasuredHeight()是得不出刷新头的高度的.
     * 很重要的方法，是用来测量刷新头的,从而确定刷新头的高度等信息
     * 首先得到刷新头的布局信息，然后由布局信息得到刷新头的宽、高，即p.width和p.height。
     * 最后再由刷新头的宽和高分别得到合适的宽高信息
     * @param child
     */
    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if(p == null){
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(MeasureSpec.UNSPECIFIED,0 + 0,p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if(lpHeight > 0){
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,MeasureSpec.EXACTLY);
        }else{
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec,childHeightSpec);
    }
}
