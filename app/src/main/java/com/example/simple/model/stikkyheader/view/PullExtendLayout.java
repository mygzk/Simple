package com.example.simple.model.stikkyheader.view;


import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;


/**
 * 这个实现了下拉刷新和上拉加载更多的功能
 *
 * @author Li Hong
 * @since 2013-7-29
 */
public class PullExtendLayout extends LinearLayout implements IPullToExtend {
    private static final String TAG = PullExtendLayout.class.getSimpleName();

    /**
     * 回滚的时间
     */
    private static final int SCROLL_DURATION = 200;
    /**
     * 阻尼系数
     */
    private float mOffsetRadio = 1.0f;

    /**
     * 上一次移动的点Y轴
     */
    private float mLastMotionY = -1;
    /**
     * 上一次移动的点的X轴
     */
    private float mLastMotionX = -1;

    /**
     * 记录是否往下拉
     * true：往下拉
     * false：往上拉
     */
    private boolean mPullDown = false;

    /**
     * 下拉刷新的布局
     */
    private ExtendLayout mHeaderLayout;

    /**
     * 上拉加载更多的布局
     */
    private ExtendLayout mFooterLayout;
    /**
     * 列表开始显示的高度
     */
    private int mHeaderHeight;
    /**
     * 列表的高度
     */
    private int mHeaderListHeight;
    /**
     * FooterView的高度
     */
    private int mFooterHeight;
    /**
     * 列表的高度
     */
    private int mFooterListHeight;

    /**
     * 下拉刷新是否可用
     */
    private boolean mPullRefreshEnabled = true;
    /**
     * 上拉加载是否可用
     */
    private boolean mPullLoadEnabled = true;

    /**
     * 是否截断touch事件
     */
    private boolean mInterceptEventEnable = true;

    /**
     * 表示是否消费了touch事件，如果是，则不调用父类的onTouchEvent方法
     */
    private boolean mIsHandledTouchEvent = false;
    /**
     * 移动点的保护范围值
     */
    private int mTouchSlop;
    /**
     * 主View
     */
    View mRefreshableView;
    /**
     * 平滑滚动的Runnable
     */
    private SmoothScrollRunnable mSmoothScrollRunnable;

    private Rect mTempRect;

    public PullExtendLayout(Context context) {
        this(context, null);
    }


    public PullExtendLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public PullExtendLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
//        if (childCount == 2) {
//            if (getChildAt(0) instanceof ExtendLayout) {
//                mHeaderLayout = (ExtendLayout) getChildAt(0);
//                mRefreshableView = getChildAt(1);
//            } else {
//                mRefreshableView = getChildAt(0);
//                mFooterLayout = (ExtendLayout) getChildAt(1);
//            }
//        } else if (childCount == 3) {
//            if (getChildAt(0) instanceof ExtendLayout) {
//                mHeaderLayout = (ExtendLayout) getChildAt(0);
//            }
//            mRefreshableView = getChildAt(1);
//            mFooterLayout = (ExtendLayout) getChildAt(2);
//        } else {
//            throw new IllegalStateException("布局异常，最多三个，最少一个");
//        }

        for (int i=0;i<childCount;i++){
            View childView =  getChildAt(i);

            if (childView instanceof AdapterView<?>||
                    childView instanceof ScrollView) {
                mRefreshableView = childView;
            }

            if(childView instanceof ExtendLayout ){
                if(mHeaderLayout==null){
                    mHeaderLayout = (ExtendLayout) childView;
                }
                if(mFooterLayout==null){
                    mFooterLayout = (ExtendLayout) childView;
                }
            }


        }


        if (mRefreshableView == null) {
            throw new IllegalStateException("布局异常，一定要有内容布局");
        }
        // mRefreshableView.setClickable(true);需要自己设置
        init(getContext());
    }

    public void setOffsetRadio(float offsetRadio) {
        this.mOffsetRadio = offsetRadio;
    }

    /**
     * 初始化
     *
     * @param context context
     */
    private void init(Context context) {
        mTouchSlop = (int) (ViewConfiguration.get(context).getScaledTouchSlop() * 1.5);
        ViewGroup.LayoutParams layoutParams = mRefreshableView.getLayoutParams();
        layoutParams.height = 10;
        mRefreshableView.setLayoutParams(layoutParams);
//        // 得到Header的高度，这个高度需要用这种方式得到，在onLayout方法里面得到的高度始终是0
//        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                refreshLoadingViewsSize();
//                getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });
    }

    /**
     * 初始化padding，我们根据header和footer的高度来设置top padding和bottom padding
     */
    private void refreshLoadingViewsSize() {
        // 得到header和footer的内容高度，它将会作为拖动刷新的一个临界值，如果拖动距离大于这个高度
        // 然后再松开手，就会触发刷新操作
        int headerHeight = (null != mHeaderLayout) ? mHeaderLayout.getContentSize() : 0;
        mHeaderListHeight = (null != mHeaderLayout) ? mHeaderLayout.getListSize() : 0;
        int footerHeight = (null != mFooterLayout) ? mFooterLayout.getContentSize() : 0;
        mFooterListHeight = (null != mFooterLayout) ? mFooterLayout.getListSize() : 0;

        if (headerHeight < 0) {
            headerHeight = 0;
        }

        if (footerHeight < 0) {
            footerHeight = 0;
        }

        mHeaderHeight = headerHeight;
        mFooterHeight = footerHeight;

        // 这里得到Header和Footer的高度，设置的padding的top和bottom就应该是header和footer的高度
        // 因为header和footer是完全看不见的
        headerHeight = (null != mHeaderLayout) ? mHeaderLayout.getMeasuredHeight() : 0;
        footerHeight = (null != mFooterLayout) ? mFooterLayout.getMeasuredHeight() : 0;
        int pLeft = getPaddingLeft();
        int pTop = -headerHeight;
        int pRight = getPaddingRight();
        int pBottom = -footerHeight;
        setPadding(pLeft, pTop, pRight, pBottom);
    }

    @Override
    protected final void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        refreshLoadingViewsSize();
        // 设置刷新View的大小
        refreshRefreshableViewSize(w, h);
        post(new Runnable() {
            @Override
            public void run() {
                requestLayout();
            }
        });
    }

    /**
     * 计算刷新View的大小
     *
     * @param width  当前容器的宽度
     * @param height 当前容器的宽度
     */
    protected void refreshRefreshableViewSize(int width, int height) {
        LayoutParams lp = (LayoutParams) mRefreshableView.getLayoutParams();
        if (lp.height != height) {
            lp.height = height;
            mRefreshableView.requestLayout();
        }
    }

    @Override
    public final boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d(TAG, "onInterceptTouchEvent");
        if (!isInterceptTouchEventEnabled()) {
            return false;
        }
        if (!isPullLoadEnabled() && !isPullRefreshEnabled()) {
            return false;
        }
        final int action = event.getAction();
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mIsHandledTouchEvent = false;
            return false;
        }
        if (action != MotionEvent.ACTION_DOWN && mIsHandledTouchEvent) {
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent,MotionEvent.ACTION_DOWN");
                mLastMotionX = event.getX();
                mLastMotionY = event.getY();
                mIsHandledTouchEvent = false;
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent,MotionEvent.ACTION_MOVE");
                final float deltaX = event.getX() - mLastMotionX;
                final float deltaY = event.getY() - mLastMotionY;
                final float absDiff = Math.abs(deltaY);
                // 位移差大于mTouchSlop，这是为了防止快速拖动引发刷新
//                if ((absDiff > mTouchSlop)) {
                mLastMotionX = event.getX();
                mLastMotionY = event.getY();
                // 第一步，先处理处理是否处于横滑状态
                if (Math.abs(deltaX) >= Math.abs(deltaY)) {
                    Log.e(TAG, "onInterceptTouchEvent,MotionEvent.ACTION_MOVE,当前处于横滑状态,不拦截，交由父类去处理");
                    return false;
                }
                mPullDown = deltaY > 0;

                if (mPullDown) {
                    Log.d(TAG, "当前处于下拉操作");
                    //下拉需要考虑如下情况：
                    // 1、头部处理
                    // 1）刷新View已到达顶部，判断是否需要
                    if (isPullRefreshEnabled() && checkIsContentViewScrollToTop((int) event.getX(), (int) event.getY())) {
                        Log.d(TAG, "当前处于下拉操作，且内容view已到达顶部，拦截，自己消耗处理，展开头部");
                        return true;
                    } else if (isPullLoadEnabled() && mFooterLayout.getState() == IExtendLayout.State.arrivedListHeight && checkIsContentViewScrollToBottom((int) event.getX(), (int) event.getY())) {
                        Log.d(TAG, "当前处于下拉操作，且内容view已底部且已是展开状态，拦截，自己消耗处理，收缩底部");
                        return true;
                    } else {
                        Log.d(TAG, "当前处于下拉操作，且内容view没有到达顶部/到达底部，不拦截，给子view进行处理,滚动列表");
                        return false;
                    }
                } else {
                    Log.d(TAG, "当前处于上拉操作");
                    if (isPullRefreshEnabled() && mHeaderLayout.getState() == IExtendLayout.State.arrivedListHeight && checkIsContentViewScrollToTop((int) event.getX(), (int) event.getY())) {
                        Log.d(TAG, "当前处于上拉操作，头部已完全展开，且内容view已到达顶部，拦截，自己消耗处理，用于头部收缩");
                        return true;
                    } else if (isPullLoadEnabled() && checkIsContentViewScrollToBottom((int) event.getX(), (int) event.getY())) {
                        Log.d(TAG, "当前处于上拉操作，内容view已到达底部，拦截，自己消耗处理，用于底部展开");
                        return true;
                    } else {
                        Log.d(TAG, "当前处于上拉操作，且内容view没有到达顶部/到达底部，不拦截，给子view进行处理,滚动列表");
                        return false;
                    }
                }
//                }else{
//                    Log.d(TAG,"absDiff <= mTouchSlop,认为不滚动，不处理");
//                }

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent,MotionEvent.ACTION_UP");
                break;

            default:
                break;
        }
        return super.onInterceptTouchEvent(event);
    }


    @Override
    public final boolean onTouchEvent(MotionEvent ev) {
        boolean handled = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent,MotionEvent.ACTION_DOWN");
                mLastMotionY = ev.getY();
                mIsHandledTouchEvent = false;
                break;

            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getY() - mLastMotionY;
                mLastMotionY = ev.getY();
                // deltaY = 0时，不重置mPullDown标志位，mPullDown标志位值沿用deltaY != 0时的状态值
                if (deltaY != 0) {
                    mPullDown = deltaY > 0;
                }
                Log.d(TAG, "onTouchEvent,MotionEvent.ACTION_MOVE,deltaY = " + deltaY);
                if (isPullRefreshEnabled() && isReadyForPullDown(deltaY)) {
                    // 处理头部滑动
                    Log.d(TAG, "onTouchEvent,MotionEvent.ACTION_MOVE,处理头部滑动");
                    pullHeaderLayout(deltaY / mOffsetRadio);
                    handled = true;
                    if (null != mFooterLayout && 0 != mFooterHeight) {
                        mFooterLayout.setState(IExtendLayout.State.RESET);
                    }
                } else if (isPullLoadEnabled() && isReadyForPullUp(deltaY)) {
                    Log.d(TAG, "onTouchEvent,MotionEvent.ACTION_MOVE,处理底部滑动");
                    // 上拉
                    pullFooterLayout(deltaY / mOffsetRadio);
                    handled = true;
                    if (null != mHeaderLayout && 0 != mHeaderHeight) {
                        mHeaderLayout.setState(IExtendLayout.State.RESET);
                    }
                } else {
                    mIsHandledTouchEvent = false;
                }
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent,MotionEvent.ACTION_UP");
//                if (mIsHandledTouchEvent) {
//                    mIsHandledTouchEvent = false;
                // 当第一个显示出来时
                if (isReadyForPullDown(0)) {
                    Log.d(TAG, "onTouchEvent,MotionEvent.ACTION_UP，resetHeaderLayout");
                    if (mPullDown) {
                        // 弹性展开头部
                        resetHeaderLayout();
                    } else {
                        // 往上拉时，收缩头部，不弹性会弹
                        collapseHeaderLayout();
                    }
                } else if (isReadyForPullUp(0)) {
                    Log.d(TAG, "onTouchEvent,MotionEvent.ACTION_UP，resetFooterLayout");
                    if (mPullDown) {
                        collapseFooterLayout();
                    } else {
                        resetFooterLayout();
                    }
                }
//                }
                break;

            default:
                break;
        }
        return handled;
    }

    @Override
    public void setPullRefreshEnabled(boolean pullRefreshEnabled) {
        mPullRefreshEnabled = pullRefreshEnabled;
    }

    @Override
    public void setPullLoadEnabled(boolean pullLoadEnabled) {
        mPullLoadEnabled = pullLoadEnabled;
    }


    @Override
    public boolean isPullRefreshEnabled() {
        return mPullRefreshEnabled && (null != mHeaderLayout);
    }

    @Override
    public boolean isPullLoadEnabled() {
        return mPullLoadEnabled && (null != mFooterLayout);
    }


    @Override
    public ExtendLayout getHeaderExtendLayout() {
        return mHeaderLayout;
    }

    @Override
    public ExtendLayout getFooterExtendLayout() {
        return mFooterLayout;
    }


    /**
     * 开始刷新，通常用于调用者主动刷新，典型的情况是进入界面，开始主动刷新，这个刷新并不是由用户拉动引起的
     *
     * @param smoothScroll 表示是否有平滑滚动，true表示平滑滚动，false表示无平滑滚动
     * @param delayMillis  延迟时间
     */
    public void doPullRefreshing(final boolean smoothScroll, final long delayMillis) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                int newScrollValue = -mHeaderHeight;
                int duration = smoothScroll ? SCROLL_DURATION : 0;

                smoothScrollTo(newScrollValue, duration, 0);
            }
        }, delayMillis);
    }


    /**
     * 判断刷新的View是否滑动到顶部
     *
     * @return true表示已经滑动到顶部，否则false
     */
    protected boolean isReadyForPullDown(float deltaY) {
        return getScrollYValue() < 0 || (getScrollYValue() == 0 && deltaY > 0);
    }

    /**
     * 判断刷新的View是否滑动到底
     *
     * @return true表示已经滑动到底部，否则false
     */
    protected boolean isReadyForPullUp(float deltaY) {
        return getScrollYValue() > 0 || (getScrollYValue() == 0 && deltaY < 0);
    }


    /**
     * 得到平滑滚动的时间，派生类可以重写这个方法来控件滚动时间
     *
     * @return 返回值时间为毫秒
     */
    protected long getSmoothScrollDuration() {
        return SCROLL_DURATION;
    }

    /**
     * 拉动Header Layout时调用
     *
     * @param delta 移动的距离
     */
    protected void pullHeaderLayout(float delta) {
        if (delta > 0) {
            Log.d(TAG, "pullHeaderLayout，正在下拉");
        } else {
            Log.d(TAG, "pullHeaderLayout，正在上拉");
        }
        // TODO 暂时注释掉
//        int oldScrollY = getScrollYValue();
//        if (delta < 0) {
//            // 向上滑动布局
//            setScrollTo(0, (int) delta);
//            if (null != mHeaderLayout && 0 != mHeaderHeight) {
//                mHeaderLayout.setState(IExtendLayout.State.RESET);
//                mHeaderLayout.onPull(0);
//            }
//            return;
//        }
        // 向下滑动布局
        setScrollBy(0, -(int) delta);
        // 未处于刷新状态，更新箭头
        int scrollY = Math.abs(getScrollYValue());
        if (null != mHeaderLayout && 0 != mHeaderHeight) {
            if (scrollY >= mHeaderListHeight) {
                mHeaderLayout.setState(IExtendLayout.State.arrivedListHeight);
                setOffsetRadio(2.0f);
            } else {
                setOffsetRadio(1.0f);
            }
            mHeaderLayout.onPull(scrollY);
        }
    }

    /**
     * 拉Footer时调用
     *
     * @param delta 移动的距离
     */
    protected void pullFooterLayout(float delta) {
        int oldScrollY = getScrollYValue();
        if (delta > 0 && (oldScrollY - delta) <= 0) {
            setScrollTo(0, 0);
            if (null != mFooterLayout && 0 != mFooterHeight) {
                mFooterLayout.setState(IExtendLayout.State.RESET);
                mFooterLayout.onPull(0);
            }
            return;
        }
        setScrollBy(0, -(int) delta);
        int scrollY = Math.abs(getScrollYValue());
        if (null != mFooterLayout && 0 != mFooterHeight) {
            if (scrollY >= mFooterListHeight) {
                mFooterLayout.setState(IExtendLayout.State.arrivedListHeight);
                setOffsetRadio(3.0f);
            } else {
                setOffsetRadio(1.0f);
            }
            mFooterLayout.onPull(Math.abs(getScrollYValue()));
        }
    }

    /**
     * 重置header
     * 在下拉时抬起时，根据滚动距离来决定头部站看还是收缩
     * scrollY < mHeaderHeight 回弹下拉，使头部收缩（MHeaderHeight是下来刷新的零界点）
     * scrollY >= mHeaderHeight 下拉超出了头部布局，布局回弹到头部布局高度
     */
    protected void resetHeaderLayout() {
        final int scrollY = Math.abs(getScrollYValue());
        Log.d(TAG, "resetHeaderLayout,abs,getScrollYValue = " + scrollY);
        Log.d(TAG, "resetHeaderLayout,mHeaderHeight = " + mHeaderHeight);
        Log.d(TAG, "resetHeaderLayout,mHeaderListHeight = " + mHeaderListHeight);
        if (scrollY < mHeaderHeight) {
            Log.d(TAG, "resetHeaderLayout,scrollY < mHeaderHeight,收起来");
            smoothScrollTo(0);
        } else if (scrollY >= mHeaderHeight) {
            Log.d(TAG, "resetHeaderLayout,scrollY >= mHeaderHeight，展开");
            smoothScrollTo(-mHeaderListHeight);
            if(pullHeaderOpenListener!=null){
                pullHeaderOpenListener.open();
            }
        }
    }

    /**
     * 在上拉抬起时，直接收起头部布局，按QQ，抬起时，不需要回弹效果
     */
    protected void collapseHeaderLayout() {
        if(pullHeaderOpenListener!=null){
            pullHeaderOpenListener.close();
        }
        smoothScrollTo(0);
    }

    /**
     * 在下拉抬起时，直接收起底部布局，按QQ，抬起时，不需要回弹效果
     */
    protected void collapseFooterLayout() {
        smoothScrollTo(0);
    }


    /**
     * 重置footer
     */
    protected void resetFooterLayout() {
        int scrollY = Math.abs(getScrollYValue());
        if (scrollY < mFooterHeight) {
            smoothScrollTo(0);
        } else if (scrollY >= mFooterHeight) {
            smoothScrollTo(mFooterListHeight);
        }
    }

    /**
     * 隐藏header和footer
     */
    public void closeExtendHeadAndFooter() {
        smoothScrollTo(0);
    }

    /**
     * 设置滚动位置
     *
     * @param x 滚动到的x位置
     * @param y 滚动到的y位置
     */
    private void setScrollTo(int x, int y) {
        scrollTo(x, y);
    }

    /**
     * 设置滚动的偏移
     *
     * @param x 滚动x位置
     * @param y 滚动y位置
     */
    private void setScrollBy(int x, int y) {
        scrollBy(x, y);
    }

    /**
     * 得到当前Y的滚动值
     *
     * @return 滚动值
     * getScrollY < 0 ，说明上边已滑出屏幕
     */
    private int getScrollYValue() {
        return getScrollY();
    }

    /**
     * 平滑滚动
     *
     * @param newScrollValue 滚动的值
     */
    private void smoothScrollTo(int newScrollValue) {
        Log.d(TAG, "smoothScrollTo,newScrollValue = " + newScrollValue);
        smoothScrollTo(newScrollValue, getSmoothScrollDuration(), 0);
    }

    /**
     * 平滑滚动
     *
     * @param newScrollValue 滚动的值
     * @param duration       滚动时候
     * @param delayMillis    延迟时间，0代表不延迟
     */
    private void smoothScrollTo(int newScrollValue, long duration, long delayMillis) {
        if (null != mSmoothScrollRunnable) {
            mSmoothScrollRunnable.stop();
        }

        int oldScrollValue = this.getScrollYValue();
        boolean post = (oldScrollValue != newScrollValue);
        Log.d(TAG, "smoothScrollTo,oldScrollValue = " + oldScrollValue);
        Log.d(TAG, "smoothScrollTo,newScrollValue = " + newScrollValue);
        Log.d(TAG, "smoothScrollTo,post = " + post);
        if (post) {
            mSmoothScrollRunnable = new SmoothScrollRunnable(oldScrollValue, newScrollValue, duration);
        } else {
            // 修正
            if (oldScrollValue < 0) {
                newScrollValue = 0;
            }
            mSmoothScrollRunnable = new SmoothScrollRunnable(oldScrollValue, newScrollValue, duration);
        }

//        if (post) {
        if (delayMillis > 0) {
            postDelayed(mSmoothScrollRunnable, delayMillis);
        } else {
            post(mSmoothScrollRunnable);
        }
//        }
    }

    /**
     * 设置是否截断touch事件
     *
     * @param enabled true截断，false不截断
     */
    private void setInterceptTouchEventEnabled(boolean enabled) {
        mInterceptEventEnable = enabled;
    }

    /**
     * 标志是否截断touch事件
     *
     * @return true截断，false不截断
     */
    private boolean isInterceptTouchEventEnabled() {
        return mInterceptEventEnable;
    }

    /**
     * 判断（touchX,touchY）点击处的滚动子View是否滚动到了头部
     *
     * @param touchX
     * @param touchY
     * @return
     */
    private boolean checkIsContentViewScrollToTop(int touchX, int touchY) {
        View childView;
        try {
            //childView = findCanScrollView(this, touchX, touchY);
            // 从内容布局开始查找
            childView = findCanScrollView(mRefreshableView, touchX, touchY);
            if (childView == null) {
                return false;
            }
            if (childView instanceof AbsListView) {
                int top = ((AbsListView) childView).getChildAt(0).getTop();
                int pad = ((AbsListView) childView).getListPaddingTop();
                if ((Math.abs(top - pad)) < mTouchSlop && ((AbsListView) childView).getFirstVisiblePosition() == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (childView instanceof RecyclerView) {
                int position;
                RecyclerView recycler = (RecyclerView) childView;
                RecyclerView.LayoutManager lm = recycler.getLayoutManager();
                if (lm instanceof LinearLayoutManager) { //包括GridLayoutManager
                    position = ((LinearLayoutManager) lm).findFirstVisibleItemPosition();
                } else if (lm instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) lm;
                    int[] lastPositions = layoutManager.findFirstVisibleItemPositions(new int[layoutManager.getSpanCount()]);
                    int size = lastPositions.length;
                    int minPosition = Integer.MAX_VALUE;
                    for (int i = 0; i < size; i++) {
                        minPosition = Math.min(minPosition, lastPositions[i]);
                    }
                    position = minPosition;
                } else {
                    position = 0;
                }
                int top = ((RecyclerView) childView).getChildAt(0).getTop();
                int pad = ((RecyclerView) childView).getPaddingTop();
                if ((Math.abs(top - pad)) < mTouchSlop && position == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (childView instanceof ScrollView) {
                if (((ScrollView) childView).getScrollY() == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "checkIsContentViewScrollToTop fail, reason=" + e);
        }
        return false;
    }

    /**
     * 判断（touchX,touchY）点击处的滚动子View是否滚动到了底部
     *
     * @param touchX
     * @param touchY
     * @return
     */
    private boolean checkIsContentViewScrollToBottom(int touchX, int touchY) {
        View childView;
        try {
            //childView = findCanScrollView(this, touchX, touchY);
            // 从内容布局开始查找
            childView = findCanScrollView(mRefreshableView, touchX, touchY);
            if (childView == null) {
                return false;
            }
            if (childView instanceof AbsListView) {
                AbsListView absListView = (AbsListView) childView;
                if (absListView.getFirstVisiblePosition() == absListView.getAdapter().getCount() - 1) {
                    return true;
                } else {
                    return false;
                }
            } else if (childView instanceof RecyclerView) {
                int position;
                RecyclerView recycler = (RecyclerView) childView;
                RecyclerView.LayoutManager lm = recycler.getLayoutManager();
                if (lm instanceof LinearLayoutManager) { //包括GridLayoutManager
                    position = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
                } else if (lm instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) lm;
                    int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
                    int size = lastPositions.length;
                    int maxPosition = Integer.MAX_VALUE;
                    for (int i = 0; i < size; i++) {
                        maxPosition = Math.max(maxPosition, lastPositions[i]);
                    }
                    position = maxPosition;
                } else {
                    position = 0;
                }
                if (position == recycler.getAdapter().getItemCount() - 1) {
                    return true;
                } else {
                    return false;
                }
            } else if (childView instanceof ScrollView) {
                ScrollView scrollView = (ScrollView) childView;
                View contentView = scrollView.getChildAt(0);
                if (contentView != null && (contentView.getMeasuredHeight() == getScrollY() + scrollView.getHeight())) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "checkIsContentViewScrollToBottom fail, reason=" + e);
        }
        return false;
    }

    private View findCanScrollView(View view, int touchX, int touchY) {
        if (mTempRect == null) {
            mTempRect = new Rect();
        }
        Rect rect = mTempRect;
        if (view.getVisibility() != View.VISIBLE) {
            return null;
        }
        boolean scrollingView = view instanceof AbsListView ||
                view instanceof RecyclerView ||
                view instanceof ScrollView ||
                view instanceof ScrollingView ||
                view instanceof WebView;
        view.getHitRect(rect);
        rect.offset(-view.getLeft(), -view.getTop());
        if (!rect.contains(touchX, touchY)) {
            return null;
        }
        if (scrollingView) {
            return view;
        } else if (view instanceof ViewGroup) {
            int N = ((ViewGroup) view).getChildCount();
            int tx, ty;
            touchX += view.getScrollX();
            touchY += view.getScrollY();
            View childView = null;
            View scrollView = null;
            if (view instanceof ViewPager) {
                ViewPager vpager = (ViewPager) view;
                if (N > 0) {
                    childView = vpager.getChildAt(N - 1);
                    tx = touchX - childView.getLeft();
                    ty = touchY - childView.getTop();
                    scrollView = findCanScrollView(childView, tx, ty);
                    if (scrollView != null) {
                        return scrollView;
                    }
                }
            } else if (N > 0) {
                for (int k = N - 1; k >= 0; k--) {
                    childView = ((ViewGroup) view).getChildAt(k);
                    tx = touchX - childView.getLeft();
                    ty = touchY - childView.getTop();
                    scrollView = findCanScrollView(childView, tx, ty);
                    if (scrollView != null) {
                        return scrollView;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 实现了平滑滚动的Runnable
     *
     * @author Li Hong
     * @since 2013-8-22
     */
    final class SmoothScrollRunnable implements Runnable {
        /**
         * 动画效果
         */
        private final Interpolator mInterpolator;
        /**
         * 结束Y
         */
        private final int mScrollToY;
        /**
         * 开始Y
         */
        private final int mScrollFromY;
        /**
         * 滑动时间
         */
        private final long mDuration;
        /**
         * 是否继续运行
         */
        private boolean mContinueRunning = true;
        /**
         * 开始时刻
         */
        private long mStartTime = -1;
        /**
         * 当前Y
         */
        private int mCurrentY = -1;

        /**
         * 构造方法
         *
         * @param fromY    开始Y
         * @param toY      结束Y
         * @param duration 动画时间
         */
        public SmoothScrollRunnable(int fromY, int toY, long duration) {
            mScrollFromY = fromY;
            mScrollToY = toY;
            mDuration = duration;
            mInterpolator = new DecelerateInterpolator();
        }

        @Override
        public void run() {
            Log.d(TAG, "run...............");
            /**
             * If the duration is 0, we scroll the view to target y directly.
             */
            if (mDuration <= 0) {
                setScrollTo(0, mScrollToY);
                return;
            }

            /**
             * Only set mStartTime if this is the first time we're starting,
             * else actually calculate the Y delta
             */
            if (mStartTime == -1) {
                mStartTime = System.currentTimeMillis();
            } else {

                final long oneSecond = 1000;    // SUPPRESS CHECKSTYLE
                long normalizedTime = (oneSecond * (System.currentTimeMillis() - mStartTime)) / mDuration;
                normalizedTime = Math.max(Math.min(normalizedTime, oneSecond), 0);

                final int deltaY = Math.round((mScrollFromY - mScrollToY)
                        * mInterpolator.getInterpolation(normalizedTime / (float) oneSecond));
                mCurrentY = mScrollFromY - deltaY;
                setScrollTo(0, mCurrentY);
                Log.d(TAG, "run " + mCurrentY);

                if (null != mHeaderLayout && 0 != mHeaderHeight) {
                    Log.d(TAG, "run  mHeaderLayout.onPull =" + Math.abs(mCurrentY));
                    mHeaderLayout.onPull(Math.abs(mCurrentY));
                    if (mCurrentY == 0) {
                        mHeaderLayout.setState(IExtendLayout.State.RESET);
                    }
                    if (Math.abs(mCurrentY) == mHeaderListHeight) {
                        mHeaderLayout.setState(IExtendLayout.State.arrivedListHeight);
                    }
                }
                if (null != mFooterLayout && 0 != mFooterHeight) {
                    mFooterLayout.onPull(Math.abs(mCurrentY));
                    if (mCurrentY == 0) {
                        mFooterLayout.setState(IExtendLayout.State.RESET);
                    }
                    if (Math.abs(mCurrentY) == mFooterListHeight) {
                        mFooterLayout.setState(IExtendLayout.State.arrivedListHeight);
                    }
                }
            }

            // If we're not at the target Y, keep going...
            if (mContinueRunning && mScrollToY != mCurrentY) {
                PullExtendLayout.this.postDelayed(this, 16);// SUPPRESS CHECKSTYLE
            }
        }

        /**
         * 停止滑动
         */
        public void stop() {
            mContinueRunning = false;
            removeCallbacks(this);
        }
    }

    //header 打开关闭
    IPullHeaderOpenListener pullHeaderOpenListener;
    public void setPullHeaderOpenListener(IPullHeaderOpenListener pullHeaderOpenListener){
        this.pullHeaderOpenListener = pullHeaderOpenListener;
    }
}
