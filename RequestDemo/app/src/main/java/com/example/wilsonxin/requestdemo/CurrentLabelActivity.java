package com.example.wilsonxin.requestdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class CurrentLabelActivity extends Activity {

    private FlowLayout id_flowlayout;
    //    private String[] flow=new String[]{"苹果手机","小米","三星","我有一个小娃娃","我是最棒的","加油","和平世界","华为"};
    private List<String> list = new ArrayList<>();
    private TextView tv_set;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentlabel);
        id_flowlayout = findViewById(R.id.id_flowlayout);
        addList();//向集合中添加标签
        for (int i=0;i<list.size();i++){
            TextView textView = buildLabel(list.get(i));
            id_flowlayout.addView(textView);
        }
    }

    private void addList() {
        list.add("苹果手机");
        list.add("牧灵");
        list.add("欧巴！！！");
        list.add("很甜");
        list.add("世界很大，我想去看看");
        list.add("未来...");
        list.add("论叛逆少女的恋爱方式");
        list.add("樱花很美");
        list.add("教主！");
        list.add("总的来说");
        list.add("城市王子与土著少女");
        list.add("战争");
        list.add("恶魔的耳朵");
        list.add("小狐狸");
        list.add("女巨人也要谈恋爱");
        list.add("反转现实");
    }

    private TextView buildLabel(final String text) {
        final TextView textView = new TextView(this);
        textView.setText(text);
        //设置标签和字体的大小
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        //标签距离上下左右的距离
        textView.setPadding((int) dpToPx(16), (int) dpToPx(8), (int) dpToPx(16), (int) dpToPx(8));
        //设置标签背景椭圆
        textView.setBackgroundResource(R.drawable.bg_gray);
//        textView.setLetterSpacing(3);//流式标签中字体的间距
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CurrentLabelActivity.this,textView.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        return textView;
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
