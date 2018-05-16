package com.example.demo;

//import android.os.Handler;
//import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.demo.module.DaggerA01SimpleComponent;
import com.example.demo.module.Student;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        Handler handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//            }
//        };

//        handler.sendEmptyMessage()


        //DaggerA01SimpleComponent.builder().a01SimpleModule();

        initView();
    }

    private void initView() {
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,student.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
