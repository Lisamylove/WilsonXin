package com.example.wilsonxin.study;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class ListViewWebView extends Activity {

    private WebView wb_webview;
    private TextView tv_fanhui;
    private TextView tv_put_title;
    private TextView tv_quanji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewwebview);
        initView();//找控件
        WebViewLoad();//webView加载
    }

    private void WebViewLoad() {
        Intent intent=getIntent();
        String result = intent.getStringExtra("result");
        String title = intent.getStringExtra("title");
        wb_webview.getSettings().setJavaScriptEnabled(true);//启用js
        wb_webview.getSettings().setBlockNetworkImage(false);
        tv_put_title.setText(title);
        wb_webview.loadUrl(result);
    }

    private void initView() {
        tv_fanhui = findViewById(R.id.tv_fanhui);
        tv_put_title = findViewById(R.id.tv_put_title);
        tv_quanji = findViewById(R.id.tv_qianji);
        wb_webview = findViewById(R.id.wb_webview);
    }
}
