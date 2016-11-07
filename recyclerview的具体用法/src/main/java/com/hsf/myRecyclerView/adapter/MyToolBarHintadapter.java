package com.hsf.myRecyclerView.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsf.myRecyclerView.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public class MyToolBarHintadapter extends RecyclerView.Adapter<MyToolBarHintadapter.MyViewHolder>{

    private Context context;
    private ArrayList<Integer> data;
    private List<Integer> lists;

    public MyToolBarHintadapter(Context context,ArrayList<Integer> data){
        this.context = context;
        this.data = data;
        getRandomHeights(data);
    }
    private void getRandomHeights(List<Integer> data) {
        lists = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            lists.add((int) (200 + Math.random() * 400));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lay_out_item,parent,false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = lists.get(position);//把随机的高度赋予item布局
        holder.itemView.setLayoutParams(params);
        holder.tv.setText(position+"");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv ;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
