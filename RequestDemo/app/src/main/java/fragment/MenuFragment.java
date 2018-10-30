package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import bean.MenuBean;
import com.example.wilsonxin.requestdemo.R;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MenuFragment extends Fragment {

    private Button bt_bj;
    private Button bt_qx;
    private Button bt_qbx;
    private Button bt_fx;
    private ListView lv_listview;
    private String jsonurl="http://api.kkmh.com/v1/daily/comic_lists/1538409600?since=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNTM4OTgxMDQ3MjUzLCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjQuMiIsIkZyb21Ib21lcGFnZVRhYk5hbWUiOiLng63pl6giLCIkbGliX3ZlcnNpb24iOiIxLjYuNiIsIiRuZXR3b3JrX3R5cGUiOiJXSUZJIiwiJHdpZmkiOnRydWUsIiRtYW51ZmFjdHVyZXIiOiJIVUFXRUkgIiwiRnJvbUhvbWVwYWdlVXBkYXRlRGF0ZSI6NiwiJHNjcmVlbl9oZWlnaHQiOjEyODAsIkhvbWVwYWdlVXBkYXRlRGF0ZSI6NSwiUHJvcGVydHlFdmVudCI6IlJlYWRIb21lUGFnZSIsIkZpbmRUYWJOYW1lIjoi5o6o6I2QIiwiYWJ0ZXN0X2dyb3VwIjowLCIkc2NyZWVuX3dpZHRoIjo3MjAsIiRvcyI6IkFuZHJvaWQiLCJUcmlnZ2VyUGFnZSI6IkhvbWVQYWdlIiwiJGNhcnJpZXIiOiJDSElOQSBNT0JJTEUiLCIkbW9kZWwiOiJIVUFXRUkgTUxBLUFMMTAiLCIkYXBwX3ZlcnNpb24iOiIzMzEwMCJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6OGMxNjQ1MjgwMTgxNDQ5NCIsIm9yaWdpbmFsX2lkIjoiQTo4YzE2NDUyODAxODE0NDk0IiwiZXZlbnQiOiJSZWFkSG9tZVBhZ2UifQ%3D%3D";
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            Gson gson=new Gson();
            MenuBean json = gson.fromJson(str, MenuBean.class);
            List<MenuBean.DataBean.ComicsBean> comics = json.getData().getComics();
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.menu_fragment,null);
        initView(view);//找控件
        getData();//通过HttpClient解析数据
        return view;
    }

    private void getData() {
        //开启线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(jsonurl);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("GET");
                    if (connection.getResponseCode()==200){
                        InputStream inputStream = connection.getInputStream();
                        ByteArrayOutputStream bos=new ByteArrayOutputStream();
                        byte[] buffer=new byte[1024];
                        int len=0;
                        while ((len=inputStream.read(buffer))!=-1){
                            bos.write(buffer,0,len);
                        }
                        bos.close();
                        inputStream.close();
                        String s = bos.toString();
                        Message message=Message.obtain(handler,0,s);
                        message.sendToTarget();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initView(View view) {
        bt_bj = view.findViewById(R.id.bt_bj);
        bt_qx = view.findViewById(R.id.bt_qx);
        bt_qbx = view.findViewById(R.id.bt_qbx);
        bt_fx = view.findViewById(R.id.bt_fx);
        lv_listview = view.findViewById(R.id.lv_listview);
    }
}
