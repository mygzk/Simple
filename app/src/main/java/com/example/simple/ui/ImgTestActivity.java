package com.example.simple.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.simple.R;
import com.squareup.picasso.Picasso;

public class ImgTestActivity extends AppCompatActivity {

    private ImageView img0, img1, img2, img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_test);

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
                .into(img2);
        Glide.with(this)
                .load(imgUrl2)
                .into(img3);

    }
}
