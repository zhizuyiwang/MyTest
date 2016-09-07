package com.hsf.myConstomFragment.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsf.myConstomFragment.R;

import java.util.Objects;
import java.util.Random;

/**
 * 实现思路：
 1.自定义一个弹幕View，继承自TextView，专门用来显示一条弹幕
 2.弹幕View能够自动从最右边匀速滚动到最左边
 3.弹幕的颜色和大小设置为随机值
 4.弹幕View的高度随机，区域在屏幕范围内
 5.在Activity中循环定时加入自定义弹幕View，形成最后的弹幕
 6.自定义文字资源，随机从文件资源中读取文字显示

 7.当弹幕从左边完全滚出时，其实线程还是在运行的，这样积累多了线程对系统负荷加大，我们需要加上判断，如果滚出了屏幕，线程也跳出while(true)循环，然后由系统自己回收。
 * Created by Administrator on 2016/9/2.
 */
public class BarrageView extends TextView{
    private Paint paint;

    //弹幕的初始化位置
    private int posX;
    private int posY;

    private int windowWidth;
    private int windowHeight;
    private int textSize;
    private int color;
    private int textWidth;
    private int speed;
    private Random random;
    private String msg;
    private int[] colors;
    private static final int MAXSIZE = 60;
    private static final int MINSIZE = 40;

    private LinearGradient mGradient;
    private Matrix mGradientMatrix;//渐变矩阵
    private Paint mPaint;//画笔

    private int mWidth = 0;//textView的宽度
    private int mTranslate = 0;//平移量
    private boolean mAnimating = true;//是否动画
    private int delta = 15;//移动增量
    private boolean isArrayColors;

    private RollThread rollThread;
    private OnRollEndListener onRollEndListener;

    public BarrageView(Context context) {
        super(context);
        init();
    }

    public BarrageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarrageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        //得到屏幕宽高
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        windowWidth = rect.width();
        windowHeight = rect.height();
        random = new Random();
        colors = new int[]{getResources().getColor(R.color.black),getResources().getColor(R.color.black1),
                getResources().getColor(R.color.green),getResources().getColor(R.color.greed1),
                getResources().getColor(R.color.blue),getResources().getColor(R.color.purple),
                getResources().getColor(R.color.red)};

        //设置x为屏幕宽
        posX = windowWidth;
        //设置y为屏幕高度内内随机，需要注意的是，文字是以左下角为起始点计算坐标的，所以要加上TextSize的大小
        posY = textSize + random.nextInt(windowHeight - textSize);

        //在初始化的时候，我们就给定一条弹幕一个随机的大小和颜色，使得每一条弹幕看起来都不一样。
        paint = new Paint();
        //设置字体的大小
        textSize = MINSIZE + random.nextInt(MAXSIZE-MINSIZE);
        paint.setTextSize(textSize);

        //设置画笔的颜色
        //color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        paint.setColor(colors[random.nextInt(7)]);
        //设置字体的宽度
        textWidth = 2 + random.nextInt(5);
        paint.setStrokeWidth(textWidth);
        //设置弹幕移动速度，随机值
        speed = 6 + random.nextInt(7);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mWidth==0){
            mWidth = getMeasuredWidth();
        }
        if(mWidth > 0){
            mPaint = getPaint();
            String text = getText().toString();
            int size;
            if(text.length()>0){
                size = mWidth*2/text.length();
            }else{
                size = mWidth;
            }
            if(isArrayColors){
                mGradient = new LinearGradient(-size,0,0,0,new int[]{getResources().getColor(R.color.purple), getResources().getColor(R.color.green),
                        getResources().getColor(R.color.blue)},
                        null, Shader.TileMode.CLAMP);

            }else{
                mGradient = new LinearGradient(-size,0,0,0,getResources().getColor(R.color.purple), getResources().getColor(R.color.green),
                        Shader.TileMode.CLAMP);
            }
            mPaint.setShader(mGradient);
            mGradientMatrix = new Matrix();

        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(getText(),posX,posY,paint);

        if(mAnimating && mGradientMatrix!=null){
            float mTextWidth = getPaint().measureText(getText().toString());//获得文字的宽度
            mTranslate+=delta;//默认颜色渐变向右侧移动
            if(mTranslate > mTextWidth+1 || mTranslate < 1){
                delta = -delta;
            }

            mGradientMatrix.setTranslate(mTranslate,0);
            mGradient.setLocalMatrix(mGradientMatrix);
        }

        if(rollThread==null){
            rollThread = new RollThread();
            rollThread.start();
        }

    }

    class RollThread extends Thread{
        private Object mPauseObject;//线程锁
        private boolean mPauseFlag;//线程是否挂起,线程是否暂停
        public RollThread(){
            mPauseObject = new Object();
            mPauseFlag = false;
        }

        @Override
        public void run() {
            super.run();
            while (true){
                checkPause();
                changePosition(speed);
                postInvalidate();
                SystemClock.sleep(60);
                //关闭线程逻辑判断
                if (needStopRollThread()) {
                    Log.e(getText(), getText() + "   -线程停止！");
                    //子控件通过getParent（）方法可以得到父控件，因此可以直接通过在子控件中移除子控件本身
                    post(new Runnable() {
                        @Override
                        public void run() {
                            //在这里要注意两点，第一移除控件的操作应该在主线程中进行，因为更新控件只能是在主线程
                            //view有自己的方法post可以在主线程中运行。第二子控件的getParent()方法得到的是view类型
                            //而View类型是没有removeView（）方法的，要强制转换为ViewGroup类型才可以移除自身

                            ((ViewGroup)BarrageView.this.getParent()).removeView(BarrageView.this);
                        }
                    });
                    if(onRollEndListener!=null){
                        onRollEndListener.onRollEnd();
                    }
                    break;
                }
            }
        }
        public void onPause(){
            synchronized (mPauseObject){
                mPauseFlag = true;
            }

        }
        public void onResume(){
            synchronized (mPauseObject){
                mPauseFlag = false;
                Log.e("TAG——————","线程唤醒");
                mPauseObject.notify();
            }
        }
        /**
         * 检测线程是否需要挂起
         */
        private void checkPause() {
            synchronized (mPauseObject){
                if(mPauseFlag){//如果线程需要挂起
                    Log.e("TAG","此线程需要被暂停");
                    try {
                        mPauseObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     那我们怎么知道什么时候暂停滚动（挂起线程）呢？
     经调查发现，View中有个方法叫onWindowVisibilityChanged(int visibility)，当view显示在窗口的时候，回调的visibility等于View.VISIBLE，
     当view不显示在窗口时，回调的visibility等于View.GONE。基于此，我们可以在这里进行判断什么时候暂停，什么时候恢复。
     * @return
     */
    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if(rollThread==null){
            return;
        }
        if(View.GONE == visibility){
            rollThread.onPause();
        }else{
            rollThread.onResume();
        }
    }

    //在弹幕消失在屏幕显示区域后就销毁线程
    private boolean needStopRollThread() {
        if (posX <= - paint.measureText(getText())){
            return true;
        }else{
            return false;
        }
    }


    private void changePosition(int speed) {
        posX-= speed;
    }

    public void setText(String msg){
        this.msg = msg;
    }
    public  String getText(){
        return msg;
    }
    /**
     * 关于资源回收
     在iOS中的UIView中有个方法叫removeFromSuperView，调用该方法就能达到资源回收的作用，在Android中没有这种方法，
     只能从父控件调用remove方法，我查了很久资料，也没查到是否这样做可以达到销毁View的作用，
     不过为此我还是在BarrageView中留了一个监听器，用来做销毁动作的。
     */

    public interface OnRollEndListener{
        void onRollEnd();
    }
    public void setOnRollEndListener(OnRollEndListener onRollEndListener){
        this.onRollEndListener = onRollEndListener;

    }
}
