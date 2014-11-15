package com.bresume.core.dao.area.impl;
import org.springframework.stereotype.Repository;

import com.bresume.core.common.base.dao.support.SimpleHibernateDao;
import com.bresume.core.dao.area.IAreaDao;
import com.bresume.core.model.entity.Area;

/**
 * 区级Dao实现类
 * 
 * @author gavin
 * 
 */
@Repository("areaDao")
public class AreaDaoImpl extends SimpleHibernateDao<Area, String> implements
		IAreaDao {
}
