package com.example.simple.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.example.simple.ui.DispatchTouchTestActivity;

/**
 * Created by guozhk on 2017/7/10.
 */

public class MyFragLayout extends FrameLayout {
  //  private String TAG = MyFragLayout.class.getSimpleName();
    private String TAG = DispatchTouchTestActivity.TAG;
    public MyFragLayout(@NonNull Context context) {
        super(context);
    }

    public MyFragLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFragLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG,"=====MyFragLayout=====dispatchTouchEvent 000=====dis:");
        boolean dis = super.dispatchTouchEvent(ev);
        Log.e(TAG,"=====MyFragLayout=====dispatchTouchEvent 111=====dis:"+dis);
        return dis;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean dis = super.onInterceptTouchEvent(ev);
      //  dis=true;
        Log.e(TAG,"=====MyFragLayout=====onInterceptTouchEvent=====dis:"+dis);
        return dis;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean dis = super.onTouchEvent(event);
        dis =true;
        Log.e(TAG,"=====MyFragLayout=====onTouchEvent=====dis:"+dis);
        return dis;
    }
}
