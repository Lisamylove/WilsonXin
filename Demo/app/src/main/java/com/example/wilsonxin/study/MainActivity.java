package com.example.wilsonxin.study;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import fragment.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_one;
    private TextView tv_three;
    private TextView tv_two;
    private TextView tv_four;
    private TextView tv_five;
    private List<TextView> list = new ArrayList<>();
    private List<TextView> lv_list = new ArrayList<>();
    private ViewPager vp_viewpager;
    private TextView tv_color_one, tv_color_two, tv_color_three, tv_color_four, tv_color_five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//TextView文字找控件，点击切换到相对应的ViewPager页面
        ViewPagerView();//ViewPager适配，滑动切换相对应的Fragment
        ViewPagerColor();//点击切换字体颜色
    }

    private void ViewPagerColor() {
        vp_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                //滑动页面遍历改变字体颜色
                for (int i = 0; i < list.size(); i++) {
                    TextView textView = list.get(i);
                    if (i == position) {
                        textView.setTextColor(Color.RED);
                    } else {
                        textView.setTextColor(Color.BLACK);
                    }
                }
                //滑动页面遍历改变字体下划线颜色
                for (int j = 0; j < lv_list.size(); j++) {
                    TextView textView1 = lv_list.get(j);
                    if (j == position) {
                        textView1.setBackgroundColor(Color.RED);
                    } else {
                        textView1.setBackgroundColor(Color.WHITE);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void ViewPagerView() {
        vp_viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new OneFragment();
                        break;
                    case 1:
                        fragment = new TwoFragment();
                        break;
                    case 2:
                        fragment = new ThreeFragment();
                        break;
                    case 3:
                        fragment = new FourFragment();
                        break;
                    case 4:
                        fragment = new FiveFragment();
                        break;
                    default:
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
    }

    private void initView() {
        vp_viewpager = findViewById(R.id.vp_viewpager);
        tv_one = findViewById(R.id.tv_one);
        tv_two = findViewById(R.id.tv_two);
        tv_three = findViewById(R.id.tv_three);
        tv_four = findViewById(R.id.tv_four);
        tv_five = findViewById(R.id.tv_five);
        tv_color_one = findViewById(R.id.tv_color_one);
        tv_color_two = findViewById(R.id.tv_color_two);
        tv_color_three = findViewById(R.id.tv_color_three);
        tv_color_four = findViewById(R.id.tv_color_four);
        tv_color_five = findViewById(R.id.tv_color_five);
        list.add(tv_one);
        list.add(tv_two);
        list.add(tv_three);
        list.add(tv_four);
        list.add(tv_five);
        lv_list.add(tv_color_one);
        lv_list.add(tv_color_two);
        lv_list.add(tv_color_three);
        lv_list.add(tv_color_four);
        lv_list.add(tv_color_five);
        listView();//循环遍历点击TextView切换到相对应的ViewPager页面
    }

    private void listView() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.tv_one:
                            vp_viewpager.setCurrentItem(0);
                            tv_color_one.setBackgroundColor(Color.RED);
                            tv_color_two.setBackgroundColor(Color.WHITE);
                            tv_color_three.setBackgroundColor(Color.WHITE);
                            tv_color_four.setBackgroundColor(Color.WHITE);
                            tv_color_five.setBackgroundColor(Color.WHITE);
                            break;
                        case R.id.tv_two:
                            vp_viewpager.setCurrentItem(1);
                            tv_color_one.setBackgroundColor(Color.WHITE);
                            tv_color_two.setBackgroundColor(Color.RED);
                            tv_color_three.setBackgroundColor(Color.WHITE);
                            tv_color_four.setBackgroundColor(Color.WHITE);
                            tv_color_five.setBackgroundColor(Color.WHITE);
                            break;
                        case R.id.tv_three:
                            vp_viewpager.setCurrentItem(2);
                            tv_color_one.setBackgroundColor(Color.WHITE);
                            tv_color_two.setBackgroundColor(Color.WHITE);
                            tv_color_three.setBackgroundColor(Color.RED);
                            tv_color_four.setBackgroundColor(Color.WHITE);
                            tv_color_five.setBackgroundColor(Color.WHITE);
                            break;
                        case R.id.tv_four:
                            vp_viewpager.setCurrentItem(3);
                            tv_color_one.setBackgroundColor(Color.WHITE);
                            tv_color_two.setBackgroundColor(Color.WHITE);
                            tv_color_three.setBackgroundColor(Color.WHITE);
                            tv_color_four.setBackgroundColor(Color.RED);
                            tv_color_five.setBackgroundColor(Color.WHITE);
                            break;
                        case R.id.tv_five:
                            vp_viewpager.setCurrentItem(4);
                            tv_color_one.setBackgroundColor(Color.WHITE);
                            tv_color_two.setBackgroundColor(Color.WHITE);
                            tv_color_three.setBackgroundColor(Color.WHITE);
                            tv_color_four.setBackgroundColor(Color.WHITE);
                            tv_color_five.setBackgroundColor(Color.RED);
                            break;
                    }
                }
            });
        }
    }
}
