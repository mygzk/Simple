package com.example.simple.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simple.R;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by guozhk on 2018/9/3.
 */

public class RcTestAdapter extends RecyclerView.Adapter<RcTestAdapter.HoderView> {

    private List<String> mDatas;

    public RcTestAdapter(List<String> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public HoderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item_test, parent, false);
        HoderView hoderView = new HoderView(view);
        return hoderView;
    }

    @Override
    public void onBindViewHolder(HoderView holder, int position) {
        holder.tv.setText(mDatas.get(position));

    }

    @Override
    public int getItemCount() {
        return this.mDatas.size();
    }

    class HoderView extends RecyclerView.ViewHolder {
        TextView tv;

        public HoderView(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.rc_item_tv);
        }


    }


}
