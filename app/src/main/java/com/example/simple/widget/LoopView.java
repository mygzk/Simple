package com.example.simple.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by guozhk on 17-1-11.
 */

public class LoopView extends View {
    private Paint mPaint;
    private int measuredHeight;
    private int measuredWidth;

    private Rect tempRect = new Rect();

    public LoopView(Context context) {
        this(context, null);
    }

    public LoopView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setTypeface(Typeface.MONOSPACE);
        //设置画笔颜色
        mPaint.setTextSize((float) 30.0);                    //设置字体大小


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
        if (measuredWidth == 0 || measuredHeight == 0) {
            System.out.println("measuredWidth or measuredHeight is 0");
            return;
        }
        String text = "ousadadas";
        mPaint.getTextBounds(text, 0, text.length(), tempRect); // 星期

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        reDraw(canvas);
    }

    private void reDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
        String text = "ousadadas";
        int xStar = measuredWidth / 2 - tempRect.width() / 2;
        //  int YStar = measuredHeight / 2 + tempRect.height() / 2;
        int YStar = tempRect.height();
        //canvas.translate(0,90);
        canvas.drawText(text, xStar, YStar, mPaint);
        int count = measuredHeight / tempRect.height();
        for (int i=1;i<=count;i++){
            canvas.drawText(text, xStar, i*tempRect.height(), mPaint);
            canvas.translate(0,i*20);
            canvas.save();
            canvas.restore();
        }


    }


}
