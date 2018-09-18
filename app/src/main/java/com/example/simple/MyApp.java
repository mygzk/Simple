package com.example.simple;

import android.app.Application;
import android.support.v4.util.LruCache;

/**
 * Created by guozhk on 2017/12/4.
 */

public class MyApp extends Application {
    private static MyApp app;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }




    public static MyApp getApp(){
        return app;
    }
}
