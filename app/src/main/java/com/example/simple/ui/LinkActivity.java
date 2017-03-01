package com.example.simple.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.util.Log;
import android.view.View;

import com.example.simple.R;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.LinkagePicker;

public class LinkActivity extends AppCompatActivity {

    private ArrayList<String> mFirstDatas;
    private ArrayList<ArrayList<String>> mSecondDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        initDatas();
        findViewById(R.id.linkbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLink();
            }
        });
    }

    private void initDatas() {
        mFirstDatas = new ArrayList<>();
        mFirstDatas.add("A");
        mFirstDatas.add("B");
        mFirstDatas.add("C");
        mSecondDatas = new ArrayList<>();
        ArrayList<String> mSecond0 = new ArrayList<>();
        mSecond0.add("A-0");
        mSecond0.add("A-1");
        mSecond0.add("A-2");
        mSecond0.add("A-3");
        mSecond0.add("A-4");
        mSecondDatas.add(mSecond0);
        ArrayList<String> mSecond1 = new ArrayList<>();
        mSecond1.add("B-0");
        mSecond1.add("B-1");
        mSecond1.add("B-2");
        mSecond1.add("B-3");
        mSecond1.add("B-4");
        mSecondDatas.add(mSecond1);
        ArrayList<String> mSecond2 = new ArrayList<>();
        mSecond2.add("C-0");
        mSecond2.add("C-1");
        mSecond2.add("C-2");
        mSecond2.add("C-3");
        mSecond2.add("C-4");
        mSecondDatas.add(mSecond2);

    }

    private void showLink() {

        LinkagePicker linkagePicker = new LinkagePicker(this, mFirstDatas, mSecondDatas);
        linkagePicker.setOnLinkageListener(new LinkagePicker.OnLinkageListener() {
            @Override
            public void onPicked(String first, String second, String third) {
                Log.e("log","first:"+first+"  second:"+second+" third:"+third);
            }
        });
        linkagePicker.onSubmit();
        linkagePicker.show();
    }
}
