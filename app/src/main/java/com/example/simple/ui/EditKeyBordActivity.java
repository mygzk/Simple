package com.example.simple.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.simple.R;

public class EditKeyBordActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_key_bord;
    }

    @Override
    protected void initView() {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("main_activity", "dispatchKeyEvent   event:"+event.toString());
        Log.i("main_activity", "dispatchKeyEvent   event:"+event.getAction());
        Log.i("main_activity", "dispatchKeyEvent   event:"+event.getKeyCode());

        return super.dispatchKeyEvent(event);
    }
}
