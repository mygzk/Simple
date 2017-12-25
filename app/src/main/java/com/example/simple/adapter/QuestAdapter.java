package com.example.simple.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simple.R;
import com.example.simple.bean.QuestionBean;
import com.example.simple.widget.TopicLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guozhk
 * create time on 2017/12/22.
 */

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.ViewHolder> {
    private List<QuestionBean> mData;

    public QuestAdapter(List<QuestionBean> mData) {
        this.mData = mData;
    }

    @Override
    public QuestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quest_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(QuestAdapter.ViewHolder holder, int position) {
        // holder.tvName.setText("");
        Map<Integer,Boolean> integers = new HashMap<>();
        if (position % 2 == 0) {
            integers.clear();
            integers.put(0,true);
            holder.topicLayout.setSelected(integers).setData("ceshi",null,TopicLayout.TOPIC_TY_ONLY);
        } else {
            integers.clear();
            integers.put(3,true);
            integers.put(1,true);
            holder.topicLayout.setSelected(integers).setData("ceshi",null,TopicLayout.TOPIC_TY_MORE);
        }

      //  holder.topicLayout.setData("ceshi",null,);
    }


    @Override
    public int getItemCount() {
        //return mData.size();
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // public TextView tvName;
        TopicLayout topicLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            // tvName = (TextView)itemView.findViewById(R.id.qt_item_name);
            topicLayout = (TopicLayout) itemView.findViewById(R.id.qt_item_topic);
            topicLayout.setOnResultCallback(new TopicLayout.OnResultCallback() {
                @Override
                public void selectCallback(int pos) {
                    Log.e("checked","pos:"+pos);


                }
            });
        }
    }

}
