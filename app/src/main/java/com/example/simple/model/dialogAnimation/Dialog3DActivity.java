package com.example.simple.model.dialogAnimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.simple.R;
import com.example.simple.ui.BaseActivity;

public class Dialog3DActivity extends BaseActivity {

    Rotate3dAnimation a;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog3_d;
    }

    @Override
    protected void initView() {
        queryViewById(R.id.dialog_3d_tv,true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.dialog_3d_tv:

               showDialog3();
            break;
        }
    }

    private void showDialog3() {
        final MyDialog dialog = new MyDialog(this);

        dialog.showDialog();
    }
}
