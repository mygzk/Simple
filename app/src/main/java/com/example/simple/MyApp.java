package com.example.simple;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;

import com.example.simple.db.dao.DaoMaster;
import com.example.simple.db.dao.DaoSession;
import com.pgyersdk.Pgyer;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.update.PgyUpdateManager;

import org.greenrobot.greendao.database.Database;

/**
 * Created by guozhk on 2017/12/4.
 */

public class MyApp extends Application {
    private static MyApp app;


    @Override
    public void onCreate() {
        super.onCreate();
       // PgyCrashManager.register();
        Pgyer.setAppId("d03390c7201e0692e17fcc6d955e6a68");

        app = this;
        initDB();
    }

    private void initDB() {

        // regular SQLite database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");

        Database db = helper.getWritableDb();

        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");

        DaoSession daoSession = new DaoMaster(db).newSession();


        Handler handler;

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
