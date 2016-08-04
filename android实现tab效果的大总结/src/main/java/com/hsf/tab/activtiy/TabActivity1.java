package com.hsf.tab.activtiy;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsf.tab.R;

import java.util.ArrayList;

/**
 * 传统的ViewPage+ViewAdapter实现Tab效果
 */
public class TabActivity1 extends AppCompatActivity implements View.OnClickListener{
    /**
     * 底部四个按钮
     */
    private LinearLayout mTabBtnWeixin;
    private LinearLayout mTabBtnFrd;
    private LinearLayout mTabBtnAddress;
    private LinearLayout mTabBtnSettings;

    /**
     * 底部四个ImageView
     */
    private ImageView mImaWenxin;
    private ImageView mImaFrd;
    private ImageView mImaAddress;
    private ImageView mImaSetting;

    /**
     * 底部四个TextView
     */
    private TextView mTvWeixin;
    private TextView mTvFrd;
    private TextView mTvAddress;
    private TextView mTvSetting;


    private ViewPager viewPager;
    /**
     * ViewPager的适配器
     */
    private PagerAdapter adapter;
    //存放View的集合
    private ArrayList<View> mViews;
    //布局填充器
    private LayoutInflater inflater;
    //当前ViewPager的下标
    private int currentposition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_tab1);
        inflater = LayoutInflater.from(this);

        //初始化控件
        initView();
        //初始化数据
        initDate();
        viewPager.setAdapter(new MyAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                resetButoon();
                switch (position){
                    case 0:
                        mImaWenxin.setBackgroundResource(R.mipmap.tab_weixin_pressed);
                        mTvWeixin.setTextColor(getResources().getColor(R.color.green));
                        break;
                    case 1:
                        mImaFrd.setBackgroundResource(R.mipmap.tab_find_frd_pressed);
                        mTvFrd.setTextColor(getResources().getColor(R.color.green));
                        break;
                    case 2:
                        mImaAddress.setBackgroundResource(R.mipmap.tab_address_pressed);
                        mTvAddress.setTextColor(getResources().getColor(R.color.green));
                        break;
                    case 3:
                        mImaSetting.setBackgroundResource(R.mipmap.tab_settings_pressed);
                        mTvSetting.setTextColor(getResources().getColor(R.color.green));
                        break;
                }
                currentposition = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void resetButoon() {

        mImaWenxin.setBackgroundResource(R.mipmap.tab_weixin_normal);
        mImaFrd.setBackgroundResource(R.mipmap.tab_find_frd_normal);
        mImaAddress.setBackgroundResource(R.mipmap.tab_address_normal);
        mImaSetting.setBackgroundResource(R.mipmap.tab_settings_normal);

        mTvWeixin.setTextColor(getResources().getColor(R.color.writer));
        mTvFrd.setTextColor(getResources().getColor(R.color.writer));
        mTvAddress.setTextColor(getResources().getColor(R.color.writer));
        mTvSetting.setTextColor(getResources().getColor(R.color.writer));
    }

    private void initDate() {
        mViews = new ArrayList<View>();
        View view1 = inflater.inflate(R.layout.fragment1_activity,null);
        View view2 = inflater.inflate(R.layout.fragment2_activity,null);
        View view3 = inflater.inflate(R.layout.fragment3_activity,null);
        View view4 = inflater.inflate(R.layout.fragment4_activity,null);
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
        mViews.add(view4);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabBtnWeixin = (LinearLayout) findViewById(R.id.id_tab_bottom_weixin);
        mTabBtnWeixin.setOnClickListener(this);
        mTabBtnFrd = (LinearLayout) findViewById(R.id.id_tab_bottom_friend);
        mTabBtnFrd.setOnClickListener(this);
        mTabBtnAddress = (LinearLayout) findViewById(R.id.id_tab_bottom_contact);
        mTabBtnAddress.setOnClickListener(this);
        mTabBtnSettings = (LinearLayout) findViewById(R.id.id_tab_bottom_setting);
        mTabBtnSettings.setOnClickListener(this);

        mImaWenxin = (ImageView) findViewById(R.id.btn_tab_bottom_weixin);
        mImaWenxin.setBackgroundResource(R.mipmap.tab_weixin_pressed);
        mImaFrd = (ImageView) findViewById(R.id.btn_tab_bottom_friend);
        mImaFrd.setBackgroundResource(R.mipmap.tab_find_frd_normal);
        mImaAddress = (ImageView) findViewById(R.id.btn_tab_bottom_contact);
        mImaAddress.setBackgroundResource(R.mipmap.tab_address_normal);
        mImaSetting = (ImageView) findViewById(R.id.btn_tab_bottom_setting);
        mImaSetting.setBackgroundResource(R.mipmap.tab_settings_normal);

        mTvWeixin = (TextView) findViewById(R.id.tv_wx);
        // getResources().getColor()返回的是资源color.xml中的id引用
        //arg1.setBackgroundColor(Color.parseColor("#87CEFA"));注意，传入的颜色字符串要完整
        mTvWeixin.setTextColor(getResources().getColor(R.color.green));
        mTvFrd = (TextView) findViewById(R.id.tv_friend);
        mTvFrd.setTextColor(getResources().getColor(R.color.writer));
        mTvAddress = (TextView) findViewById(R.id.tv_contact);
        mTvAddress.setTextColor(getResources().getColor(R.color.writer));
        mTvSetting = (TextView) findViewById(R.id.tv_set);
        mTvSetting.setTextColor(getResources().getColor(R.color.writer));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_tab_bottom_weixin:
                resetButoon();
                currentposition = 0;
                mImaWenxin.setBackgroundResource(R.mipmap.tab_weixin_pressed);
                mTvWeixin.setTextColor(getResources().getColor(R.color.green));
                viewPager.setCurrentItem(0,false);

                break;
            case R.id.id_tab_bottom_friend:
                resetButoon();
                currentposition = 1;
                mImaFrd.setBackgroundResource(R.mipmap.tab_find_frd_pressed);
                mTvFrd.setTextColor(getResources().getColor(R.color.green));
                viewPager.setCurrentItem(1,false);


                break;
            case R.id.id_tab_bottom_contact:
                resetButoon();
                currentposition = 2;
                mImaAddress.setBackgroundResource(R.mipmap.tab_address_pressed);
                mTvAddress.setTextColor(getResources().getColor(R.color.green));
                viewPager.setCurrentItem(2,false);


                break;
            case R.id.id_tab_bottom_setting:
                resetButoon();
                currentposition = 3;
                mImaSetting.setBackgroundResource(R.mipmap.tab_settings_pressed);
                mTvSetting.setTextColor(getResources().getColor(R.color.green));
                viewPager.setCurrentItem(3,false);


                break;
        }

    }

    public class MyAdapter extends PagerAdapter{

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return mViews.size();
        }

        /**
         * Remove a page for the given position.  The adapter is responsible
         * for removing the view from its container, although it only must ensure
         * this is done by the time it returns from {@link #finishUpdate(ViewGroup)}.
         *
         * @param container The containing View from which the page will be removed.
         * @param position  The page position to be removed.
         * @param object    The same object that was returned by
         *                  {@link #instantiateItem(View, int)}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
            Log.e("TAG",position+"");
        }

        /**
         * Create the page for the given position.  The adapter is responsible
         * for adding the view to the container given here, although it only
         * must ensure this is done by the time it returns from
         * {@link #finishUpdate(ViewGroup)}.
         *
         * @param container The containing View in which the page will be shown.
         * @param position  The page position to be instantiated.
         * @return Returns an Object representing the new page.  This does not
         * need to be a View, but can be some other container of the page.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViews.get(position);
            Log.e("TAG",position+"");
            container.addView(view);
            return view;
        }

        /**
         * Determines whether a page View is associated with a specific key object
         * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
         * required for a PagerAdapter to function properly.
         *
         * @param view   Page View to check for association with <code>object</code>
         * @param object Object to check for association with <code>view</code>
         * @return true if <code>view</code> is associated with the key object <code>object</code>
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


    }
}
