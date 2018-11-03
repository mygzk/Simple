package com.example.simple.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.simple.R;
import com.example.simple.widget.scollerLayout.AbsListViewDelegate;
import com.example.simple.widget.scollerLayout.ScrollableFragmentListener;
import com.example.simple.widget.scollerLayout.ScrollableListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragmentFragment extends Fragment implements ScrollableListener {

    protected ScrollableFragmentListener mListener;
    private AbsListViewDelegate mAbsListViewDelegate = new AbsListViewDelegate();
    private ListView listView;


    public ListFragmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mListener != null) {
            mListener.onFragmentAttached(this, 0);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ScrollableFragmentListener) activity;
        } catch (ClassCastException e) {
//            throw new ClassCastException(
//                    activity.toString() + " must implement ScrollableFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        if (mListener != null) {
            mListener.onFragmentDetached(this, 0);
        }

        super.onDetach();
        mListener = null;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         listView = view.findViewById(R.id.test_list);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("test-" + i);
        }
        listView.setAdapter(new MyAdapter(getContext(), data));

    }


    class MyAdapter extends BaseAdapter {
        Context context;
        List<String> datas;

        public MyAdapter(Context context, List<String> datas) {
            this.context = context;
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HolderView holderView = null;
            if (convertView == null) {
                holderView = new HolderView();
                TextView tv = new TextView(context);
                tv.setGravity(Gravity.CENTER);
                int itemHeight = (int) (context.getResources().getDisplayMetrics().density * 46);
                AbsListView.LayoutParams lp =
                        new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight);
                tv.setLayoutParams(lp);
                holderView.tvClass = tv;
                convertView = tv;
                convertView.setTag(holderView);

            } else {
                holderView = (HolderView) convertView.getTag();
            }

            holderView.tvClass.setText(datas.get(position) + "");
            return convertView;
        }
    }

    class HolderView {
        TextView tvClass;

    }


    @Override
    public boolean isViewBeingDragged(MotionEvent event) {
        return mAbsListViewDelegate.isViewBeingDragged(event, listView);
    }

//    @Override
//    public boolean isViewBeingDragged(MotionEvent event) {
//        return false;
//    }
}
