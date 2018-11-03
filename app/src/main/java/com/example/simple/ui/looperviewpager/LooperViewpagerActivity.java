package com.example.simple.ui.looperviewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simple.R;

import java.util.ArrayList;
import java.util.List;

public class LooperViewpagerActivity extends Activity {

    private ViewPager vp;
    private List<String> urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper_viewpager);

        initView();
    }


    private void initView() {

        vp = (ViewPager) findViewById(R.id.test_vp);
        urls = new ArrayList<>();
        urls.add("http://seopic.699pic.com/photo/00005/5186.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50010/0719.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50009/9449.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50002/5923.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50001/9330.jpg_wh1200.jpg");
        urls.add("http://seopic.699pic.com/photo/50009/9191.jpg_wh1200.jpg");

        ImgAdapter loopVPAdapter = new ImgAdapter(this, urls, vp);

        vp.setAdapter(loopVPAdapter);

        vp.setOffscreenPageLimit(1);
        TextView tv;
        ImageView imv;


        Intent intent = new Intent();

    }
}
