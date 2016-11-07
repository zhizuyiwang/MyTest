package com.hsf.myRecyclerView.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/13.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> mFragmentTiles = new ArrayList<>();

    public MyViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments,ArrayList<String> titles) {
        super(fm);
        this.mFragments = fragments;
        this.mFragmentTiles = titles;

    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTiles.get(position);
    }

    public void addData(Fragment fragment,String title){

        mFragments.add(fragment);
        mFragmentTiles.add(title);
    }
    public void removeData(Fragment fragment,String title){
        mFragments.remove(fragment);
        mFragmentTiles.remove(title);
    }

}
