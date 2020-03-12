package com.example.simple.model.stikkyheader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.simple.R;
import com.example.simple.utils.DensityUtil;

import it.carlom.stikkyheader.core.StikkyHeader;
import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.StikkyHeaderListView;

public class StikkyheaderActivity extends AppCompatActivity {
    private ListView mListView;
    FrameLayout containerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stikkyheader);


        initView();
    }

    private void initView() {

        containerView =  findViewById(R.id.layout_container);
        mListView = (ListView) findViewById(R.id.listview);


        IO2014HeaderAnimator animator = new IO2014HeaderAnimator(this);

        StikkyHeader header=   StikkyHeaderBuilder.stickTo(mListView)
                .setHeader(R.id.header,containerView )
                .minHeightHeaderDim(R.dimen.min_height_header_materiallike)
               // .animator(animator)
                .build();


        populateListView(mListView);



    }


    public  void populateListView(ListView listView) {
        String[] elements = new String[50];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = "row " + i;
        }

        listView.setAdapter(new ArrayAdapter<>(listView.getContext(), android.R.layout.simple_list_item_1, elements));
    }

}
