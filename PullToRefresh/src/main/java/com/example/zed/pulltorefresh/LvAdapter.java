package com.example.zed.pulltorefresh;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by guo xiao xiao on 2016/6/23.
 */
public class LvAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public LvAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Animation a;
    float x;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView tv = new TextView(context.getApplicationContext());
        tv.setText(list.get(position) + position);
        tv.setHeight(50);
        tv.setTextSize(20);
        tv.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        float X = event.getX();

                        int disx = (int)(X - x);
                        int[] location=new int[2];
                        tv.getLocationOnScreen(location);
                        tv.setPadding(disx,0,0,0);



                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("触摸事件","手指抬起了");
                        tv.setPadding(0,0,0,0);

                        break;

                    default:

                        break;
                }


                return true;
            }
        });
        return tv;
    }
}
