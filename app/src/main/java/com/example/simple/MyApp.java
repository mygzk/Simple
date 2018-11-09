package com.example.simple;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.simple.db.dao.DaoMaster;
import com.example.simple.db.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by guozhk on 2017/12/4.
 */

public class MyApp extends Application {
    private static MyApp app;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initDB();
    }

    private void initDB() {

        // regular SQLite database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");

        Database db = helper.getEncryptedWritableDb("123456");

        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");

        DaoSession daoSession = new DaoMaster(db).newSession();

    }


    public static MyApp getApp(){
        return app;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e("app","====level:"+level);
    }



}
