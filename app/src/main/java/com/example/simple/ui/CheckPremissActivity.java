package com.example.simple.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.simple.R;
import com.example.simple.model.prermission.PermissionConstant;
import com.example.simple.model.prermission.PermissionsChecker;
import com.example.simple.model.prermission.PermissionsHelper;

public class CheckPremissActivity extends AppCompatActivity implements View.OnClickListener {
    // 权限检测器
    private PermissionsChecker mPermissionsChecker;
    private PermissionsHelper mPermissionsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_premiss);
        initView();
    }

    private void initView() {
        mPermissionsChecker = new PermissionsChecker(this);
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PermissionConstant.PERMISSIONS)) {
            mPermissionsHelper = new PermissionsHelper(this, PermissionConstant.PERMISSIONS);
        }
        findViewById(R.id.tv_text1).setOnClickListener(this);
        findViewById(R.id.tv_text2).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPermissionsHelper != null) {
            Log.e("check", "2222222222222222222");
            mPermissionsHelper.checkPermissions();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_text1:
                break;
            case R.id.tv_text2:
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.e("check", "33333333333");
        if (!mPermissionsHelper.doRequestPermissionsResult(requestCode, permissions, grantResults)) {
            mPermissionsHelper.showMissingPermissionDialog(this);
        }
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("check", "00000000000000000000000");

    }
}
