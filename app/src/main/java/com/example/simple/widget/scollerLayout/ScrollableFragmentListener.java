package com.example.simple.widget.scollerLayout;

/**
 * Created by guozhk on 2018/3/19.
 */

public interface ScrollableFragmentListener {

    void onFragmentAttached(ScrollableListener fragment, int position);

    void onFragmentDetached(ScrollableListener fragment, int position);
}
