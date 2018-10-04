package com.example.simple.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.simple.R;

/**
 * Create by guozhk
 * Date : 2018/10/4
 * Dre :发标文件item
 **/
public class BiddingItemLayout extends FrameLayout {
    private int TYPE_TEXT = 0;
    private int TYPE_EDIT = 1;

    //label内容
    private String mLabelText;
    private int mLabelSize = dip2px(getContext(), 14);
    private int mLabelColor;

    private String mContent;
    private int mTvContentSize = dip2px(getContext(), 14);

    private int mTvContentColor;
    private int mEtContentSize;
    private int mEtContentColor;
    private String mHintContent;
    private int mHintSize = dip2px(getContext(), 14);

    private int mHintColor;
    private int mImgRight = R.mipmap.ic_launcher;
    private boolean mImageRightVisible;
    private int mType;

    private RelativeLayout rootView;
    private TextView tvLabel;
    private TextView tvContent;
    private EditText etContent;
    private ImageView imgRight;
    private View lineView;


    private IContentClickListener mIContentClickListener;
    private IRightImgClickListener mIRightImgClickListener;

    public BiddingItemLayout(@NonNull Context context) {
        this(context, null);
    }

    public BiddingItemLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BiddingItemLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.bindding_item,
                defStyleAttr, 0);
        int n = ta.getIndexCount();
        mLabelColor = getResources().getColor(R.color.blue_font);
        mTvContentColor = getResources().getColor(R.color.blue_font);
        mEtContentColor = getResources().getColor(R.color.blue_font);
        mHintColor = getResources().getColor(R.color.blue_font);

        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.bindding_item_label_text:
                    mLabelText = ta.getString(attr);
                    break;
                case R.styleable.bindding_item_label_text_color:
                    mLabelColor = ta.getColor(attr, getResources().getColor(R.color.blue_font));
                    break;
                case R.styleable.bindding_item_label_text_size:
                   /* mLabelSize = (int) ta.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14,
                            getResources().getDisplayMetrics()));*/
                    mLabelSize = ta.getDimensionPixelSize(attr, dip2px(getContext(), 14));

                    break;
                case R.styleable.bindding_item_content:
                    mContent = ta.getString(attr);
                    break;
                case R.styleable.bindding_item_content_tv_text_color:
                    mTvContentColor = ta.getColor(attr, getResources().getColor(R.color.blue_font));
                    break;
                case R.styleable.bindding_item_content_tv_text_size:
                    mTvContentSize = ta.getDimensionPixelSize(attr, dip2px(getContext(), 14));
                    break;
                case R.styleable.bindding_item_content_ev_text_color:
                    mEtContentColor = ta.getColor(attr, getResources().getColor(R.color.blue_font));
                    break;
                case R.styleable.bindding_item_content_ev_text_size:
                    mEtContentSize = ta.getDimensionPixelSize(attr, dip2px(getContext(), 14));
                    break;

                case R.styleable.bindding_item_content_hint:
                    mHintContent = ta.getString(attr);
                    break;
                case R.styleable.bindding_item_content_hint_color:
                    mHintColor = ta.getColor(attr, getResources().getColor(R.color.blue_font));
                    break;
                case R.styleable.bindding_item_content_hint_size:
                    mHintSize = ta.getDimensionPixelSize(attr, dip2px(getContext(), 14));
                    break;
                case R.styleable.bindding_item_type:
                    mType = ta.getInt(attr, TYPE_TEXT);
                    break;
                case R.styleable.bindding_item_img_right:
                    mImgRight = ta.getResourceId(attr, R.mipmap.ic_launcher);
                    break;
                case R.styleable.bindding_item_img_visible:
                    mImageRightVisible = ta.getBoolean(attr, false);
                    break;
                default:
                    break;
            }
        }
        ta.recycle();
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_bidding_input, this);
        rootView = view.findViewById(R.id.item_bidding_toot);
        tvLabel = view.findViewById(R.id.item_bidding_lable);
        tvContent = view.findViewById(R.id.item_bidding_content_tv);
        etContent = view.findViewById(R.id.item_bidding_content_et);
        imgRight = view.findViewById(R.id.item_bidding_right_img);
        lineView = view.findViewById(R.id.item_bidding_bottom_line);

        if (!TextUtils.isEmpty(mLabelText)) {
            tvLabel.setText(mLabelText);
        }
        tvLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLabelSize);
        tvLabel.setTextColor(mLabelColor);


        float size = tvLabel.getTextSize();

        Log.e("item", "mLabelSize:" + mLabelSize);
        Log.e("item", "size:" + size);
        if (mType == TYPE_TEXT) {
            if (!TextUtils.isEmpty(mContent)) {
                tvContent.setText(mContent);
            }
            tvContent.setTextColor(mTvContentColor);
            tvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTvContentSize);
            tvContent.setVisibility(VISIBLE);
            etContent.setVisibility(GONE);
        } else {
            if (!TextUtils.isEmpty(mContent)) {
                etContent.setText(mContent);
            } else {
                if (!TextUtils.isEmpty(mHintContent)) {
                    etContent.setHint(mHintContent);
                }
                etContent.setHintTextColor(mHintColor);
            }
            etContent.setTextColor(mEtContentColor);
            etContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, mEtContentSize);
            tvContent.setVisibility(GONE);
            etContent.setVisibility(VISIBLE);
        }

        tvContent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIContentClickListener != null) {
                    mIContentClickListener.click(v);
                }
            }
        });

        if (mImageRightVisible) {
            imgRight.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIRightImgClickListener != null) {
                        mIRightImgClickListener.click(v);
                    }
                }
            });

            if (mImgRight > 0) {
                imgRight.setImageResource(mImgRight);
            }

            imgRight.setVisibility(VISIBLE);
        } else {
            imgRight.setVisibility(GONE);
        }

    }


    @Override
    public RelativeLayout getRootView() {
        return rootView;
    }

    public void setRootView(RelativeLayout rootView) {
        this.rootView = rootView;
    }

    public TextView getTvLabel() {
        return tvLabel;
    }

    public void setTvLabel(TextView tvLabel) {
        this.tvLabel = tvLabel;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public void setTvContent(TextView tvContent) {
        this.tvContent = tvContent;
    }

    public EditText getEtContent() {
        return etContent;
    }

    public void setEtContent(EditText etContent) {
        this.etContent = etContent;
    }

    public ImageView getImgRight() {
        return imgRight;
    }

    public void setImgRight(ImageView imgRight) {
        this.imgRight = imgRight;
    }

    public View getLineView() {
        return lineView;
    }

    public void setLineView(View lineView) {
        this.lineView = lineView;
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public void setIContentClickListener(IContentClickListener mIContentClickListener) {
        this.mIContentClickListener = mIContentClickListener;
    }

    public void setIRightImgClickListener(IRightImgClickListener mIRightImgClickListener) {
        this.mIRightImgClickListener = mIRightImgClickListener;
    }

    public interface IContentClickListener {
        void click(View view);
    }

    public interface IRightImgClickListener {
        void click(View view);
    }


}
