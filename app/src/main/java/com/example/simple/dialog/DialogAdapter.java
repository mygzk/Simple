package com.example.simple.dialog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simple.R;

import java.util.List;

/**
 * Created by guozhk on 2018/10/7.
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.HoderView> {

    private List<String> mDatas;
    private SparseBooleanArray mCbMap;
    private boolean mCh = false;

    private boolean mOnBind = false;
    ;

    public DialogAdapter(List<String> mDatas) {
        this(mDatas, false);
    }

    public DialogAdapter(List<String> mDatas, boolean ch) {
        this.mDatas = mDatas;
        this.mCh = ch;
        initCheckMap();
    }

    private void initCheckMap() {
        if (mCh) {
            mCbMap = new SparseBooleanArray();
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
        holder.tvtip.setText(mDatas.get(position));

        if (mCh) {
            holder.checkBox.setChecked(mCbMap.get(position));


            holder.checkBox.setChecked(mCbMap.get(position));

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkCheckBox(position, !mCbMap.get(position));
                }
            });

//            holder.label.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    checkCheckBox(position, !mSelectedItemsIds.get(position));
//                }
//            });


        }

    }

    @Override
    public int getItemCount() {
        return this.mDatas.size();
    }

    class HoderView extends RecyclerView.ViewHolder {
        TextView tvtip;
        CheckBox checkBox;

        public HoderView(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            checkBox = itemView.findViewById(R.id.dialog_item_cb);
            tvtip = itemView.findViewById(R.id.dialog_item_tv);

        }
    }


    public void checkCheckBox(int position, boolean value) {
        if (value) {
            mCbMap.put(position, true);
        } else {
            mCbMap.delete(position);
        }


        notifyDataSetChanged();
    }

    /**
     * 全选或则全部取消
     */
    public void selectOrCancelAll(boolean cancel) {

        if (mDatas == null) {
            return;
        }
        for (int i = 0; i < mDatas.size(); i++) {
            if (cancel) {
                checkCheckBox(i, false);

            } else {
                checkCheckBox(i, true);
            }
        }


    }

    /**
     * 删除选中的item
     */
    public void removeSelect() {
        if (mCbMap != null && mCbMap.size() > 0) {
            //Loop to all the selected rows array
            for (int i = (mCbMap.size() - 1); i >= 0; i--) {

                //Check if selected rows have value i.e. checked item
                if (mCbMap.valueAt(i)) {

                    //remove the checked item
                    mDatas.remove(mCbMap.keyAt(i));
                }
            }

            mCbMap = new SparseBooleanArray();
            notifyDataSetChanged();
        }
    }

    public void getSelectPos(){

        if (mCbMap != null && mCbMap.size() > 0) {


            String pos="";
            for (int i = 0; i < mCbMap.size(); i++) {

                //Check if selected rows have value i.e. checked item
                if (mCbMap.valueAt(i)) {
                    pos=pos+i+",";
                }
            }

            notifyDataSetChanged();
            Log.e("mAdapter","select pos:"+pos);


        }
    }
}
