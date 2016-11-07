package com.hsf.myDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.btn3)
    Button btn3;
    @Bind(R.id.btn4)
    Button btn4;
    @Bind(R.id.btn5)
    Button btn5;
    @Bind(R.id.btn6)
    Button btn6;

    private final String[] items= {"Android","iOS","h5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                showDialog1();
                break;
            case R.id.btn2:
                showDialog2();
                break;
            case R.id.btn3:
                showDialog3();
                break;
            case R.id.btn4:
                showDialog4();
                break;
            case R.id.btn5:
                //自定义列表项对话框
                showDialog5();
                break;
            case R.id.btn6:
                //自定义View的对话框
                showDialog6();
                break;
        }
    }

    private void showDialog6() {
        TableLayout login = (TableLayout) getLayoutInflater().inflate(R.layout.dialog_view,null);

        new AlertDialog.Builder(this)
                .setTitle("自定义View对话框")
                .setIcon(R.mipmap.ic_launcher)
                /*
                * setView(可设置边距)
                * 设置一个自定义的View放置到message的下方，可以是一个View对象，也可以是一个view资源ID
                * 对于使用View对象的参数，还可以设置他的边距。 如:
                * builder.setView(new EditText(this),20,20,20,20);;
                * */
                .setView(login,0,0,0,0)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create().show();

    }

    private void showDialog5() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("自定义列表项对话框").setIcon(R.mipmap.ic_launcher);
        //创建适配器
        MyBuilderAcapter adapter = new MyBuilderAcapter(items, this);
        //setAdapter 可以传入ArrayAdapter SimpleAdapter BaseAdapter 的对象
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this, "--" + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void showDialog4() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("多选列表对话框").setIcon(R.mipmap.ic_launcher);

        //参数2: boolean[] 有两个作用 :
        // 1.设置初始化时选中哪个列表项
        // 2.动态的获取多选列项中的选中状态
        builder.setMultiChoiceItems(items, new boolean[]{true, false, false},
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Log.i("mmmmkkkk", "+++" + items[which] + " ---- " + isChecked);
                    }

                });
        builder.create().show();
    }

    private void showDialog3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("单选列表对话框").setIcon(R.mipmap.ic_launcher);

        // 设置单选列表项, 默认选中第二项
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //设置确定按钮 和 按钮的点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击确定后

            }
        });
        //设置取消按钮 和 按钮的点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击取消后
            }
        });
        builder.create().show();
    }

    private void showDialog2() {
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setTitle("简单列表对话框").setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this, "选中了" + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置对话框标题
        builder.setTitle("简单对话框");
        //设置图标
        builder.setIcon(R.mipmap.ic_launcher);
        //设置对话框内容   \n 可以直接换行
        builder.setMessage("对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了对话框的内容,换行" +
                "了对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了" +
                "对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了" +
                "对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了对话框的内容,换行了" +
                "");
        //设置确定按钮 和 按钮的点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击确定后
                Toast.makeText(AlertDialogActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
            }
        });

        //设置取消按钮 和 按钮的点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击取消后
                Toast.makeText(AlertDialogActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
            }
        });
        //创建并显示
        builder.create().show(); //如果把按钮设置在create之后 就不会显示了
    }

}
