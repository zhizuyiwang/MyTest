package com.hsf.cosutomView.modle;

import java.util.Random;

/**
 * 随机生成器
 * Created by Administrator on 2016/10/28.
 */

public class RandomGenerator {
    private static final Random RANDOM = new Random();
    // 区间随机
    public float getRandom(float lower, float upper) {
        float min = Math.min(lower, upper);
        float max = Math.max(lower, upper);
        return getRandom(max - min) + min;
    }
    public float getRandom(float upper) {
        return RANDOM.nextFloat() * upper;
    }
    public int getRandom(int width) {
        return RANDOM.nextInt(width);
    }
}
