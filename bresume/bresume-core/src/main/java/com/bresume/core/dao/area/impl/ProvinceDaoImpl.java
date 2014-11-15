package com.bresume.core.dao.area.impl;



import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.area.IProvinceDao;
import com.bresume.core.model.entity.Province;

/**
 * dao实现类
 * @author gavin
 */
@Repository("provinceDao")
public class ProvinceDaoImpl extends SimpleHibernateDao<Province, String>
		implements IProvinceDao {

	@Override
	public Province getProvinceByName(String name) {
		String hql="from Province where name=?";
		return (Province)createQuery(hql, name).uniqueResult();
	}
}
