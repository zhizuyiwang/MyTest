package com.hsf.myRecyclerView.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hsf.myRecyclerView.bean.Bean;
import com.hsf.myRecyclerView.R;
import com.hsf.myRecyclerView.utils.UtilTools;
import com.hsf.myRecyclerView.view.SyncHorizontalScrollView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LinkageListViewActivity extends AppCompatActivity {


    @Bind(R.id.myScrollView)
    SyncHorizontalScrollView myScrollView;
    @Bind(R.id.listView1)
    ListView listView1;
    @Bind(R.id.container1)
    LinearLayout container1;
    @Bind(R.id.listView2)
    ListView listView2;
    @Bind(R.id.container2)
    LinearLayout container2;
    @Bind(R.id.container_scrollView)
    SyncHorizontalScrollView containerScrollView;
    @Bind(R.id.activity_linkage_list_view)
    LinearLayout activityLinkageListView;

    private MyLeftAdapter leftAdapter;
    private MyRightAdapter rightAdapter;
    private ArrayList<String> data1;
    private ArrayList<Bean> data2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkage_list_view);
        ButterKnife.bind(this);
        intData();
        leftAdapter = new MyLeftAdapter();
        rightAdapter = new MyRightAdapter();
        //设置两个水平控件的联动
        myScrollView.setScrollView(containerScrollView);
        containerScrollView.setScrollView(myScrollView);

        listView1.setAdapter(leftAdapter);
        //这里需要注意的是在ScrollView中使用ListView时,需要计算ListView子项目的高度,否则可能只给你显示一行
        //UtilTools.setListViewHeightBasedOnChildren(listView1);
        listView2.setAdapter(rightAdapter);
        listView2.getParent().requestDisallowInterceptTouchEvent(true);
        //UtilTools.setListViewHeightBasedOnChildren(listView2);
    }

    private void intData() {
        data1 = new ArrayList<>();
        data2 = new ArrayList<>();
        data1.add("指标1");
        data1.add("指标2");
        data1.add("指标3");
        data1.add("指标4");
        data1.add("指标5");
        data1.add("指标6");
        data1.add("指标7");
        data1.add("指标8");
        data1.add("指标9");
        data1.add("指标10");
        data1.add("指标11");
        data1.add("指标12");
        data1.add("指标13");
        data1.add("指标14");
        data1.add("指标15");
        data1.add("指标16");
        data1.add("指标17");
        data1.add("指标18");
        data1.add("指标19");
        data1.add("指标20");
        data1.add("指标21");
        data1.add("指标22");

        for (int i = 1; i<=22;i++){
            if(1==22){
                data2.add(new Bean("aaaaa"+i,"bbbbb"+i,"ccccc"+i,"ddddd"+i,"eeeee"+i,"fffff3wwwwwwwwwwwww"+i));
            }else{
                data2.add(new Bean("aaaaa"+i,"bbbbb"+i,"ccccc"+i,"ddddd"+i,"eeeee"+i,"fffff223222222222233"+i));
            }

        }


    }

    public class MyLeftAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return data1.size();
        }


        @Override
        public Object getItem(int position) {
            return null;
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.list1_layout,null);
            TextView tv = (TextView) convertView.findViewById(R.id.tv);
            tv.setText(data1.get(position));
            return convertView;
        }
    }
    public class MyRightAdapter extends BaseAdapter{
        
        @Override
        public int getCount() {
            return data2.size();
        }


        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.list2_layout,null);
            TextView tv1 = (TextView) convertView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) convertView.findViewById(R.id.tv2);
            TextView tv3 = (TextView) convertView.findViewById(R.id.tv3);
            TextView tv4 = (TextView) convertView.findViewById(R.id.tv4);
            TextView tv5 = (TextView) convertView.findViewById(R.id.tv5);
            final TextView tv6 = (TextView) convertView.findViewById(R.id.tv6);
            tv6.setOnClickListener(new View.OnClickListener() {
                Boolean flag = true;
                @Override
                public void onClick(View v) {
                    if(flag){

                        flag = false;
                        tv6.setEllipsize(null); // 展开
                        //tv.setSingleLine(flag);
                    }else{
                        flag = true;
                        tv6.setEllipsize(TextUtils.TruncateAt.END); // 收缩
                        //tv.setSingleLine(flag);
                    }
                }
            });

            tv1.setText(data2.get(position).msg1);
            tv2.setText(data2.get(position).msg2);
            tv3.setText(data2.get(position).msg3);
            tv4.setText(data2.get(position).msg4);
            tv5.setText(data2.get(position).msg5);
            tv6.setText(data2.get(position).msg6);
            return convertView;
        }
    }
}
