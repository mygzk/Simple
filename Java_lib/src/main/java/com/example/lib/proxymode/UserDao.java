package com.example.lib.proxymode;

/**
 * Created by guozhk on 2018/4/17.
 */

/**
 * 目标对象
 */

public class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
