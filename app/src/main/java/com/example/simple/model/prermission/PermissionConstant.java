package com.example.simple.model.prermission;

import android.Manifest;
import android.support.annotation.NonNull;

/**
 * Created by：guozhk
 * date: 16-10-27 14:54
 */
public class PermissionConstant {


/*    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.CALL_PHONE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
    <uses-permission android:name="android.permission.READ_LOGS" />
  <!--  <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />-->
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />*/

    //权限检测 请求码
    public static final int REQUEST_CODE = 0; // 请求码
    public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
            Manifest.permission.READ_LOGS,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static final String[] PERMISSIONS_READ_PHONE_STATE = new String[]{
            Manifest.permission.READ_PHONE_STATE
    };

    /**
     * 检测包含谋个权限
     *
     * @param permissions 权限集合
     * @param permiss     检测权限
     * @return boolean
     */
    public static boolean isContansPermiss(@NonNull String[] permissions, String permiss) {
        for (String p : permissions) {
            if (p.equals(permiss)) {
                return true;
            }
        }
        return false;
    }
}
