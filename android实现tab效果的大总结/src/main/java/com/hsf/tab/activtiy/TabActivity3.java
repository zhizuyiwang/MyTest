package com.hsf.tab.activtiy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsf.tab.R;
import com.hsf.tab.fragment.Fragment1;
import com.hsf.tab.fragment.Fragment2;
import com.hsf.tab.fragment.Fragment3;
import com.hsf.tab.fragment.Fragment4;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager+FragmentPagerAdapter实现
 */
public class TabActivity3 extends AppCompatActivity implements View.OnClickListener{
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
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private FragmentPagerAdapter adapter;
    private int currentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_tab3);
        initView();
        initDate();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                resetButton();
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

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initDate() {
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();

        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);

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
        resetButton();
        switch (view.getId()) {
            case R.id.id_tab_bottom_weixin:
                currentPosition = 0;
                mImaWenxin.setBackgroundResource(R.mipmap.tab_weixin_pressed);
                mTvWeixin.setTextColor(getResources().getColor(R.color.green));
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.id_tab_bottom_friend:
                currentPosition = 1;
                mImaFrd.setBackgroundResource(R.mipmap.tab_find_frd_pressed);
                mTvFrd.setTextColor(getResources().getColor(R.color.green));
                viewPager.setCurrentItem(1, false);


                break;
            case R.id.id_tab_bottom_contact:
                currentPosition = 2;
                mImaAddress.setBackgroundResource(R.mipmap.tab_address_pressed);
                mTvAddress.setTextColor(getResources().getColor(R.color.green));
                viewPager.setCurrentItem(2, false);


                break;
            case R.id.id_tab_bottom_setting:
                currentPosition = 3;
                mImaSetting.setBackgroundResource(R.mipmap.tab_settings_pressed);
                mTvSetting.setTextColor(getResources().getColor(R.color.green));
                viewPager.setCurrentItem(3, false);
                break;
        }
    }

    private void resetButton() {
        mImaWenxin.setBackgroundResource(R.mipmap.tab_weixin_normal);
        mImaFrd.setBackgroundResource(R.mipmap.tab_find_frd_normal);
        mImaAddress.setBackgroundResource(R.mipmap.tab_address_normal);
        mImaSetting.setBackgroundResource(R.mipmap.tab_settings_normal);

        mTvWeixin.setTextColor(getResources().getColor(R.color.writer));
        mTvFrd.setTextColor(getResources().getColor(R.color.writer));
        mTvAddress.setTextColor(getResources().getColor(R.color.writer));
        mTvSetting.setTextColor(getResources().getColor(R.color.writer));
    }

    private class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
