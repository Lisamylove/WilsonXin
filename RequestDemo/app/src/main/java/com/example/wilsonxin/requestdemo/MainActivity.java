package com.example.wilsonxin.requestdemo;

import adapter.ListAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import bean.TitleBean;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * ButtonActivity中点击okHttp按钮跳转至该页面
     */
    private String jsonurl="http://api.kkmh.com/v1/topic_new/discovery_list?sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNTM5MjQ1MTA0ODg0LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjQuMiIsIkZyb21GaW5kQ2F0ZWdvcnlUYWJOYW1lIjoi5YWo6YOoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjYiLCIkbmV0d29ya190eXBlIjoiV0lGSSIsIiR3aWZpIjp0cnVlLCIkbWFudWZhY3R1cmVyIjoiSFVBV0VJICIsIkZyb21GaW5kVGFiTmFtZSI6IuaOqOiNkCIsIiRzY3JlZW5faGVpZ2h0IjoxMjgwLCJIb21lcGFnZVVwZGF0ZURhdGUiOjAsIlByb3BlcnR5RXZlbnQiOiJSZWFkRmluZFBhZ2UiLCJhYnRlc3RfZ3JvdXAiOjAsIiRzY3JlZW5fd2lkdGgiOjcyMCwiRmluZENhdGVnb3J5VGFiTmFtZSI6IuWFqOmDqCIsIiRvcyI6IkFuZHJvaWQiLCJUcmlnZ2VyUGFnZSI6IkhvbWVQYWdlIiwiJGNhcnJpZXIiOiJDSElOQSBNT0JJTEUiLCIkbW9kZWwiOiJIVUFXRUkgTUxBLUFMMTAiLCIkYXBwX3ZlcnNpb24iOiIzMzEwMCJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6OGMxNjQ1MjgwMTgxNDQ5NCIsIm9yaWdpbmFsX2lkIjoiQTo4YzE2NDUyODAxODE0NDk0IiwiZXZlbnQiOiJSZWFkRmluZFBhZ2UifQ%3D%3D";
    private ListView lv_list;
    private List<TitleBean.DataBean.InfosBean> infos;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            Gson gson=new Gson();
            TitleBean json = gson.fromJson(str, TitleBean.class);
            infos = json.getData().getInfos();
            lv_list.setAdapter(new ListAdapter(MainActivity.this, infos));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//隐藏标题栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            /**
             * FLAG_FULLSCREEN    导航栏包含导航栏的标签完全隐藏
             * FLAGS_CHANGED       导航栏不做改变
             */
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        initView();//找控件
        GetData();//解析
        ListViewOnClick();//listview的点击事件
    }

    private void ListViewOnClick() {
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,ViewPagerActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("result", (Serializable) infos.get(position).getTopics());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * get请求
     * 导入依赖包
     */
    private void GetData() {
        //创建OKHttpClient，也就是OKhttp的客户端
        OkHttpClient okHttpClient=new OkHttpClient();
        //创建请求，builder是辅助类，辅助进行网络请求的
        Request request=new Request.Builder().url(jsonurl).build();
        //创建请求队列，将请求放进去
        Call call=okHttpClient.newCall(request);
        //请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG","--------请求失败---------");
            }
            /**
             *  onResponse回调的参数是response
             *  如果我们希望获得返回的字符串，可以通过response.body().string();获取
             * 如果我们希望获得返回的是二进制字节数组，可以通过response.body().bytes();获取
             * 如果我们希望获得返回的是InputStream，则调用response.body().byteStream();获取
             */
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TAG","---------请求成功--------");
                String string = response.body().string();
                Message message=handler.obtainMessage(1,string);
                message.sendToTarget();
            }
        });
    }

    //找控件
    private void initView() {
        lv_list = findViewById(R.id.lv_list);
    }
}
