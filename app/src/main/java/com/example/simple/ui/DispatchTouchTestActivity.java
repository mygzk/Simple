package com.example.simple.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.simple.R;
import com.example.simple.widget.MyFragLayout;

public class DispatchTouchTestActivity extends AppCompatActivity {
    private String TAG = DispatchTouchTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_touch_test);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "=====DispatchTouchTestActivity=====dispatchTouchEvent=====");

         return super.dispatchTouchEvent(ev);
    }
}
