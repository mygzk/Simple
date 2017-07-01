package com.example.simple.ui;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.simple.R;
import com.example.simple.widget.CircleProgressView;

public class DrawActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleProgressView mCirecleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        initView();

    }

    private void initView() {
        findViewById(R.id.draw_circle).setOnClickListener(this);
        findViewById(R.id.draw_duihao).setOnClickListener(this);
        mCirecleView =(CircleProgressView) findViewById(R.id.progress_circle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.draw_circle:
                mCirecleView.setProgressNotInUiThread(80);
                break;
            case R.id.draw_duihao:
               // mCirecleView.clear();
                break;
            default:
                break;

        }

    }
}
