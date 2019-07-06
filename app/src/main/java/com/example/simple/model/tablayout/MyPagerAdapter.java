package com.example.simple.model.tablayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guozhk on 2019/1/22.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {


    private Context context;
    private List<Fragment> fragmentList;
    private List<String> list_Title;

    public MyPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        initTabTitle();

    }


    private void initTabTitle() {
        this.list_Title = new ArrayList<>();

//        list_Title.add("第一个");
//        list_Title.add("三是的撒大大所多是");
//        list_Title.add("二");
//        list_Title.add("三是的是");
//
//        list_Title.add("三是的撒大大所多是");


        for (int i = list_Title.size(); i < fragmentList.size(); i++) {
            list_Title.add("A-" + i);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    /**
     * //此方法用来显示tab上的名字
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }


}
