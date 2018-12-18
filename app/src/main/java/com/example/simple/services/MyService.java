package com.example.simple.services;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private String TAG = MyService.class.getSimpleName();


    private MyBinder myBinder = new MyBinder();
    public MyService() {

        Log.e(TAG, "=====MyService======");
    }


    @Override
    public void onCreate() {
        Log.e(TAG, "=====onCreate======");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "=====onBind======");
       // throw new UnsupportedOperationException("Not yet implemented");

        return myBinder;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "=====onStartCommand======");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        Log.e(TAG, "=====unbindService======");
        super.unbindService(conn);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "=====onUnbind======");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "=====onDestroy======");
        super.onDestroy();
    }



    public class MyBinder extends Binder{

        public MyService getService(){
            return MyService.this;
        }

    }
}
