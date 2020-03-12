package com.example.simple.model.pullloadmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.simple.R;

public class PullMoreActivity extends AppCompatActivity {
    private PullLoadMoreView pullLoadMoreView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_more);



        pullLoadMoreView = findViewById(R.id.pullLoadMoreView);

        //添加头部布局
        pullLoadMoreView.addHeadView(R.layout.top_layout);
        //添加监听open/close
        pullLoadMoreView.setViewStateListener(new PullLoadMoreView.ViewStateListener() {
            @Override
            public void onViewState(PullLoadMoreView.VIewState viewState) {
                if (viewState == PullLoadMoreView.VIewState.OPEN) {
                    Toast.makeText(PullMoreActivity.this, "Open", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PullMoreActivity.this, "Close", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
