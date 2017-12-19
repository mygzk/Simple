package com.example.simple.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.simple.R;
import com.example.simple.evenBean.TestMessEven;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EvenBusTestActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_even_bus_test;
    }

    @Override
    protected void initView() {
        queryViewById(R.id.test1, true);
        queryViewById(R.id.test2, true);
        queryViewById(R.id.test3, true);
        queryViewById(R.id.test4, true);
        queryViewById(R.id.test5, true);
    }

    @Override
    public void onClick(View v) {
        long threadId = Thread.currentThread().getId();
        Log.e(TAG, "threadId:" + threadId + " messEven");
        super.onClick(v);
        switch (v.getId()) {
            case R.id.test1:
                TestMessEven even = new TestMessEven(1, "主线程消息");
                EventBus.getDefault().post(even);
                break;
            case R.id.test2:
                new MyThread().start();
                break;
            case R.id.test3:
                break;
            case R.id.test4:
                break;
            case R.id.test5:

                break;

            default:
                break;
        }
    }


    @Subscribe
    public void onEvenMessage(TestMessEven messEven) {
        date("default",Thread.currentThread(),messEven);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvenMessage5(TestMessEven messEven) {
        date("POSTING",Thread.currentThread(),messEven);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvenMessage1(TestMessEven messEven) {
        date("ASYNC",Thread.currentThread(),messEven);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvenMessage2(TestMessEven messEven) {
        date("MAIN",Thread.currentThread(),messEven);

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvenMessage3(TestMessEven messEven) {
        date("BACKGROUND",Thread.currentThread(),messEven);

    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onEvenMessage4(TestMessEven messEven) {
        date("MAIN_ORDERED",Thread.currentThread(),messEven);

    }


    private void  date(String tag,Thread thread,TestMessEven even){
        Log.e(TAG, tag+" threadId:" + thread.getId()+" threadName:"+thread.getName()
                + " messEven" + even.toString());
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            TestMessEven even = new TestMessEven(1, "子线程");
            EventBus.getDefault().post(even);
        }
    }
}
