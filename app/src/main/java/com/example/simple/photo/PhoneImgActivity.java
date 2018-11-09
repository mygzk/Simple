package com.example.simple.photo;

import com.bumptech.glide.Glide;
import com.example.simple.R;
import com.example.simple.photo.view.PhotoView;
import com.example.simple.ui.BaseActivity;

/**
 * 单个图片预览
 */
public class PhoneImgActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_img;
    }

    @Override
    protected void initView() {

        PhotoView photoView = queryViewById(R.id.phoneview_0);
        photoView.enable();
        Glide.with(this).load("http://img17.3lian.com/d/file/201702/18/e79b607cd2c91cde65783b5816934572.jpg").into(photoView);


    }
}
