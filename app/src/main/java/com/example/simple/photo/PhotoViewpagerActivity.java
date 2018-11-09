package com.example.simple.photo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.simple.R;
import com.example.simple.photo.view.PhotoView;
import com.example.simple.ui.BaseActivity;

/**
 * 多张图片
 */
public class PhotoViewpagerActivity extends BaseActivity {

    private ViewPager vpPhoto;

    private String[] imgs = new String[]{
            "http://pic34.photophoto.cn/20150112/0034034439579927_b.jpg",
            "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=9b867a04b299a9012f38537575fc600e/4d086e061d950a7b86bee8d400d162d9f2d3c913.jpg",
            "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=429e131206f3d7ca18fb37359a76d47c/7acb0a46f21fbe09b1df973861600c338744ad1d.jpg",
            "http://pic11.photophoto.cn/20090603/0034034495016977_b.jpg",
            "http://pic25.photophoto.cn/20121128/0034034441708087_b.jpg",
            "http://pic5.photophoto.cn/20071207/0033034142319193_b.jpg",
            "http://pic172.nipic.com/file/20180713/24206353_161702250000_2.jpg"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_viewpager;
    }

    @Override
    protected void initView() {
        vpPhoto = queryViewById(R.id.photo_vp);
        vpPhoto.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        initViewPager();

    }

    private void initViewPager() {
        vpPhoto.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(PhotoViewpagerActivity.this);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                // view.setImageResource(imgs[position]);
                Glide.with(PhotoViewpagerActivity.this).load(imgs[position]).into(view);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

    }
}
