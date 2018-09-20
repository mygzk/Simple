package com.example.simple.ui;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.simple.R;
import com.example.simple.widget.CircleProgressView;
import com.example.simple.widget.CircleView;

public class DrawActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleProgressView mCirecleView;

    private CircleView circleProgressBar;
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
        circleProgressBar =(CircleView) findViewById(R.id.progress_circle_1);

        circleProgressBar.setFirstColor(Color.LTGRAY);
	//	circleProgressBar.setColorArray(colors); //觉得进度条颜色丑的，这里可以自行传入一个颜色渐变数组。
		circleProgressBar.setCircleWidth(6);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.draw_circle:
                mCirecleView.setProgressNotInUiThread(80);
                break;
            case R.id.draw_duihao:

               // mCirecleView.clear();

                circleProgressBar.setProgress(80,true);
                break;
            default:
                break;

        }

    }
}
