package com.example.simple.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.example.simple.R;
import com.example.simple.fragment.ListFragmentFragment;
import com.example.simple.widget.scollerLayout.ScrollableFragmentListener;
import com.example.simple.widget.scollerLayout.ScrollableListener;
import com.example.simple.widget.scollerLayout.TouchCallbackLayout;
import com.example.simple.widget.scollerLayout.ViewPagerHeaderHelper1;

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
    private ViewPagerHeaderHelper1 mViewPagerHeaderHelper;


    private SparseArrayCompat<ScrollableListener> mScrollableListenerArrays =
            new SparseArrayCompat<>();

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

       mViewPagerHeaderHelper = new ViewPagerHeaderHelper1(this,mViewPager,mHeaderView,mScrollableListenerArrays);
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

    /*******start******/
    @Override
    public void onFragmentAttached(ScrollableListener listener, int position) {
        mScrollableListenerArrays.put(position, listener);
    }

    @Override
    public void onFragmentDetached(ScrollableListener listener, int position) {
        mScrollableListenerArrays.remove(position);
    }




}
