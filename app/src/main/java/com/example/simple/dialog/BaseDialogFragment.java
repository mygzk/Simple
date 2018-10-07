package com.example.simple.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * DialogFragment
 * Created by guozhk on 16-4-7.
 */
public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {
    private String TAG = BaseDialogFragment.class.getSimpleName();

    private static final float DEFAULT_DIMAMOUNT = 0.2F;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            //设置窗体背景色透明
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置宽高
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            if (getDialogWidth() > 0) {
                layoutParams.width = getDialogWidth();
            } else {
                layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            }
            if (getDialogHeight() > 0) {
                layoutParams.height = getDialogHeight();
            } else {
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            }
            //透明度
            layoutParams.dimAmount = getDimAmount();
            //位置
            layoutParams.gravity = getGravity();
            window.setAttributes(layoutParams);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            //去除标题
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //设置窗体背景色透明
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.setCanceledOnTouchOutside(isCancelableOutside());

            dialog.getWindow().setWindowAnimations(getDialogAnimationRes());
        }
        //return inflater.inflate(doGetContentViewId(), container, false);

        View view = null;
        if (doGetContentViewId() > 0) {
            view = inflater.inflate(doGetContentViewId(), container, false);
        }
        if (getDialogView() != null) {
            view = getDialogView();
        }
        //bindView(view);
        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    //默认弹窗位置为中心
    public int getGravity() {
        return Gravity.CENTER;
    }

    //默认宽高为包裹内容
    public int getDialogHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    public int getDialogWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    //默认透明度为0.2
    public float getDimAmount() {
        return DEFAULT_DIMAMOUNT;
    }

    public abstract int doGetContentViewId();


    // protected abstract void bindView(DialogListView view);

    protected abstract View getDialogView();


    public abstract void initView(View view);


    public boolean isCancelableOutside() {
        return false;
    }

    //获取弹窗显示动画,子类实现
    protected int getDialogAnimationRes() {
        return 0;
    }


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


}
