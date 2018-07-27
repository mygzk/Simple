package com.example.simple.model.custonviewpager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.simple.R;
import com.example.simple.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class TabViewpagerActivity extends BaseActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private List<String> listTitles;
     private List<Fragment> fragments;
    private ArrayList<Object> listTextViews;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_viewpager;
    }

    @Override
    protected void initView() {
        mViewPager = queryViewById(R.id.tab_viewpage);
        mTabLayout = queryViewById(R.id.tabView_tab);


        initData();
    }



    private void initData() {
        listTitles = new ArrayList<>();
        fragments = new ArrayList<>();
        listTextViews = new ArrayList<>();

        listTitles.add("推荐");
        listTitles.add("热点");
        listTitles.add("视频");
        listTitles.add("北京");
        listTitles.add("社会");
        listTitles.add("娱乐");
        listTitles.add("问答");
        listTitles.add("图片");
        listTitles.add("科技");
        listTitles.add("汽车");
        listTitles.add("体育");
        listTitles.add("财经");
        listTitles.add("军事");
        listTitles.add("国际");
        for (int i = 0; i < listTitles.size(); i++) {
            ContentFragment fragment = ContentFragment.newInstance(listTitles.get(i));
            fragments.add(fragment);

        }
        //mTabLayout.setTabMode(TabLayout.SCROLL_AXIS_HORIZONTAL);//设置tab模式，当前为系统默认模式
        for (int i=0;i<listTitles.size();i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(listTitles.get(i)));//添加tab选项
        }

        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
            //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
            @Override
            public CharSequence getPageTitle(int position) {
                return listTitles.get(position);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器

    }



}
