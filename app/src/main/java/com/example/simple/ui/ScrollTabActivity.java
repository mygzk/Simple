package com.example.simple.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.example.simple.R;
import com.example.simple.fragment.ListFragmentFragment;
import com.example.simple.widget.scollerLayout.ScrollableFragmentListener;
import com.example.simple.widget.scollerLayout.ScrollableListener;
import com.example.simple.widget.scollerLayout.TouchCallbackLayout;
import com.example.simple.widget.scollerLayout.TouchCallbackLayout1;
import com.example.simple.widget.scollerLayout.ViewPagerHeaderHelper;

import java.util.ArrayList;
import java.util.List;

public class ScrollTabActivity extends BaseActivity implements
        TouchCallbackLayout.TouchEventListener, ScrollableFragmentListener{


//    public class ScrollTabActivity extends BaseActivity {

    private ViewPager mViewPager;
    private LinearLayout mHeaderView;
    private LinearLayout mHeaderContent;
    private LinearLayout mHeadTab;

    private TouchCallbackLayout touchCallbackLayout;
    private ViewPagerHeaderHelper mViewPagerHeaderHelper;
    private Interpolator mInterpolator = new DecelerateInterpolator();
    private static final long DEFAULT_DURATION = 300L;
    private static final float DEFAULT_DAMPING = 1.5f;

    private SparseArrayCompat<ScrollableListener> mScrollableListenerArrays =
            new SparseArrayCompat<>();
    private int mTouchSlop;
    private int mHeaderHeight;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_tab;
    }

    @Override
    protected void initView() {
        touchCallbackLayout = (TouchCallbackLayout) findViewById(R.id.cumulative_layout);
        mViewPager  = queryViewById(R.id.cumulative_viewpager);
        mHeaderView  = queryViewById(R.id.cumulative_header);
        mHeaderContent  = queryViewById(R.id.cumulative_header_content);
        mHeadTab  = queryViewById(R.id.cumulative_header_Tab);

        mViewPager.setAdapter(new CumulativeAdapter(getSupportFragmentManager()));


        viewTreeObserver(mHeaderContent);


    }


   class CumulativeAdapter extends FragmentPagerAdapter{

       List<Fragment> fragments;


       public CumulativeAdapter(FragmentManager fm) {
           super(fm);
           intFragment();

       }

       private void intFragment() {
           fragments = new ArrayList<>();
           fragments.add(new ListFragmentFragment());
       }


       @Override
       public Fragment getItem(final int position) {
           return fragments.get(position);
       }

       @Override
       public int getCount() {
           return fragments.size();
       }


   }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /*************************/
   private void viewTreeObserver(final View view) {
       ViewTreeObserver
               vto = view.getViewTreeObserver();
       vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
           @Override
           public void onGlobalLayout() {
               view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
               int height = view.getMeasuredHeight();
               if (height != 0) {
                   mHeaderHeight = height;
               }
               ViewCompat.setTranslationY(mViewPager, mHeaderHeight);
           }
       });

       mViewPagerHeaderHelper = new ViewPagerHeaderHelper(this,mViewPager,mHeaderView,mScrollableListenerArrays);
       touchCallbackLayout.setTouchEventListener(this);
   }


    /*******start******/
    @Override
    public boolean onLayoutInterceptTouchEvent(MotionEvent event) {
        return mViewPagerHeaderHelper.onLayoutInterceptTouchEvent(event,
                 mHeaderHeight);
    }

    @Override
    public boolean onLayoutTouchEvent(MotionEvent event) {
        return mViewPagerHeaderHelper.onLayoutTouchEvent(event);
    }

    /*******end******/
//
//    @Override
//    public boolean isViewBeingDragged(MotionEvent event) {
//        return mScrollableListenerArrays.valueAt(mViewPager.getCurrentItem())
//                .isViewBeingDragged(event);
//    }
//
//    @Override
//    public void onMoveStarted(float y) {
//
//    }
//
//    @Override
//    public void onMove(float y, float yDx) {
//        float headerTranslationY = ViewCompat.getTranslationY(mHeaderView) + yDx;
//        if (headerTranslationY >= 0) { // pull end
//            headerExpand(0L);
//        } else if (headerTranslationY <= -mHeaderHeight) { // push end
//            headerFold(0L);
//        } else {
//            ViewCompat.animate(mHeaderView)
//                    .translationY(headerTranslationY)
//                    .setDuration(0)
//                    .start();
//            ViewCompat.animate(mViewPager)
//                    .translationY(headerTranslationY + mHeaderHeight)
//                    .setDuration(0)
//                    .start();
//        }
//    }
//
//    @Override
//    public void onMoveEnded(boolean isFling, float flingVelocityY) {
//        float headerY = ViewCompat.getTranslationY(mHeaderView); // 0到负数
//        if (headerY == 0 || headerY == -mHeaderHeight) {
//            return;
//        }
//        if (mViewPagerHeaderHelper.getInitialMotionY() - mViewPagerHeaderHelper.getLastMotionY()
//                < -mTouchSlop) {  // pull > mTouchSlop = expand
//            headerExpand(headerMoveDuration(true, headerY, isFling, flingVelocityY));
//        } else if (mViewPagerHeaderHelper.getInitialMotionY()
//                - mViewPagerHeaderHelper.getLastMotionY()
//                > mTouchSlop) { // push > mTouchSlop = fold
//            headerFold(headerMoveDuration(false, headerY, isFling, flingVelocityY));
//        } else {
//            if (headerY > -mHeaderHeight / 2f) {  // headerY > header/2 = expand
//                headerExpand(headerMoveDuration(true, headerY, isFling, flingVelocityY));
//            } else { // headerY < header/2= fold
//                headerFold(headerMoveDuration(false, headerY, isFling, flingVelocityY));
//            }
//        }
//    }
//
//    private long headerMoveDuration(boolean isExpand, float currentHeaderY, boolean isFling,
//                                    float velocityY) {
//        long defaultDuration = DEFAULT_DURATION;
//        if (isFling) {
//            float distance = isExpand ? Math.abs(mHeaderHeight) - Math.abs(currentHeaderY)
//                    : Math.abs(currentHeaderY);
//            velocityY = Math.abs(velocityY) / 1000;
//            defaultDuration = (long) (distance / velocityY * DEFAULT_DAMPING);
//            defaultDuration =
//                    defaultDuration > DEFAULT_DURATION ? DEFAULT_DURATION : defaultDuration;
//        }
//        return defaultDuration;
//    }
//
//    private void headerFold(long duration) {
//        ViewCompat.animate(mHeaderView)
//                .translationY(-mHeaderHeight)
//                .setDuration(duration)
//                .setInterpolator(mInterpolator)
//                .start();
//        ViewCompat.animate(mViewPager).translationY(0).
//                setDuration(duration).setInterpolator(mInterpolator).start();
//        mViewPagerHeaderHelper.setHeaderExpand(false);
//    }
//
//    private void headerExpand(long duration) {
//        ViewCompat.animate(mHeaderView)
//                .translationY(0)
//                .setDuration(duration)
//                .setInterpolator(mInterpolator)
//                .start();
//        ViewCompat.animate(mViewPager)
//                .translationY(mHeaderHeight)
//                .setDuration(duration)
//                .setInterpolator(mInterpolator)
//                .start();
//        mViewPagerHeaderHelper.setHeaderExpand(true);
//    }

    @Override
    public void onFragmentAttached(ScrollableListener listener, int position) {
        mScrollableListenerArrays.put(position, listener);
    }

    @Override
    public void onFragmentDetached(ScrollableListener listener, int position) {
        mScrollableListenerArrays.remove(position);
    }




}
