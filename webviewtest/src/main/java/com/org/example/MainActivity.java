package com.org.example;

import android.app.Fragment;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private Button mBtn;
    private WebView mWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        FrameLayout f;
        mBtn =  findViewById(R.id.test);
        mWeb = findViewById(R.id.wb_test);

        WebSettings webSettings =mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        mWeb.loadUrl("file:///android/html/javascript.html");

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 通过Handler发送消息
                mWeb.post(new Runnable() {
                    @Override
                    public void run() {

                        // 注意调用的JS方法名要对应上
                        // 调用javascript的callJS()方法
                        mWeb.loadUrl("javascript:callJS()");
                    }
                });
            }
        });



       // FrameLayout f;




    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
