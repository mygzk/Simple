package com.example.simple.ui.scaleView;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.simple.R;
import com.example.simple.adapter.MyVpAdater;
import com.example.simple.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerSimpleActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_pager_simple;
    }

    @Override
    protected void initView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setPageMargin(0);
        viewPager.setOffscreenPageLimit(3);
//        List<Integer> list = new ArrayList<>();
//        list.add(R.drawable.page_guide_first_bg);
//        list.add(R.drawable.page_guide_second_bg);
//        list.add(R.drawable.page_guide_third_bg);
//        list.add(R.drawable.page_guide_four_bg);
//        list.add(R.drawable.page_launch_icon);
        List<CardFragment> list = new ArrayList<>();
        for (int i=0;i<5;i++){
            CardFragment cardFragment = CardFragment.newInstance(i);
            list.add(cardFragment);
        }


        MyVpAdater adater = new MyVpAdater(getSupportFragmentManager(),this, list);
        viewPager.setAdapter(adater);
       viewPager.setPageTransformer(false, new ScaleTransformer());
    }
}
