package com.org.vptest.widgets;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by guozhk on 2018/9/19.
 */

public class ImgAdapter extends LoopVPAdapter<String> {


    public ImgAdapter(Context context, List<String> datas, ViewPager viewPager) {
        super(context, datas, viewPager);
    }

    private ViewGroup.LayoutParams layoutParams;

    @Override
    protected View getItemView(String data) {
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
        }
        ImageView imageView = new ImageView(mContext);
        if(views!=null&&views.size()>0){
            if(currentPosition%views.size()==0){
                imageView.setBackgroundColor(Color.BLUE);
            }else if(currentPosition%views.size()==1){
                imageView.setBackgroundColor(Color.RED);
            }else if(currentPosition%views.size()==2){
                imageView.setBackgroundColor(Color.GRAY);
            }
        }else {
            imageView.setBackgroundColor(Color.BLUE);
        }


        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(mContext).load(data).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

      //  imageView.setImageURI();
        //ImageUtils.loadImage(mContext, data, imageView);
        return imageView;
    }

}
