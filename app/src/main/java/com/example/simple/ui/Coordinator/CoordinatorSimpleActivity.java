package com.example.simple.ui.Coordinator;


import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.example.simple.R;
import com.example.simple.ui.BaseActivity;

public class CoordinatorSimpleActivity extends BaseActivity {

    Toolbar mToolbar;
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coordinator_simple;
    }

    @Override
    protected void initView() {

        mCollapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
//
        mToolbar.setTitle("建筑资质发标发标你好法发顺丰的说法撒旦飞洒地方撒");
        //mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
//        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
//        mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
//        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
      //  mCollapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);
        //mCollapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER_HORIZONTAL);

    }
}
