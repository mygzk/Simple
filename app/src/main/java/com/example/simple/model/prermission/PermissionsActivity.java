package com.example.simple.model.prermission;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.simple.R;
import com.example.simple.dialog.CommTipFragmentDialog;


/**
 * Created by：guozhk
 * date: 16-10-27 15:03
 */
public class PermissionsActivity extends AppCompatActivity {

    public static final int PERMISSIONS_GRANTED = 0; // 权限授权
    public static final int PERMISSIONS_DENIED = 1; // 权限拒绝

    private static final int PERMISSION_REQUEST_CODE = 0; // 系统权限管理页面的参数
    private static final String EXTRA_PERMISSIONS =
            "me.chunyu.clwang.permission.extra_permission"; // 权限参数
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案

    private PermissionsChecker mChecker; // 权限检测器
    private boolean isRequireCheck; // 是否需要系统权限检测, 防止和系统提示框重叠

    // 启动当前权限页面的公开接口
    public static void startActivityForResult(Activity activity, int requestCode, String... permissions) {
        Intent intent = new Intent(activity, PermissionsActivity.class);
        intent.putExtra(EXTRA_PERMISSIONS, permissions);
        ActivityCompat.startActivityForResult(activity, intent, requestCode, null);
       // activity.overridePendingTransition(R.anim.fade, R.anim.hold);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() == null || !getIntent().hasExtra(EXTRA_PERMISSIONS)) {
            throw new RuntimeException("PermissionsActivity需要使用静态startActivityForResult方法启动!");
        }
        setContentView(R.layout.activity_permissions);

        mChecker = new PermissionsChecker(this);
        isRequireCheck = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRequireCheck) {
            String[] permissions = getPermissions();
            if (mChecker.lacksPermissions(permissions)) {
                requestPermissions(permissions); // 请求权限
            } else {
                allPermissionsGranted(); // 全部权限都已获取
            }
        } else {
            isRequireCheck = true;
        }
    }

    // 返回传递的权限参数
    private String[] getPermissions() {
        return getIntent().getStringArrayExtra(EXTRA_PERMISSIONS);
    }

    // 请求权限兼容低版本
    private void requestPermissions(String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    // 全部权限均已获取
    private void allPermissionsGranted() {
        setResult(PERMISSIONS_GRANTED);
        finish();
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE && hasAllPermissionsGranted(grantResults)) {
            isRequireCheck = true;
            allPermissionsGranted();
        } else {
            isRequireCheck = false;
            showMissingPermissionDialog();
        }
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
    private void showMissingPermissionDialog() {
        CommTipFragmentDialog dialog = new CommTipFragmentDialog();
        final CommTipFragmentDialog tipdialog = new CommTipFragmentDialog();
        tipdialog.setTitle(this.getResources().getString(R.string.permiss_settings));
        tipdialog.setTip(this.getResources().getString(R.string.permiss_help_text));
        tipdialog.setCancle(this.getResources().getString(R.string.permiss_quit));
        tipdialog.setSure(this.getResources().getString(R.string.permiss_settings));
        tipdialog.setOnSureClickListenser(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAppSettings();
            }
        });
        tipdialog.setOnCancleClickListenser(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(PERMISSIONS_DENIED);
                tipdialog.dismiss();
                finish();
            }
        });
        tipdialog.setCancelable(false);
        tipdialog.show(getSupportFragmentManager(), "check Premiss");

  /*      AlertDialog.Builder builder = new AlertDialog.Builder(PermissionsActivity.this);
        builder.setTitle(R.string.permiss_help);
        builder.setMessage(R.string.permiss_help_text);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.permiss_quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setResult(PERMISSIONS_DENIED);
                finish();
            }
        });

        builder.setPositiveButton(R.string.permiss_settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.setCancelable(false);

        builder.show();*/
    }

    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }
}
