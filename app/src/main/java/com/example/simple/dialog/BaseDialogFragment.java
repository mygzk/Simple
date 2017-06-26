package com.example.simple.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by guozhk on 16-4-7.
 */
public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {
    private boolean mOnTouchOutCancel = true;
    private boolean mOnBackCancel = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            //  dialog.getWindow().setLayout((int) (dm.widthPixels * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = (int) (dm.widthPixels * 0.8);
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialogWindow.setGravity(Gravity.CENTER);
            dialogWindow.setAttributes(lp);
            dialog.setCanceledOnTouchOutside(onTouchOutsideCancel());
            if (!onBackCancel()) {
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            return true;
                        }
                        return false;
                    }
                });
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return inflater.inflate(doGetContentViewId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    public abstract int doGetContentViewId();

    public abstract void initView(View view);


    public <T extends View> T queryViewById(View parentView, int viewId) {
        return (T) parentView.findViewById(viewId);
    }

    protected <T extends View> T queryViewById(View parentView, int viewId, boolean clickListener) {
        if (viewId > 0) {
            T view = queryViewById(parentView, viewId);
            if (clickListener && view != null)
                addOnClickListener(view);
            return view;
        }
        return null;
    }

    protected void addOnClickListener(View... views) {
        for (int i = 0; i < views.length; i++)
            views[i].setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public void setOnTouchOutCancel(boolean mOnTouchOutCancel) {
        this.mOnTouchOutCancel = mOnTouchOutCancel;
    }

    public void setOnBackCancel(boolean mOnBackCancel) {
        this.mOnBackCancel = mOnBackCancel;
    }

    //触摸外部默认消失
    public boolean onTouchOutsideCancel() {
        return mOnTouchOutCancel;
    }

    //点击返回键消失
    public boolean onBackCancel() {
        return mOnBackCancel;
    }
}
