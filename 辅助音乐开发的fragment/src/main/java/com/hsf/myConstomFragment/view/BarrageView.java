package com.hsf.myConstomFragment.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

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
    private Random random;
    private String msg;
    private static final int MAXSIZE = 60;
    private static final int MINSIZE = 20;

    private RollThread rollThread;

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
        paint.setColor(Color.BLACK);
        //设置字体的宽度
        textWidth = 2 + random.nextInt(5);
        paint.setStrokeWidth(textWidth);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(getText(),posX,posY,paint);
        if(rollThread==null){
            rollThread = new RollThread();
            rollThread.start();
        }

    }

    class RollThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                changePosition();
                postInvalidate();
                SystemClock.sleep(60);
                //关闭线程逻辑判断
                if (needStopRollThread()) {
                    Log.e(getText(), getText() + "   -线程停止！");
                    break;
                }
            }

        }
    }

    private boolean needStopRollThread() {
        if (posX <= - paint.measureText(getText())){
            return true;
        }else{
            return false;
        }
    }

    private void changePosition() {
        posX-=8;
    }

    public void setText(String msg){
        this.msg = msg;
    }
    public  String getText(){
        return msg;
    }

}
