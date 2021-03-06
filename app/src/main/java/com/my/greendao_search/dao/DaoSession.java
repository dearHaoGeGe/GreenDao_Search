package com.my.greendao_search.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.my.greendao_search.bean.Case;

import com.my.greendao_search.dao.CaseDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig caseDaoConfig;

    private final CaseDao caseDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        caseDaoConfig = daoConfigMap.get(CaseDao.class).clone();
        caseDaoConfig.initIdentityScope(type);

        caseDao = new CaseDao(caseDaoConfig, this);

        registerDao(Case.class, caseDao);
    }
    
    public void clear() {
        caseDaoConfig.getIdentityScope().clear();
    }

    public CaseDao getCaseDao() {
        return caseDao;
    }

}
