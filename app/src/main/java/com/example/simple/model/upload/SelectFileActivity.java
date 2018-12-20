package com.example.simple.model.upload;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simple.R;
import com.example.simple.ui.BaseActivity;
import com.example.simple.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectFileActivity extends BaseActivity {

    private LinearLayout labelConer;
    private RecyclerView rcFiles;
    private FileAdapter mAdater;


    private FileUtil mFileUtil;

    private List<FileBean.FilePath> mLabelData;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_file;
    }

    @Override
    protected void initView() {
        labelConer = queryViewById(R.id.file_lable_container);
        rcFiles = queryViewById(R.id.file_rc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcFiles.setLayoutManager(linearLayoutManager);
        mAdater = new FileAdapter(new ArrayList<FileBean>());
        mAdater.setItemClick(new FileAdapter.OnItemClick() {
            @Override
            public void onItemClick(int pos) {
                itemClick(mAdater.getItemData(pos));
            }
        });
        rcFiles.setAdapter(mAdater);


        mFileUtil = new FileUtil();
        mFileUtil.setIResulitCallback(new FileUtil.IResultCallback() {
            @Override
            public void result(List<FileBean> result) {
                mAdater.update(result);
            }

            @Override
            public void fail(String msg) {
                Toast.makeText(SelectFileActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        mFileUtil.findFiles("");
    }

    private void itemClick(FileBean itemData) {
        Log.e("itemClick", "itemData0 " + itemData.file.getPath());
        Log.e("itemClick", "itemData1 " + itemData.file.getAbsolutePath());

        updateFloderPath(itemData.getPathData());
        if (itemData.isDir) {
            mFileUtil.findFiles(itemData.path);
        } else {
            Toast.makeText(this, itemData.name, Toast.LENGTH_SHORT).show();
        }
    }


    private void updateFloderPath(List<FileBean.FilePath> labelData) {


        if (labelData == null) {
            return;
        }
        if (labelData.size() == 0) {
            return;
        }
        if (mLabelData == null) {
            mLabelData = new ArrayList<>();
        } else {
            mLabelData.clear();
        }

        labelConer.removeAllViews();

        for (final FileBean.FilePath path : labelData) {
            if (path == null) {
                continue;
            }
            mLabelData.add(path);

            TextView tv = new TextView(this);
            tv.setTextColor(getResources().getColor(R.color.color_282828));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, DensityUtil.dip2px(this, 12));
            int padding = DensityUtil.dip2px(this, 5);
            tv.setPadding(padding, padding, padding, padding);
            tv.setText(path.currentpath);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateFloderPath(FileBean.getPathData(path.path));
                    mFileUtil.findFiles(path.path);
                }
            });
            labelConer.addView(tv);
        }
    }


    @Override
    public void onBackPressed() {
        if (mLabelData != null && mLabelData.size() > 0) {
            if (mLabelData.size() > 1) {
                FileBean.FilePath path = mLabelData.get(mLabelData.size() - 2);
                updateFloderPath(FileBean.getPathData(path.path));
                mFileUtil.findFiles(path.path);
            } else {
                mLabelData.clear();
                labelConer.removeAllViews();
                mFileUtil.findFiles("");
            }
        } else {
            super.onBackPressed();
        }

    }
}
