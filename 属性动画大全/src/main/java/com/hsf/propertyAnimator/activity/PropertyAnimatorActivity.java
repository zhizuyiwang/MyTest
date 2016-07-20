package com.hsf.propertyAnimator.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hsf.propertyAnimator.R;

public class PropertyAnimatorActivity extends AppCompatActivity implements View.OnClickListener{
    private Button value;
    private TextView tv_status;
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
    private Animator animator;

    private int flag = 0;
    private int flag1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animator);
        initView();
    }

    private void initView() {
        value = (Button) findViewById(R.id.value_btn);
        value.setOnClickListener(this);
        tv_status = (TextView) findViewById(R.id.tv_status);
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
        tv2= (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);


    }

    ////同一个对象设置多个属性动画，会同时执行。可见，属性动画实例调用start方法后，是一个异步的过程
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.value_btn:
                //表示数值从0到5到3最后到8
                ValueAnimator anim = ValueAnimator.ofFloat(0f,5f,3f,8f);
                anim.setDuration(5000);
                //anim.setInterpolator(new AccelerateDecelerateInterpolator());//动画的加速器
                //anim.setRepeatCount(1);//动画的重复次数
                //anim.setRepeatMode(2);//动画的执行模式

                //可以对动画执行过程进行监听
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float currentValue = (float) valueAnimator.getAnimatedValue();
                        Log.e("TAG",currentValue+"");
                    }
                });
                anim.start();
                tv_status.setText("我是ValueAnimator，只是对数值值进行了一个平滑的动画过渡");
                break;
            case R.id.object_btn://透明度
                ObjectAnimator obj = ObjectAnimator.ofFloat(tv1,"alpha",1f,0,1f);
                obj.setDuration(5000);
                obj.start();
                break;
            case R.id.object_btn1://缩放
                //在垂直方向上缩放
                ObjectAnimator obj1 = null;
                if( flag % 3 == 0){
                    //在垂直方向上缩放
                    obj1 = ObjectAnimator.ofFloat(tv2,"scaleY",1f,3f,1f);
                    Toast.makeText(PropertyAnimatorActivity.this,"垂直方向上缩放",Toast.LENGTH_SHORT).show();
                }else if(flag % 3 == 1){//没有效果
                    obj1 = ObjectAnimator.ofFloat(tv2,"scaleX",1f,3f,1f);
                    Toast.makeText(PropertyAnimatorActivity.this,"水平方向上缩放",Toast.LENGTH_SHORT).show();
                }else if(flag % 3 == 2){
                    obj1 = ObjectAnimator.ofFloat(tv2,"scale",1f,3f,1f);
                    Toast.makeText(PropertyAnimatorActivity.this,"中心上缩放",Toast.LENGTH_SHORT).show();
                }
                obj1.setDuration(5000);
                obj1.start();
                flag++;
                break;
            case R.id.object_btn2://平移
                float currentTranslationX = tv3.getTranslationX();
                ObjectAnimator obj2 = ObjectAnimator.ofFloat(tv3,"translationX",currentTranslationX,-1000f,currentTranslationX);
                obj2.setDuration(5000);
                obj2.start();
                //对动画进行监听，包括，动画开始，动画结束，动画重复，动画取消
                obj2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
                break;
            case R.id.object_btn3://旋转
                ObjectAnimator obj4 = null;
                if(flag1 % 3 == 0){
                    //中心旋转
                    obj4 = ObjectAnimator.ofFloat(tv4,"rotation",0f,360f);
                }else if(flag1 % 3 == 1){
                    //绕X轴旋转
                    obj4 = ObjectAnimator.ofFloat(tv4,"rotationX",0f,360f);
                }else if(flag1 % 3 == 2){
                    //绕Y轴旋转
                    obj4 = ObjectAnimator.ofFloat(tv4,"rotationY",0f,360f);
                }
                obj4.setDuration(5000);
                obj4.start();
                flag1++;
                break;
            case R.id.object_btn4://组合动画
                /**实现组合动画功能主要需要借助AnimatorSet这个类，这个类提供了一个play()方法，如果我们向这个方法中传入一个Animator对象
                (ValueAnimator或ObjectAnimator)将会返回一个AnimatorSet.Builder的实例，AnimatorSet.Builder中包括以下四个方法：
                after(Animator anim)   将现有动画插入到传入的动画之后执行
                after(long delay)   将现有动画延迟指定毫秒后执行
                before(Animator anim)   将现有动画插入到传入的动画之前执行
                with(Animator anim)   将现有动画和传入的动画同时执行
                (多个动画需求不同时时推荐)，利用AnimatorSet，组合多个Animation，可以对多个动画属性进行顺序控制
                 同时执行：set.playTogether(animator1,animator2,animator3)
                 顺序执行：set.playSequentially(animator1,animator2,animator3)
                 分布执行：play().with();
                         play().after();
                 在写一个小动画时发现AnimatorSet没有setRepeatCount()与setRepeatMode()方法，但是动画效果又要求重复执行，这里提供的解决方法时：

                 给动画集合中的子动画设置setRepeatCount(),想重复执行多少次动画就设置多少次，如果想无限循环，这里直接设置int的最大值应该就可以了
                */
                ObjectAnimator moveTo = ObjectAnimator.ofFloat(tv5,"translationX",-500f,0f);
                ObjectAnimator rotation = ObjectAnimator.ofFloat(tv5,"rotation",0f,360f);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(tv5,"alpha",1f,0f,1f);
                AnimatorSet set = new AnimatorSet();
                set.setDuration(5000);
                set.play(rotation).with(alpha).after(moveTo);
                set.start();
                break;
            case R.id.object_btn5://使用XML动画
                /**
                 * 在XML文件中我们一共可以使用如下三种标签：
                 *<animator>  对应代码中的ValueAnimator
                 *<objectAnimator>  对应代码中的ObjectAnimator
                 *<set>  对应代码中的AnimatorSet
                 */

                animator = AnimatorInflater.loadAnimator(PropertyAnimatorActivity.this, R.animator.animation_set);
                animator.setTarget(tv6);
                animator.start();
                break;
            case R.id.stop://停止XML组合动画
                if(animator!=null && animator.isRunning()){
                    animator.end();
                }
                break;
        }

    }
}
