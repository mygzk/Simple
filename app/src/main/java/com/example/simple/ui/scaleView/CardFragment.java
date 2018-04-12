package com.example.simple.ui.scaleView;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.simple.R;


public class CardFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;


    public CardFragment() {
    }

    public static CardFragment newInstance(int param1) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    String[] imgs = {"http://img5.imgtn.bdimg.com/it/u=2311659762,3547267587&fm=27&gp=0.jpg",
            "http://img0.ph.126.net/oaaCMtALDidvHqFL9GQSFQ==/6608427721772598867.jpg",
            "http://scimg.jb51.net/allimg/170112/106-1F11210134T24.jpg",
            "http://pic11.nipic.com/20101210/6349502_123326015514_2.jpg"

    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView img = view.findViewById(R.id.viewpager_card_img);
        Glide.get(getContext());
        Glide.with(getContext()).load(imgs[mParam1 % imgs.length]).into(img);


    }
}
