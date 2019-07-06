package com.example.simple.dialog;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

/**
 * 创建diaolog
 * Created by guozhk on 2018/10/7.
 */

public class DialogHelper extends BaseDialogFragment {
    private String TAG = this.getClass().getSimpleName();

    private int mLayoutResId;
    private View mDialogView;
    private IBindView mBindView;
    private int[] mClickId;
    private OnDismissListener mDismissListener;
    private int mWidth;
    private int mHight;
    private Boolean mIsCancelableOutside = true;
    private int mGravity;
    //透明度
    private float mDimAmount = 0.2F;
    //动画
    private int mDialogAnimationRes;

    private OnViewClick mOnViewClick;


    @Override
    public int doGetContentViewId() {
        return mLayoutResId;
    }

    @Override
    protected View getDialogView() {
        return mDialogView;
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mDismissListener != null) {
            mDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public void initView(View view) {
        if (mClickId != null) {
            for (int id : mClickId) {
                queryViewById(view, id, true);
            }
        }

        if (mBindView != null) {
            mBindView.bindView(view);
        }

    }


    @Override
    public int getDialogHeight() {

        return mHight;
    }

    @Override
    public int getDialogWidth() {
        return mWidth;
    }


    @Override
    public boolean isCancelableOutside() {
        return mIsCancelableOutside;
    }


    @Override
    public int getGravity() {
        if (mGravity == 0) {
            return Gravity.CENTER;
        }
        return mGravity;
    }

    @Override
    public float getDimAmount() {
        return mDimAmount;
    }

    @Override
    protected int getDialogAnimationRes() {
        return mDialogAnimationRes;
    }


    @Override
    public void onClick(View v) {
        Log.e(TAG, "----");
        if (mOnViewClick != null) {
            mOnViewClick.click(v,this);
        }

    }


    public void setDialogView(View mDialogView) {
        this.mDialogView = mDialogView;
    }

    public static class Builder {

        private int layoutResId;
        private View dialogView;
        private int[] clickId;
        IBindView bindView;
        OnDismissListener dismissListener;
        private int width;
        private int hight;
        private boolean isCancelableOutside;
        private int gravity;
        private float dimAmount = 0.2F;
        private int dalogAnimationRes;
        private OnViewClick onViewClick;

        public Builder layoutResId(int layoutresId) {
            this.layoutResId = layoutresId;
            return this;
        }

        public Builder dialogView(View view) {
            this.dialogView = view;
            return this;
        }

        public Builder clickId(int... id) {
            this.clickId = id;
            return this;
        }

        public Builder bindView(IBindView bindView) {
            this.bindView = bindView;
            return this;
        }


        public Builder bindView(OnDismissListener dismissListener) {
            this.dismissListener = dismissListener;
            return this;
        }


        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHight(int hight) {
            this.hight = hight;
            return this;
        }

        public Builder setCancelableOutside(boolean isCancelableOutside) {
            this.isCancelableOutside = isCancelableOutside;
            return this;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setDimAmount(float dimAmount) {
            this.dimAmount = dimAmount;
            return this;
        }

        public Builder setDalogAnimationRes(int dalogAnimationRes) {
            this.dalogAnimationRes = dalogAnimationRes;
            return this;
        }

        public Builder setOnViewClick(OnViewClick onViewClick) {
            this.onViewClick = onViewClick;
            return this;
        }

        public DialogHelper create() {
            DialogHelper dialog = new DialogHelper();
            dialog.mLayoutResId = this.layoutResId;
            dialog.mDialogView = this.dialogView;
            dialog.mClickId = this.clickId;
            dialog.mBindView = this.bindView;
            dialog.mDismissListener = this.dismissListener;
            dialog.mWidth = this.width;
            dialog.mHight = this.hight;
            dialog.mGravity = this.gravity;
            dialog.mIsCancelableOutside = this.isCancelableOutside;
            dialog.mDimAmount = this.dimAmount;
            dialog.mDialogAnimationRes = this.dalogAnimationRes;
            dialog.mOnViewClick = this.onViewClick;
            return dialog;

        }


    }


    /***
     * 初始化view
     */
    public interface IBindView {
        void bindView(View view);
    }

    /**
     * 消失监听
     */
    interface OnDismissListener {
        void onDismiss(DialogInterface dialog);
    }

    public interface OnViewClick {
        void click(View v,DialogFragment dialog);
    }

}
