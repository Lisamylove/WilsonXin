package com.example.wilsonxin.requestdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import bean.TitleBean;

import java.io.Serializable;
import java.util.List;

public class ViewPagerActivity extends Activity {

    private TextView tv_img_title;
    private ImageView iv_img;
    private List<TitleBean.DataBean.InfosBean> result;
    private ViewPager vp_img;

    /**
     * MainActivity中点击ListView条目，跳转至该图片轮播页面
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        Intent intent=getIntent();
        result = (List<TitleBean.DataBean.InfosBean>) intent.getSerializableExtra("result");
        Log.d("TAG==========", result +"");
        initView();//找控件
//        vp_img.setAdapter();
    }

    private void initView() {
        vp_img = findViewById(R.id.vp_img);
        tv_img_title = findViewById(R.id.tv_img_title);
        iv_img = findViewById(R.id.iv_msg);
    }
}
