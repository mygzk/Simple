package com.example.simple.model.upload;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simple.R;

import java.util.List;

/**
 * Created by guozhk on 2018/12/20.
 * 文件列表
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.HoldeView> {

    private List<FileBean> mData;


    public FileAdapter(List<FileBean> mData) {
        this.mData = mData;
    }


    public void update(List<FileBean> data) {

        if (data != null) {
            this.mData.clear();
            this.mData.addAll(data);
            notifyDataSetChanged();
        }
    }


    public FileBean getItemData(int pos){
        return mData.get(pos);
    }

    @NonNull
    @Override
    public HoldeView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item_file, parent, false);

        return new HoldeView(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HoldeView holder, int position) {
        holder.tvName.setText(mData.get(position).name);
        if (mData.get(position).isDir) {
            holder.imgLable.setImageResource(R.mipmap.icon_wenjianjia);
        } else {
            holder.imgLable.setImageResource(R.mipmap.icon_wenjian_default);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
        //return 10;
    }

    class HoldeView extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgLable;

        public HoldeView(View itemView) {
            super(itemView);

            initView(itemView);
        }

        private void initView(View itemView) {
            imgLable = itemView.findViewById(R.id.rc_item_file_floder_img);
            tvName = itemView.findViewById(R.id.rc_item_file_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClick != null) {
                        mItemClick.onItemClick(getAdapterPosition());
                    }
                }
            });

        }
    }

    private OnItemClick mItemClick;

    public void setItemClick(OnItemClick mItemClick) {
        this.mItemClick = mItemClick;
    }

    public interface OnItemClick {
        void onItemClick(int pos);
    }


}
