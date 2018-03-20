package com.example.simple.widget.scollerLayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

/**
 * Created by guozhk on 2018/3/19.
 */

public class TouchCallbackLayout1 extends FrameLayout {

    private int mTouchSlop;
    private int mMinimumFlingVelocity;
    private int mMaximumFlingVelocity;


    private View mHeaderView;//头部
    private View mHeadTabView;//tab
    private View mContentView;//底部内容

    private int mTabHeight=96;
    private int mHeaderHeight;


    private Interpolator mInterpolator = new DecelerateInterpolator();
    private static final long DEFAULT_DURATION = 300L;
    private static final float DEFAULT_DAMPING = 1.5f;

   // private int mHeaderHeight;


    public TouchCallbackLayout1(@NonNull Context context) {
        this(context,null);
    }

    public TouchCallbackLayout1(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchCallbackLayout1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTouchSlop = viewConfiguration.getScaledTouchSlop();
        mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // TODO: 2018/3/20 初始化 视图

        if(getChildCount()!=2){
            new RuntimeException("is not has two childe views");
        }

        mHeaderView = getChildAt(0);
        mContentView = getChildAt(1);




    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mHeaderHeight = mHeaderView.getHeight();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);


    }
    /****/




    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return onLayoutInterceptTouchEvent(ev,mHeaderHeight);
        //return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        return onLayoutTouchEvent(event);
    }



    private boolean mIsHeaderExpand = true;

    private float mInitialMotionY;
    private float mInitialMotionX;
    private float mLastMotionY;
    private boolean mIsBeingMove;
    private boolean mHandlingTouchEventFromDown;

    private VelocityTracker mVelocityTracker;



    public boolean onLayoutInterceptTouchEvent(MotionEvent event, int headerHeight) {
        mHeaderHeight = headerHeight;

        final float x = event.getX(), y = event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                //boolean isViewBeingDragged = mListener.isViewBeingDragged(event);
                boolean isViewBeingDragged = isViewBeingDragged(event);

                if (isViewBeingDragged && !mIsHeaderExpand || mIsHeaderExpand) {

                    if (mIsHeaderExpand && y < headerHeight) {
                        return mIsBeingMove;
                    }

                    mInitialMotionX = x;
                    mInitialMotionY = y;
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (mInitialMotionY > 0f && !mIsBeingMove) {
                    final float yDiff = y - mInitialMotionY;
                    final float xDiff = x - mInitialMotionX;
                    if ((!mIsHeaderExpand && yDiff > mTouchSlop)  // header fold , pull
                            || (mIsHeaderExpand && yDiff < 0))// header expand, push
                    {
                        if (Math.abs(yDiff) > Math.abs(xDiff)) {
                            mIsBeingMove = true;
                           // mListener.onMoveStarted(y);
                            onMoveStarted(y);
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mIsBeingMove) {
                   // mListener.onMoveEnded(false, 0f);
                   onMoveEnded(false, 0f);
                }
                resetTouch();
                break;
        }

        return mIsBeingMove;
    }

    public boolean onLayoutTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mHandlingTouchEventFromDown = true;
        }

        if (mHandlingTouchEventFromDown) {
            if (mIsBeingMove) {
                mLastMotionY = event.getY();
            } else {
                onLayoutInterceptTouchEvent(event, mHeaderHeight);
                return true;
            }
        }

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        int action = event.getAction();
        final int count = event.getPointerCount();

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                final float y = event.getY();
                if (mIsBeingMove && y != mLastMotionY) {
                    final float yDx = mLastMotionY == -1 ? 0 : y - mLastMotionY;
                    onMove(y, yDx);
                    mLastMotionY = y;
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                // Check the dot product of current velocities.
                // If the pointer that left was opposing another velocity vector, clear.
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
                final int upIndex = event.getActionIndex();
                final int id1 = event.getPointerId(upIndex);
                final float x1 = mVelocityTracker.getXVelocity(id1);
                final float y1 = mVelocityTracker.getYVelocity(id1);
                for (int i = 0; i < count; i++) {
                    if (i == upIndex) continue;
                    final int id2 = event.getPointerId(i);
                    final float vx = x1 * mVelocityTracker.getXVelocity(id2);
                    final float vy = y1 * mVelocityTracker.getYVelocity(id2);

                    final float dot = vx + vy;
                    if (dot < 0) {
                        mVelocityTracker.clear();
                        break;
                    }
                }

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mIsBeingMove) {

                    boolean isFling = false;
                    float velocityY = 0;

                    if (action == MotionEvent.ACTION_UP) {
                        final VelocityTracker velocityTracker = mVelocityTracker;
                        final int pointerId = event.getPointerId(0);
                        velocityTracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
                        velocityY = velocityTracker.getYVelocity(pointerId);
                        if ((Math.abs(velocityY) > mMinimumFlingVelocity)) {
                            isFling = true;
                        }
                    }

                    onMoveEnded(isFling, velocityY);
                }
                resetTouch();

                break;
        }

        return true;
    }

    private void resetTouch() {
        mIsBeingMove = false;
        mHandlingTouchEventFromDown = false;
        mInitialMotionY = mLastMotionY = -1f;
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }




    public boolean isViewBeingDragged(MotionEvent event) {
        // TODO: 2018/3/20
//        return mScrollableListenerArrays.valueAt(mContentView.getCurrentItem())
//                .isViewBeingDragged(event);

        return false;
    }


    public void onMoveStarted(float y) {

    }



    public void onMoveEnded(boolean isFling, float flingVelocityY) {
        float headerY = ViewCompat.getTranslationY(mHeaderView); // 0到负数
        if (headerY == 0 || headerY == -mHeaderHeight) {
            return;
        }
        if (getInitialMotionY() - getLastMotionY()
                < -mTouchSlop) {  // pull > mTouchSlop = expand
            headerExpand(headerMoveDuration(true, headerY, isFling, flingVelocityY));
        } else if (getInitialMotionY()
                - getLastMotionY()
                > mTouchSlop) { // push > mTouchSlop = fold
            headerFold(headerMoveDuration(false, headerY, isFling, flingVelocityY));
        } else {
            if (headerY > -mHeaderHeight / 2f) {  // headerY > header/2 = expand
                headerExpand(headerMoveDuration(true, headerY, isFling, flingVelocityY));
            } else { // headerY < header/2= fold
                headerFold(headerMoveDuration(false, headerY, isFling, flingVelocityY));
            }
        }
    }


    public void onMove(float y, float yDx) {
        float headerTranslationY = ViewCompat.getTranslationY(mHeaderView) + yDx;
        if (headerTranslationY >= 0) { // pull end
            headerExpand(0L);
        } else if (headerTranslationY <= -mHeaderHeight) { // push end
            headerFold(0L);
        } else {
            ViewCompat.animate(mHeaderView)
                    .translationY(headerTranslationY)
                    .setDuration(0)
                    .start();
            ViewCompat.animate(mContentView)
                    .translationY(headerTranslationY + mHeaderHeight)
                    .setDuration(0)
                    .start();
        }
    }


    private long headerMoveDuration(boolean isExpand, float currentHeaderY, boolean isFling,
                                    float velocityY) {
        long defaultDuration = DEFAULT_DURATION;
        if (isFling) {
            float distance = isExpand ? Math.abs(mHeaderHeight) - Math.abs(currentHeaderY)
                    : Math.abs(currentHeaderY);
            velocityY = Math.abs(velocityY) / 1000;
            defaultDuration = (long) (distance / velocityY * DEFAULT_DAMPING);
            defaultDuration =
                    defaultDuration > DEFAULT_DURATION ? DEFAULT_DURATION : defaultDuration;
        }
        return defaultDuration;
    }

    private void headerFold(long duration) {
        ViewCompat.animate(mHeaderView)
                .translationY(-mHeaderHeight)
                .setDuration(duration)
                .setInterpolator(mInterpolator)
                .start();
        ViewCompat.animate(mContentView).translationY(0).
                setDuration(duration).setInterpolator(mInterpolator).start();
        setHeaderExpand(false);
    }

    private void headerExpand(long duration) {
        ViewCompat.animate(mHeaderView)
                .translationY(0)
                .setDuration(duration)
                .setInterpolator(mInterpolator)
                .start();
        ViewCompat.animate(mContentView)
                .translationY(mHeaderHeight)
                .setDuration(duration)
                .setInterpolator(mInterpolator)
                .start();
        setHeaderExpand(true);
    }



    public void setHeaderExpand(boolean isHeaderExpand) {
        this.mIsHeaderExpand = isHeaderExpand;
    }

    public float getInitialMotionY() {
        return mInitialMotionY;
    }

    public float getLastMotionY() {
        return mLastMotionY;
    }
}
