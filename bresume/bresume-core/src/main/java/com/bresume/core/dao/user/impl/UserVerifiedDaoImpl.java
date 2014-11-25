package com.bresume.core.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.user.IUserVerifiedDao;
import com.bresume.core.model.entity.user.UserVerified;

@Repository
public class UserVerifiedDaoImpl extends SimpleHibernateDao<UserVerified,String> implements IUserVerifiedDao {

}
