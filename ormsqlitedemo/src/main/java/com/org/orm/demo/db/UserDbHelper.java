package com.org.orm.demo.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class UserDbHelper {

    private Context mContext;
    private Dao<UserEntity, Integer> simpleDao = null;
    //private AppOrmLiteSqliteHelper mHelper;


    private static UserDbHelper mHelper;

    private UserDbHelper(Context context) {
        try {
            simpleDao = AppOrmLiteSqliteHelper.getInstance(context).getDao(UserEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static synchronized UserDbHelper getInstance(Context context) {

        if (mHelper == null) {
            synchronized (UserDbHelper.class) {
                if (mHelper == null) {
                    mHelper = new UserDbHelper(context);
                }
            }
        }
        return mHelper;
    }


    /***
     * 添加数据的几种方式
     * create
     * create (List<>)
     * createIfNotExists
     * createOrUpdate
     *
     * @param entity
     * @throws SQLException
     */
    public void insterDate(UserEntity entity) {


        try {
            if (simpleDao != null) {
                 simpleDao.createOrUpdate(entity); //添加
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * 查询方式
     *
     * @return lsit
     */
    public List<UserEntity> queryUser() {
        try {
            if (simpleDao != null) {
                return simpleDao.queryForAll();

                //  simpleDao.queryForId(1);

                // simpleDao.queryForEq("name", "11");
                //simpleDao.query()

//            QueryBuilder<UserEntity, Integer> queryBuilder = simpleDao.queryBuilder();
//            Where where = queryBuilder.where();
//            where.eq();
//            where.eq()
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;

    }


    public void delData(UserEntity entity) {
        try {
            if (simpleDao != null) {
                simpleDao.delete(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
