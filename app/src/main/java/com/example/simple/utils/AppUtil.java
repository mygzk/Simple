package com.example.simple.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Created by guozhk on 2018/12/21.
 */

public class AppUtil {




    /**
     * 获取Apk文件的Log图标
     * @param context
     * @param apk_path Apk路径
     * @return
     */
    public static Drawable getApkThumbnail(Context context, String apk_path){
        if(context == null){
            return null;
        }

        try{
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageArchiveInfo(apk_path, PackageManager.GET_ACTIVITIES);
            ApplicationInfo appInfo = packageInfo.applicationInfo;
            /**获取apk的图标 */
            appInfo.sourceDir = apk_path;
            appInfo.publicSourceDir = apk_path;
            if(appInfo != null){
                Drawable apk_icon = appInfo.loadIcon(pm);
                return apk_icon;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Drawable转Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable){
        if(drawable == null){
            return null;
        }

        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        //建立对应的Bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);

        return bitmap;
    }
}
