package com.example.simple.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by guozhk on 2018/9/22.
 */

public class MyListview extends ListView{

    private String TAG = this.getClass().getSimpleName();

    public MyListview(Context context) {
        super(context);
    }

    public MyListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e(TAG,"==onTouchEvent=ev:"+ev.toString());
        boolean res= super.onTouchEvent(ev);
        Log.e(TAG,"==onTouchEvent=res:"+res);
        return res;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG,"==dispatchTouchEvent=ev:"+ev.toString());
        boolean res= super.dispatchTouchEvent(ev);
        Log.e(TAG,"==dispatchTouchEvent=res:"+res);
        return res;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG,"==onInterceptTouchEvent=ev:"+ev.toString());
        boolean res= super.onInterceptTouchEvent(ev);
        Log.e(TAG,"==onInterceptTouchEvent=res:"+res);
        return res;
    }


}
