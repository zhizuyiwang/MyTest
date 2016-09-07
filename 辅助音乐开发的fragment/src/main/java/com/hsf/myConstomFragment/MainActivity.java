package com.hsf.myConstomFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button change;
    private FrameLayout content;
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;
    private Fragment fragment5;
    private Fragment fragment6;
    private Fragment fragment7;
    private Fragment fragment8;
    private Fragment fragment9;
    private Fragment fragment10;
    private Fragment fragment11;
    private Fragment fragment12;
    private Fragment fragment13;
    private Fragment fragment14;
    private Fragment fragment15;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Random random;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        initData();
        initView();
        changeTheme(0);
    }
    private void initView() {
        content = (FrameLayout) findViewById(R.id.content);
        change = (Button) findViewById(R.id.change);
        change.setOnClickListener(this);
    }

    private void initData() {
        fragmentManager = getSupportFragmentManager();
        random = new Random();
        currentPosition = random.nextInt(15);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change:
                currentPosition = random.nextInt(3);
                Log.e("TAG",currentPosition+"");
                changeTheme(currentPosition);
                break;
        }

    }

    private void changeTheme(int currentPosition) {
        transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (currentPosition){
            case 0:
                if(fragment1==null){
                    fragment1 = new Fragment1();
                    transaction.add(R.id.content,fragment1);
                }else{

                    transaction.show(fragment1);
                }
                break;
            case 1:
                if(fragment2==null){
                    fragment2 = new Fragment2();
                    transaction.add(R.id.content,fragment2);
                }else{

                    transaction.show(fragment2);
                }
                break;
            case 2:
                if(fragment3==null){
                    fragment3 = new Fragment3();
                    transaction.add(R.id.content,fragment3);
                }else{

                    transaction.show(fragment3);
                }

                break;
            case 3:
                if(fragment4==null){
                    fragment4 = new Fragment4();
                    transaction.add(R.id.content,fragment4);
                }else{
                    transaction.show(fragment4);
                }

                break;
            case 4:
                if(fragment5==null){
                    fragment5 = new Fragment5();
                    transaction.add(R.id.content,fragment5);
                }else{
                    transaction.show(fragment5);
                }

                break;
            case 5:
                if(fragment6==null){
                    fragment6 = new Fragment6();
                    transaction.add(R.id.content,fragment6);
                }else{
                    transaction.show(fragment6);
                }
                break;
            case 6:
                if(fragment7==null){
                    fragment7 = new Fragment7();
                    transaction.add(R.id.content,fragment7);
                }else{
                    transaction.show(fragment7);
                }
                break;
            case 7:
                if(fragment8==null){
                    fragment8 = new Fragment8();
                    transaction.add(R.id.content,fragment8);
                }else{
                    transaction.show(fragment8);
                }
                break;
            case 8:
                if(fragment9==null){
                    fragment9 = new Fragment9();
                    transaction.add(R.id.content,fragment9);
                }else{
                    transaction.show(fragment9);
                }
                break;
            case 9:
                if(fragment10==null){
                    fragment10 = new Fragment10();
                    transaction.add(R.id.content,fragment10);
                }else{
                    transaction.show(fragment10);
                }
                break;
            case 10:
                if(fragment11==null){
                    fragment11 = new Fragment11();
                    transaction.add(R.id.content,fragment11);
                }else{
                    transaction.show(fragment11);
                }
                break;
            case 11:
                if(fragment12==null){
                    fragment12 = new Fragment12();
                    transaction.add(R.id.content,fragment12);
                }else{
                    transaction.show(fragment12);
                }
                break;
            case 12:
                if(fragment13==null){
                    fragment13 = new Fragment13();
                    transaction.add(R.id.content,fragment13);
                }else{
                    transaction.show(fragment13);
                }
                break;
            case 13:
                if(fragment14==null){
                    fragment14 = new Fragment14();
                    transaction.add(R.id.content,fragment14);
                }else{
                    transaction.show(fragment14);
                }
                break;
            case 14:
                if(fragment15==null){
                    fragment15 = new Fragment15();
                    transaction.add(R.id.content,fragment15);
                }else{
                    transaction.show(fragment15);
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
        if(fragment5!=null){
            transaction.hide(fragment5);
        }
        if(fragment6!=null){
            transaction.hide(fragment6);

        }
        if(fragment7!=null){
            transaction.hide(fragment7);
        }
        if(fragment8!=null){
            transaction.hide(fragment8);

        }
        if(fragment9!=null){
            transaction.hide(fragment9);
        }
        if(fragment10!=null){
            transaction.hide(fragment10);

        }
        if(fragment11!=null){
            transaction.hide(fragment11);
        }
        if(fragment12!=null){
            transaction.hide(fragment12);

        }
        if(fragment13!=null){
            transaction.hide(fragment13);
        }
        if(fragment14!=null){
            transaction.hide(fragment14);

        }
    }
}
