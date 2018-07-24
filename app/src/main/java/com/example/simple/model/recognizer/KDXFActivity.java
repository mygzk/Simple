package com.example.simple.model.recognizer;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.simple.R;
import com.example.simple.ui.BaseActivity;

/**
 * 科大讯飞sdk 测试
 */
public class KDXFActivity extends BaseActivity {
TextView tvStart;
    @Override
    protected int getLayoutId() {
        initRecognizer();
        return R.layout.activity_kdxf;
    }


    private void initRecognizer(){
        SpeechRecognizerManager.getInstance().initRecognizer(this,SpeechRecognizerManager.TYPE_KDXF);
        SpeechRecognizerManager.getInstance().setRecogUIIntrface(new RecogUIIntrface() {
            @Override
            public void showRecogPage(boolean show) {
                Log.e("RecogUIIntrface","showRecogPage  show:"+show);
            }

            @Override
            public void recogContent(String content) {
                Log.e("RecogUIIntrface","recogContent: "+content);
            }

            @Override
            public void refreshRecogBtnUI(boolean cancelFlag) {
                Log.e("RecogUIIntrface","refreshRecogBtnUI cancelFlag:"+cancelFlag);
            }

            @Override
            public void sendRecogMessage(String recogSr) {
                Log.e("RecogUIIntrface","sendRecogMessage："+recogSr);
            }

            @Override
            public void recogError(int errorCode, String errorMessage) {
                Log.e("RecogUIIntrface","recogError："+errorMessage);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        tvStart= queryViewById(R.id.kdxf_start);
        queryViewById(R.id.kdxf_end,true);
        tvStart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return  SpeechRecognizerManager.getInstance().recogTouch(v,event);
               // return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kdxf_start:
            break;
            case R.id.kdxf_end:
                break;
                default:
                    break;
        }
    }
}
