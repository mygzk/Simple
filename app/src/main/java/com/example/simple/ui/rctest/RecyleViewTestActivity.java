package com.example.simple.ui.rctest;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.simple.R;
import com.example.simple.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guozhk on 2018/9/3.
 */
public  class RecyleViewTestActivity extends BaseActivity {

    private RecyclerView rc;
    private List<String> mDatas;
    private RcTestAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyle_view_test;
    }

    @Override
    protected void initView() {

        rc = findViewById(R.id.test_rc);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rc.setLayoutManager(manager);


        rc.addItemDecoration(new RecycleViewDivider(rc.getContext(),
                manager.getOrientation()));

//        rc.addItemDecoration(new DividerItemDecoration(rc.getContext(),
//                manager.getOrientation());



        mDatas = new ArrayList<>();

        for (int i=0;i<10;i++){
            mDatas.add("test-"+i);
        }
        mAdapter = new RcTestAdapter(mDatas);

        rc.setAdapter(mAdapter);

    }
}