package com.example.simple.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.simple.ui.DispatchTouchTestActivity;

/**
 * Created by guozhk on 2017/7/10.
 */

public class MyText extends TextView {
   // private String TAG = MyText.class.getSimpleName();
    private String TAG=  DispatchTouchTestActivity.TAG;
    public MyText(Context context) {
        super(context);
    }

    public MyText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean dis = super.dispatchTouchEvent(event);
        Log.e(TAG,"=====MyText=====dispatchTouchEvent=====dis:"+dis);
        return dis;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"=====MyText=====onTouchEvent=====event:"+event.toString());
        boolean dis = super.onTouchEvent(event);
        Log.e(TAG,"=====MyText=====onTouchEvent=====dis:"+dis);
        return dis;
    }





}
