package com.example.simple.model.kdxf;


import android.view.View;

import com.example.simple.R;
import com.example.simple.ui.BaseActivity;

/**
 * 科大讯飞sdk 测试
 */
public class KDXFActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        SpeechRecognizerUtil.getInstance().init(this);
        return R.layout.activity_kdxf;
    }

    @Override
    protected void initView() {
        queryViewById(R.id.kdxf_start,true);
        queryViewById(R.id.kdxf_end,true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kdxf_start:
                SpeechRecognizerUtil.getInstance().startRecognizer();
            break;
            case R.id.kdxf_end:
                SpeechRecognizerUtil.getInstance().stopRecognizer();
                break;
                default:
                    break;
        }
    }
}
