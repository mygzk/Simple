package com.example.simple.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.widget.TextView;

/**
 * Create by guozhk
 * Date : 2018/10/2
 * Dre :
 **/
public class TextViewUtils {

    /**
     * 富文本添加图片到末尾 并自动匹配高度
     *
     * @param textView textView
     * @param context context
     * @param drawable drawable
     */
    public static void addDrawableInEnd(TextView textView, Context context, Drawable drawable, String str) {

        if (drawable == null) {
            return;
        }
        context = context.getApplicationContext()
        ;
        TextPaint paint = textView.getPaint();// 获取文本控件字体样式
        Paint.FontMetrics fm = paint.getFontMetrics();
        int textFontHeight = (int) Math.ceil(fm.descent - fm.top) + 2;// 计算字体高度座位图片高度
        int imageWidth = drawable.getIntrinsicWidth() * textFontHeight
                / drawable.getIntrinsicHeight();// 计算图片根据字体大小等比例缩放后的宽度
        drawable.setBounds(0, dip2px(context, 1), imageWidth,
                textFontHeight);// 缩放图片  也可根据实际需求

        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM); //   ImageSpan.ALIGN_BASELINE放置位置
        String space = " ";
        str = str + space;
        int strLength = str.length();
        SpannableString ss = new SpannableString(str);
        ss.setSpan(span, strLength - 1, strLength, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(ss.subSequence(0, strLength));

    }

    public static int dip2px(Context context, float value) {

        return (int) (context.getResources().getDisplayMetrics().density * value + 0.5f);
    }

}
