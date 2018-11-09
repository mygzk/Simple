package com.example.simple.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by guozhk
 * Date : 2018/11/9
 * Dre :
 **/
@Entity
public class TestEntity {
    @NotNull
    private String name;

    @Generated(hash = 1962567288)
    public TestEntity(@NotNull String name) {
        this.name = name;
    }

    @Generated(hash = 1020448049)
    public TestEntity() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
