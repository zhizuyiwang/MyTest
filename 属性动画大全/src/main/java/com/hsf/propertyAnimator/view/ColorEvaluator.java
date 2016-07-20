package com.hsf.propertyAnimator.view;

import android.animation.TypeEvaluator;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ColorEvaluator implements TypeEvaluator {
    private int mCureentRed = -1;
    private int mCureentGreen = -1;
    private int mCureentBlue = -1;
    @Override
    public Object evaluate(float fraction, Object start, Object end) {
        String startColor = (String) start;
        String endColor = (String) end;

        int startRed = Integer.parseInt(startColor.substring(1,3),16);
        int startGreen = Integer.parseInt(startColor.substring(3,5),16);
        int startBlue = Integer.parseInt(startColor.substring(5,7),16);
        int endRed = Integer.parseInt(endColor.substring(1,3),16);
        int endGreen = Integer.parseInt(endColor.substring(3,5),16);
        int endBlue = Integer.parseInt(endColor.substring(5,7),16);

        //初始化颜色值
        if(mCureentRed == -1){
            mCureentRed = startRed;
        }
        if(mCureentGreen == -1){
            mCureentGreen = startGreen;
        }
        if(mCureentBlue == -1){
            mCureentBlue = startBlue;
        }
        //计算初始颜色和结束颜色之间的差值
        int difRed = Math.abs(startRed - endRed);
        int difGreen = Math.abs(startGreen - endGreen);
        int difBlue = Math.abs(startBlue - endBlue);
        int difColor = difRed + difGreen + difBlue;

        if(mCureentRed != endRed){

            mCureentRed = getCureentColor(startRed,endRed,difColor,0,fraction);

        }else if(mCureentGreen != endGreen){

            mCureentGreen = getCureentColor(startGreen,endGreen,difColor,difRed,fraction);

        }else if(mCureentBlue != endBlue){

            mCureentBlue = getCureentColor(startBlue,endBlue,difColor,difRed+difGreen,fraction);
        }
        //将计算的得出的颜色值组装后返回
        String cureentColor = "#"+getHexColor(mCureentRed) + getHexColor(mCureentGreen) + getHexColor(mCureentBlue);

        return cureentColor;
    }


    /**将十进制颜色值转换为十六进制
     * @param value
     * @return
     */
    private String getHexColor(int value) {
        String hexString = Integer.toHexString(value);
        if(hexString.length()==1){
            hexString = "0"+hexString;
        }
        return hexString;
    }

    /**
     * 根据fraction的值，计算当前颜色
     * @param startColor
     * @param endColor
     * @param difColor
     * @param offSet
     * @param fraction
     * @return
     */
    public int getCureentColor(int startColor,int endColor,int difColor,int offSet, float fraction){
        int cureentColor = 0 ;

        if(startColor > endColor){
            cureentColor = (int) (startColor - (fraction*difColor - offSet));
            if(cureentColor<endColor){
                cureentColor = endColor;
            }
        }else if(startColor < endColor){
            cureentColor = (int) (startColor + (fraction*difColor - offSet));
            if(cureentColor > endColor){
                cureentColor = endColor;
            }
        }
        return cureentColor;
    }
}
