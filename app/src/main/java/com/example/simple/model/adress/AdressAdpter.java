package com.example.simple.model.adress;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.simple.R;

import java.util.List;

/**
 * Created by guozhk on 2018/12/9.
 */

public class AdressAdpter extends RecyclerView.Adapter<AdressAdpter.HoderView> {

    private List<AdressBean> mDatas;

    public AdressAdpter(List<AdressBean> mDatas) {
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public HoderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_select_adress_list_item, parent, false);
        HoderView hoderView = new HoderView(view);
        return hoderView;
    }


    public void updateDatas(List<AdressBean> datas) {
        if (datas != null) {
            mDatas.clear();
        }
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull HoderView holder, int position) {
        holder.tvAdress.setText(mDatas.get(position).getAreaName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public AdressBean getItemData(int pos){
        return mDatas.get(pos);
    }

    class HoderView extends RecyclerView.ViewHolder {
        private TextView tvAdress;


        public HoderView(View itemView) {
            super(itemView);

            initView(itemView);
        }

        private void initView(View view) {
            tvAdress = view.findViewById(R.id.dialog_select_list_item_address);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("AdressAdpter", "-=-=-=-");
                    if(mOnItemClick!=null){
                        mOnItemClick.onItemClick(getAdapterPosition());
                    }
                }
            });
        }


    }



    private onItemClick mOnItemClick;

    public void setOnItemClick(onItemClick mOnItemClick) {
        this.mOnItemClick = mOnItemClick;
    }

    public interface onItemClick{

        void onItemClick(int pos);
    }

}
