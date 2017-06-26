package com.example.simple.prermission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.simple.R;
import com.example.simple.dialog.CommTipFragmentDialog;


/**
 * Created by guozhk on 16-12-27.
 */

public class PermissionsHelper {

    public static final int PERMISSIONS_GRANTED = 0; // 权限授权
    public static final int PERMISSIONS_DENIED = 1; // 权限拒绝
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案

    private static final int PERMISSION_REQUEST_CODE = 0; // 系统权限管理页面的参数

    private PermissionsChecker mChecker; // 权限检测器
    private boolean isRequireCheck; // 是否需要系统权限检测, 防止和系统提示框重叠


    private String[] mPermissions;
    private AppCompatActivity mActivity;

    public PermissionsHelper(AppCompatActivity context, String[] permissions) {
        this.mPermissions = permissions;
        mChecker = new PermissionsChecker(context.getApplicationContext());
        isRequireCheck = true;
        mActivity = context;
    }

    public void checkPermissions() {
        if (mPermissions == null) {
            return;
        }
        if (mPermissions.length == 0) {
            return;
        }
        if (isRequireCheck) {
            if (mChecker.lacksPermissions(mPermissions)) {
                requestPermissions(mPermissions); // 请求权限
            } else {
                //   allPermissionsGranted(); // 全部权限都已获取
            }
        } else {
            isRequireCheck = true;
        }
    }


    // 请求权限兼容低版本
    private void requestPermissions(String... permissions) {
        ActivityCompat.requestPermissions(mActivity, permissions, PERMISSION_REQUEST_CODE);
    }


    public boolean doRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE && hasAllPermissionsGranted(grantResults)) {
            isRequireCheck = true;
        } else {

            isRequireCheck = false;
            //showMissingPermissionDialog();
        }

        return isRequireCheck;
    }


    // 含有全部的权限
    private boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }


    // 显示缺失权限提示
    public void showMissingPermissionDialog(final AppCompatActivity activity) {
        final CommTipFragmentDialog tipdialog = new CommTipFragmentDialog();
        tipdialog.setTitle(activity.getResources().getString(R.string.permiss_settings));
        tipdialog.setTip(activity.getResources().getString(R.string.permiss_help_text));
        tipdialog.setCancle(activity.getResources().getString(R.string.permiss_quit));
        tipdialog.setSure(activity.getResources().getString(R.string.permiss_settings));
        tipdialog.setOnSureClickListenser(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAppSettings();
            }
        });
        tipdialog.setOnCancleClickListenser(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipdialog.dismiss();
                activity.setResult(PERMISSIONS_DENIED);
                activity.finish();

            }
        });
        tipdialog.setCancelable(false);
        Log.e("check","11mActivity:"+mActivity);
        Log.e("check","22mActivity:"+mActivity.isFinishing());
        tipdialog.show(activity.getSupportFragmentManager(), "check Premiss");

    }


    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + mActivity.getApplicationContext().getPackageName()));
        mActivity.startActivity(intent);
    }


}
