package com.example.simple.dialog;

import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.simple.R;
import com.example.simple.utils.DensityUtil;


/**
 * Created by：guozhk
 * date: 16-9-18 14:34
 */
public class CommTipFragmentDialog extends BaseDialogFragment {
    private TextView tvTitle;
    private TextView tvContent;
    private Button btnCancle;
    private Button btnSure;

    private String mTitle;
    private String mTip;
    private String mCancle;
    private String mSure;
    View.OnClickListener onSureClickListenser;
    View.OnClickListener onCancleClickListenser;


    @Override
    public int doGetContentViewId() {
        return R.layout.dialog_comm_tip;
    }

    @Override
    public void initView(View view) {
        tvTitle = queryViewById(view, R.id.dialog_comm_tip_title);
        tvContent = queryViewById(view, R.id.dialog_comm_tip_content);
        tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        tvContent.setMaxHeight((int) (DensityUtil.getHeightInPx(getActivity()) * 0.6));
        btnCancle = queryViewById(view, R.id.dialog_btn_calcle);
        btnSure = queryViewById(view, R.id.dialog_btn_sure);

        if (onCancleClickListenser == null) {
            btnCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();

                }
            });
        } else {
            btnCancle.setOnClickListener(onCancleClickListenser);
        }


        if (!TextUtils.isEmpty(mTitle)) {
            tvTitle.setText(mTitle);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mTip)) {
            tvContent.setText(mTip);
        }
        if (!TextUtils.isEmpty(mCancle)) {
            btnCancle.setText(mCancle);
        }
        if (!TextUtils.isEmpty(mSure)) {
            btnSure.setText(mSure);
        }
        if (onSureClickListenser != null) {
            btnSure.setOnClickListener(onSureClickListenser);
        }
    }

    public void setTitle(String title) {
        mTitle = title;

    }

    public void setTip(String tip) {
        mTip = tip;

    }

    public void setCancle(String mCancle) {
        this.mCancle = mCancle;
    }

    public void setSure(String mSure) {
        this.mSure = mSure;
    }

    public void setOnSureClickListenser(View.OnClickListener onSureClickListenser) {
        this.onSureClickListenser = onSureClickListenser;

    }

    public void setOnCancleClickListenser(View.OnClickListener onCancleClickListenser) {
        this.onCancleClickListenser = onCancleClickListenser;
    }

}
