package com.example.simple.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by guozhk on 2017/7/10.
 */

public class MyFragLayout extends FrameLayout {
    private String TAG = MyFragLayout.class.getSimpleName();
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
        Log.e(TAG,"=====MyFragLayout=====dispatchTouchEvent=====");
        return super.dispatchTouchEvent(ev);
    }
}
