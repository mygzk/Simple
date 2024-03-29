package com.example.simple.db.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.simple.db.entity.TestEntity;

import com.example.simple.db.dao.TestEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig testEntityDaoConfig;

    private final TestEntityDao testEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        testEntityDaoConfig = daoConfigMap.get(TestEntityDao.class).clone();
        testEntityDaoConfig.initIdentityScope(type);

        testEntityDao = new TestEntityDao(testEntityDaoConfig, this);

        registerDao(TestEntity.class, testEntityDao);
    }
    
    public void clear() {
        testEntityDaoConfig.clearIdentityScope();
    }

    public TestEntityDao getTestEntityDao() {
        return testEntityDao;
    }

}
