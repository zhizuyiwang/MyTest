package com.hsf.myRecyclerView.activity;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hsf.myRecyclerView.R;
import com.hsf.myRecyclerView.adapter.MyViewPagerAdapter;
import com.hsf.myRecyclerView.fragment.FragmentOne;
import com.hsf.myRecyclerView.fragment.FragmentThree;
import com.hsf.myRecyclerView.fragment.FragmentTwo;

import java.util.ArrayList;

/**
 * 隐藏系统自带的actionBar有三种方式：
 * 第一种是在Application中的主题设置为NoActionBar
 * 因为第一种的权限很大，用了这种该app的所有Activity都没有ActionBar了，所以有了第二种
 * 第二种是在需要隐藏ActionBar的activity中设置一个隐藏ActionBar的主题
 * 一般如下：
 <style name="AppTheme.NoActionBar">
 <item name="windowActionBar">false</item>
 <item name="windowNoTitle">true</item>
 </style>
    第三种：是在代码中用getSupportActionBar().hint();隐藏
    若Activity继承的Activity则使用requestWindowFeature(Window.FEATURE_NO_TITLE);
 */
public class ToolBarHintActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> fragmentTitles = new ArrayList<>();
    private MyViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar_hint);

        initData();
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"我是SnackBar",Snackbar.LENGTH_SHORT).setAction("action",null).show();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewPage);
        adapter = new MyViewPagerAdapter(getSupportFragmentManager(),fragments,fragmentTitles);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("TabOne"));//给TabLayout添加新的Tab
        tabLayout.addTab(tabLayout.newTab().setText("TabTwo"));
        tabLayout.addTab(tabLayout.newTab().setText("TabThree"));
        tabLayout.setupWithViewPager(viewPager);//给TabLayout设置关联ViewPager，如果设置了ViewPager，
        // 那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题

    }

    private void initData() {
        fragments.add(FragmentOne.newInstance());
        fragments.add(FragmentTwo.newInstance());
        fragments.add(FragmentThree.newInstance());

        fragmentTitles.add("TabOne");
        fragmentTitles.add("TabTwo");
        fragmentTitles.add("TabThree");
    }
}
