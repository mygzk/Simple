package com.example.simple.model.datepick;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.simple.R;
import com.example.simple.dialog.DialogHelper;
import com.example.simple.ui.BaseActivity;

import org.w3c.dom.Text;

/**
 * 日期选择器
 */
public class DatePickerTestActivity extends BaseActivity {
    private TextView tvPick1;
    private TextView tvPick2;
    private int DIALOG_TYPE_DATE = 0;
    private int DIALOG_TYPE_TIME = 1;

    private int mType = DIALOG_TYPE_DATE;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_date_picker_test;
    }

    @Override
    protected void initView() {
        tvPick1 = queryViewById(R.id.date_pick_tv1, true);
        tvPick2 = queryViewById(R.id.date_pick_tv2, true);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date_pick_tv1:
                mType = DIALOG_TYPE_DATE;
                selectDatePickStyleDialog();
                break;
            case R.id.date_pick_tv2:
                mType = DIALOG_TYPE_TIME;
                selectDatePickStyleDialog();
                break;
            case R.id.dialog_select_pic_style01:
                showPickerDialog(DatePickerDialog.THEME_TRADITIONAL);
                break;
            case R.id.dialog_select_pic_style02:
                showPickerDialog(DatePickerDialog.THEME_HOLO_DARK);
                break;
            case R.id.dialog_select_pic_style03:
                showPickerDialog(DatePickerDialog.THEME_HOLO_LIGHT);
                break;
            case R.id.dialog_select_pic_style04:
                showPickerDialog(DatePickerDialog.THEME_DEVICE_DEFAULT_DARK);
                break;
            case R.id.dialog_select_pic_style05:
                showPickerDialog(DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT);
                break;
            case R.id.dialog_select_pic_style06:
                showPickerDialog(0);
                break;
            case R.id.dialog_select_pic_style07:
                showPickerDialog(6);
                break;
            default:
                break;
        }
    }

    private void selectDatePickStyleDialog() {
        final DialogHelper.Builder builder = new DialogHelper.Builder();
        builder.layoutResId(R.layout.dialog_date_pick_style)
                .setWidth(getResources().getDisplayMetrics().widthPixels)

                .setGravity(Gravity.BOTTOM)
                .setCancelableOutside(true)
                .bindView(new DialogHelper.IBindView() {
                    @Override
                    public void bindView(View view) {
                        view.findViewById(R.id.dialog_select_pic_style01).setOnClickListener(DatePickerTestActivity.this);
                        view.findViewById(R.id.dialog_select_pic_style02).setOnClickListener(DatePickerTestActivity.this);
                        view.findViewById(R.id.dialog_select_pic_style03).setOnClickListener(DatePickerTestActivity.this);
                        view.findViewById(R.id.dialog_select_pic_style04).setOnClickListener(DatePickerTestActivity.this);
                        view.findViewById(R.id.dialog_select_pic_style05).setOnClickListener(DatePickerTestActivity.this);
                        view.findViewById(R.id.dialog_select_pic_style06).setOnClickListener(DatePickerTestActivity.this);
                        view.findViewById(R.id.dialog_select_pic_style07).setOnClickListener(DatePickerTestActivity.this);
                    }
                })
                .create();
        DialogHelper dialog = builder.create();


        dialog.show(getSupportFragmentManager(), "t");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showPickerDialog(int theme){

        if( mType == DIALOG_TYPE_DATE){
            showDatePickerDialog(theme);
        }else {
            showTimePickerDialog(theme);
        }

    }



    private void showDatePickerDialog(int theme) {
        //   DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT;
        new DatePickerDialog(this, theme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Log.e(TAG, "year:" + year + " month:" + month + " dayOfMonth:" + dayOfMonth);
                    }
                }, 2018, 11, 7)
                .show();
    }


     private void showTimePickerDialog(int theme){
   // private void showDatePickerDialog(int theme) {

        new TimePickerDialog(this,
                theme,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view,
                                          int hourOfDay, int minute) {
                        Log.e(TAG, "hourOfDay:" + hourOfDay + " minute:" + minute);
                    }
                },
                // 设置初始时间
                17, 42
                // true表示采用24小时制
                , true).show();
    }


}
