package com.hsf.myRecyclerView.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hsf.myRecyclerView.R;
import com.hsf.myRecyclerView.adapter.MySimpleAdapter;
import com.hsf.myRecyclerView.view.MyDecoration;

import java.util.ArrayList;

public class SimpleRecyclerViewActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<String> lists;
    private MySimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_simple_recycler_view);

        initData();
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//设置RecyclerView的布局管理
        recyclerView.addItemDecoration(new MyDecoration(this,LinearLayoutManager.VERTICAL));//设置RecyclerView中item的分割线，用的少，一般都用在item中设置margin分隔两个item
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置item的添加删除动画，采用默认的动画效果
        adapter = new MySimpleAdapter(this,lists);
        adapter.setOnItemClickListener(new MySimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(SimpleRecyclerViewActivity.this,"点击的是："+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Snackbar.make(view,"长按的是："+position,Snackbar.LENGTH_SHORT).setAction("action",null).show();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_add:
                lists.add(1, "add");
//                adapter.notifyDataSetChanged();//用这个则可以更新数据，但是没有动画效果
                adapter.notifyItemInserted(1);//注意：RecyclerView中添加用notifyItemInserted()；才有动画效果
                break;
            case R.id.action_delete:
                lists.remove(1);
                adapter.notifyItemRemoved(1);//RecyclerView中删除用notifyItemRemoved()；才有动画效果
                break;
            case R.id.action_linear:
                recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));//设置RecyclerView的GridLayoutManager布局管理,默认垂直，还有一个设置水平排列的构造方法
                break;
            case R.id.action_grid:
                recyclerView.setLayoutManager(new GridLayoutManager(this,3));//设置RecyclerView的GridLayoutManager布局管理，默认为垂直
                break;
            case R.id.action_grid_horizontal:
                recyclerView.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false));//设置RecyclerView的GridLayoutManager的水平布局管理，参数分别为context，列数或行数，排列方式，是否反转布局的内容
                break;
            case R.id.action_staggeredgrid:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));//设置RecyclerView的StaggeredGridLayoutManager的布局管理，它是GridLayout升级版，可以显示交错式网格布局，参数分别为列数或行数，排列方式
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData(){
        lists = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++)
        {
            lists.add(""+(char)i);
        }
    }

}
