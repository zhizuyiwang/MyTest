package com.hsf.myDialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/23.
 */

public class MyBuilderAcapter extends BaseAdapter {
    private String[] data;
    private Context context;

    public MyBuilderAcapter(String[] data,Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.length;
    }


    @Override
    public Object getItem(int position) {
        return getItem(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.dialog_item,null);
        TextView textView = (TextView) convertView.findViewById(R.id.tv);
        textView.setText(data[position]);
        return convertView;
    }
}
