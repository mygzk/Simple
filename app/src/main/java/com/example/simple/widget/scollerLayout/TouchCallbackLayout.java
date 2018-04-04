package com.example.simple.widget.scollerLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by guozhk on 2018/3/19.
 */

public class TouchCallbackLayout extends FrameLayout {
        private String TAG = TouchCallbackLayout.class.getSimpleName();

    public void setTouchEventListener(TouchEventListener touchEventListener) {
        mTouchEventListener = touchEventListener;
    }

    private TouchEventListener mTouchEventListener;

    public TouchCallbackLayout(Context context) {
        this(context,null);
    }

    public TouchCallbackLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }





    public TouchCallbackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface TouchEventListener {
        public boolean onLayoutInterceptTouchEvent(MotionEvent ev);

        public boolean onLayoutTouchEvent(MotionEvent ev);
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (mTouchEventListener != null) {
            return mTouchEventListener.onLayoutInterceptTouchEvent(ev);
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {

        if (mTouchEventListener != null) {
            return mTouchEventListener.onLayoutTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }
}
