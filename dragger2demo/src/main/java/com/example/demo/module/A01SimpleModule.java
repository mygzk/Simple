package com.example.demo.module;

import com.example.demo.MainActivity;

import dagger.Module;

/**
 * Created by guozhk on 2018/5/11.
 */

@Module
public class A01SimpleModule {

    private MainActivity activity;


    public A01SimpleModule(MainActivity activity) {
        this.activity = activity;
    }
}
