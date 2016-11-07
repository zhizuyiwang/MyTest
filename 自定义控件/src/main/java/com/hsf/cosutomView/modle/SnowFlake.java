package com.hsf.cosutomView.modle;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * 雪花类：
 * 雪花的属性包含: 随机量, 位置, 增量, 大小, 角度, 画笔.
 * 绘画的过程中, 使用角度会移动点的位置, 每次速率都不同.
 * 当雪花移出屏幕时, 会重新使用, 在屏幕的顶端重新落下.
 * Created by Administrator on 2016/10/28.
 */

public class SnowFlake {
    //雪花的角度
    private static final float ANGE_RANGE = 0.1f; // 角度范围
    private static final float HALF_ANGLE_RANGE = ANGE_RANGE / 2f; // 一半的角度
    private static final float HALF_PI = (float) Math.PI / 2f; // 半PI
    private static final float ANGLE_SEED = 25f; // 角度随机种子
    private static final float ANGLE_DIVISOR = 10000f; // 角度的分母

    // 雪花的移动速度
    private static final float INCREMENT_LOWER = 2f;
    private static final float INCREMENT_UPPER = 4f;

    // 雪花的大小
    private static final float FLAKE_SIZE_LOWER = 7f;
    private static final float FLAKE_SIZE_UPPER = 20f;

    private final RandomGenerator mRandom; // 随机控制器
    private final Point mPosition; // 雪花位置
    private float mAngle; // 角度
    private final float mIncrement; // 雪花的速度
    private final float mFlakeSize; // 雪花的大小
    private final Paint mPaint; // 画笔

    private SnowFlake(RandomGenerator random, Point position, float angle, float increment, float flakeSize, Paint paint) {
        mRandom = random;
        mPosition = position;
        mIncrement = increment;
        mFlakeSize = flakeSize;
        mPaint = paint;
        mAngle = angle;
    }

    /**
     * 生产雪花的方法（包括位置，随机器，速度，大小，画笔，角度）
     * @param width
     * @param height
     * @param paint
     * @return
     */
    public static SnowFlake createSnowFlake(int width,int height,Paint paint){
        RandomGenerator mRandom = new RandomGenerator();
        int x = mRandom.getRandom(width);
        int y = mRandom.getRandom(height);
        Point position = new Point(x, y);
        float angle = mRandom.getRandom(ANGLE_SEED) / ANGLE_SEED * ANGE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
        float increment = mRandom.getRandom(INCREMENT_LOWER, INCREMENT_UPPER);
        float flakeSize = mRandom.getRandom(FLAKE_SIZE_LOWER, FLAKE_SIZE_UPPER);
        return new SnowFlake(mRandom, position, angle, increment, flakeSize, paint);
    }

    // 绘制雪花
    public void draw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        move(width, height);
        canvas.drawCircle(mPosition.x, mPosition.y, mFlakeSize, mPaint);
    }

    // 移动雪花
    private void move(int width, int height) {
        double x = mPosition.x + (mIncrement * Math.cos(mAngle));
        double y = mPosition.y + (mIncrement * Math.sin(mAngle));

        mAngle += mRandom.getRandom(-ANGLE_SEED, ANGLE_SEED) / ANGLE_DIVISOR; // 随机晃动

        mPosition.set((int) x, (int) y);

        // 移除屏幕, 重新开始
        if (!isInside(width, height)) {
            reset(width);
        }
    }

    private void reset(int width) {
        mPosition.x = mRandom.getRandom(width);
        mPosition.y = (int) (-mFlakeSize - 1); // 最上面
        mAngle = mRandom.getRandom(ANGLE_SEED) / ANGLE_SEED * ANGE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
    }

    private boolean isInside(int width, int height) {
        int x = mPosition.x;
        int y = mPosition.y;
        return x >= -mFlakeSize - 1 && x + mFlakeSize <= width && y >= -mFlakeSize - 1 && y - mFlakeSize < height;
    }


}

