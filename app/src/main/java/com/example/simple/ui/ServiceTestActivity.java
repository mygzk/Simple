package com.example.simple.ui;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.simple.R;
import com.example.simple.services.MyService;

public class ServiceTestActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_test;
    }

    @Override
    protected void initView() {

        queryViewById(R.id.service_start, true);
        queryViewById(R.id.service_stop, true);
        queryViewById(R.id.service_bind, true);
        queryViewById(R.id.service_unbind, true);

    }


    @Override
    public void onClick(View v) {
        Intent serviceIntent = new Intent(this, MyService.class);
        switch (v.getId()) {

            case R.id.service_start:

                startService(serviceIntent);
                break;

            case R.id.service_stop:
                stopService(serviceIntent);
                break;

            case R.id.service_bind:
                bindService(serviceIntent, connection, BIND_AUTO_CREATE);


                break;

            case R.id.service_unbind:

                unbindService(connection);
                break;
            default:
                break;
        }
    }

    private MyService myService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            myService = myBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            Log.e(TAG, "name:" + name);

        }
    };
}
