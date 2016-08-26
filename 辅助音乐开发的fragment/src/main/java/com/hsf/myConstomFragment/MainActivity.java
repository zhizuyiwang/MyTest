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
        changeTheme(currentPosition);
    }
    private void initView() {
        content = (FrameLayout) findViewById(R.id.content);
        change = (Button) findViewById(R.id.change);
        change.setOnClickListener(this);
    }

    private void initData() {
        fragmentManager = getSupportFragmentManager();
        random = new Random();
        currentPosition = random.nextInt(4);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change:
                currentPosition = random.nextInt(4);
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
}
