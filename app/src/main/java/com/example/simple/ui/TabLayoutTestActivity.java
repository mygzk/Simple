package com.example.simple.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.simple.R;

public class TabLayoutTestActivity extends Activity {
    private String TAG = TabLayoutTestActivity.class.getSimpleName();
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_test);

        initView();
    }

    private void initView() {
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
        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "===============i:" + finalI);
                    tabLayout.getTabAt(finalI).select();
                }
            });
            tab.setCustomView(view);
            tabLayout.addTab(tab);
        }


    }
}
