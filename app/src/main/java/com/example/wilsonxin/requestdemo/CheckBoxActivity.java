package com.example.wilsonxin.requestdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.*;

public class CheckBoxActivity extends Activity implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private RadioGroup rg_radiogroup;
    private CheckBox cb_rb1;
    private CheckBox cb_rb2, cb_rb3, cb_rb4, cb_rb5, cb_rb6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);
        initView();//找控件
    }

    private void initView() {
        rg_radiogroup = findViewById(R.id.RG_radiogroup);//找控件
        rg_radiogroup.setOnCheckedChangeListener(this);//RadioGroup的监听事件
        //CheckBox的控件和点击事件
        cb_rb1 = findViewById(R.id.cb_cb1);
        cb_rb2 = findViewById(R.id.cb_cb2);
        cb_rb3 = findViewById(R.id.cb_cb3);
        cb_rb4 = findViewById(R.id.cb_cb4);
        cb_rb5 = findViewById(R.id.cb_cb5);
        cb_rb6 = findViewById(R.id.cb_cb6);
        cb_rb1.setOnCheckedChangeListener(this);
        cb_rb2.setOnCheckedChangeListener(this);
        cb_rb3.setOnCheckedChangeListener(this);
        cb_rb4.setOnCheckedChangeListener(this);
        cb_rb5.setOnCheckedChangeListener(this);
        cb_rb6.setOnCheckedChangeListener(this);
    }

    /**
     * //RadioGroup的监听事件方法
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_rb3) {
            Toast.makeText(this, "选择正确", Toast.LENGTH_SHORT).show();
        }
        RadioButton rb = findViewById(checkedId);
        Toast.makeText(this, rb.getText(), Toast.LENGTH_SHORT).show();
    }
    /**
     * //CheckBox的监听事件方法
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        CheckBox checkBox= (CheckBox) buttonView;
        if (isChecked){
            Toast.makeText(this,"选中了:"+checkBox.getText(),Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this,"取消了:"+checkBox.getText(),Toast.LENGTH_SHORT).show();
        }
    }
}
