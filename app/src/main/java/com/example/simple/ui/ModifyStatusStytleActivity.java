package com.example.simple.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.simple.R;
import com.example.simple.utils.StatusBarUtil;

public class ModifyStatusStytleActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_status_stytle);
        initView();
    }

    private void initView() {

        findViewById(R.id.btn_test1).setOnClickListener(this);
        findViewById(R.id.btn_test2).setOnClickListener(this);
        findViewById(R.id.btn_test3).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test1:
                StatusBarUtil.transparencyBar(this);
                break;
            case R.id.btn_test2:
                StatusBarUtil.setStatusBarColor(this,R.color.colorAccent);
                break;
            case R.id.btn_test3:
                StatusBarUtil.StatusBarLightMode(this);
                break;
            default:
                break;
        }

    }
}
