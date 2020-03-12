package com.example.simple.model.stikkyheader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.simple.R;
import com.example.simple.utils.DensityUtil;


public class ExtendMsgHeader extends ExtendLayout {

    private String TAG  = ExtendMsgHeader.class.getSimpleName();


    private Context mContext;
    /**
     * 原点
     */

    private ExpendPoint mExpendPoint;

    boolean arrivedListHeight = false;

    float containerHeight ;
    float listHeight ;


    public ExtendMsgHeader(Context context) {
        super(context);
        mContext = context;
    }

    public ExtendMsgHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        containerHeight =DensityUtil.getScreenHeight(context) -DensityUtil.dip2px(context,60);
//        listHeight = DensityUtil.dip2px(context,120);


        listHeight = DensityUtil.getScreenHeight(context) -DensityUtil.dip2px(context,80);
        containerHeight =  DensityUtil.dip2px(context,65);
    }
//
//    public ExtendTestHeader(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        mContext = context;
//    }

    @Override
    public int getContentSize() {
        return (int) (containerHeight);
    }

    @Override
    public int getListSize() {
        return (int) (listHeight);
    }



    @Override
    protected void bindView(View container) {

        mExpendPoint = findViewById(R.id.expend_point);


    }


    @Override
    protected View createLoadingView(Context context, AttributeSet attrs) {
        return LayoutInflater.from(context).inflate(R.layout.extend_header_msg, null);
    }

    @Override
    protected void onArrivedListHeight() {
        arrivedListHeight = true;
    }

    @Override
    protected void onReset() {
        mExpendPoint.setVisibility(VISIBLE);
        mExpendPoint.setAlpha(1);
        mExpendPoint.setTranslationY(0);
        arrivedListHeight = false;
    }


    @Override
    public void onPull(int offset) {
        Log.e(TAG,"onPull offset:"+offset);
        if(iLayoutScrollListener!=null){
            iLayoutScrollListener.onPull(offset);
        }
        if (!arrivedListHeight) {
            mExpendPoint.setVisibility(VISIBLE);
            float percent = Math.abs(offset) / containerHeight;
            int moreOffset = Math.abs(offset) - (int) containerHeight;
            if (percent <= 1.0f) {
                mExpendPoint.setPercent(percent);
                mExpendPoint.setTranslationY(-Math.abs(offset) / 2 + mExpendPoint.getHeight() / 2);
            } else {
                float subPercent = (moreOffset) / (listHeight - containerHeight);
                subPercent = Math.min(1.0f, subPercent);
                mExpendPoint.setTranslationY(-(int) containerHeight / 2 + mExpendPoint.getHeight() / 2 + (int) containerHeight * subPercent / 2);
                mExpendPoint.setPercent(1.0f);
                float alpha = (1 - subPercent * 2);
                mExpendPoint.setAlpha(Math.max(alpha, 0));
            }
        }
        if (Math.abs(offset) >= listHeight) {
            mExpendPoint.setVisibility(INVISIBLE);
        }
    }


    private ILayoutScrollListener iLayoutScrollListener;

    public void setiLayoutScrollListener(ILayoutScrollListener iLayoutScrollListener) {
        this.iLayoutScrollListener = iLayoutScrollListener;
    }

}
