package com.hsf.propertyAnimator.activity;

import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.hsf.propertyAnimator.R;

/**
 * 补间动画只对view有效果
 *
 * 无需逐一定义每一帧，只要定义开始、结束的帧，和指定动画持续时间。
 补间动画有4种（均为Animation抽象类子类）：
 AlphaAnimation（透明度，0~1）
 ScaleAnimation（大小缩放，X、Y轴缩放，还包括缩放中心pivotX、pivotY）
 TranslationAnimation（位移，X、Y轴位移）
 RotateAnimation（旋转，包括缩放中心pivotX、pivotY）

 有一个属性，可以控制速度，即为Interpolator（插值），有以下几种（Interpolator的实现类):
 LinearInterpolator(匀速）
 AccelerateInterpolator（先慢后快）
 AccelerateDecelerateInterpolator（先慢中快后慢）
 DecelerateInterpolator（先快后慢）
 CycleInterpolator（循环播放，速度为正弦曲线）
 AnticipateInterpolator（先回撤，再匀速向前）
 OvershootInterpolator（超过，拉回）
 BounceInterpolator(回弹）
 *
 */
public class BetweenAnimatorActivity extends AppCompatActivity implements View.OnClickListener {
    private Button value1;
    private Button value2;
    private Button value3;
    private Button value4;
    private Button value5;
    private Button value6;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private Button stop;
    private Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_between_animator);
        initView();
    }

    private void initView() {

        value1 = (Button) findViewById(R.id.object_btn);
        value1.setOnClickListener(this);
        value2 = (Button) findViewById(R.id.object_btn1);
        value2.setOnClickListener(this);
        value3 = (Button) findViewById(R.id.object_btn2);
        value3.setOnClickListener(this);
        value4 = (Button) findViewById(R.id.object_btn3);
        value4.setOnClickListener(this);
        value5 = (Button) findViewById(R.id.object_btn4);
        value5.setOnClickListener(this);
        value6 = (Button) findViewById(R.id.object_btn5);
        value6.setOnClickListener(this);
        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(this);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
    }

    /**
     * 启动补间动画有两种方法
     * 1、先把动画设置到view中，再使动画自己去执行
     * 2、由view去启动动画，并把动画实例传过去
     * 两者的区别是第一种只能启动一次动画，第二种可以任意启动
     * */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.object_btn:
                AlphaAnimation alpha = new AlphaAnimation(1f,0f);
                alpha.setDuration(2000);
                //设置动画结束后保留效果
                //alpha.setFillAfter(true);
                // alpha.setRepeatCount(Animation.INFINITE);
                // alpha.setRepeatMode(Animation.REVERSE);
                // alpha.setStartOffset(1000);//动画延迟多长时间执行
                // tv1.setAnimation(alpha);//也可以先把动画设置到view中，再把动画自己启动起来
                // alpha.startNow();
                tv1.startAnimation(alpha);
                break;
            case R.id.object_btn1:

                /**
                 * ScaleAnimation(float fromX, float toX, float fromY,
                 * float toY,int pivotXType, float pivotXValue, int pivotYType, float pivotYValue):
                 *
                 * float fromX 动画起始时 X坐标上的伸缩尺寸
                 * float toX 动画结束时 X坐标上的伸缩尺寸
                 * float fromY 动画起始时Y坐标上的伸缩尺寸
                 * float toY 动画结束时Y坐标上的伸缩尺寸
                 * int pivotXType 动画在X轴相对于物件位置类型
                 * float pivotXValue 动画相对于物件的X坐标的开始位置
                 * int pivotYType 动画在Y轴相对于物件位置类型
                 * float pivotYValue 动画相对于物件的Y坐标的开始位置
                 *
                 * 以下是Animation的定义的常量值，如：重复模式和重复次数等数值
                 * public static final int ABSOLUTE = 0;
                 * public static final int INFINITE = -1;
                 * public static final int RELATIVE_TO_PARENT = 2;
                 * public static final int RELATIVE_TO_SELF = 1;
                 * public static final int RESTART = 1;
                 * public static final int REVERSE = 2;
                 * public static final int START_ON_FIRST_FRAME = -1;
                 * public static final int ZORDER_BOTTOM = -1;
                 * public static final int ZORDER_NORMAL = 0;
                 * public static final int ZORDER_TOP = 1;
                 *
                 * */
                ScaleAnimation scale = new ScaleAnimation(0f,1.4f,1f,1.4f,Animation.RELATIVE_TO_SELF,
                        0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                scale.setDuration(3000);
                scale.setStartOffset(1000);
                //scale.setFillAfter(true);
                tv2.startAnimation(scale);
                break;
            case R.id.object_btn3://旋转
                /**
                 * float fromDegrees：旋转的开始角度。
                 * float toDegrees：旋转的结束角度。
                 * int pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
                 * float pivotXValue：X坐标的伸缩值。
                 * int pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
                 * float pivotYValue：Y坐标的伸缩值。
                 * */
                RotateAnimation rotation = new RotateAnimation(0,-360,Animation.RELATIVE_TO_SELF,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f);
                //RotateAnimation rotation1 = new RotateAnimation(0,359,);
                rotation.setDuration(3000);
                //tv4.setAnimation(rotation);
                //rotation.startNow();
                 tv4.startAnimation(rotation);
                //tv4.clearAnimation();//停止动画
                //rotation.cancel();//停止动画
                break;
            case R.id.object_btn4://组合
                AlphaAnimation alpha1 = new AlphaAnimation(1f,0f);
                TranslateAnimation tra = new TranslateAnimation(0,150,0,0);
                RotateAnimation ro = new RotateAnimation(0,360,0.5f,0.5f);
                AnimationSet set = new AnimationSet(true);
                set.addAnimation(alpha1);
                set.addAnimation(tra);
                set.addAnimation(ro);
                set.setDuration(3000);
                tv5.startAnimation(set);
                break;
            case R.id.object_btn5://XML进行动画操作
                anim = AnimationUtils.loadAnimation(BetweenAnimatorActivity.this, R.anim.between_set);
                anim.setDuration(5000);
                tv6.startAnimation(anim);

                break;
            case R.id.object_btn2://平移
                /**
                 * float fromXDelta 动画开始的点离当前View X坐标上的差值
                 * float toXDelta 动画结束的点离当前View X坐标上的差值
                 * float fromYDelta 动画开始的点离当前View Y坐标上的差值
                 * float toYDelta 动画开始的点离当前View Y坐标上的差值
                 * */
                TranslateAnimation translation = new TranslateAnimation(0,150,0,0);
                translation.setDuration(2000);
                translation.setRepeatCount(5);
                translation.setRepeatMode(Animation.REVERSE);
                translation.setFillAfter(true);
                tv3.startAnimation(translation);
                break;
            case R.id.stop:
                //发现这里用anim.cancel()方法不管用
                tv6.clearAnimation();
                break;
        }

    }
}
