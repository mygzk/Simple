package com.org.orm.demo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class AppOrmLiteSqliteHelper extends OrmLiteSqliteOpenHelper {
    /**
     * 数据库名称
     */
    private static final String DB_NAME = "com.abcdangdang.config.db";

    /**
     * 当前数据库版本
     */
    private static final int DB_VERSION = 1;

    /**
     * 数据库加密秘钥
     */
    private static final String DB_PASSWORD = "test";


    private static AppOrmLiteSqliteHelper mDbHelper;

    public static synchronized AppOrmLiteSqliteHelper getInstance(Context context) {

        if (mDbHelper == null) {
            synchronized (AppOrmLiteSqliteHelper.class) {
                if (mDbHelper == null) {
                    mDbHelper = new AppOrmLiteSqliteHelper(context.getApplicationContext());
                }
            }
        }
        return mDbHelper;
    }


    private AppOrmLiteSqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            // 创建Config表
            TableUtils.createTableIfNotExists(connectionSource, UserEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            // 创建Config表
            TableUtils.dropTable(connectionSource, UserEntity.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // 创建Config表
            TableUtils.createTableIfNotExists(connectionSource, UserEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
