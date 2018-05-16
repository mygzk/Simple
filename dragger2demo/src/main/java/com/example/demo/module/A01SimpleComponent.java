package com.example.demo.module;

import com.example.demo.MainActivity;

import dagger.Component;

/**
 * Created by guozhk on 2018/5/11.
 */
@Component(modules = A01SimpleModule.class)
public interface A01SimpleComponent {

    void inject(MainActivity activity);
}
