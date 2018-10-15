package com.example.wilsonxin.requestdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class ButtonActivity extends Activity implements View.OnClickListener {
    private Button ok_get;
    private Button bt_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        initView();//找控件
        //设置点击事件
        bt_button.setOnClickListener(this);
        ok_get.setOnClickListener(this);
    }
    private void initView() {
        ok_get = findViewById(R.id.Ok_post);
        bt_button = findViewById(R.id.bt_button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Ok_post:
                Intent intent=new Intent(ButtonActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_button:
                Intent intent2=new Intent(ButtonActivity.this,CurrentLabelActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
