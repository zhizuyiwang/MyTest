package com.hsf.propertyAnimator.activity;

import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.hsf.propertyAnimator.R;
import com.hsf.propertyAnimator.view.CustomInterpolator;

/**
 * 属性动画的机制已经不是再针对于View而进行设计的了，而是一种不断地对值进行操作的机制，它可以将值赋值到指定对象的指定属性上.
 *
 * 关于ViewPropertyAnimator有几个细节还是值得大家注意一下的：
 * 1、整个ViewPropertyAnimator的功能都是建立在View类新增的animate()方法之上的，这个方法会创建并返回一个
 *    ViewPropertyAnimator的实例，之后的调用的所有方法，设置的所有属性都是通过这个实例完成的。
 *
 * 2、大家注意到，在使用ViewPropertyAnimator时，我们自始至终没有调用过start()方法，这是因为新的接口中使用了隐式
 *    启动动画的功能，只要我们将动画定义完成之后，动画就会自动启动。并且这个机制对于组合动画也同样有效，只要我们不断地连缀
 *    新的方法，那么动画就不会立刻执行，等到所有在ViewPropertyAnimator上设置的方法都执行完毕后，动画就会自动启动。当然
 *    如果不想使用这一默认机制的话，我们也可以显式地调用start()方法来启动动画。
 *
 * 3、ViewPropertyAnimator的所有接口都是使用连缀的语法来设计的，每个方法的返回值都是它自身的实例，因此调用完一个方法
 *    之后可以直接连缀调用它的另一个方法，这样把所有的功能都串接起来，我们甚至可以仅通过一行代码就完成任意复杂度的动画功能。
 */
public class ViewPropertyAnimatorActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bt;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animator2);
        bt = (Button) findViewById(R.id.bt1);
        bt.setOnClickListener(this);
        tv = (TextView) findViewById(R.id.tv);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt1:
                tv.animate().setDuration(2000).setInterpolator(new LinearInterpolator())
                        .alpha(1f).rotationY(360).start();
                break;
        }
    }
}
