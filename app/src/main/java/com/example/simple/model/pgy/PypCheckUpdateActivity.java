package com.example.simple.model.pgy;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;

import com.example.simple.R;
import com.example.simple.ui.BaseActivity;
import com.example.simple.widget.UpdateProgressDiolog;
import com.pgyersdk.update.DownloadFileListener;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;


/**
 * 蒲公英检查更新
 */
public class PypCheckUpdateActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pyp_check_update;
    }

    @Override
    protected void initView() {
            queryViewById(R.id.pypChekUpdate,true);
            queryViewById(R.id.dialog,true);
            queryViewById(R.id.down,true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pypChekUpdate:
                checkUpdate();

                break;
            case R.id.dialog:
                progressDialog();

                break;
            case R.id.down:
downAPkTest();

                break;
        }
    }

    private void downAPkTest() {

        UpdateUtil updateUtil = new UpdateUtil();
        updateUtil.testInstall();
}


    private void progressDialog() {
        UpdateProgressDiolog diolog = new UpdateProgressDiolog(this);

        diolog.updateToatalSize(100);
        diolog.updateProcess(30);
        diolog.show();

    }


    private void checkUpdate(){
        PgyHelper helper = new PgyHelper(getSupportFragmentManager(),this);
        helper.checkUpdate();;

    }
}
