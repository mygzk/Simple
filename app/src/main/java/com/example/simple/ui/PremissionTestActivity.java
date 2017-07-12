package com.example.simple.ui;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.simple.R;
import com.example.simple.utils.WindowUtils;

public class PremissionTestActivity extends AppCompatActivity implements View.OnClickListener {

    private int REQUEST_CAMERA = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premission_test);

        initView();
    }

    private void initView() {
        findViewById(R.id.btn_test1).setOnClickListener(this);
        findViewById(R.id.btn_test2).setOnClickListener(this);
        findViewById(R.id.btn_test3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test1:
                request();
                break;
            case R.id.btn_test2:
                WindowUtils.showPopupWindow(this);
                break;
            case R.id.btn_test3:
                showdialog();
                break;
            default:
                break;
        }
    }

    private void request() {
        // 检查摄像头权限是否已经有效
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // 摄像头权限还未得到用户的同意
            requestCameraPermission();
        } else {
            // 摄像头权限以及有效，显示摄像头预览界面
            //showCameraPreview();

            Log.e("prermiss", "hase premission...");
        }
    }

    private void requestCameraPermission() {
        // 摄像头权限已经被拒绝
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            // 如果用户已经拒绝劝降，那么提供额外的权限说明
          /*  Snackbar.make(mLayout, R.string.permission_camera_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(PremissionTestActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUEST_CAMERA);
                        }
                    })
                    .show();*/


            ActivityCompat.requestPermissions(PremissionTestActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA);
        } else {
            // 摄像头还没有被拒绝，直接申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA);
        }
    }


    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
            // BEGIN_INCLUDE(permission_result)
            // 收到摄像头权限申请的结果
            // 检查摄像头权限是否已经通过
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 摄像头权限已经申请成功，可以展示摄像预览界面了
               /* Snackbar.make(mLayout, R.string.permision_available_camera,
                        Snackbar.LENGTH_SHORT).show();
                showCameraPreview();*/
                Log.e("prermiss", "request premission succ...");
            } else {
                Log.e("prermiss", "request premission fail...");
                // 摄像头权限申请失败
               /* Snackbar.make(mLayout, R.string.permissions_not_granted,
                        Snackbar.LENGTH_SHORT).show();*/
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("prermiss", "0000000000000000000000000000000");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("prermiss", "111111111111111111111111111111111");
    }


    private void getA() {
        AlertDialog tipdialog = new AlertDialog.Builder(this.getApplication())
                .setTitle(this.getResources().getString(R.string.permiss_settings))
                .setMessage(this.getResources().getString(R.string.permiss_help_text))
                .setNeutralButton(this.getResources().getString(R.string.permiss_quit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setPositiveButton(this.getResources().getString(R.string.permiss_settings), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
        tipdialog.setCanceledOnTouchOutside(false);
        tipdialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
        tipdialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
        tipdialog.show();
    }


        private void showUpdateSuccessDialog(){
            final WindowManager wm = (WindowManager) this.getApplication().getSystemService(Context.WINDOW_SERVICE);

            WindowManager.LayoutParams para = new WindowManager.LayoutParams();
            para.height = 280;//WRAP_CONTENT
            para.width = 600;//WRAP_CONTENT
            para.format = 1;

            para.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

            para.gravity = Gravity.CENTER;

           // para.type = WindowManager.LayoutParams.TYPE_TOAST;
            para.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;;

            final View contentView = LayoutInflater.from(this.getApplication()).inflate(R.layout.popupwindow, null);
            TextView tvDlgTiTle = (TextView) contentView.findViewById(R.id.tv_title_dialog);
            Button tvDlgSubTitle = (Button) contentView.findViewById(R.id.negativeBtn);
            Button tvDlgBtn = (Button) contentView.findViewById(R.id.positiveBtn);

            tvDlgTiTle.setText("更新成功");
            tvDlgSubTitle.setText("固件将会重启");
            tvDlgBtn.setText("固件重启将会持续几分钟...");

            tvDlgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wm.removeView(contentView);
                }
            });
            wm.addView(contentView, para);
        }



        private void showdialog(){

         View   selfView = LayoutInflater.from(this).inflate(R.layout.popupwindow, null);
            TextView content = (TextView) selfView.findViewById(R.id.tv_title_dialog);
            content.setText("是否接受来自" +  "的位置共享");

            TextView cancel = (TextView) selfView.findViewById(R.id.negativeBtn);
            TextView ok = (TextView) selfView.findViewById(R.id.positiveBtn);

            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    540,
                   280,
                    WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                    PixelFormat.TRANSLUCENT);

            WindowManager    windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            //       windowManager.updateViewLayout(selfView, params);
            windowManager.addView(selfView, params);


        }

}
