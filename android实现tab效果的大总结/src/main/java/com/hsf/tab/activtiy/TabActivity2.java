package com.hsf.tab.activtiy;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

/**
 * FragmentManager+Fragment实现
 */
public class TabActivity2 extends AppCompatActivity implements View.OnClickListener{

    /**
     * 四个fragment
     */
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
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

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_tab2);
        fragmentManager = getSupportFragmentManager();
        initView();
        //默认选中第一个fragment
        setFragment(0);
    }

    private void initView() {
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
        //重置按钮的状态
        resetButton();
        switch (view.getId()){
            case R.id.id_tab_bottom_weixin:
                mImaWenxin.setBackgroundResource(R.mipmap.tab_weixin_pressed);
                mTvWeixin.setTextColor(getResources().getColor(R.color.green));
                setFragment(0);
                break;
            case R.id.id_tab_bottom_friend:
                mImaFrd.setBackgroundResource(R.mipmap.tab_find_frd_pressed);
                mTvFrd.setTextColor(getResources().getColor(R.color.green));
                setFragment(1);
                break;
            case R.id.id_tab_bottom_contact:
                mImaAddress.setBackgroundResource(R.mipmap.tab_address_pressed);
                mTvAddress.setTextColor(getResources().getColor(R.color.green));
                setFragment(2);
                break;
            case R.id.id_tab_bottom_setting:
                mImaSetting.setBackgroundResource(R.mipmap.tab_settings_pressed);
                mTvSetting.setTextColor(getResources().getColor(R.color.green));
                setFragment(3);
                break;
        }
    }



    private void setFragment(int flag){
        //创建一个fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //隐藏所有的fragment，为了防止fragment重叠
        hideFragment(transaction);
        switch (flag){
            case 0:
                if(fragment1==null){
                    fragment1 = new Fragment1();
                    transaction.add(R.id.id_content,fragment1);
                }else{
                    transaction.show(fragment1);
                }
                break;
            case 1:
                if(fragment2==null){
                    fragment2 = new Fragment2();
                    transaction.add(R.id.id_content,fragment2);
                }else{
                    transaction.show(fragment2);
                }
                break;
            case 2:
                if(fragment3==null){
                    fragment3 = new Fragment3();
                    transaction.add(R.id.id_content,fragment3);
                }else{
                    transaction.show(fragment3);
                }
                break;
            case 3:
                if(fragment4 == null){
                    fragment4 = new Fragment4();
                    transaction.add(R.id.id_content,fragment4);
                }else{
                    transaction.show(fragment4);
                }
                break;
        }

        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction) {
        if(fragment1!=null){
            transaction.hide(fragment1);
        }
        if(fragment2!=null){
            transaction.hide(fragment2);
        }
        if(fragment3!=null){
            transaction.hide(fragment3);
        }
        if(fragment4!=null){
            transaction.hide(fragment4);
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


}
