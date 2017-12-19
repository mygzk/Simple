package com.example.simple.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.simple.evenBean.TestMessEven;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by guozhk on 2017/12/14.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(getLayoutId());
        initView();
    }


    protected abstract int getLayoutId();

    protected abstract void  initView();


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {

    }


    protected <T extends View> T queryViewById(int resId) {
        return (T) findViewById(resId);

    }

    protected <T extends View> T queryViewById(View parentView, int resId) {
        return (T) parentView.findViewById(resId);

    }

    protected <T extends View> T queryViewById(int viewId, boolean clickListener) {
        if (viewId > 0) {
            T view = (T) findViewById(viewId);
            if (clickListener && view != null)
                addOnClickListener(view);
            return view;
        }
        return null;
    }

    protected void addOnClickListener(View... views) {
        for (int i = 0; i < views.length; i++)
            views[i].setOnClickListener(this);
    }


    @Subscribe
    public void onEvenMessage5(TestMessEven even) {
        long threadId = Thread.currentThread().getId();
        Log.e(TAG, "0 baseactivity threadId:" + threadId );

    }
}
