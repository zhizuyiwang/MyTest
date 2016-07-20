package com.hsf.propertyAnimator.view;

import android.animation.TypeEvaluator;

/**
 * Created by Administrator on 2016/7/19.
 * 此类是自定义的实现从初始化对象到最终对象的过度方式
 */
public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startEvaluator, Object endEvaluator) {
        Point start = (Point) startEvaluator;
        Point end = (Point) endEvaluator;
        float x = start.getX()+fraction*(end.getX()-start.getX());
        float y = start.getY()+fraction*(end.getY()-start.getY());
        Point point = new Point(x,y);
        return point;
    }
}
