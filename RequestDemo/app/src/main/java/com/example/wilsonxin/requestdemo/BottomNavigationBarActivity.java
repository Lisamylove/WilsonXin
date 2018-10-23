package com.example.wilsonxin.requestdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import fragment.BirdFragment;
import fragment.CoffeeFragment;
import fragment.FishFragment;
import fragment.FlyFragment;


public class BottomNavigationBarActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {

    private BottomNavigationBar button_bar;
    private FrameLayout framelayout;
    private Button button_menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_bottomnavigationbar);
        initView();//找控件
        AddItem();//BottomNavigationBar中添加导航Icon饼初始化
        //设置监听事件
        button_bar.setTabSelectedListener(this);
        ChageFragment(new FishFragment(), "fish fragment");
    }

    private void AddItem() {
        button_bar.addItem(new BottomNavigationItem(R.mipmap.icon_one, "Fish").setActiveColor(Color.BLUE))
                .addItem(new BottomNavigationItem(R.mipmap.icon_two, "Fly").setActiveColor(Color.YELLOW))
                .addItem(new BottomNavigationItem(R.mipmap.icon_three, "Bird").setActiveColor(Color.GREEN))
                .addItem(new BottomNavigationItem(R.mipmap.icon_four, "Coffee"))
                //设置导航栏加载样式
//                .setMode(BottomNavigationBar.MODE_SHIFTING)
//                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setFirstSelectedPosition(0)//设置默认选择项
                .initialise();//初始化
    }

    private void initView() {
        button_bar = findViewById(R.id.button_Bar);
        framelayout = findViewById(R.id.framelayout);
        button_menu = findViewById(R.id.button_menu);
        button_menu.setOnClickListener(this);//设置菜单的点击事件
    }

    //BottomNavigationBar监听事件方法
    //添加监听事件，重写方法，最主要的是在方法中进行fragment的切换
    @Override
    public void onTabSelected(int position) {
        //未选中-->选中
        switch (position) {
            case 0:
                ChageFragment(new FishFragment(), "fish fragment");
                break;
            case 1:
                ChageFragment(new FlyFragment(), "fly fragment");
                break;
            case 2:
                ChageFragment(new BirdFragment(), "bird fragment");
                break;
            case 3:
                ChageFragment(new CoffeeFragment(), "coffee fragment");
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {
        //选中-->未选中
    }

    @Override
    public void onTabReselected(int position) {
        //选中-->选中
    }

    public void ChageFragment(Fragment fragment, String tag) {
        //getSupportFragmentManager()获取管理者
        //beginTransaction()开启事务
        //commit()提交事务
        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager.replace(R.id.framelayout, fragment, tag);
        fragmentManager.commit();//提交事务
    }

    /**
     * 标题栏菜单按钮的点击事件
     * 1、SlidingMenu使用前导入依赖包
     * 2、修改依赖包中 build.
     * * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_menu:
                SlidingMenuDate();//侧滑菜单
                break;
        }
    }
    //侧滑菜单
    private void SlidingMenuDate() {
        SlidingMenu slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setBehindOffset(200);
        //设置让侧滑依附于activity之上
        slidingMenu.attachToActivity(BottomNavigationBarActivity.this, SlidingMenu.SLIDING_CONTENT);
        //设置侧滑布局
        slidingMenu.setMenu(R.layout.menu);
    }
}
