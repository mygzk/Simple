package com.example.simple.model.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.simple.R;
import com.example.simple.model.adress.AdressBean;
import com.example.simple.model.adress.AdressView;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutTest1Activity extends FragmentActivity {
    private String TAG = TabLayoutTest1Activity.class.getSimpleName();
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_test_1);

        initView();
    }

    private void initView() {

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e(TAG, "====onTabSelected====tab:" + tab.getPosition() + " " + tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(TestTabFragment.newInstance("aaa"));
        fragmentList.add(TestTabFragment.newInstance("bbb"));
        fragmentList.add(TestTabFragment.newInstance("ccc"));
//        fragmentList.add(TestTabFragment.newInstance("ddd"));
//        fragmentList.add(TestTabFragment.newInstance("eeee"));
//        fragmentList.add(TestTabFragment.newInstance("ffff"));
//        fragmentList.add(TestTabFragment.newInstance("gggg"));
//        fragmentList.add(TestTabFragment.newInstance("hhhh"));


        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),TabLayoutTest1Activity.this,fragmentList));
        tabLayout.setupWithViewPager(viewPager);//此方法就是让tablayout和ViewPager联动


        TabUtil.setTabWidth(tabLayout,30);
    }





}
