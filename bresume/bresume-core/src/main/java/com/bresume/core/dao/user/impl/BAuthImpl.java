package com.bresume.core.dao.user.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.user.IBAuthDao;
import com.bresume.core.model.entity.user.BAuth;

@Repository
public class BAuthImpl extends SimpleHibernateDao<BAuth, String> implements
		IBAuthDao {

	@Override
	public boolean delete(String userId, int type) {
		Query query = this.createQuery(
				"detete from BAuth where user.id=? and type=?", userId, type);
		return query.executeUpdate() > 0;
	}

}
