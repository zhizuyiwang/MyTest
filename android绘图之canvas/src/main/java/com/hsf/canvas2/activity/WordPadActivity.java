package com.hsf.canvas2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hsf.canvas2.R;
import com.hsf.canvas2.view.WordPadView;

/**
 * 写字板
 */
public class WordPadActivity extends AppCompatActivity implements View.OnClickListener{
    private WordPadView wordPadView;
    private Button selectWordPadColor;
    private Button selectPaintColor;
    private Button selectRubberWidth;
    private Button flag;
    private boolean flag1 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_pad);
        initView();

    }

    private void initView() {
        wordPadView = (WordPadView) findViewById(R.id.wordPadView);
        selectWordPadColor = (Button) findViewById(R.id.word_pad_color);
        selectWordPadColor.setOnClickListener(this);
        selectPaintColor = (Button) findViewById(R.id.paint_color);
        selectPaintColor.setOnClickListener(this);
        selectRubberWidth = (Button) findViewById(R.id.rubber_width);
        selectRubberWidth.setOnClickListener(this);
        flag = (Button) findViewById(R.id.flag);
        flag.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.paint_color:

                break;
            case R.id.word_pad_color:

                break;
            case R.id.rubber_width:

                break;
            case R.id.flag:
                if(flag1){
                    flag.setText("使用橡皮擦");
                    wordPadView.setFlag(flag1);
                    flag1 = false;
                }else{
                    flag.setText("使用画笔");
                    wordPadView.setFlag(flag1);
                    flag1 = true;
                }
                break;
        }


    }
}
