package com.example.simple;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;

/**
 * Created by guozhk on 2017/12/4.
 */

public class MyApp extends Application {
    private static MyApp app;


    @Override
    public void onCreate() {
        super.onCreate();


        initKDXF();
        app = this;
    }

    /**
     * 初始化科大讯飞
     */
    private void initKDXF() {
        SpeechUtility.createUtility(this, "appid=5b55349a");
    }

    public static MyApp getApp(){
        return app;
    }
}
