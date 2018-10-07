package com.example.simple.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.simple.R;
import com.example.simple.dialog.DialogHelper;

import org.w3c.dom.Text;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
    }


    public void showdialog(View view) {
        new DialogHelper.Builder()
                .layoutResId(R.layout.dialog_test_1)
                .clickId(R.id.dialog_test_01)
                .setCancelableOutside(true)
                .setGravity(Gravity.BOTTOM)
                .setDalogAnimationRes(R.style.animate_dialog) //设置弹窗动画
                .bindView(new DialogHelper.IBindView() {
                    @Override
                    public void bindView(View view) {
                        ((TextView) view.findViewById(R.id.dialog_test_01)).setText("werwer");
                    }
                })
                .create()
                .show(getSupportFragmentManager(), "t");

    }

    public void showdialog1(View view) {
        new DialogHelper.Builder()
                .layoutResId(R.layout.dialog_test_1)
                .clickId(R.id.dialog_test_01)
                .setCancelableOutside(true)
                .bindView(new DialogHelper.IBindView() {
                    @Override
                    public void bindView(View view) {
                        ((TextView) view.findViewById(R.id.dialog_test_01)).setText("werwer");
                    }
                })
                .create()
                .show(getSupportFragmentManager(), "t");

    }


}
