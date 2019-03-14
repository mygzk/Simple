package com.example.simple.model.cehua;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simple.R;
import com.example.simple.widget.QuickSwipeMenuLayout;

import java.util.List;

public class CehuaAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mDatas;


    public CehuaAdapter(Context context, List<String> datas) {

        this.mContext = context;
        this.mDatas = datas;

    }


    public void update(List<String> datas) {

        if (datas != null) {
            mDatas.clear();

            mDatas.addAll(datas);

            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Holder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_cehua, null);
            holder = new Holder();
            holder.tv = view.findViewById(R.id.tv_name);
            holder.tvRight1 = view.findViewById(R.id.swipe_right_1);
            holder.tvRight2 = view.findViewById(R.id.swipe_right_2);


            holder.itemRoot = view.findViewById(R.id.root_id);
            holder.contextRoot = view.findViewById(R.id.swipe_context);


            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }


        holder.tv.setText(mDatas.get(i));


        holder.tvRight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "删除 tv click", Toast.LENGTH_SHORT).show();
            }
        });

        holder.tvRight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "置顶 tv click", Toast.LENGTH_SHORT).show();
            }
        });


        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemRoot.closeMenu();
                holder.itemRoot.setCanSwip(false);
                Toast.makeText(mContext, "点击item ", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemRoot.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.itemRoot.closeMenu();
                holder.itemRoot.setCanSwip(true);
                Toast.makeText(mContext, "长按 item ", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        return view;
    }


    class Holder {
        QuickSwipeMenuLayout itemRoot;
        RelativeLayout contextRoot;


        TextView tv;
        TextView tvRight1;
        TextView tvRight2;
    }
}
