package com.example.simple.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.simple.R;
import com.example.simple.dialog.DialogHelper;
import com.example.simple.dialog.DialogListView;
import com.example.simple.utils.DensityUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
                .setWidth(getResources().getDisplayMetrics().widthPixels)
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

    public void showdialogList(View view) {

        DialogListView dialogListView = new DialogListView(this);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            datas.add("test-" + i);
        }

        dialogListView.updateDatas(datas);

        DialogHelper dialog = new DialogHelper.Builder()
                .layoutResId(R.layout.dialog_test_1)
                .setWidth(getResources().getDisplayMetrics().widthPixels)
                .setHight(((int) (getResources().getDisplayMetrics().heightPixels * 0.6)))
                //.clickId(R.id.dialog_test_01)
                .dialogView(dialogListView)
                .setGravity(Gravity.BOTTOM)
                .setCancelableOutside(true)
//                .bindView(new DialogHelper.IBindView() {
//                    @Override
//                    public void bindView(View view) {
//                        ((TextView) view.findViewById(R.id.dialog_test_01)).setText("werwer");
//                    }
//                })
                .create();

        dialog.show(getSupportFragmentManager(), "t");

    }


}
