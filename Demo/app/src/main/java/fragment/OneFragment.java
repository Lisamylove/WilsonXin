package fragment;

import adapter.OneListViewAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.wilsonxin.study.ListViewWebView;
import com.example.wilsonxin.study.R;
import com.google.gson.Gson;
import dao.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class OneFragment extends Fragment {
    private String jsonurl="http://api.kkmh.com/v1/daily/comic_lists/0?since=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNTM4OTgxMDM0NjUxLCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjQuMiIsIkZyb21Ib21lcGFnZVRhYk5hbWUiOiLng63pl6giLCIkbGliX3ZlcnNpb24iOiIxLjYuNiIsIiRuZXR3b3JrX3R5cGUiOiJXSUZJIiwiJHdpZmkiOnRydWUsIiRtYW51ZmFjdHVyZXIiOiJIVUFXRUkgIiwiRnJvbUhvbWVwYWdlVXBkYXRlRGF0ZSI6MCwiJHNjcmVlbl9oZWlnaHQiOjEyODAsIkhvbWVwYWdlVXBkYXRlRGF0ZSI6MCwiUHJvcGVydHlFdmVudCI6IlJlYWRIb21lUGFnZSIsIkZpbmRUYWJOYW1lIjoi5o6o6I2QIiwiYWJ0ZXN0X2dyb3VwIjowLCIkc2NyZWVuX3dpZHRoIjo3MjAsIiRvcyI6IkFuZHJvaWQiLCJUcmlnZ2VyUGFnZSI6IkhvbWVQYWdlIiwiJGNhcnJpZXIiOiJDSElOQSBNT0JJTEUiLCIkbW9kZWwiOiJIVUFXRUkgTUxBLUFMMTAiLCIkYXBwX3ZlcnNpb24iOiIzMzEwMCJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6OGMxNjQ1MjgwMTgxNDQ5NCIsIm9yaWdpbmFsX2lkIjoiQTo4YzE2NDUyODAxODE0NDk0IiwiZXZlbnQiOiJSZWFkSG9tZVBhZ2UifQ%3D%3D";
//    private String jsonurl = "http://api.kkmh.com/v1/daily/comic_lists/1521993600?since=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNTIyMzExNjQ0MjU3LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjQuMiIsIkZyb21Ib21lcGFnZVRhYk5hbWUiOiLng63pl6giLCIkbGliX3ZlcnNpb24iOiIxLjYuNiIsIiRuZXR3b3JrX3R5cGUiOiJXSUZJIiwiJHdpZmkiOnRydWUsIiRtYW51ZmFjdHVyZXIiOiJIVUFXRUkgIiwiRnJvbUhvbWVwYWdlVXBkYXRlRGF0ZSI6MywiJHNjcmVlbl9oZWlnaHQiOjEyODAsIkhvbWVwYWdlVXBkYXRlRGF0ZSI6NCwiUHJvcGVydHlFdmVudCI6IlJlYWRIb21lUGFnZSIsIkZpbmRUYWJOYW1lIjoi5o6o6I2QIiwiYWJ0ZXN0X2dyb3VwIjowLCIkc2NyZWVuX3dpZHRoIjo3MjAsIiRvcyI6IkFuZHJvaWQiLCJUcmlnZ2VyUGFnZSI6IkhvbWVQYWdlIiwiJGNhcnJpZXIiOiJDSElOQSBNT0JJTEUiLCIkbW9kZWwiOiJIVUFXRUkgTUxBLUFMMTAiLCIkYXBwX3ZlcnNpb24iOiIzMzEwMCJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6Yzg1Yjc2ZGJhNjM4MzIzMSIsIm9yaWdpbmFsX2lkIjoiQTpjODViNzZkYmE2MzgzMjMxIiwiZXZlbnQiOiJSZWFkSG9tZVBhZ2UifQ%3D%3D";
    private OneListViewAdapter adapter;
    private List<User.DateBean.ComicBean> comic;
    private User user;
    private Handler handler = new Handler(new Handler.Callback() {


        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {
                String obj = (String) msg.obj;
                Log.d("---" + obj, "TAG");
                Gson gson = new Gson();
                user = gson.fromJson(obj, User.class);
                comic = user.getDate().getComic();
                adapter = new OneListViewAdapter(getActivity(), comic);
                lv_listview.setAdapter(adapter);
            }
            return false;
        }
    });
    private ListView lv_listview;
    private SwipeRefreshLayout swp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onefragment, container, false);
        initView(view);
        listViewOnClick();//listview点击事件
        initHttp();//解析
        refreshViewList();//下拉刷新
        return view;
    }

    private void listViewOnClick() {
        lv_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), ListViewWebView.class);
                intent.putExtra("result", comic.get(position).getUrl());
                intent.putExtra("title",comic.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    private void refreshViewList() {
        swp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {//下拉刷新的方法
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       adapter.notifyDataSetChanged();//刷新适配器
                       initHttp();//解析
                       Toast.makeText(getContext(),"刷新成功",Toast.LENGTH_SHORT).show();
                       swp.setRefreshing(false);//设置停止刷新
                   }
               },2000);
            }
        });
    }

    private void initView(View view) {
        lv_listview = view.findViewById(R.id.lv_listview);
        swp = view.findViewById(R.id.swipeRefreshlayout);
    }

    private void initHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(jsonurl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");//设置请求方式
                    httpURLConnection.setConnectTimeout(5000);//设置连接超时时间
                    httpURLConnection.setReadTimeout(5000);//设置访问超时时间
                    httpURLConnection.connect();//开启连接
                    //如果应答码是200的时候，表示请求成功；这里的HttpURLConnection.HTTP_OK就是200
                    if (httpURLConnection.getResponseCode() == httpURLConnection.HTTP_OK) {
                        //获得连接的输入流
                        InputStream inputStream = httpURLConnection.getInputStream();
                        //转换成一个加强型的buffered流
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                        final String s = bufferedReader.readLine();
//                        String replace = s.replace("", "111");
                        //发送消息
                        Message message = handler.obtainMessage(0, s);
                        message.sendToTarget();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
