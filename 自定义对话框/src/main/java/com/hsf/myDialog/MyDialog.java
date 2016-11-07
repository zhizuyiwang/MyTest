package com.hsf.myDialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Administrator on 2016/9/27.
 */

public class MyDialog extends ProgressDialog{

    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        Button ok = (Button) findViewById(R.id.dialog_ok_button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog.this.dismiss();
            }
        });
        //初始化对话框
        init();

    }

    private void init() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = 600;
        params.height = 600;
        getWindow().setAttributes(params);

    }

    @Override
    public void show() {
        super.show();
    }
}
