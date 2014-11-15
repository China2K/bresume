package com.bresume.core.dao.area.impl;
import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.area.ICityDao;
import com.bresume.core.model.entity.City;

/**
 * dao实现类
 * 
 * @author gavin
 * 
 */
@Repository("cityDao")
public class CityDaoImpl extends SimpleHibernateDao<City, String> implements
		ICityDao {

	@Override
	public City getCityByName(String city) {
		String hql = "from City where name=?";
		return (City) createQuery(hql, city).uniqueResult();
	}

}
