package com.example.simple.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.simple.ui.scaleView.CardFragment;

import java.util.List;

/**
 * Created by guozhk on 2018/4/12.
 */

public class MyVpAdater extends FragmentPagerAdapter {
    private List<CardFragment> list;
    private Context context;


    public MyVpAdater(FragmentManager fm, Context context,List<CardFragment> list ) {
        super(fm);
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
}
