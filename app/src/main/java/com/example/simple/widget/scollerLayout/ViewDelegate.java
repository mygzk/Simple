package com.example.simple.widget.scollerLayout;

import android.view.View;

/**
 * Created by guozhk on 2018/3/20.
 */

public interface ViewDelegate {
    public boolean isReadyForPull(View view, float x, float y);
}
