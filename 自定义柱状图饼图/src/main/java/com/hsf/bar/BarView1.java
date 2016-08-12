package com.hsf.bar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Administrator on 2016/8/11.
 */
public class BarView1 extends View {
    /**
     * 首先，分析一下需要画什么东西，也就是一个柱状图的主要要素
     * 1、横、纵坐标轴
     * 2、横纵坐标轴刻度线
     * 3、横纵坐标轴刻度值
     * 4、横纵坐标轴箭头
     * 5、柱状图的显示
     * 6、柱状图每个柱子的名称及颜色、字体大小
     * 7、柱状图每个柱子的数值
     * 8、柱状图的颜色
     * 9、柱状图的标题
     * @param context
     */
    private  final String TAG = BarView1.class.getName();
    //画笔
    private Paint mPaint;
    //标题
    private String title;
    //标题颜色
    private int titleColor;
    //标题大小
    private float titleSize;
    //X坐标轴最大值
    private float maxAxisValueX = 900;
    //X坐标轴刻度线数量
    private int axisDivideSizeX = 9;
    //Y坐标轴最大值
    private float maxAxisValueY = 700;
    //Y坐标轴刻度线数量
    private int axisDivideSizeY = 7;
    //视图宽度
    private int width;
    //视图高度
    private int height;
    //坐标原点位置
    private final int originX = 50;
    private final int originY = 200;
    //柱状图数据
    private int columnInfo[][];



    public BarView1(Context context) {
        super(context);
        initPain();
    }

    public BarView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPain();
    }

    public BarView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPain();
    }


    private void initPain() {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawXAxis(canvas,mPaint);
        drawYAxis(canvas,mPaint);
        drawBarChart(canvas,mPaint);
    }

    /**
     * 画图表的各个柱子
     */
    private void drawBarChart(Canvas canvas, Paint mPaint) {

    }

    /**
     * 根据view的大小画出图表的y轴
     * @param canvas
     * @param mPaint
     */
    private void drawYAxis(Canvas canvas, Paint mPaint) {
    }

    /**
     * 根据view的大小画出图表的x轴
     * @param canvas
     * @param mPaint
     */
    private void drawXAxis(Canvas canvas, Paint mPaint) {

    }


    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
    /**
     * sp 2 px
     *
     * @param spVal
     * @return
     */
    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());
    }

}
