package com.example.simple.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
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

/**
 * <declare-styleable name="bindding_item">
 * <p>
 * <attr name="content_hint" format="string" />
 * <attr name="content_hint_color" format="string" />
 * <attr name="hint_ev_text_size" format="integer" />
 * <attr name="hint_ev_text_color" format="color" />
 * <p>
 * <attr name="img_right" format="reference" />
 * <p>
 * <attr name="type" format="enum">
 * <enum name="tv" value="0" />
 * <enum name="ev" value="1" />
 * </attr>
 * <p>
 * <attr name="secondColor" format="color" />
 * </declare-styleable>
 */
public class BiddingItemLayout extends FrameLayout {
    private int TYPE_TEXT = 0;
    private int TYPE_EDIT = 1;

    //label内容
    private String mLabelText;
    private int mLabelSize = 14;
    private int mLabelColor;

    private String mContent;
    private int mTvContentSize;
    private int mTvContentColor;
    private int mEtContentSize;
    private int mEtContentColor;
    private String mHintContent;
    private int mHintSize;
    private int mHintColor;
    private int mImgRight = R.mipmap.ic_launcher;
    private int mType;

    private RelativeLayout rootView;
    private TextView tvLabel;
    private TextView tvContent;
    private EditText etContent;
    private ImageView imgRight;
    private View lineView;


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
                /*    mLabelSize = (int) ta.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14,
                            getResources().getDisplayMetrics()));
                    */
                    mLabelSize = ta.getDimensionPixelSize(attr, 14);
                    break;
                case R.styleable.bindding_item_content:
                    mContent = ta.getString(attr);
                    break;
                case R.styleable.bindding_item_content_tv_text_color:
                    mTvContentColor = ta.getColor(attr, getResources().getColor(R.color.blue_font));
                    break;
                case R.styleable.bindding_item_content_tv_text_size:
                    mTvContentSize = ta.getDimensionPixelSize(attr, 14);
                    break;

                case R.styleable.bindding_item_content_ev_text_color:
                    mEtContentColor = ta.getColor(attr, getResources().getColor(R.color.blue_font));
                    break;
                case R.styleable.bindding_item_content_ev_text_size:
                    mEtContentSize = ta.getDimensionPixelSize(attr, 14);
                    break;

                case R.styleable.bindding_item_content_hint:
                    mHintContent = ta.getString(attr);
                    break;
                case R.styleable.bindding_item_content_hint_color:
                    mHintColor = ta.getColor(attr, getResources().getColor(R.color.blue_font));
                    break;
                case R.styleable.bindding_item_content_hint_size:
                    mHintSize = ta.getDimensionPixelSize(attr, 14);
                    break;

                case R.styleable.bindding_item_type:
                    mType = ta.getInt(attr, TYPE_TEXT);
                    break;
                case R.styleable.bindding_item_img_right:
                    mImgRight = ta.getResourceId(attr, R.mipmap.ic_launcher);
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
        tvLabel.setTextSize(mLabelSize);
        tvLabel.setTextColor(mLabelColor);


        if (mType == TYPE_TEXT) {
            if (!TextUtils.isEmpty(mContent)) {
                tvContent.setText(mContent);
            }
            tvContent.setTextColor(mTvContentColor);
            tvContent.setTextSize(mTvContentSize);

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
            etContent.setTextSize(mEtContentSize);
        }

        if (mImgRight > 0) {
            imgRight.setImageResource(mImgRight);
        }


    }


}
