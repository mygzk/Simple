package com.example.simple.model.stikkyheader;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.simple.model.stikkyheader.view.ExtendLayout;
import com.example.simple.model.stikkyheader.view.ExtendMsgHeader;
import com.example.simple.model.stikkyheader.view.ILayoutScrollListener;
import com.example.simple.model.stikkyheader.view.PullExtendLayout;
import com.example.simple.utils.DensityUtil;


public class PullLayoutUtil {

    private String TAG  = PullLayoutUtil.class.getSimpleName();

    private PullExtendLayout mPullExtendLayout;
    private View belowView;
    private ExtendMsgHeader extendLayout;

    private int sreenWidth ;
    private int sreenHeight ;

    public PullLayoutUtil(PullExtendLayout mPullExtendLayout, View belowView, ExtendMsgHeader extendLayout) {
        this.mPullExtendLayout = mPullExtendLayout;
        this.belowView = belowView;
        this.extendLayout = extendLayout;
        sreenWidth =  DensityUtil.getScreenWidth(mPullExtendLayout.getContext());
        sreenHeight = DensityUtil.getScreenHeight(mPullExtendLayout.getContext());

        if(extendLayout.getState() == ExtendLayout.State.arrivedListHeight){

            belowView.setAlpha(1);
        }else {
            belowView.setAlpha(0.8f);
        }
        init();

        initPull();
    }

    private void init() {
        belowView.setClickable(true);
        belowView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return mPullExtendLayout.onTouchEvent(event);
            }
        });
    }



    private void initPull(){
        if(extendLayout==null){
            return;
        }
        extendLayout.setiLayoutScrollListener(new ILayoutScrollListener() {
            @Override
            public void onPull(int offset) {
                Log.e(TAG,"initPull offset:"+offset);
                Log.e(TAG,"initPull subPercent:"+sreenHeight);

                float subPercent = (offset*01f) / sreenHeight;
                Log.e(TAG,"initPull subPercent:"+subPercent);

                if(subPercent<=0.5){
                    subPercent = 0.8f;
                }
                if(subPercent>=0.7){
                    subPercent = 1f;
                }


                belowView.setAlpha(subPercent);


            }
        });

    }





}
