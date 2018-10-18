package com.example.wilsonxin.requestdemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class AnimationActivity extends Activity implements View.OnClickListener {

    /**
     * 33-Android之属性动画组合
     */
    private Button mButton;
    private Button animation_one;
    private Button animation_two;
    private ImageView iv_imageview;
    private Button bt_trans;
    private Button bt_rotate;
    private Button bt_scale;
    private Button bt_alpha;
    private ImageView iv_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();//找控件
        ValueAnimatorOfInt();//ofInt动画效果
    }

    public void combinel1(View view) {
        //设置属性动画的数据及动画效果   透明缩放
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0);
        //设置属性动画的target，设置要展示的动画效果
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(iv_imageview, alpha, scaleX, scaleY);
        animator.setDuration(5000);
        Log.d("TAG" + "组合动画方式一执行时间=", String.valueOf(animator.getDuration()));
        animator.setRepeatCount(2);//动画重复次数
        Log.d("TAG" + "组合动画方式一重复次数=", String.valueOf(animator.getRepeatCount()));
        animator.start();
    }

    public void combinel2(View view) {
        final ObjectAnimator animator = ObjectAnimator.ofFloat(iv_imageview, "x", 1.0f, 0);
        //设置刷新监听
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取当前的值
                Float value = (Float) animation.getAnimatedValue();
                //设置内容
                iv_imageview.setScaleX(value);
                iv_imageview.setScaleY(value);
                iv_imageview.setAlpha(value);
            }
        });
        animator.setDuration(3000);
        Log.d("TAG" + "组合动画方式二执行时间=", String.valueOf(animator.getDuration()));
        animator.setRepeatCount(2);//动画重复次数
        Log.d("TAG" + "组合动画方式二重复次数=", String.valueOf(animator.getRepeatCount()));
        //监听动画是否结束
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Restore view
//                FrameLayout.LayoutParams ll = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
//                ll.setMargins(0, 0, 0, 0);
//                iv_imageview.setLayoutParams(ll);
                Toast.makeText(AnimationActivity.this, "动画效果结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });
        animator.start();
    }

    private void ValueAnimatorOfInt() {
        // 创建动画作用对象：此处以Button为例
        // 步骤1：设置属性数值的初始值 & 结束值
        // 初始值 = 当前按钮的宽度，此处在xml文件中设置为150
        // 结束值 = 500
        ValueAnimator valueAnimator = ValueAnimator.ofInt(mButton.getLayoutParams().width, 1000);
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置
        // 即默认设置了如何从初始值150 过渡到 结束值500

        // 步骤2：设置动画的播放各种属性
        valueAnimator.setDuration(2000);
        // 设置动画运行时长:1s

        // 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
        // 设置更新监听器：即数值每次变化更新都会调用该方法
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                // 获得每次变化后的属性值
                int currentValue = (Integer) animator.getAnimatedValue();
                // 输出每次变化后的属性值进行查看
//                System.out.println(currentValue);
                // 每次值变化时，将值手动赋值给对象的属性
                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化
                mButton.getLayoutParams().width = currentValue;
                // 步骤4：刷新视图，即重新绘制，从而实现动画效果
                mButton.requestLayout();
            }
        });
        valueAnimator.start();// 启动动画
    }

    //找控件
    private void initView() {
        mButton = findViewById(R.id.Button);
        animation_one = findViewById(R.id.animation_one);
        animation_two = findViewById(R.id.animation_two);
        iv_imageview = findViewById(R.id.iv_imageview);
        iv_img = findViewById(R.id.iv_img);
        bt_trans = findViewById(R.id.bt_trans);//位移
        bt_rotate = findViewById(R.id.bt_rotate);//旋转
        bt_scale = findViewById(R.id.bt_scale);//缩放
        bt_alpha = findViewById(R.id.bt_alpha);
        //按钮设置监听事件
        animation_one.setOnClickListener(this);
        animation_two.setOnClickListener(this);
        bt_trans.setOnClickListener(this);
        bt_rotate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animation_one:
                combinel1(v);
                break;
            case R.id.animation_two:
                combinel2(v);
                break;
            case R.id.bt_trans:
                Trans();
                break;
            case R.id.bt_rotate:
                Rotate();
                break;
        }
    }

    private void Rotate() {
        /**
         * 属性动画  旋转
         */
        ObjectAnimator animator=ObjectAnimator.ofFloat(iv_imageview,"rotation",0,100,150,167,230,360);
        animator.setDuration(3000);
        animator.setRepeatCount(3);
        animator.start();
    }

    private void Trans() {
        /**属性动画   位移
         * target   目标控件
         * property 动画类型
         * values   可变参数
         */
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv_img, "translationX", -300, -10, -30, -80, -150, -300);
        animator.setDuration(5000);
        animator.start();
        Toast toast = Toast.makeText(this, "位移动画", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
