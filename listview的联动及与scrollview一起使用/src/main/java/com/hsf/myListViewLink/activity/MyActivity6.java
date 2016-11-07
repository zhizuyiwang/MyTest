package com.hsf.myListViewLink.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hsf.myListViewLink.R;

public class MyActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my6);
        /**
         *LinearLayout 特有的 Android:layout_weight 和 android:weightSum
         * 假设：我们要在一个盒子里放置其他物体。盒子可用空间的比例就是weightSum，盒子中每个物体可用空间的比例就是layout_weight。
         * 例如，盒子的weightSum是1，我们需要往盒子里放置两个物体：物体A和物体B。物体A的layout_weight为0.25，物体B的layout_weight为0.75。
         * 那么，物体A可以占据盒子25%的空间，而物体B可以占据剩下的75%的空间
         *
         *
         */
    }
}
