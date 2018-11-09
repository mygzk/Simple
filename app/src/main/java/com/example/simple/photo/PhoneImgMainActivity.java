package com.example.simple.photo;

import android.content.Intent;
import android.view.View;

import com.example.simple.R;
import com.example.simple.ui.BaseActivity;

/**
 * 图片预览主页
 * https://github.com/bm-x/PhotoView.git
 */
public class PhoneImgMainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_img_main;
    }

    @Override
    protected void initView() {
        queryViewById(R.id.photo_main1, true);
        queryViewById(R.id.photo_main2, true);
      /*  queryViewById(R.id.photo_main3, true);
        queryViewById(R.id.photo_main4, true);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_main1:
                startActivity(new Intent(this, PhoneImgActivity.class));
                break;
            case R.id.photo_main2:
                startActivity(new Intent(this, PhotoViewpagerActivity.class));
                break;
            case R.id.photo_main3:
                break;
            case R.id.photo_main4:
                break;
        }
    }
}
