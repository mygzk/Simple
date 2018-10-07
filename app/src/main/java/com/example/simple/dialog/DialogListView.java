package com.example.simple.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.simple.R;
import com.example.simple.widget.MyFragLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guozhk on 2018/10/7.
 */

public class DialogListView extends FrameLayout {

    private FrameLayout dialogTitleLayout;
    private FrameLayout dialogButtomLayout;
    private RecyclerView mDialogRc;

    private DialogAdapter mADapter;
    private List<String> mdatas;


    public DialogListView(@NonNull Context context) {
        this(context, null);
    }

    public DialogListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialogListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    boolean checkAll = false;

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_list, this);
        dialogTitleLayout = view.findViewById(R.id.dialog_title_layout);
        dialogButtomLayout = view.findViewById(R.id.dialog_bottom_layout);
        mDialogRc = view.findViewById(R.id.dialog_rc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mDialogRc.setLayoutManager(linearLayoutManager);
        mdatas = new ArrayList<>();
        mADapter = new DialogAdapter(mdatas, true);

        mDialogRc.setAdapter(mADapter);


        view.findViewById(R.id.dialog_bottom_left).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAll) {
                    checkAll = false;
                    mADapter.selectOrCancelAll(true);
                } else {
                    checkAll = true;
                    mADapter.selectOrCancelAll(false);
                }
            }
        });

        view.findViewById(R.id.dialog_bottom_left_dele).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mADapter.removeSelect();
            }
        });
        view.findViewById(R.id.dialog_bottom_label).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mADapter.getSelectPos();
            }
        });


    }


    public void updateDatas(List<String> datas) {
        mADapter.updateData(datas);
    }


}
