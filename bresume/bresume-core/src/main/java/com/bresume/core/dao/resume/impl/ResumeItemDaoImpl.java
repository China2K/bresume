package com.bresume.core.dao.resume.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.common.utils.search.SearchFactory;
import com.bresume.core.dao.resume.IResumeItemDao;
import com.bresume.core.model.entity.resume.ResumeItem;

@Repository
public class ResumeItemDaoImpl extends SimpleHibernateDao<ResumeItem, String>
		implements IResumeItemDao {

	
	@Override
	public List<?> findResumeItem(Class<?> entity, SearchBean[] searchBean, Order order) {
		DetachedCriteria criteria = SearchFactory.generateCriteria(entity,
				searchBean);
		if (order != null) {
			criteria.addOrder(order);
		}

		return findByCriteria(criteria);
	}

}
