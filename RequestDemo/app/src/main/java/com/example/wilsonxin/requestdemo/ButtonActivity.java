package com.example.wilsonxin.requestdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class ButtonActivity extends Activity {
    private Button ok_get;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        initView();//找控件
        ButtonOnClick();//按钮设置点击事件
    }

    private void ButtonOnClick() {
        ok_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ButtonActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        ok_get = findViewById(R.id.Ok_post);
    }
}
