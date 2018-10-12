package com.example.wilsonxin.requestdemo;

import adapter.ViewPagerImgAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import bean.TitleBean;

import java.util.List;

public class ViewPagerActivity extends Activity {
    private ViewPager vp_img;
    private List<TitleBean.DataBean.InfosBean.TopicsBean> result;

    /**
     * MainActivity中点击ListView条目，跳转至该图片轮播页面
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        Intent intent=getIntent();
        result = (List<TitleBean.DataBean.InfosBean.TopicsBean>) intent.getSerializableExtra("result");
        Log.d("TAG==========", result +"");
        initView();//找控件
        vp_img.setAdapter(new ViewPagerImgAdapter(ViewPagerActivity.this, result));
    }

    private void initView() {
        vp_img = findViewById(R.id.vp_img);
    }
}
