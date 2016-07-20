package com.hsf.propertyAnimator.view;

import android.animation.TimeInterpolator;

/**
 * 自定义一个加速器，系统内部实现了很多的加速器，都是大同小异，主要区别在于实现的getInterpolation()方法的
 * 返回值不同，传入的参数input决定着fraction的值，input是在0到1之间变化的，根据动画执行的时间确定input的变化率，
 * input是均匀变化的，所以匀速的加速器内部getInterpolation()的返回值就是input本身.
 * 因此自定义的加速器主要是在getInterpolation()方法中编写计算fraction值的逻辑
 * 例如：系统默认的加速器是先加速后减速，我们自定义一个先减速后加速的补间器
 * Created by Administrator on 2016/7/20.
 */
public class CustomInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float fraction;
        if(input <= 0.5f){
            fraction = (float)( Math.sin(Math.PI*input))/2;
        }else{
            fraction = (float) (2-Math.sin(Math.PI*input))/2;
        }
        return fraction;
    }
}
