package com.example.simple.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.simple.R;
import com.example.simple.widget.MyFragLayout;
import com.example.simple.widget.MyText;

public class DispatchTouchTestActivity extends AppCompatActivity {

   // private String TAG = DispatchTouchTestActivity.class.getSimpleName();
    public static String TAG ="DispatchTouchEvent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_touch_test);

        initView();


   //     Glide.with(this).load("").into(null);
    }

    private void initView() {
        MyText myText = findViewById(R.id.dispath_tv) ;
//        myText.setOnClickListener(new DialogListView.OnClickListener() {
//            @Override
//            public void onClick(DialogListView v) {
//                Log.e(TAG, "myText view click ");
//
//            }
//        });



    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "=====DispatchTouchTestActivity=====dispatchTouchEvent 000=====dis:");
       boolean dis = super.dispatchTouchEvent(ev);
        Log.e(TAG, "=====DispatchTouchTestActivity=====dispatchTouchEvent 111=====dis:"+dis);

         return dis;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean dis = super.onTouchEvent(event);
        Log.e(TAG,"=====DispatchTouchTestActivity=====onTouchEvent=====dis:"+dis);
        return dis;
    }
}
