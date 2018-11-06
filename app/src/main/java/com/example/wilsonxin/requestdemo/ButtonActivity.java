package com.example.wilsonxin.requestdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class ButtonActivity extends Activity implements View.OnClickListener {
    private Button ok_get;
    private Button bt_button;
    private Button bt_donghua;
    private Button bt_bottomNavigationBar;
    private Button bt_xuanzekuang;
    private Button bt_recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        initView();//找控件
        //设置点击事件
        bt_button.setOnClickListener(this);
        ok_get.setOnClickListener(this);
        bt_donghua.setOnClickListener(this);
        bt_bottomNavigationBar.setOnClickListener(this);
        bt_xuanzekuang.setOnClickListener(this);
        bt_recyclerView.setOnClickListener(this);
    }

    private void initView() {
        ok_get = findViewById(R.id.Ok_post);
        bt_button = findViewById(R.id.bt_button);
        bt_donghua = findViewById(R.id.bt_donghua);
        bt_bottomNavigationBar = findViewById(R.id.bt_BottomNavigationBar);
        bt_xuanzekuang = findViewById(R.id.bt_xuanzekuang);
        bt_recyclerView = findViewById(R.id.bt_recyclerView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Ok_post://Okhttp请求按钮
                Intent intent = new Intent(ButtonActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_button://流式标签按钮
                Intent intent2 = new Intent(ButtonActivity.this, CurrentLabelActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt_donghua://Android动画按钮
                Intent intent1 = new Intent(ButtonActivity.this, AnimationActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_BottomNavigationBar://导航栏编写
                Intent intent3 = new Intent(ButtonActivity.this, BottomNavigationBarActivity.class);
                startActivity(intent3);
                break;
            case R.id.bt_xuanzekuang:
                Intent intent4 = new Intent(ButtonActivity.this, CheckBoxActivity.class);
                startActivity(intent4);
                break;
            case R.id.bt_recyclerView:
                Intent intent5 = new Intent(ButtonActivity.this, RecyclerViewActivity.class);
            default:
                break;
        }
    }
}
