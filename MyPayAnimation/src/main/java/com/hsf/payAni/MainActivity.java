package com.hsf.payAni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button progress;
    private Button success;
    private Button fail;
    private ConfirmView confirmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        progress = (Button) findViewById(R.id.progress);
        progress.setOnClickListener(this);
        success = (Button) findViewById(R.id.success);
        success.setOnClickListener(this);
        fail = (Button) findViewById(R.id.fail);
        fail.setOnClickListener(this);
        confirmView = (ConfirmView) findViewById(R.id.confirm);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.progress:
                confirmView.animatedWithState(ConfirmView.State.Progressing);
                break;
            case R.id.fail:
                confirmView.animatedWithState(ConfirmView.State.Fail);
                break;
            case R.id.success:
                confirmView.animatedWithState(ConfirmView.State.Success);
                break;
        }
    }
}
