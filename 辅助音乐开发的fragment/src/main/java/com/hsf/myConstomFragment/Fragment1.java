package com.hsf.myConstomFragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsf.myConstomFragment.view.BarrageView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Random;

/**
 * 城市
 * Created by Administrator on 2016/8/2.
 */
public class Fragment1 extends Fragment{
    private RelativeLayout content;
    //添加弹幕的时间间隔
    private static final int DALY_TIME = 800;
    private Random random;
    private String[] msgs;
    private boolean pauseFlag;
    private ArrayList<TextView> list = new ArrayList<TextView>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment1,container,false);
        content = (RelativeLayout) view.findViewById(R.id.content);
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


                if(!isHidden() && pauseFlag){
                    Log.e("TAG","创建新的弹幕线程");
                    BarrageView barrageView = new BarrageView(getContext());
                    barrageView.setText(msgs[random.nextInt(msgs.length)]);
                    content.addView(barrageView,lp);

                }else{
                    if(isHidden()){

                        for(int i = 0; i<container.getChildCount();i++){
                            if(container.getChildAt(i) instanceof TextView){
                                list.add((TextView) container.getChildAt(i));
                            }
                        }
                        for (int i = 0;i<list.size();i++){
                            content.removeView(list.get(i));
                        }
                    }
                }

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

    //在这里加上判断条件，是因为当不可见时，创建弹幕的线程是不会停止的，而当可见时，所有的线程全都显示出来
    //因此要保证在不可见时不要创建新的弹幕，在可见时再创建。但问题出现了：
    //在返回桌面后虽然不创建新的弹幕了，但之前创建的弹幕依然会在回到桌面后运行滚动，导致在重新进入弹幕界面后，弹幕
    //要重新开始，所以想实现这样一个功能，就是给弹幕添加个暂停的功能，在回到桌面后，原来已经创建的弹幕线程停止，不要继续滚动
    //在回到弹幕界面后，再让弹幕从原来的位置继续滚动。这个功能的思路是，由于弹幕滚动是一个线程决定的，在BarrageView中自定义了
    //一个RollThread，我们可以给线程添加两个方法，一个是暂停（挂起），一个是继续(恢复)
    @Override
    public void onResume() {
        super.onResume();
        pauseFlag = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        pauseFlag = false;
    }
}
