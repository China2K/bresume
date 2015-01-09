package com.bresume.core.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.user.IBAuthDao;
import com.bresume.core.model.entity.user.BAuth;

@Repository
public class BAuthImpl extends SimpleHibernateDao<BAuth, String> implements
		IBAuthDao {

}
