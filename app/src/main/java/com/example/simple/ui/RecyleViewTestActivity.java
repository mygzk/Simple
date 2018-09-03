package com.example.simple.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.simple.R;
import com.example.simple.adapter.RcTestAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyleViewTestActivity extends BaseActivity {

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

        mDatas = new ArrayList<>();

        for (int i=0;i<10;i++){
            mDatas.add("test-"+i);
        }
        mAdapter = new RcTestAdapter(mDatas);

        rc.setAdapter(mAdapter);

    }
}
