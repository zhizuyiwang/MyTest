package com.hsf.myConstomFragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hsf.myConstomFragment.view.BarrageView;

import java.security.acl.Group;
import java.util.Random;

/**
 * 城市
 * Created by Administrator on 2016/8/2.
 */
public class Fragment1 extends Fragment{
    private LinearLayout content;
    //添加弹幕的时间间隔
    private static final int DALY_TIME = 800;
    private Random random;
    private String[] msgs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment1,container,false);
        initDate();

        final Handler handler = new Handler();
        //设置弹幕的宽高为全屏
        final ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        final Runnable createView = new Runnable() {
            @Override
            public void run() {
                //在Activity中有个addContentView(view, lp)方法能够新加view到根视图下。
                //lp动态设置为全屏的方法是new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                //handler.postDelayed(this, DELAY_TIME);的意思是弹幕出来的时间不一致，这样就不会像兵列一样整齐地出来了。

                BarrageView barrageView = new BarrageView(getContext());
                barrageView.setText(msgs[random.nextInt(msgs.length)]);
                container.addView(barrageView,lp);
                handler.postDelayed(this,DALY_TIME);
            }
        };
        handler.post(createView);
        return view;

    }

    /**
     * 初始化数据
     */
    private void initDate() {
        random = new Random();
        msgs = new String[]{"你好啊！！！","徒弟最聪明了","看，你的鞋带开了","hahahha"};

    }
}
