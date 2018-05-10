package com.example.simple.ui;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.simple.R;
import com.squareup.picasso.Picasso;

public class ImgTestActivity extends AppCompatActivity {

    private String TAG= "ImgTestActivity";

    private ImageView img0, img1, img2, img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_test);
        Log.e(TAG,"  onCreate.....");
        initView();
    }

    String imgUrl = "http://pic35.nipic.com/20131121/2531170_145358633000_2.jpg";
    String imgUrl1 = "http://pic.58pic.com/58pic/15/28/08/76X58PIC2UP_1024.jpg";
    String imgUrl2="http://img.ycwb.com/news/attachement/gif/site2/20160921/507b9d762551194c19be5f.gif";

    private void initView() {
        img0 = (ImageView) findViewById(R.id.imgtest_0);
        img1 = (ImageView) findViewById(R.id.imgtest_1);
        img2 = (ImageView) findViewById(R.id.imgtest_2);
        img3 = (ImageView) findViewById(R.id.imgtest_3);


        Picasso.with(this)
                .load(imgUrl)
                .placeholder(R.drawable.zw)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .fit()
                .into(img0);
        Picasso.with(this)
                .load(imgUrl1)
                .placeholder(R.drawable.zw)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .fit()
                .into(img1);

        Glide.with(this)
                .load(imgUrl)
                .asBitmap()
                .into(img2);
        Glide.with(this)
                .load(imgUrl2)
                .into(img3);

    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Log.e(TAG,"  onSaveInstanceState.....");
//    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG,"  onSaveInstanceState.....");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG,"  onRestoreInstanceState.....");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.e(TAG,"  onConfigurationChanged.....");
        super.onConfigurationChanged(newConfig);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"  onResume.....");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"  onRestart.....");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"  onPause.....");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"  onStop.....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"  onDestroy.....");
    }

}
