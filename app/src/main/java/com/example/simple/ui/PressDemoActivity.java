package com.example.simple.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.simple.R;

public class PressDemoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press_demo);

        ImageView imgPress = (ImageView) findViewById(R.id.img_press);
        imgPress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("AssistantChatPage", "=====ACTION_DOWN=====");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("AssistantChatPage", "=====ACTION_MOVE=====");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("AssistantChatPage", "=====ACTION_UP=====");

                        break;
                    default:
                        break;

                }

                return true;
            }
        });
    }
}
