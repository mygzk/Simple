package com.example.simple.ui.Coordinator;


import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;

import com.example.simple.R;
import com.example.simple.ui.BaseActivity;

public class CoordinatorSimpleActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coordinator_simple;
    }

    @Override
    protected void initView() {
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
//        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
//        mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
//        //通过CollapsingToolbarLayout修改字体颜色
//        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色

    }
}
