package com.example.simple.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.simple.R;

/**
 * create by guozhk on 2019/3/28 15:09
 **/
public class UpdateProgressDiolog extends Dialog {
    private Context mContext;
    private ProgressBar mProgressBar;
    // private TextView tvTotalSize;
    private TextView tvProcess;


    private int mMaxSize;


    public UpdateProgressDiolog(@NonNull Context context) {
        this(context, R.style.dialog_update);

    }

    public UpdateProgressDiolog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        initView();

    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/

    public void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_update_progress, null);
        setContentView(view);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8);
        dialogWindow.setAttributes(lp);

        setCanceledOnTouchOutside(false);
        setCancelable(false);

        mProgressBar = view.findViewById(R.id.dialog_update_process);
        //  tvTotalSize = view.findViewById(R.id.dialog_update_total);
        tvProcess = view.findViewById(R.id.dialog_update_process_tv);


        mProgressBar.setMax(mMaxSize);
        mProgressBar.setProgress(0);
        // tvTotalSize.setText(getSizeM(0));
    }

    public void updateProcess(int size) {
        mProgressBar.setProgress(size);
        tvProcess.setText(size + "%");
    }

    public void updateToatalSize(int size) {
        mProgressBar.setMax(size);
        // tvTotalSize.setText(getSizeM(size));
    }

    public UpdateProgressDiolog setmMaxSize(int mMaxSize) {
        this.mMaxSize = mMaxSize;
        return this;
    }

    private String getSizeM(int size) {
        if (size <= 0) {
            return "0M";
        }
        float s = size / (1024 * 1024 * 1f);
        return s + "M";
    }
}
