package com.example.simple.dialog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.simple.R;
import com.example.simple.adapter.QuestAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guozhk on 2018/10/7.
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.HoderView> {

    private List<String> mDatas;
    private Map<Integer, Boolean> mCbMap;
    private boolean mCh = false;

    private boolean mOnBind = false;;

    public DialogAdapter(List<String> mDatas) {
         this(mDatas, false);
    }

    public DialogAdapter(List<String> mDatas, boolean ch) {
        this.mDatas = mDatas;
        this.mCh = ch;
        initCheckMap();
    }

    private void initCheckMap(){
        if (mCh) {
            mCbMap = new HashMap<>();
            for (int i = 0; i < mDatas.size(); i++) {
                mCbMap.put(i, false);
            }
        }
    }


    public void updateData(List<String> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        initCheckMap();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HoderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_item_list, parent, false);
        HoderView vh = new HoderView(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HoderView holder, final int position) {
        Log.e("adpter","position:"+position);
        mOnBind=true;
        holder.tvtip.setText(mDatas.get(position));

        if (mCh) {

            holder.cb.setChecked(mCbMap.get(position));
            mOnBind=false;
            holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.e("adpter","position:"+position);
                    Log.e("adpter","isChecked:"+isChecked);
                    mCbMap.put(position, isChecked);
                    if(!mOnBind){
                        //notifyItemChanged(position);
                        notifyDataSetChanged();
                    }


                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return this.mDatas.size();
        //return 10;
    }

    class HoderView extends RecyclerView.ViewHolder {
        TextView tvtip;
        CheckBox cb;

        public HoderView(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            cb = itemView.findViewById(R.id.dialog_item_cb);
            tvtip = itemView.findViewById(R.id.dialog_item_tv);

        }
    }
}
