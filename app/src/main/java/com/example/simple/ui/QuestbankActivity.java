package com.example.simple.ui;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.simple.R;
import com.example.simple.adapter.QuestAdapter;
import com.example.simple.bean.QuestionBean;

import java.util.ArrayList;
import java.util.List;


public class QuestbankActivity extends BaseActivity {

    private RecyclerView rvTk;
    private QuestAdapter mAdapter;
    private List<QuestionBean> mData = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_questbank;
    }

    @Override
    protected void initView() {
        queryViewById(R.id.tk_tv, true);
        rvTk = queryViewById(R.id.tk_rc);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTk.setLayoutManager(manager);
        mAdapter = new QuestAdapter(mData);
        rvTk.setAdapter(mAdapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tk_tv:
                finish();
                break;
            default:
                break;
        }
    }
}
